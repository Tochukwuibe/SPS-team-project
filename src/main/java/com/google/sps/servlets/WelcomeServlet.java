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

package com.google.sps.servlets;

import com.google.sps.data.LearningPathService;
import com.google.sps.data.User;
import com.google.sps.data.UserService;
import com.google.sps.html.HtmlRenderer;
import com.google.sps.html.LandingPageModel;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handle requests for the landing/welcome page
 */
@WebServlet({"/home"})
public class WelcomeServlet extends HttpServlet {

	private HtmlRenderer renderer;
	private LearningPathService service;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		renderer = new HtmlRenderer(config.getServletContext());
		service = new LearningPathService();

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = UserService.getUser();

		LandingPageModel model = new LandingPageModel(user);
		model.getLearningPaths().addAll(service.listLearningPaths());

		renderer.renderLandingPage(model, response);
	}
}
