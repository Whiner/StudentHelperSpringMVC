<#import "htmlpatterns/page.ftl" as p>
<@p.pagestruct title="sHelper main" css="">

<div>
    <form method="post" action="/general">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input placeholder="Дисциплина" type="text" name="discipline">
        <input placeholder="Номер" type="number" name="number">
        <button type="submit">Добавить</button>
    </form>
</div>
<div>
    <form method="get" action="/general">
        <input type="text" placeholder="Фильтр" name="filter">
        <button type="submit">Поиск</button>
    </form>
</div>
<div>
    <div>Список студработ</div>
    <div>
        <#list studentWorks as sw>
        <table border="1px">
                <tr>
                    <td width="50px">
                        <b>${sw.id}</b>
                    </td>
                    <td width="300px">
                        ${sw.discipline}
                    </td>
                    <td width="100px">
                        <i>${sw.number}</i>
                    </td>
                    <td width="200px">
                        <b>${sw.ownerName}</b>
                    </td>
                </tr>
        <#else>
        </table>
            <b>Список пуст</b>
        </#list>
    </div>
</div>
</@p.pagestruct>