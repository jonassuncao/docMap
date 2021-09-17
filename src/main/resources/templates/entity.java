package ${package};

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

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
    ${className}() {
        super();
    }
<#list attributes as attribute>

    ${attribute.getSets}
</#list>
}
