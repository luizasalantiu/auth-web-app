package app.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
	/**
	 * Handles request.
	 * 
	 * @param request
	 * @param response
	 * @return In case a redirect is needed that is not part of this command's
	 *         unit of work, returns a redirect path.
	 */
	public abstract String handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
}
