package com.google.sps.servlets;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.sps.data.Json;
import com.google.sps.data.LearningItem;
import com.google.sps.data.LearningPathService;
import com.google.sps.data.User;
import com.google.sps.data.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Handle requests for a single learning path
 */
@WebServlet({"/learning-path/*"})
public class ItemFeedbackServlet extends HttpServlet {

	private LearningPathService learningPathService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		learningPathService = new LearningPathService();
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO add lots of validations

		response.setContentType("application/json");

		User user = UserService.getUser();
		String[] parts = request.getPathInfo().split("/");

		if (parts.length != 4) {
			throw new IllegalStateException(request.getPathInfo());
		}

		long pathId = Long.parseLong(parts[1]);
		long ItemId = Long.parseLong(parts[3]);

		try {

			boolean completed = Boolean.parseBoolean(request.getParameter("completed"));
			int rating = Integer.parseInt(request.getParameter("rating"));

			LearningItem learningItem = learningPathService.submitFeedback(pathId, ItemId, user.getId(), rating, completed);

			// anonymous class
			Json res = new Json() {
				double averageRating = 0.0; // FIXME
				int numberOfRatings = (int) learningItem.getRatingCount();
				boolean done = true;
				long itemId = ItemId;
				long learningPath = pathId;
			};

			response.getWriter().println(res.toJson());

		} catch (EntityNotFoundException e) {
			System.out.printf("Error: learning path %d not found ", pathId);
			response.sendError(404);
		}
	}
}