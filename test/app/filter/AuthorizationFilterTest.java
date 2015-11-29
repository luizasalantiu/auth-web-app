package app.filter;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import app.helper.PathHelper;
import app.helper.RoleParseException;
import app.security.Authorizer;
import app.security.SessionManager;

public class AuthorizationFilterTest {

	private static final String DUMMY_CONTEXT_PATH = "test_context_path";

	private AuthorizationFilter filter;
	private SessionManager sessionManagerMock;
	private Authorizer authorizerMock;

	private HttpServletRequest reqMock;
	private HttpServletResponse respMock;
	private HttpSession mockSession;
	private FilterChain filterChainMock;

	@Before
	public void setUp() throws Exception {
		reqMock = mock(HttpServletRequest.class);
		respMock = mock(HttpServletResponse.class);
		mockSession = mock(HttpSession.class);
		filterChainMock = mock(FilterChain.class);

		when(reqMock.getContextPath()).thenReturn(DUMMY_CONTEXT_PATH);

		sessionManagerMock = mock(SessionManager.class);
		authorizerMock = mock(Authorizer.class);

		filter = new AuthorizationFilter(sessionManagerMock, authorizerMock);
	}

	private String getWithContextPath(String path) {
		return DUMMY_CONTEXT_PATH + path;
	}

	@Test
	public void testThat_WhenThereIsNoSession_CommandActionIsInvoked() throws IOException, ServletException {
		when(sessionManagerMock.getSession(any())).thenReturn(null);

		filter.doFilter(reqMock, respMock, filterChainMock);
		verify(respMock).sendRedirect(getWithContextPath("/login"));
	}

	@Test
	public void testThat_WhenUserNotAuthorized_UserIsRedirectedToUnauthorizedPage()
			throws IOException, ServletException, RoleParseException {
		when(sessionManagerMock.getSession(reqMock)).thenReturn(mockSession);
		when(authorizerMock.isAuthorized(any(), any())).thenReturn(false);

		filter.doFilter(reqMock, respMock, filterChainMock);
		verify(respMock).sendRedirect(getWithContextPath(PathHelper.pathToUnauthorized()));
	}

	@Test
	public void testThat_WhenUserAuthorized_FilterChainIsInvoked()
			throws IOException, ServletException, RoleParseException {
		when(sessionManagerMock.getSession(any())).thenReturn(mockSession);
		when(authorizerMock.isAuthorized(any(), any())).thenReturn(true);

		filter.doFilter(reqMock, respMock, filterChainMock);

		verify(respMock, times(0)).sendRedirect(any());
		verify(filterChainMock).doFilter(reqMock, respMock);
	}
}
