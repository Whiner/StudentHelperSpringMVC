<#import "htmlpatterns/page.ftl" as p>
<@p.pagestruct title="sHelper main" css="">



<div class="row m-3">
    <form method="post" action="/general">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input placeholder="Дисциплина" type="text" name="discipline">

        <input type="text" placeholder="Тема" name="theme"/>
        <label>
            <select name="status">
                <option>Готова</option>
                <option>В процессе</option>
                <option>Не начата</option>
            </select>
        </label>

        <label> Тип работы
            <select name="type" required>
                <option>Лабораторная</option>
                <option>Контрольная</option>
                <option>Курсовая</option>
                <option>Дипломная</option>
                <option>Практическая</option>
                <option>Экзамен</option>
            </select>
        </label>
        <input placeholder="Номер" type="number" name="number">
        <button type="submit">Добавить</button>
    </form>
</div>
<div class="row m-3">
    <form method="get" action="/general">
        <input type="text" placeholder="Фильтр" name="filter">
        <button type="submit">Поиск</button>
    </form>
</div>

<div class="card-columns mt-4">
        <#list studentWorks as sw>
            <#if sw.type == "LABORATORY">
                <#assign
                cardColor = "bg-light"
                textColor = ""
                header = "Лабораторная"
                />
            <#elseif sw.type == "COURSE_PROJECT">
                <#assign
                cardColor = "bg-warning"
                textColor = "text-white"
                header = "Курсовая"
                />
            <#elseif sw.type == "TEST">
                <#assign
                cardColor = "bg-info"
                textColor = "text-white"
                header = "Контрольная"
                />
            <#elseif sw.type == "PRACTICAL">
                <#assign
                cardColor = "bg-secondary"
                textColor = "text-white"
                header = "Практическая"
                />
            <#elseif sw.type == "EXAM">
                <#assign
                cardColor = "bg-success"
                textColor = "text-white"
                header = "Экзамен"
                />
            <#elseif sw.type == "DIPLOMA">
                <#assign
                cardColor = "bg-danger"
                textColor = "text-white"
                header = "Дипломная"
                />
            <#else>
                <#assign
                cardColor = ""
                textColor = ""
                header = "Чет левое ты ввёл, братан"
                />
            </#if>
            <div class="card ${textColor} ${cardColor} mb-3">
                <div class="card-header">${header}</div>
                <div class="card-body">
                    <h5 class="card-title">${sw.discipline} <#if sw.number??>${sw.number}</#if> </h5>
                    <p class="card-text">
                        Тема: ${sw.theme}
                    </p>
                <#--<p class="card-text">-->
                <#--Дата сдачи: ${sw.deliveryDate}-->
                <#--</p>-->
                    <p class="card-text">
                        Статус: ${sw.status}
                    </p>
                </div>
            </div>
        <#else>
            <b>Список пуст</b>
        </#list>
</div>
</@p.pagestruct>