package app.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.helper.PathHelper;
import app.helper.ServiceLocator;
import app.security.SessionManager;

public class LogoutCommand extends Command {

	private SessionManager sessionManager;

	public LogoutCommand(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public LogoutCommand() {
		this(ServiceLocator.instance().getSessionManager());
	}

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		sessionManager.endSession(request);
		response.sendRedirect(request.getContextPath() + PathHelper.pathToIndexPage());

		return null;
	}
}
