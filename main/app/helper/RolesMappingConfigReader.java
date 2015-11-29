package app.helper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Reads xml describing roles and which pages these roles have access to.
 */
public class RolesMappingConfigReader {
	// XML node tags
	private static final String ROLE_TAG = "role";
	private static final String ROLE_NAME_TAG = "name";
	private static final String PAGE_TAG = "page";

	public Map<String, List<String>> getRoleMappings(URL url) throws RoleParseException {
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RoleParseException(e);
		}

		Document doc;
		try {
			doc = documentBuilder.parse(url.openStream());
		} catch (SAXException | IOException e) {
			throw new RoleParseException(e);
		}

		doc.getDocumentElement().normalize();

		return processRoleNodes(doc);
	}

	private Map<String, List<String>> processRoleNodes(Document doc) throws RoleParseException {
		Map<String, List<String>> hashMap = new HashMap<String, List<String>>();

		NodeList roleNodes = doc.getElementsByTagName(ROLE_TAG);
		for (int i = 0; i < roleNodes.getLength(); i++) {

			Element roleElem = (Element) roleNodes.item(i);
			String role;
			try {
				role = getElementValue(roleElem.getElementsByTagName(ROLE_NAME_TAG).item(0));
				hashMap.put(role, getPagesForRole(roleElem));
			} catch (Exception e) {
				throw new RoleParseException(e);
			}
		}
		return hashMap;
	}

	private List<String> getPagesForRole(Element roleElem) throws Exception {
		NodeList accessNodes = roleElem.getElementsByTagName(PAGE_TAG);

		List<String> pages = new ArrayList<String>();
		for (int x = 0; x < accessNodes.getLength(); x++) {
			pages.add(getElementValue(accessNodes.item(x)));
		}

		return pages;
	}

	private String getElementValue(Node node) throws Exception {
		return node.getTextContent().toString().trim();
	}
}
