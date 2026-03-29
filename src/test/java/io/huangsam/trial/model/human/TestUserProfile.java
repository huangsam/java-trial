package io.huangsam.trial.model.human;

import com.google.gson.Gson;
import io.huangsam.trial.stdlib.util.ResourceExplorer;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Let's load {@code UserProfile} records via JSON, CXV and XML files. It's
 * common to do file operations to grab data and process them, so we're doing
 * exactly that.
 *
 * @see <a href="https://www.baeldung.com/java-json">Baeldung on JSON</a>
 * @see <a href="https://www.baeldung.com/java-csv">Baeldung on CSV</a>
 * @see <a href="https://www.baeldung.com/java-xml">Baeldung on XML</a>
 */
public class TestUserProfile {
    private final ResourceExplorer explorer = new ResourceExplorer();

    @Test
    void testUserProfileGetters() {
        UserProfile profile = new UserProfile("Foo Bar", "foo.bar@example.com", 1);

        assertEquals("Foo Bar", profile.name());
        assertEquals("foo.bar@example.com", profile.email());
        assertEquals(1, profile.age());
    }

    @Test
    void testLoadUserProfileFromJson() throws IOException {
        InputStream jsonStream = explorer.getResourceStream("test.json");
        String jsonContent = new String(jsonStream.readAllBytes());
        UserProfile[] profiles = new Gson().fromJson(jsonContent, UserProfile[].class);

        assertEquals(2, profiles.length);
        assertTrue(Arrays.stream(profiles).allMatch(profile -> profile.name().contains("Doe")));
    }

    @Test
    void testLoadUserProfileFromCsv() throws IOException {
        InputStream csvStream = explorer.getResourceStream("test.csv");
        List<String> lines = explorer.parseCsvLines(csvStream);

        assertEquals(3, lines.size());

        List<UserProfile> profiles = lines.stream()
                .filter(line -> !line.startsWith("name"))
                .map(this::parseProfileFromCsvLine)
                .toList();

        assertEquals(lines.size() - 1, profiles.size());
        assertTrue(profiles.stream().allMatch(profile -> profile.name().contains("Doe")));
    }

    @Test
    void testLoadUserProfileFromXml() throws Exception {
        InputStream xmlStream = explorer.getResourceStream("test.xml");
        Document doc = explorer.parseXml(xmlStream);
        NodeList nodeList = doc.getElementsByTagName("user");

        List<UserProfile> profiles = explorer.streamify(nodeList)
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
}
