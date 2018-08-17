<#import "htmlpatterns/page.ftl" as p>
<#import "htmlpatterns/account.ftl" as a>

<@p.pagestruct title = "Login page" css="account.css">

<div class="main mt-5">
<#--<div class="row user-logo">-->
<#--<img class="m-auto" src="../images/user.png" alt="Ups...">-->
<#--</div>-->
    <@a.signform action = "login" button_text="Войти"/>
<#--<div class="row footer-lg-page p-3 justify-content-center">-->
<#--<p>-->
<#--ТЕБЕ ЕСЛИ МОЯ ФОРМА НЕ НРАВИТСЯ ТАК ТЫ ЕБАТЬ ВЫЙДИ СО МНОЙ РАЗ НА РАЗ , А НЕ В ИНЕТЕ БАЗАРЬ-->
<#--</p>-->
<#--</div>-->
</div>
</@p.pagestruct>
