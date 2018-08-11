<#import "htmlpatterns/page.ftl" as p>
<@p.pagestruct title = "Hello Page" css="main.css">
    <#include "htmlpatterns/navbar.ftl"/>
    <div>
        <div>
            <h2>Здоров, пацаны</h2>
        </div>
        <div>
            <a href="/general">Go to the main page</a>
        </div>
    </div>
</@p.pagestruct>