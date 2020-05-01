<#include "common/header.ftl">

<div class="container">
        <h1>${path.name} (${path.completionPercentage}% done)</h1>

    <#list path.sections as sec>
            <div class="section">
                    <h5>Section: ${sec.name}</h5>
                    <p>${sec.description}</p>
                <#list sec.items as item>
                        <div class="learning-item ${item.completed?string('completed','incomplete')} card mb-2">
                                <div class="card-header">
                                        <div class="row">
                                                <h5 class="col-md-8">
                                                    <#if item.completed>
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
                                                <p class="col-md-4">Rating: <#if item.rating??>${item.rating}<#else>-</#if>/5</p>
                                        </div>
                                </div>
                                <div class="card-body hidden">
                                        <p class="card-text">${item.description}</p>
                                        <a href="${item.url}/" class="btn btn-primary">Start Learning</a>
                                        <#if user.id != "">
                                            <button type="button" class="btn btn-secondary"
                                                onclick="initializeModal('${item.id}', '${item.name}')"
                                                data-toggle="modal" data-target="#exampleModal">Done</button>
                                        </#if>
                                </div>
                        </div>
                </#list>
            </div>
    </#list>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
        <div class="modal-dialog" role="document">
                <div class="modal-content">
                        <div class="modal-header">
                                <h5 class="modal-title" id="itemFeedbackModal">Item Feedback</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                </button>
                        </div>
                        <div class="modal-body">
                                <form id="feedback">
                                        <h4 id="feedback-title">Item Name</h4>
                                        <input id="path-id" type="text" value="${path.id}" name="path-id" hidden>
                                        <input id="feedback-id" type="text" value="..." name="feedback-id" hidden>
                                        <label for="ratingValues">Rating:</label>
                                        <select class="form-control" id="ratingValues">
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                                <option>4</option>
                                                <option>5</option>
                                        </select>
                                </form>
                        </div>
                        <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <button id="feedback-submit" type="button" class="btn btn-primary" data-dismiss="modal"
                                        onclick="submitFeedback()">Submit
                                </button>
                        </div>
                </div>
        </div>
    </div>

</div>

<#include "common/footer.ftl">