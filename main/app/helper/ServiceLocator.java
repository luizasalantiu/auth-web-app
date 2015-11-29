package app.helper;

import app.dao.InMemoryUserDAO;
import app.dao.UserDAO;
import app.security.Authenticator;
import app.security.AuthenticatorImpl;
import app.security.Authorizer;
import app.security.AuthorizerImpl;
import app.security.SessionManager;
import app.security.SessionManagerImpl;

/**
 * Static service locator pattern, implemented as singleton. In order to avoid
 * hiding of dependencies specific to this pattern, classes use this service
 * locator in their default constructor to get concrete implementations and
 * inject them into an explicit constructor. This makes it very easy to later
 * use an IoC container (eg. Spring) which would use the explicit constructors
 * as they are. 
 * TODO find a better package for this
 */
public class ServiceLocator {

	private static ServiceLocator service;

	/**
	 * Private constructor
	 */
	private ServiceLocator() {
	}

	/**
	 * Static method to get instance.
	 */
	public static ServiceLocator instance() {
		if (service == null) {
			service = new ServiceLocator();
		}
		return service;
	}

	public Authenticator getAuthenticator() {
		UserDAO userDAO = getUserDAO();
		PasswordHelper passwordHelper = new PasswordHelper();

		return new AuthenticatorImpl(userDAO, passwordHelper);
	}

	public Authorizer getAuthorizer() {
		return new AuthorizerImpl(getRolesMappingConfigReader());
	}

	public RolesMappingConfigReader getRolesMappingConfigReader() {
		return new RolesMappingConfigReader();
	}

	public UserDAO getUserDAO() {
		return new InMemoryUserDAO();
	}

	public SessionManager getSessionManager() {
		return new SessionManagerImpl();
	}

	public PasswordHelper getPasswordHelper() {
		return new PasswordHelper();
	}

}