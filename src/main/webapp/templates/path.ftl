<#include "common/header.ftl">

<div class="container">
        <h1>${path.name}</h1>

    <#list path.sections as sec>
            <div>
                    <h2>Section: ${sec.name} <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Feedback</button></h2>
                    <#include "feedback.ftl">

                    <ul>
                        <li>oi</li>
                        <#list sec.items as item>
                                <li>${item.name}</li>
                        </#list>
                    </ul>
            </div>
    </#list>
</div>

<#include "common/footer.ftl">