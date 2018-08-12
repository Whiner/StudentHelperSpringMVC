<#import "htmlpatterns/page.ftl" as p>
<#import "htmlpatterns/account.ftl" as a>

<@p.pagestruct title = "Login page" css="">
    <div class="login-panel">
        <div class="main">
            <@a.signform action = "login" button_text="Войти"/>
        </div>
    </div>
</@p.pagestruct>
