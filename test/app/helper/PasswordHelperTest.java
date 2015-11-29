package app.helper;

import org.junit.Test;

import junit.framework.TestCase;

public class PasswordHelperTest extends TestCase {

	private PasswordHelper passwordHelper;
	private final String plaintextPassword = "test_12345";

	protected void setUp() throws Exception {
		passwordHelper = new PasswordHelper();
	}

	@Test
	public void testHashPasswordNotNullOrEmpty() {
		String hash = passwordHelper.hashPassword(plaintextPassword);
		assertNotNull(hash);
		assertNotSame(hash, "");
	}

	@Test
	public void testPasswordsMatch() {
		String hash = passwordHelper.hashPassword(plaintextPassword);
		assertTrue(passwordHelper.passwordsMatch(plaintextPassword, hash));
	}

	@Test
	public void testPasswordsDontMatch() {
		String incorrect_hash = passwordHelper.hashPassword("other_12345");
		assertFalse(passwordHelper.passwordsMatch(plaintextPassword, incorrect_hash));
	}
}
