package app.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.helper.PathHelper;
import app.helper.ServiceLocator;
import app.model.User;
import app.security.AuthenticationException;
import app.security.Authenticator;
import app.security.SessionManager;

public class LoginCommand extends Command {
	private static final String LOGIN_FIELD_NAME = "login";
	private static final String PASSWORD_FIELD_NAME = "password";

	private Authenticator authenticator;
	private SessionManager sessionManager;

	public LoginCommand(Authenticator authenticator, SessionManager sessionManager) {
		this.authenticator = authenticator;
		this.sessionManager = sessionManager;
	}

	public LoginCommand() {
		this(ServiceLocator.instance().getAuthenticator(), ServiceLocator.instance().getSessionManager());
	}

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String resultPage = null;

		switch (request.getMethod()) {
		case "GET":
			resultPage = handleGet(request, response);
			break;
		case "POST":
			resultPage = handlePost(request, response);
			break;
		}

		return resultPage;
	}

	private String handleGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("login_attr", LOGIN_FIELD_NAME);
		request.setAttribute("password_attr", PASSWORD_FIELD_NAME);

		request.getRequestDispatcher(PathHelper.pathToLogin()).forward(request, response);

		return null;
	}

	private String handlePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter(LOGIN_FIELD_NAME);
		String password = request.getParameter(PASSWORD_FIELD_NAME);

		User user = null;

		try {
			// Try to authenticate user
			user = authenticator.authenticate(login, password);

		} catch (AuthenticationException e) {
			request.setAttribute("message", "Login failed. \n" + e.getMessage());
			String url = PathHelper.pathToLoginFailedPage();
			request.getRequestDispatcher(url).forward(request, response);
		}

		if (user != null) {
			sessionManager.initSession(user, request);
			response.sendRedirect(request.getContextPath() + PathHelper.pathToIndexPage());
		}

		return null;
	}
}
