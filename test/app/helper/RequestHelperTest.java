package app.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import app.command.Command;
import app.command.DefaultCommand;

public class RequestHelperTest {

	private RequestHelper requestHelper;

	Map<String, Command> commands;

	@Before
	public void setUp() throws Exception {
		commands = new HashMap<String, Command>();

		commands.put("/path1", mock(Command.class));
		commands.put("/path2", mock(Command.class));

		requestHelper = new RequestHelper(commands);
	}

	@Test
	public void test_WhenCommandNotFound_ShouldReturnDefaultCommand() {
		String unregisteredPath = "/unregisteredaction";
		HttpServletRequest reqMock = mock(HttpServletRequest.class);
		when(reqMock.getServletPath()).thenReturn(unregisteredPath);

		Command command = requestHelper.getCommand(reqMock);
		assertTrue(command instanceof DefaultCommand);
	}

	@Test
	public void test_WhenCommandFound_CommandIsReturned() {
		String testPath = "/testpath";
		Command expectedCommand = mock(Command.class);
		commands.put(testPath, expectedCommand);

		HttpServletRequest reqMock = mock(HttpServletRequest.class);
		when(reqMock.getServletPath()).thenReturn(testPath);

		Command actual = requestHelper.getCommand(reqMock);
		assertEquals(expectedCommand, actual);
	}
}
