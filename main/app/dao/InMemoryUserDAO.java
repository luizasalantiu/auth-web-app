package app.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.model.User;

public class InMemoryUserDAO implements UserDAO {

	private final User user_1 = new User(1, "username_pg_1",
			"$2a$10$YwscloLFGBu3LxtrApXcW.iwvcfITcAo7jhaGPNtp/pkKtWHWG7LG", Arrays.asList("PAG_1"));
	private final User user_2 = new User(2, "username_pg_2",
			"$2a$10$0Cr/tXAzpG0Nl/XBKRRxRu33Rsy8K1vJu3V9xEsH92DJdhHXrc2A6", Arrays.asList("PAG_2"));
	private final User user_3 = new User(3, "username_pg_3",
			"$2a$10$icwjA5kpIiAI/QOEB72qmu6a0m7XLM7m7bpm6g7Va3Bp571wCC9dy", Arrays.asList("PAG_3"));

	List<User> users = new ArrayList<User>();

	public InMemoryUserDAO() {
		users.add(user_1);
		users.add(user_2);
		users.add(user_3);
	}

	@Override
	public User getUser(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}

		return null;
	}
}
