<#import "htmlpatterns/page.ftl" as p>
<@p.pagestruct title="sHelper main" css="general.css">

<#--кнопка добавления-->
<div class="row">
    <div class="col-8">
        <button class="btn btn-primary m-4" type="button" data-toggle="collapse" data-target="#collapsedAddMenu"
                aria-expanded="false" aria-controls="collapsedAddMenu">
            +
        </button>
    </div>
    <div class="col-4">
        <form class="form-inline mt-4 mr-3 justify-content-end" method="get" action="/general">
            <div class="form-group">
                <input class="form-control" type="text" placeholder="Фильтр" name="filter">
                <button class="btn btn-primary ml-2" type="submit">Фильтр</button>
            </div>
        </form>
    </div>
</div>

<#--добавление-->
<div class="row">
    <div class="collapse mt-1 mx-1" id="collapsedAddMenu">
        <div class="card card-body">
            <form method="post" action="general/add">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-4">
                            <div class="form-group">
                                <label class="w-100">Дисциплина
                                    <input class="form-control w-100" type="text" name="discipline" required/>
                                </label>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-group">
                                <label class="w-100">Тема
                                    <input class="form-control theme" type="text" name="theme"/>
                                </label>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-group">
                                <label class="mb-0 w-100">Статус выполнения
                                    <select class="form-control" name="status" required>
                                        <option>Готова</option>
                                        <option>В процессе</option>
                                        <option>Не начата</option>
                                    </select>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4">
                            <div class="form-group">
                                <label class="mb-0 w-100">Тип работы
                                    <select class="form-control" name="type" required>
                                        <option>Лабораторная</option>
                                        <option>Контрольная</option>
                                        <option>Курсовая</option>
                                        <option>Дипломная</option>
                                        <option>Практическая</option>
                                        <option>Экзамен</option>
                                    </select>
                                </label>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-group">
                                <label class="w-100">Номер
                                    <input class="form-control" type="number" name="number">
                                </label>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-group">
                                <label class="w-100">Дата сдачи
                                    <input class="form-control" type="date" name="date">
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <button type="submit" class="btn btn-primary btn-lg m-auto">Добавить</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>




<#--инфа о работе-->
<div class="row">
    <div class="collapse mt-1 mx-1 <#if work??>show</#if>" id="collapsedInfoMenu">
        <div class="card card-body">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-2">
                        <button class="btn btn-sm bnt-primary">Открыть методу</button>
                    </div>
                    <div class="col-2">
                        <button class="btn btn-sm btn-success">Добавить методу</button>
                    </div>
                    <div class="col-2">
                        <button class="btn btn-sm btn-danger">Удалить методу</button>
                    </div>
                    <div class="col-5">

                    </div>
                    <div class="col-1">
                        <div class="del-button mx-auto">
                            <a href="/general">
                                <img src="../images/delete.png" class="w-100" alt="close">
                            </a>
                        </div>
                    </div>
                </div>
                <div class="row my-4">
                    <div class="container-fluid notes mx-3 p-2">
                        <#if work?? && work.info??>
                            <#list work.info.notes as note>
                                <div class="row mb-1">
                                    <div class="col-8">
                                        <div>
                                            + ${note.note}
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div>
                                            <span>${note.date}</span>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        <#else>
                            <div class="col-12">
                                <span>Пустовато как то</span>
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="row">
                <#if work??>
                    <form class="form-inline w-100" action="/general/add-note" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="studentWorkId" value="${work.id}">
                        <div class="col-11">
                            <input class="form-control w-100" type="text" name="note" placeholder="Заметка"/>
                        </div>
                        <div class="col-1">
                            <button class="btn btn-sm btn-primary w-100" type="submit">Добавить</button>
                        </div>
                    </form>
                </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<#--тупа форма атдихает-->
<form action="/general" method="get" id="submit_id" hidden>
    <input id="field_id" type="hidden" name="workId">
</form>


<#--карточки-->
<div class="row mx-3">
    <div class="card-columns mt-4 w-100">
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

        <#--onclick="document.location='/general/show_info/${sw.id}'"-->
            <div class="card ${textColor} ${cardColor} mb-4"
                 onclick="document.getElementById('field_id').value = ${sw.id}
                         document.forms['submit_id'].submit()">

                <div class="card-header">
                    <ul class="nav nav-pills card-header-pills">
                        <li class="nav-item ml-2 w-84">
                            ${header}
                        </li>
                        <li class="nav-item w-7">
                            <button class="del-button btn" type="button" data-toggle="collapse"
                                    data-target="#collapsedInfoMenu"
                                    aria-expanded="false" aria-controls="collapsedInfoMenu">
                                <img class="w-100 va-top" src="../images/info.png" alt="Info">
                            </button>
                        </li>
                        <li class="nav-item">
                            <div class="del-button">
                                <a href="/general/${sw.id}/del">
                                    <img class="w-100" src="../images/delete.png" alt="Del">
                                </a>
                            </div>
                        </li>

                    </ul>
                </div>

                <div class="card-body">
                    <h5 class="card-title">${sw.discipline} <#if sw.number??>№${sw.number}</#if> </h5>
                    <p class="card-text">
                        Тема: ${sw.theme}
                    </p>
                    <#if sw.deliveryDate??>
                        <p class="card-text">
                            Дата сдачи: ${sw.deliveryDate}
                        </p>
                    </#if>
                    <p class="card-text">
                        Статус: ${sw.status}
                    </p>
                </div>
            </div>

        <#else>
            <b>Список пуст</b>
        </#list>
    </div>
</div>
</@p.pagestruct>