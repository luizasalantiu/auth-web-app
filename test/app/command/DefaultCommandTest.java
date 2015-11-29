package app.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

public class DefaultCommandTest {

	private DefaultCommand command;

	private HttpServletRequest reqMock;
	private HttpServletResponse respMock;

	@Before
	public void setUp() {
		reqMock = mock(HttpServletRequest.class);
		respMock = mock(HttpServletResponse.class);

		command = new DefaultCommand();
	}

	@Test
	public void test_ShouldReturnServletPath() {
		String expectedPath = "returnPath";
		when(reqMock.getServletPath()).thenReturn(expectedPath);

		String actual = command.handle(reqMock, respMock);

		assertEquals(expectedPath, actual);
	}
}
