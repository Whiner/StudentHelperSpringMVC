<#assign
exist = Session.SPRING_SECURITY_CONTEXT??
>
<#if exist>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    isAdmin = user.isAdmin()
    username = user.getUsername()
    >
<#else>
    <#assign
    isAdmin = false
    username = "unknown"
    >
</#if>


