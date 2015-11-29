package app.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import app.model.User;

public class SessionManagerImpl implements SessionManager {
	private static final int MAX_INACTIVE_INTERVAL = 300; // 5 minutes. would be nice to set this in web.xml instead of hard coded

	@Override
	public HttpSession initSession(User user, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);

		// In a big application, we would save only the needed fields, and
		// potentially save
		// the pages to which a session has access to instead of saving the
		// entire User object.
		session.setAttribute(SessionAttribute.USER, user);
		// set for easy access in views
		session.setAttribute(SessionAttribute.USERNAME, user.getUsername());

		return session;
	}

	@Override
	public HttpSession getSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute(SessionAttribute.USER) != null) {
			return session;
		}

		return null;
	}

	@Override
	public void endSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}
	}

	@Override
	public void setSessionAttribute(HttpSession session, String attribute, Object value) {
		session.setAttribute(attribute, value);
	}

	@Override
	public Object getSessionAttribute(HttpSession session, String attribute) {
		return session.getAttribute(attribute);
	}

}
