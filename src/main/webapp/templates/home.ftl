<#include "common/header.ftl">

<div class="container">
        <h1>Hello ${user.name} (${user.id})</h1>

        <h3>Learning Paths:</h3>
        <ul>
            <#list learningPaths as lp>
                    <li><a href="/paths/#{lp.id}">${lp.name}</a></li>
            </#list>
        </ul>
</div>

<#include "common/footer.ftl">