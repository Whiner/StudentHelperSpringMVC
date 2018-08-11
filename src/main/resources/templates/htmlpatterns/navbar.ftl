<#import "account.ftl" as lgout/>
<#include "variables.ftl"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Student Helper</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link " href="/">Home</a>
            <a class="nav-item nav-link" href="/general">Works</a>
            <#if isAdmin>
                <a class="nav-item nav-link" href="/user">User list</a>
            </#if>

        </div>
    </div>
    <div class="navbar-text mr-4">
        <span>${username}</span>
    </div>
    <div class="form-inline">
        <@lgout.logout/>
    </div>
</nav>