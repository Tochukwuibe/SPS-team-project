// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').trigger('focus')
});

function initializeModal(itemId, itemName) {
    console.log("initializing", arguments);

    $("#feedback-title").text(itemName);
    $("#feedback-id").text(itemId);
    const form = $("#feedback");
}

function submitFeedback() {
    console.log("Sending the submission form info.");
    const itemId = document.getElementById("feedback-id").innerText;
    const pathId = document.getElementById("path-id").innerText;
    const options = document.getElementById("ratingValues");
    const rating = options.options[options.selectedIndex].value;

    const headers = new Headers();
    headers.append("rating", rating);
    //If done is always going to be true, is
    //it really necessary to be sent?
    headers.append("done", "true");
    const init = { method: 'POST',
               headers: headers,
               mode: 'cors',
               cache: 'default' };
    const path = '/paths/' + pathId + '/item/' + itemId;

    fetch(path, init).then(response => response.text()).then((finalResponse) => {
        //What should be done with it?
    });
}