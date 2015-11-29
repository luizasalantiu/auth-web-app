package app.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import app.helper.PathHelper;
import app.security.SessionManager;

public class LogoutCommandTest {
	private static final String DUMMY_CONTEXT_PATH = "test_context_path";

	private LogoutCommand command;

	private SessionManager sessionManagerMock;

	private HttpServletRequest reqMock;
	private HttpServletResponse respMock;

	@Before
	public void setUp() throws Exception {
		reqMock = mock(HttpServletRequest.class);
		respMock = mock(HttpServletResponse.class);
		when(reqMock.getContextPath()).thenReturn(DUMMY_CONTEXT_PATH);

		sessionManagerMock = mock(SessionManager.class);
		command = new LogoutCommand(sessionManagerMock);
	}

	private String getWithContextPath(String path) {
		return DUMMY_CONTEXT_PATH + path;
	}

	@Test
	public void test_OnLogout_ShouldCloseSessionAndRedirectToHomePage() throws ServletException, IOException {
		command.handle(reqMock, respMock);

		verify(sessionManagerMock).endSession(reqMock);
		verify(respMock).sendRedirect(getWithContextPath(PathHelper.pathToIndexPage()));
	}
}
