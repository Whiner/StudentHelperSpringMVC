<#import "htmlpatterns/page.ftl" as p>
<#import "htmlpatterns/account.ftl" as a>

<@p.pagestruct title = "Login Page">
    <h2>Login page</h2>
    <@a.signform action = "login" button_text="Войти"/>
    <a href="/registration">Register</a>
</@p.pagestruct>