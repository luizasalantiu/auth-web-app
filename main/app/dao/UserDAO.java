package app.dao;

import app.model.User;

public interface UserDAO {
	/**
	 * Returns user with username provided
	 * 
	 * @param username
	 * @return
	 */
	public User getUser(String username);
}
