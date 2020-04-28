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
    $("#feedback-id").attr("value", itemId);
}

async function submitFeedback() {
    console.log("Sending the submission form info.");
    const itemId = document.getElementById("feedback-id").value;
    const pathId = document.getElementById("path-id").value;
    const rating = document.getElementById("ratingValues").value;

    console.log("TESTING VALUES: itemId " + itemId +
    " pathId " + pathId + " rating " + rating);

    //const form = $("#feedback");
    const FD = new FormData();
    FD.append("rating", rating);
    FD.append("done", true);

    //const body = new URLSearchParams();
    //body.append('rating', rating);
    //body.append('done', true);

    const path = '/item/' + itemId;
    const response = await fetch("path", {
        method: 'POST',
        FD
    });
    const resText = await response.text();
}