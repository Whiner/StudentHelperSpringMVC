<#import "htmlpatterns/page.ftl" as p>
<@p.pagestruct title="Adminka">
    <form action="/user" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" name="username" placeholder="Имя пользователя" value="${user.username}"/>
        <input type="hidden" name="id" value="${user.id}"/>
        <#list roles as role>
         <div>
            <label>
                <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                ${role}
            </label>
         </div>
        </#list>
        <button type="submit">Save</button>
    </form>
</@p.pagestruct>