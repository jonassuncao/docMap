package ${package};

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

<#list relationships as relationship>
<#if relationship.pack??>import ${relationship.pack};</#if>
</#list>

/**
<#list headers as header>
* ${header}
</#list>
*/
@Entity
<#if uniques?has_content>
@Table(name = "${entity}", uniqueConstraints = {
    <#list uniques?keys as unq>
        <#assign hasMore = unq?has_next>
        @UniqueConstraint(name = "${unq}", columnNames = {"${uniques[unq]}"})<#if hasMore>,</#if>
    </#list>
})
<#else>
@Table(name="${entity}")
</#if>
public class ${className} implements Serializable {

    private static final long serialVersionUID = 1L;

<#list attributes as attribute>
 <#if attribute.description?has_content>
    /**
    <#list attribute.description as desc>
    * ${desc}
    </#list>
    */
 </#if>
 <#if attribute.options?has_content>
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
<#if relationship.description?has_content>
    /**
     <#list relationship.description as desc>
     * ${desc}
     </#list>
     */
</#if>
<#if relationship.column??>
    ${relationship.column}
</#if>
<#if relationship.options?has_content>
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
