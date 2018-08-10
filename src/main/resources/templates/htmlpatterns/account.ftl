<#macro signform action button_text>
    <form action = "/${action}" method="post">
        <div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <label> User Name :
                <input type="text" name="username"/>
            </label>
        </div>
        <div>
            <label> Password:
                <input type="password" name="password"/>
            </label>
        </div>
        <div>
            <input type="submit" value="${button_text}"/>
        </div>
    </form>
</#macro>

<#macro logout>
     <form action="/logout" method="post">
         <input type="hidden" name="_csrf" value="${_csrf.token}"/>
         <input type="submit" value="Log out">
     </form>
</#macro>