package app.security;

import app.dao.UserDAO;
import app.helper.PasswordHelper;
import app.helper.ServiceLocator;
import app.model.User;

public class AuthenticatorImpl implements Authenticator {
	private UserDAO userDAO;
	private PasswordHelper passwordHelper;

	public AuthenticatorImpl(UserDAO userDAO, PasswordHelper passwordHelper) {
		this.userDAO = userDAO;
		this.passwordHelper = passwordHelper;
	}

	public AuthenticatorImpl() {
		this(ServiceLocator.instance().getUserDAO(), ServiceLocator.instance().getPasswordHelper());
	}

	@Override
	public User authenticate(final String login, final String plaintextPassword) throws AuthenticationException {

		if (login == null || plaintextPassword == null)
			throw new AuthenticationException("Username and/or password were not provided.");

		User user = userDAO.getUser(login);
		if (null == user)
			throw new AuthenticationException("User does not exist.");

		String hashPassword = user.getPassword();
		if (passwordHelper.passwordsMatch(plaintextPassword, hashPassword)) {
			return user;
		} else {
			throw new AuthenticationException("Password is incorrect.");
		}
	}
}
