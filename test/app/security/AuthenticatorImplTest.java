package app.security;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import app.dao.InMemoryUserDAO;
import app.dao.UserDAO;
import app.helper.PasswordHelper;
import app.model.User;

public class AuthenticatorImplTest {

	private static final String login = "username_test";
	private static final String password = "hashed_password_test";

	private Authenticator authenticator;

	private UserDAO userDAOMock;
	private PasswordHelper passwordHelperMock;

	@Before
	public void setUp() {
		userDAOMock = mock(InMemoryUserDAO.class);
		passwordHelperMock = mock(PasswordHelper.class);

		authenticator = new AuthenticatorImpl(userDAOMock, passwordHelperMock);
	}

	@Test
	public void test_WhenUserNotProvided_ShouldThrowException() {
		try {
			authenticator.authenticate(null, "password");
		} catch (AuthenticationException e) {
			assertEquals(e.getMessage(), "Username and/or password were not provided.");
		}
	}

	@Test
	public void test_WhenPasswordNotProvided_ShouldThrowException() {
		try {
			authenticator.authenticate("user", null);
		} catch (AuthenticationException e) {
			assertEquals(e.getMessage(), "Username and/or password were not provided.");
		}
	}

	@Test
	public void test_WhenCredentialsNotProvided_ShouldThrowException() {
		try {
			authenticator.authenticate(null, null);
		} catch (AuthenticationException e) {
			assertEquals(e.getMessage(), "Username and/or password were not provided.");
		}
	}

	@Test
	public void test_WhenUserNotFound_ShouldThrowException() throws AuthenticationException {
		when(userDAOMock.getUser(any())).thenReturn(null);

		try {
			authenticator.authenticate(login, password);
		} catch (AuthenticationException e) {
			assertEquals(e.getMessage(), "User does not exist.");
		}
	}

	@Test
	public void test_WhenPasswordIncorrect_ShouldThrowException() throws AuthenticationException {
		User user = new User(1, login, password, null);

		when(userDAOMock.getUser(login)).thenReturn(user);
		when(passwordHelperMock.passwordsMatch("differentpassword", password)).thenReturn(false);

		try {
			authenticator.authenticate(login, password);
		} catch (AuthenticationException e) {
			assertEquals(e.getMessage(), "Password is incorrect.");
		}
	}

	@Test
	public void test_WhenCredentialsAreCorrect_ShouldReturnUser() throws AuthenticationException {
		User expectedUser = new User(1, login, password, null);

		when(userDAOMock.getUser(login)).thenReturn(expectedUser);
		when(passwordHelperMock.passwordsMatch(any(), any())).thenReturn(true);

		User actual = authenticator.authenticate(login, password);

		assertEquals(expectedUser, actual);
	}
}
