<#import "htmlpatterns/page.ftl" as p>
<@p.pagestruct title="sHelper main" css="general.css">


<div class="row">
    <div class="col-8">
        <button class="btn btn-primary m-4" type="button" data-toggle="collapse" data-target="#collapsedMenu"
                aria-expanded="false" aria-controls="collapseExample">
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
<div class="row">
    <div class="collapse mt-1 mx-1" id="collapsedMenu">
        <div class="card card-body">
            <form method="post" action="general/add">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-4">
                            <div class="form-group">
                                <label class="w-100">Дисциплина
                                    <input class="form-control w-100" type="text" name="discipline"/>
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
                                    <select class="form-control" name="status">
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


<div class="row mx-3">
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

        <#--<input type="hidden" name="id" value="${sw.id}">-->
            <div class="card ${textColor} ${cardColor} mb-4">
                <div class="card-header">
                    <ul class="nav nav-pills card-header-pills">
                        <li class="nav-item w-93">
                            ${header}
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