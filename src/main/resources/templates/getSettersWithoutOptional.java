<#if set??>
    ${set.modifier} ${set.return} set${set.name}(${set.typeVariable} ${set.variable}){
        this.${set.variable} = ${set.variable};
    }
</#if>

<#if get??>
    ${get.modifier} ${get.typeVariable} <#if get.typeVariable=="Boolean">is<#else>get</#if>${get.name}(){
        return ${get.variable};
    }
</#if>