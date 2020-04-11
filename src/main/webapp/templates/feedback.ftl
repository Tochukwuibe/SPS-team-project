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
                                        <h4 id="feedback-title">Item Name goes here</h4>
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