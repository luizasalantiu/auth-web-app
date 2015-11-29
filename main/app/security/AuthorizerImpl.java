package app.security;

import java.net.URL;
import java.util.List;
import java.util.Map;

import app.helper.PathHelper;
import app.helper.RoleParseException;
import app.helper.RolesMappingConfigReader;
import app.model.User;

public class AuthorizerImpl implements Authorizer {

	private static Map<String, List<String>> roleMapping = null;

	private RolesMappingConfigReader configReader;

	public AuthorizerImpl(RolesMappingConfigReader configReader) {
		this.configReader = configReader;
	}

	private void initRoleMappings() throws RoleParseException {
		URL url = getClass().getClassLoader().getResource(PathHelper.pathToRolesMapping());
		roleMapping = configReader.getRoleMappings(url);
	}

	@Override
	public boolean isAuthorized(User user, String uri) throws RoleParseException {
		if (roleMapping == null)
			initRoleMappings();

		List<String> userRoles = user.getRoles();
		for (String userRole : userRoles) {
			List<String> pagesAllowedForRole = roleMapping.get(userRole);
			if (pagesAllowedForRole.stream().anyMatch(pg -> pg.equals(uri))) {
				return true;
			}
		}

		return false;
	}
}
