package ${package};

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

<#list relationships as relationship>
<#if relationship.pack??>import ${relationship.pack};</#if>
</#list>

/**
<#list headers as header>
* ${header}
</#list>
*/
@Entity
@Table(name="${entity}")
public class ${className} implements Serializable {

    private static final long serialVersionUID = 1L;

<#list attributes as attribute>
 <#if attribute.description?size??>
    /**
    <#list attribute.description as desc>
    * ${desc}
    </#list>
    */
 </#if>
 <#if attribute.options?size??>
  <#list attribute.options as opt>
    ${opt}
  </#list>
 </#if>
  <#if attribute.column??>
    ${attribute.column}
  </#if>
    private ${attribute.type} ${attribute.name};

</#list>
<#list relationships as relationship>
<#if relationship.description?size??>
    /**
     <#list relationship.description as desc>
     * ${desc}
     </#list>
     */
</#if>
<#if relationship.column??>
    ${relationship.column}
</#if>
<#if relationship.options?size??>
<#list relationship.options as opt>
    ${opt}
</#list>
</#if>
    private ${relationship.type} ${relationship.getName()}<#if relationship.initializer??> = ${relationship.initializer}</#if>;

</#list>
    ${className}() {
        super();
    }
<#list attributes as attribute>

    ${attribute.getSets}
</#list>
<#list relationships as relationship>

    ${relationship.getSets}
</#list>
}
