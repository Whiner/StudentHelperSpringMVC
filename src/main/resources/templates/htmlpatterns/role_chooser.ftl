<#macro chooser role_name>
    <#if role_name == "ADMIN">
        <img class="role-img" src="../images/admin_role.png" alt="admin">
        <span class="mb-0">Admin</span>
    <#elseif role_name == "MODERATOR">
        <img class="role-img" src="../images/moder_role.png" alt="moder">
        <span class="mb-0">Moderator</span>
    <#elseif role_name == "USER">
        <img class="role-img" src="../images/user_role.png" alt="user">
        <span class="mb-0">User</span>
    </#if>
</#macro>