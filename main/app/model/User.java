package app.model;

import java.util.Collections;
import java.util.List;

public class User {
	private int id;
	private String username;
	private String password;
	private List<String> roles;

	public User(int id, String userId, String password, List<String> roles) {
		this.id = id;
		this.username = userId;
		this.password = password;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public List<String> getRoles() {
		return Collections.unmodifiableList(roles);
	}
}
