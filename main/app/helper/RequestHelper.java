package app.helper;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import app.command.Command;
import app.command.DefaultCommand;
import app.config.CommandMapping;

public class RequestHelper {

	/**
	 * Command handlers for request actions
	 */
	private Map<String, Command> commands;

	public RequestHelper(Map<String, Command> commands) {
		this.commands = commands;
	}

	public RequestHelper() {
		this(CommandMapping.get());
	}

	/**
	 * Finds appropriate Command to handle a request
	 * 
	 * @param request
	 * @return Command for request
	 */
	public Command getCommand(HttpServletRequest request) {
		String actionPath = request.getServletPath();
		Command command = commands.get(actionPath);

		if (command == null) {
			command = new DefaultCommand();
		}

		return command;
	}
}
