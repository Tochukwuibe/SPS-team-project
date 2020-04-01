package com.google.sps.html;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Html generator abstraction, for producing HTML for various web pages.
 */
public class HtmlRenderer {

	private final Configuration cfg;

	/**
	 * Create a new HtmlRenderer that loads templates from the {@link ServletContext}
	 */
	public HtmlRenderer(ServletContext servletContext) {
		// Reference: https://freemarker.apache.org/docs/api/freemarker/template/Configuration.html#setSetting-java.lang.String-java.lang.String-
		cfg = new Configuration(Configuration.VERSION_2_3_30);
		cfg.setServletContextForTemplateLoading(servletContext, "templates/");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
	}

	public void renderLandingPage(LandingPageModel model, HttpServletResponse response) {
		renderHttpResponse("home.ftl", model, response);
	}

	public void renderLearningPath(LearningPathModel model, HttpServletResponse response) {
		renderHttpResponse("path.ftl", model, response);
	}

	/**
	 * Convenience method for evaluating the given FreeMarker template and write the resulting
	 * HTML to the response
	 *
	 * @param templatePath the .ftl file path (relative to webapp/templates/)
	 * @param model        the data model for variable interpolation
	 */
	private void renderHttpResponse(String templatePath, Object model, HttpServletResponse response) {
		response.setContentType("text/html;");
		try {
			Template template = cfg.getTemplate(templatePath);
			template.process(model, response.getWriter());
		} catch (IOException | TemplateException e) {
			throw new RuntimeException("Could not render template", e);
		}
	}
}
