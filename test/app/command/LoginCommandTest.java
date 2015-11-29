package app.command;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import app.helper.PathHelper;
import app.model.User;
import app.security.AuthenticationException;
import app.security.Authenticator;
import app.security.SessionManager;

public class LoginCommandTest {
	private static final String DUMMY_CONTEXT_PATH = "test_context_path";

	private LoginCommand command;

	private Authenticator authenticatorMock;
	private SessionManager sessionManagerMock;

	private HttpServletRequest reqMock;
	private HttpServletResponse respMock;
	private RequestDispatcher reqDispatcher;

	@Before
	public void setUp() throws Exception {
		reqMock = mock(HttpServletRequest.class);
		respMock = mock(HttpServletResponse.class);

		reqDispatcher = mock(RequestDispatcher.class);
		when(reqMock.getRequestDispatcher(any())).thenReturn(reqDispatcher);
		when(reqMock.getContextPath()).thenReturn(DUMMY_CONTEXT_PATH);

		authenticatorMock = mock(Authenticator.class);
		sessionManagerMock = mock(SessionManager.class);

		command = new LoginCommand(authenticatorMock, sessionManagerMock);
	}

	// This could be extracted into a helper test class to reuse across tests
	private String getWithContextPath(String path) {
		return DUMMY_CONTEXT_PATH + path;
	}

	@Test
	public void test_OnGetLogin_LoginAndPasswordAttributeNamesAreSet() throws ServletException, IOException {
		when(reqMock.getMethod()).thenReturn("GET");

		command.handle(reqMock, respMock);
		verify(reqMock, times(2)).setAttribute(any(), any());
	}

	@Test
	public void test_OnGetLogin_UserIsForwardedToLoginPage() throws ServletException, IOException {
		when(reqMock.getMethod()).thenReturn("GET");

		command.handle(reqMock, respMock);
		verify(reqMock).getRequestDispatcher(PathHelper.pathToLogin());
		verify(reqDispatcher).forward(reqMock, respMock);
	}

	@Test
	public void test_OnPostLogin_WhenAuthenticationFails_UserIsShownLoginFailed()
			throws ServletException, IOException, AuthenticationException {
		when(reqMock.getMethod()).thenReturn("POST");

		AuthenticationException exceptionMock = mock(AuthenticationException.class);
		doThrow(exceptionMock).when(authenticatorMock).authenticate(any(), any());

		command.handle(reqMock, respMock);

		verify(reqMock).getRequestDispatcher(PathHelper.pathToLoginFailedPage());
		verify(reqDispatcher).forward(reqMock, respMock);
	}

	@Test
	public void test_OnPostLogin_WhenAuthenticationSucceeds_SessionIsSaved()
			throws ServletException, IOException, AuthenticationException {
		when(reqMock.getMethod()).thenReturn("POST");

		User userMock = mock(User.class);
		when(authenticatorMock.authenticate(any(), any())).thenReturn(userMock);

		command.handle(reqMock, respMock);

		verify(sessionManagerMock).initSession(userMock, reqMock);
	}

	@Test
	public void test_OnPostLogin_WhenAuthenticationSucceeds_UserIsRedirectedToIndex()
			throws AuthenticationException, ServletException, IOException {
		when(reqMock.getMethod()).thenReturn("POST");

		User userMock = mock(User.class);
		when(authenticatorMock.authenticate(any(), any())).thenReturn(userMock);

		command.handle(reqMock, respMock);

		verify(respMock).sendRedirect(getWithContextPath(PathHelper.pathToIndexPage()));
	}
}
