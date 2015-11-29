package app.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import app.command.Command;
import app.helper.RequestHelper;

public class FrontControllerTest {
	private static final String DUMMY_CONTEXT_PATH = "test_context_path";

	private FrontController controller;
	private RequestHelper helper;
	private Command commandMock;

	private HttpServletRequest reqMock;
	private HttpServletResponse respMock;

	@Before
	public void setUp() throws Exception {
		reqMock = mock(HttpServletRequest.class);
		respMock = mock(HttpServletResponse.class);
		when(reqMock.getContextPath()).thenReturn(DUMMY_CONTEXT_PATH);

		commandMock = mock(Command.class);
		helper = mock(RequestHelper.class);
		when(helper.getCommand(any())).thenReturn(commandMock);

		controller = new FrontController();
	}

	private String getWithContextPath(String path) {
		return DUMMY_CONTEXT_PATH + path;
	}

	@Test
	public void test_ShouldDelegateWork() throws ServletException, IOException {
		controller.processRequest(reqMock, respMock, helper);

		verify(commandMock, times(1)).handle(reqMock, respMock);
	}

	@Test
	public void test_WhenCommandReturnsRedirectPath_ControllerShouldRedirectToIt()
			throws ServletException, IOException {
		String expectedRedirectPath = "/redirectTestPath";

		when(helper.getCommand(any())).thenReturn(commandMock);
		when(commandMock.handle(any(), any())).thenReturn(expectedRedirectPath);

		controller.processRequest(reqMock, respMock, helper);

		verify(respMock).sendRedirect(getWithContextPath(expectedRedirectPath));
	}
}
