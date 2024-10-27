package io.huangsam.trial.people;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Let's load {@code UserProfile} records via JSON, CXV and XML files. It's common
 * to do file operations to grab data and process them, so we're doing exactly that.
 *
 * @see <a href="https://www.baeldung.com/java-json">Baeldung on JSON</a>
 * @see <a href="https://www.baeldung.com/java-csv">Baeldung on CSV</a>
 * @see <a href="https://www.baeldung.com/java-xml">Baeldung on XML</a>
 */
public class TestUserProfile {
    @Test
    void testUserProfileGetters() {
        UserProfile profile = new UserProfile("Foo Bar", "foo.bar@example.com", 1);

        assertEquals("Foo Bar", profile.name());
        assertEquals("foo.bar@example.com", profile.email());
        assertEquals(1, profile.age());
    }

    @Test
    void testLoadUserProfileFromJson() throws IOException {
        InputStream jsonStream = getStream("test.json");
        String jsonContent = new String(jsonStream.readAllBytes());
        UserProfile[] profiles = new Gson().fromJson(jsonContent, UserProfile[].class);

        assertEquals(2, profiles.length);
        assertTrue(Arrays.stream(profiles).allMatch(profile -> profile.name().contains("Doe")));
    }

    @Test
    void testLoadUserProfileFromCsv() throws IOException {
        InputStream csvStream = getStream("test.csv");
        String csvContent = new String(csvStream.readAllBytes());
        String[] lines = csvContent.split("\n");

        assertEquals(3, lines.length);

        List<UserProfile> profiles = Arrays.stream(lines)
                .filter(line -> !line.startsWith("name"))
                .map(this::parseProfileFromCsvLine)
                .toList();

        assertEquals(lines.length - 1, profiles.size());
        assertTrue(profiles.stream().allMatch(profile -> profile.name().contains("Doe")));
    }

    @Test
    void testLoadUserProfileFromXml() throws Exception {
        InputStream xmlStream = getStream("test.xml");
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(xmlStream);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("user");

        List<UserProfile> profiles = Stream.iterate(0, i -> i < nodeList.getLength(), i -> i + 1)
                .map(nodeList::item)
                .map(this::parseProfileFromXmlElement)
                .toList();

        assertEquals(2, profiles.size());
        assertTrue(profiles.stream().allMatch(profile -> profile.name().contains("Doe")));
    }

    private UserProfile parseProfileFromCsvLine(String line) {
        String[] fields = line.split(",");
        return new UserProfile(fields[0], fields[1], Integer.parseInt(fields[2]));
    }

    private UserProfile parseProfileFromXmlElement(Node node) {
        Element element = (Element) node;
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        String email = element.getElementsByTagName("email").item(0).getTextContent();
        int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
        return new UserProfile(name, email, age);
    }

    private InputStream getStream(String name) {
        return getClass().getClassLoader().getResourceAsStream(name);
    }
}
