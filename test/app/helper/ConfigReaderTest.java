package app.helper;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;

import junit.framework.TestCase;

public class ConfigReaderTest extends TestCase {

	private RolesMappingConfigReader reader;
	private Map<String, List<String>> expected;

	protected void setUp() throws Exception {
		reader = new RolesMappingConfigReader();

		expected = new HashMap<String, List<String>>();
		expected.put("PAG_1", Arrays.asList("/page1.jsp"));
		expected.put("PAG_2", Arrays.asList("/page2.jsp", "/page3.jsp"));
	}

	@Test
	public void test_ContentIsReadCorrectly() throws ParserConfigurationException, RoleParseException {
		URL path = ConfigReaderTest.class.getResource("ConfigReaderTestFile.xml");
		Map<String, List<String>> actual = reader.getRoleMappings(path);

		assertEquals(expected, actual);
	}
}
