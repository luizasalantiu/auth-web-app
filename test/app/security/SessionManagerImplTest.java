package app.security;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import app.model.User;

public class SessionManagerImplTest {
	private SessionManager sessionManager;
	private User userMock;

	@Before
	public void setUp() throws Exception {
		sessionManager = new SessionManagerImpl();
		userMock = mock(User.class);
	}

	@Test
	public void test_InitSession_UserShouldBeSavedInSession() {
		HttpSession mockSession = mock(HttpSession.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		Mockito.doReturn(mockSession).when(mockRequest).getSession(anyBoolean());

		HttpSession actual = sessionManager.initSession(userMock, mockRequest);

		assertEquals(actual, mockSession);
		verify(mockSession, times(1)).setAttribute(SessionAttribute.USER, userMock);
	}
}
