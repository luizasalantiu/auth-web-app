package app.config;

import java.util.HashMap;
import java.util.Map;

import app.command.Command;
import app.command.LoginCommand;
import app.command.LogoutCommand;

public class CommandMapping {
	public static Map<String, Command> get() {
		Map<String, Command> commands = new HashMap<String, Command>();

		commands.put("/login", new LoginCommand());
		commands.put("/logout", new LogoutCommand());

		return commands;
	}
}
