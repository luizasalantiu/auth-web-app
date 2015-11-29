package app.helper;

public class PathHelper {
	public static String pathToSecuredResource(String resource) {
		return "/secured/" + resource;
	}

	public static String pathToLogin() {
		return "/login.jsp";
	}

	public static String pathToLoginFailedPage() {
		return "/login_failed.jsp";
	}

	public static String pathToIndexPage() {
		return "/index.jsp";
	}

	public static String pathToUnauthorized() {
		return "/unauthorized.jsp";
	}

	public static String pathToErrorPage() {
		return "/error.jsp";
	}

	public static String pathToRolesMapping() {
		return "/WEB-INF/RoleMapping.xml";
	}
}
