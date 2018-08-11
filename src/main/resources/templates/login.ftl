<#import "htmlpatterns/page.ftl" as p>
<#import "htmlpatterns/account.ftl" as a>

<@p.pagestruct title = "Login Page" css="">
    <#include "htmlpatterns/navbar.ftl"/>
    <div class="login-panel">
        <div class="header">
            <h2>Login page</h2>
        </div>
        <div class="main">
            <@a.signform action = "login" button_text="Войти"/>
        </div>
        <div class="reg">
            <a href="/registration">Register</a>
        </div>
    </div>
</@p.pagestruct>
