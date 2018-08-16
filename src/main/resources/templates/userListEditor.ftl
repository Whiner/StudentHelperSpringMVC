<#import "htmlpatterns/page.ftl" as p>
<@p.pagestruct title="Adminka" css="">
    <form action="/user" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" name="username" placeholder="Имя пользователя" value="${user.username}"/>
        <input type="hidden" name="id" value="${user.id}"/>
        <div>
        <#list roles as role>

            <label> ${role}
                <input type="radio" value="${role}" name="role" ${(user.role == role)?string("checked", "")}>
            </label>

        </#list>
        </div>
        <button type="submit">Save</button>
    </form>
</@p.pagestruct>