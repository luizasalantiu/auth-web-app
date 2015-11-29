package app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.command.Command;
import app.helper.RequestHelper;

/**
 * Provides a centralized entry point for web request handling (Servlet Front
 * Strategy)
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestHelper helper = new RequestHelper();
		processRequest(request, response, helper);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestHelper helper = new RequestHelper();
		processRequest(request, response, helper);
	}

	/**
	 * Process any incoming request.
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response, RequestHelper requestHelper)
			throws ServletException, IOException {

		Command command = requestHelper.getCommand(request);

		// When command cannot handle the request or it is not part of its unit
		// of work, returns redirect path
		String redirectPath = command.handle(request, response);

		if (redirectPath != null) {
			response.sendRedirect(request.getContextPath() + redirectPath);
		}
	}
}
