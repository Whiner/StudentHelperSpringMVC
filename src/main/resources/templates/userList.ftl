<#import "htmlpatterns/page.ftl" as p>
<@p.pagestruct title = "User List" css="">


    <#list users as user>

    <#--<td>${user.activity?string("active", "diactive")}</td>-->


<div class="list-group">
    <div class="list-group-item list-group-item-action flex-column align-items-start">
        <div class="user-role">
            <#if user.role.name() == "ADMIN">

            <#elseif user.role.name() == "MODERATOR">

            <#elseif user.role.name() == "USER">

            </#if>
        </div>
        <div>
            ${user.username}
        </div>
        <div>
            <a href="/user/${user.id}">Edit user</a>
        </div>
        <div>
            <a href="/user/${user.id}/del">Delete user</a>
        </div>
    </div>

</div>
    </#list>
</@p.pagestruct>