<#import "htmlpatterns/page.ftl" as p>
<#import "htmlpatterns/account.ftl" as a>
<@p.pagestruct title = "Spring Security" >
<h2>Registration page</h2>
<#if (user_exist)??>
    <b>${user_exist}</b>
</#if>

<@a.signform action="registration" button_text="Регистрация"/>
</@p.pagestruct>
