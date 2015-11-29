package app.helper;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Uses third party for password hashing. See
 * http://www.mindrot.org/projects/jBCrypt/
 */
public class PasswordHelper {

	private static int workFactor = 10;

	/**
	 * Generate a string representing an account password suitable for saving in
	 * a database.
	 * 
	 * @param plaintextPassword
	 *            The account's password in plain text
	 * @return String - a string of length 60 that is the bcrypt hashed password
	 *         in crypt(3) format
	 */
	public String hashPassword(String plaintextPassword) {
		String salt = BCrypt.gensalt(workFactor);
		String hashed = BCrypt.hashpw(plaintextPassword, salt);

		return (hashed);
	}

	public boolean passwordsMatch(String plaintextPassword, String storedHash) {
		if (BCrypt.checkpw(plaintextPassword, storedHash))
			return true;

		return false;
	}
}
