package app.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.xml.sax.SAXException;

import app.helper.RoleParseException;
import app.helper.RolesMappingConfigReader;
import app.model.User;

public class AuthorizerImplTest {

	private Authorizer authorizer;

	private User userMock;

	private static final String ROLE_PAG_1 = "PAG_1";
	private static final String ROLE_PAG_2 = "PAG_2";
	private static final String ROLE_PAG_3 = "PAG_3";

	@Before
	public void setUp() throws ParserConfigurationException, SAXException, IOException, RoleParseException {
		userMock = mock(User.class);

		HashMap<String, List<String>> dummyRoleMapping = new HashMap<String, List<String>>();
		dummyRoleMapping.put(ROLE_PAG_1, Arrays.asList("page1.jsp"));
		dummyRoleMapping.put(ROLE_PAG_2, Arrays.asList("page2.jsp"));
		dummyRoleMapping.put(ROLE_PAG_3, Arrays.asList("page3.jsp"));

		RolesMappingConfigReader mockRreader = mock(RolesMappingConfigReader.class);
		Mockito.doReturn(dummyRoleMapping).when(mockRreader).getRoleMappings(anyObject());

		authorizer = new AuthorizerImpl(mockRreader);
	}

	@Test
	public void test_WhenIsAuthorizedForPage_ShouldReturnTrue() throws RoleParseException {
		List<String> userRoles = Arrays.asList(ROLE_PAG_1);
		when(userMock.getRoles()).thenReturn(userRoles);

		String uri = "page1.jsp";

		assertTrue(authorizer.isAuthorized(userMock, uri));
	}

	@Test
	public void test_WhenIsNotAuthorizedForPage_ShouldReturnFalse() throws RoleParseException {
		List<String> userRoles = Arrays.asList(ROLE_PAG_3);
		when(userMock.getRoles()).thenReturn(userRoles);

		String uri = "page1.jsp";

		assertFalse(authorizer.isAuthorized(userMock, uri));
	}

	@Test
	public void test_WhenIsNotAuthorizedForPage_ShouldReturnFalse_() throws RoleParseException {
		List<String> userRoles = Arrays.asList(ROLE_PAG_3);
		when(userMock.getRoles()).thenReturn(userRoles);

		String uri = "page1.jsp";

		assertFalse(authorizer.isAuthorized(userMock, uri));
	}
}