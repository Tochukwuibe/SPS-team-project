<#include "common/header.ftl">

<div class="container">
        <h1>${path.name}</h1>

        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Feedback</button></h2>
    <#include "feedback.ftl">
    <#list path.sections as sec>
            <div class="section">
                    <h5>Section: ${sec.name}</h5>
                    <p>${sec.description}</p>
                <#list sec.items as item>
                        <div class="card mb-2">
                                <div class="card-header">
                                        <div class="row">
                                                <h5 class="col-md-8">
                                                    <#if false>
                                                            <!-- completed icon -->
                                                            <svg class="bi bi-check-box" width="1em" height="1em"
                                                                 viewBox="0 0 16 16" fill="currentColor"
                                                                 xmlns="http://www.w3.org/2000/svg">
                                                                    <path fill-rule="evenodd"
                                                                          d="M15.354 2.646a.5.5 0 010 .708l-7 7a.5.5 0 01-.708 0l-3-3a.5.5 0 11.708-.708L8 9.293l6.646-6.647a.5.5 0 01.708 0z"
                                                                          clip-rule="evenodd"/>
                                                                    <path fill-rule="evenodd"
                                                                          d="M1.5 13A1.5 1.5 0 003 14.5h10a1.5 1.5 0 001.5-1.5V8a.5.5 0 00-1 0v5a.5.5 0 01-.5.5H3a.5.5 0 01-.5-.5V3a.5.5 0 01.5-.5h8a.5.5 0 000-1H3A1.5 1.5 0 001.5 3v10z"
                                                                          clip-rule="evenodd"/>
                                                            </svg>
                                                    <#else>
                                                            <!-- incomplete icon -->
                                                            <svg class="bi bi-square" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                                    <path fill-rule="evenodd" d="M14 1H2a1 1 0 00-1 1v12a1 1 0 001 1h12a1 1 0 001-1V2a1 1 0 00-1-1zM2 0a2 2 0 00-2 2v12a2 2 0 002 2h12a2 2 0 002-2V2a2 2 0 00-2-2H2z" clip-rule="evenodd"/>
                                                            </svg>
                                                    </#if>
                                                    ${item.name}
                                                </h5>
                                                <p class="col-md-4">Rating: 100%</p>
                                        </div>
                                </div>
                                <div class="card-body hidden">
                                        <p class="card-text">${item.description}</p>
                                        <a href="${item.url}/" class="btn btn-primary">Start Learning</a>
                                        <a href="#" class="btn btn-secondary">Done</a>
                                </div>
                        </div>
                </#list>
            </div>
    </#list>
</div>

<#include "common/footer.ftl">