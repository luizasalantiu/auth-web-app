package app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.helper.PathHelper;
import app.helper.RoleParseException;
import app.helper.ServiceLocator;
import app.model.User;
import app.security.Authorizer;
import app.security.SessionAttribute;
import app.security.SessionManager;

public class AuthorizationFilter implements Filter {
	private SessionManager sessionManager;
	private Authorizer authorizer;

	public AuthorizationFilter(SessionManager sessionManager, Authorizer authorizer) {
		this.sessionManager = sessionManager;
		this.authorizer = authorizer;
	}

	public AuthorizationFilter() {
		this(ServiceLocator.instance().getSessionManager(), ServiceLocator.instance().getAuthorizer());
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		HttpSession session = sessionManager.getSession(request);

		if (session != null) {
			User user = (User) sessionManager.getSessionAttribute(session, SessionAttribute.USER);
			String attemptedPath = ((HttpServletRequest) request).getServletPath();

			try {
				if (!authorizer.isAuthorized(user, attemptedPath)) {
					response.sendRedirect(request.getContextPath() + PathHelper.pathToUnauthorized());
				} else {
					chain.doFilter(req, res);
				}
			} catch (RoleParseException e) {
				// error details would be logged here (or earlier in stack
				// method calls)
				response.sendRedirect(request.getContextPath() + PathHelper.pathToErrorPage());
			}
		} else {
			// TODO move command paths somewhere accessible so we don't use magic strings like this one
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	@Override
	public void destroy() {
	}
}