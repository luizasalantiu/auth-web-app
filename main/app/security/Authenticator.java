package app.security;

import app.model.User;

public interface Authenticator {

	/**
	 * Authenticates user.
	 * 
	 * @param login
	 * @param password
	 * @return true if user is successfully authenticated. False otherwise.
	 * @throws AuthenticationException
	 */
	public User authenticate(final String login, final String password) throws AuthenticationException;
}
