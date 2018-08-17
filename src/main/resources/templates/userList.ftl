<#import "htmlpatterns/page.ftl" as p>
<#import "htmlpatterns/role_chooser.ftl" as rc>
<@p.pagestruct title = "User List" css="userList.css">

<#--<td>${user.activity?string("active", "diactive")}</td>-->

<div class="list-group mt-3">
    <#list users as user>
        <div class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="container-fluid row">
                <div class="user-role col-2 text-center">
                    <@rc.chooser role_name=user.role.name()/>
                <#--<#if user.role.name() == "ADMIN">-->
                <#--<img class="role-img" src="../images/admin_role.png" alt="admin">-->
                <#--<label class="mb-0">Admin</label>-->
                <#--<#elseif user.role.name() == "MODERATOR">-->
                <#--<img class="role-img" src="../images/moder_role.png" alt="moder">-->
                <#--<label class="mb-0">Moderator</label>-->
                <#--<#elseif user.role.name() == "USER">-->
                <#--<img class="role-img" src="../images/user_role.png" alt="user">-->
                <#--<label class="mb-0">User</label>-->
                <#--</#if>-->
                </div>
            <#--<div>email</div>-->
                <div class="col-8">
                    <label>${user.username}</label>
                </div>
                <div class="col-1">
                    <a href="/user/${user.id}">
                        <img class="user-edit-icon" src="../images/edit_user.png" alt="edit">
                    </a>
                </div>
                <div class="col-1">
                    <#if user.role.name() != "ADMIN">
                        <a href="/user/${user.id}/del">
                            <img class="user-edit-icon" src="../images/delete_user.png" alt="delete">
                        </a>
                    </#if>
                </div>
            </div>
        </div>
    </#list>
</div>

</@p.pagestruct>