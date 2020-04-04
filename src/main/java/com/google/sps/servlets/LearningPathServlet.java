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

import com.google.sps.data.LearningPath;
import com.google.sps.data.LearningSection;
import com.google.sps.data.User;
import com.google.sps.html.HtmlRenderer;
import com.google.sps.html.LearningPathModel;

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
@WebServlet({"/paths/*"})
public class LearningPathServlet extends HttpServlet {

	private HtmlRenderer renderer;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		renderer = new HtmlRenderer(config.getServletContext());
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = UserService.getUser();

		// TODO figure out which path to load, based on the ID

		LearningSection html = new LearningSection("HTML", 11);
		html.getItems().add("Item 1");
		html.getItems().add("Item 2");

		LearningSection css = new LearningSection("CSS", 11);
		css.getItems().add("Item 3");
		css.getItems().add("Item 4");

		LearningPath path = new LearningPath("Web Development", 1);
		path.getSections().add(html);
		path.getSections().add(css);

		LearningPathModel model = new LearningPathModel(user, path);
		renderer.renderLearningPath(model, response);
	}
}
