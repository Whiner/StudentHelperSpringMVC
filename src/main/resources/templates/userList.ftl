<#import "htmlpatterns/page.ftl" as p>
<#import "htmlpatterns/role_chooser.ftl" as rc>
<@p.pagestruct title = "User List" css="userList.css">

<#--<td>${user.activity?string("active", "diactive")}</td>-->
<div class="row">
    <form class="form-inline mt-4 ml-3 justify-content-end" method="get" action="/user">
        <div class="form-group">
            <input class="form-control" type="text" placeholder="Поиск" name="search">
            <button class="btn btn-primary ml-2" type="submit">Поиск</button>
        </div>
    </form>
</div>
<div class="list-group mt-3">
    <div class="list-group-item list-group-item-action flex-column align-items-start">
        <div class="container-fluid row">
            <div class="col-2 text-center">
                <label class="off-label-mb">Права</label>
            </div>
            <div class="col-4">
                <label class="off-label-mb">Email</label>
            </div>
            <div class="col-4">
                <label class="off-label-mb">Имя пользователя</label>
            </div>
            <div class="col-1">
                <label class="off-label-mb">Ред.</label>
            </div>
            <div class="col-1">

            </div>
        </div>
    </div>

    <#list users as user>
        <div class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="container-fluid row">
                <div class="user-role col-2 text-center">
                    <@rc.chooser role_name=user.role.name()/>
                </div>
                <div class="col-4">
                    <label class="off-label-mb">${user.email}</label>
                </div>
                <div class="col-4">
                    <label class="off-label-mb">${user.username}</label>
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
    <#else>
        <div class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="container-fluid row text-center">
                <label class="off-label-mb">Не найдено</label>
            </div>
        </div>
    </#list>
</div>

</@p.pagestruct>