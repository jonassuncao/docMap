<#if set??>
    ${set.modifier} ${set.return} set${set.name}(${set.typeVariable} ${set.variable}){
        this.${set.variable} = ${set.variable};
    }
</#if>

<#if get??>
    ${get.modifier} <#if get.required>${get.typeVariable}<#else>Optional<${get.typeVariable}></#if> <#if get.typeVariable=="Boolean">is<#else>get</#if>${get.name}(){
<#if get.required>
        return ${get.variable};
<#else>
        return Optional.ofNullable(${get.variable});
</#if>
    }
</#if>