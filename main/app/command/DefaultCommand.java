package app.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultCommand extends Command {

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) {
		return request.getServletPath();
	}
}
