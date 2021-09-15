package ${package};

import java.io.Serializable;
<#list imports as import>
${import}
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
    private ${attribute}
    </#list>

    ${className}() {
        super();
    }

    private void setTest(String value){
        this.value = value;
    }

    protected Optional<String> getTest(){
        this.value = value;
    }
}
