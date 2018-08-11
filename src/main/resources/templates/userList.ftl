<#import "htmlpatterns/page.ftl" as p>
<@p.pagestruct title = "User List" css="">
    <#include "htmlpatterns/navbar.ftl"/>
    <table border="1px">
        <thead>
        <tr>
            <td width="300px">User name</td>
            <td width="100px">User role</td>
            <td width="50px">Activity</td>
            <td width="100px">Edit</td>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td>
                    <#list user.roles as role>
                        <span>
                            ${role.name()}
                        </span>
                        <#sep>,
                    </#list>
            </td>
            <td>${user.activity?string("active", "diactive")}</td>
            <td align="center">
                <a href="/user/${user.id}">Edit user</a>
            </td>
            <td align="center">
                <a href="/user/${user.id}/del">Delete user</a>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</@p.pagestruct>