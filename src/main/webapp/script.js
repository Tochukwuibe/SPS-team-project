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

function initializeModal(pathId, itemId, itemName) {
    console.log("initializing", arguments);

    //var myvariable = "${pathId}";
    //alert("PathId is " + myvariable);

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

    const XHR = new XMLHttpRequest();
    const FD = new FormData();
    FD.append("rating", rating);
    FD.append("item-id", itemId);

    // Define what happens on successful data submission
    XHR.addEventListener( 'load', function(event) {
        alert( 'Yeah! Data sent and response loaded.' );
    } );

    // Define what happens in case of error
    XHR.addEventListener( 'error', function(event) {
        alert( 'Oops! Something went wrong.' );
    } );

    // Set up our request
    const path = '/paths/' + pathId + '/item/' + itemId;
    console.log("path is " + path);
    XHR.open('POST', path);

    // Finally, send our data.
    XHR.send(FD);
}