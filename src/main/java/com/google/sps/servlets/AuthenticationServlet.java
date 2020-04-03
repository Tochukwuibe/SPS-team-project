package com.google.sps.servlets;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.sps.data.User;
import static com.google.sps.servlets.NicknameServlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/authentication")
public class AuthServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    UserService userService = UserServiceFactory.getUserService();

    // If user is not logged in, show a login form (could also redirect to a login page)
    if (!userService.isUserLoggedIn()) {
      String loginUrl = userService.createLoginURL("/");
      out.println("<p>Login <a href=\"" + loginUrl + "\">here</a>.</p>");
      return;
    }

    // If user has not set a nickname, redirect to nickname page
    User user = getUserInfo(userService.getCurrentUser().getUserId());
    
    if (nickname == null) {
      response.sendRedirect("/EditProfile");
      return;
    }

    // User is logged in and has a nickname, so the request can proceed
    String logoutUrl = userService.createLogoutURL("/");
    
    out.println("<h1>Home</h1>");
    out.println("<p>Hello " + user.getName() + "!</p>");

    // Here we can have some dropdown to change profile information and logout link

    //out.println("<p>Logout <a href=\"" + logoutUrl + "\">here</a>.</p>");
    //out.println("<p>Change your nickname <a href=\"/nickname\">here</a>.</p>");

    // It would be useful to have also a different servlet dedicated for one static page
    // that shows your learning paths, progress, and information. 

  }

}