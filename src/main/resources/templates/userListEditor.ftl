<#import "htmlpatterns/page.ftl" as p>
<#import "htmlpatterns/role_chooser.ftl" as rc>
<@p.pagestruct title="Adminka" css="userListEditor.css">
<div class="mx-auto mt-4">
    <form action="/user" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group-row">
            <label class="ml-2 w-50"> Имя пользователя
                <input class="form-control" type="text" name="username" value="${user.username}"/>
            </label>
        </div>
        <input type="hidden" name="id" value="${user.id}"/>
        <div class="frb-group w-75">
        <#list roles as role>
            <div class="frb frb-default p-2">
                <input type="radio"
                       id="radio-button-${role}"
                       name="role"
                       value="${role}"
                    ${(user.role == role)?string("checked", "")}>
                <label for="radio-button-${role}">
                    <span class="frb-title">
                        <@rc.chooser role_name = role.name()/>
                    <#--${role}-->
                    </span>
                    <span class="frb-description">Role capabilities</span>
                </label>
            </div>
        </#list>
        </div>
        <button class="btn btn-primary ml-2 w-25" type="submit">Save</button>
    </form>
</div>
</@p.pagestruct>