<#macro signform action button_text>
<div class="auth container mx-auto mt-5">

    <div class="row auth-header">
        <div class="col">
            <#if action == "registration">
                <span>Регистрация</span>
            <#else>
                <span>Авторизация</span>
            </#if>
        </div>
    </div>

    <form action="/${action}" method="post">
        <#if action == "registration">
            <div class="row">
                <div class="form-group m-auto">
                    <label class="m-2"> Email
                        <input class="form-control my-1" type="email" name="email" required/>
                    </label>
                </div>
            </div>
        </#if>
        <div class="row">
            <div class="form-group m-auto">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <label class="m-2"> Username
                    <input class="form-control my-1" type="text" name="username" required/>
                </label>
            </div>
        </div>
        <div class="row">
            <div class="form-group m-auto">
                <label class="m-2"> Password
                    <input class="form-control my-1" type="password" name="password" required/>
                </label>
            </div>
        </div>
        <div class="row mt-3">
            <div class="m-auto">
                <button class="btn btn-outline-success login-submit-button" type="submit">${button_text}</button>
            </div>
        </div>
        <div class="row">
            <#if action == "login">
                <div class="m-auto">
                    <a href="/registration">
                        <button type="button" class="btn btn-sm btn-outline-secondary mt-2 mx-auto">
                            Нет аккаунта?
                        </button>
                    </a>
                </div>
            </#if>
        </div>
    </form>


</div>
</#macro>

<#macro logout>
     <form action="/logout" method="post">
         <input type="hidden" name="_csrf" value="${_csrf.token}"/>
         <button type="submit" class="btn btn-warning">Log out</button>
     </form>
</#macro>