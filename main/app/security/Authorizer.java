package app.security;

import app.helper.RoleParseException;
import app.model.User;

public interface Authorizer {

	/**
	 * Returns boolean indicating whether user has the appropriate role for the
	 * specified URI.
	 */
	public boolean isAuthorized(User user, String uri) throws RoleParseException;
}
