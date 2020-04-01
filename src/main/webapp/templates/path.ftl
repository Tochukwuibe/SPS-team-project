<#include "common/header.ftl">

<div class="container">
        <h1>${path.name}</h1>

    <#list path.sections as sec>
            <div>
                    <h2>Section: ${sec.name}</h2>
                    <ul>
                        <#list sec.items as item>
                                <li>${item}</li>
                        </#list>
                    </ul>
            </div>
    </#list>
</div>

<#include "common/footer.ftl">