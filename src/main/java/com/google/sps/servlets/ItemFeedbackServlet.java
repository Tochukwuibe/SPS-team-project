package com.google.sps.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.sps.data.ItemFeedback;
import com.google.sps.data.ItemFeedbackService;
import com.google.sps.data.Json;
import com.google.sps.data.LearningPath;
import com.google.sps.data.LearningPathService;
import com.google.sps.data.User;
import com.google.sps.data.UserService;

import java.io.IOException;


/**
 * Handle requests for a single learning path
 */
@WebServlet({"/learning-path/*"})
public class ItemFeedbackServlet extends HttpServlet {

  private ItemFeedbackService itemFeedbackService;
  private LearningPathService  learningPathService;

  @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		itemFeedbackService = new ItemFeedbackService();
    learningPathService = new LearningPathService();
	}


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

      response.setContentType("application/json");

      User user =  UserService.getUser();;

      System.out.println(request.getPathInfo());
      // TODO figure out which path to load, based on the ID
      String[] parts = request.getPathInfo().split("/");

      if (parts.length != 4) {
          throw new IllegalStateException(request.getPathInfo());
      }

      long PathId = Long.parseLong(parts[1]);
      long ItemId = Long.parseLong(parts[3]);

      try {

        boolean completed = Boolean.parseBoolean(request.getParameter("completed"));
        int rating = Integer.parseInt(request.getParameter("rating"));

        ItemFeedback feedback = new ItemFeedback(PathId, ItemId, user.getId(), rating, completed);
        itemFeedbackService.store(feedback);

        LearningPath path = learningPathService.updateRating(PathId, feedback);
        
        // anonymous class
        Json res = new Json() {
            double averageRating = path.getAverageRating();
            int numberOfRatings = path.getNumberOfRatings();
            boolean done = true;
            long itemId = ItemId;
            long learningPath = PathId;
        };

        response.getWriter().println(res.toJson());
        
      } catch (EntityNotFoundException e) {
          System.out.printf("Error: learning path %d not found ", PathId);
          response.sendError(404);
      }

  
    }


}