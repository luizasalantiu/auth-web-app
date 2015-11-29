package app.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import app.model.User;

public interface SessionManager {

	/**
	 * Initializes session and sets appropriate attributes.
	 * 
	 * @param user
	 * @param request
	 * @return session created
	 */
	public HttpSession initSession(User user, HttpServletRequest request);

	/**
	 * Returns session for request when one exists.
	 * 
	 * @param request
	 * @return session is session exists, null otherwise
	 */
	public HttpSession getSession(HttpServletRequest request);

	/**
	 * Ends session associated with this request
	 * 
	 * @param request
	 */
	public void endSession(HttpServletRequest request);

	/**
	 * Sets session attribute
	 */
	public void setSessionAttribute(HttpSession session, String attribute, Object value);

	/**
	 * Returned session attribute value
	 */
	public Object getSessionAttribute(HttpSession session, String attribute);
}