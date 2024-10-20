package io.huangsam.trial.people;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUserProfile {
    @Test
    void testUserProfileGetters() {
        UserProfile profile = new UserProfile("Foo Bar", "foo.bar@example.com", 1);

        assertEquals("Foo Bar", profile.getName());
        assertEquals("foo.bar@example.com", profile.getEmail());
        assertEquals(1, profile.getAge());
    }

    @Test
    void testLoadUserProfileFromJson() throws IOException {
        String jsonFilePath = "src/test/resources/test.json";
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        Gson gson = new Gson();
        UserProfile[] profiles = gson.fromJson(jsonContent, UserProfile[].class);

        assertEquals(2, profiles.length);
        assertTrue(Arrays.stream(profiles).allMatch(profile -> profile.getName().contains("Doe")));
    }

    @Test
    void testLoadUserProfileFromCsv() throws IOException {
        String csvFilePath = "src/test/resources/test.csv";
        String csvContent = new String(Files.readAllBytes(Paths.get(csvFilePath)));
        String[] lines = csvContent.split("\n");

        assertEquals(3, lines.length);

        List<UserProfile> profiles = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("name")) {
                continue;
            }
            profiles.add(parseProfileFromCsvLine(line));
        }

        assertEquals(2, profiles.size());
        assertTrue(profiles.stream().allMatch(profile -> profile.getName().contains("Doe")));
    }

    @Test
    void testLoadUserProfileFromXml() throws Exception {
        String xmlFilePath = "src/test/resources/test.xml";
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(xmlFilePath));
        doc.getDocumentElement().normalize();

        List<UserProfile> profiles = new ArrayList<>();
        NodeList nodeList = doc.getElementsByTagName("user");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            profiles.add(parseProfileFromXmlElement(element));
        }

        assertEquals(2, profiles.size());
        assertTrue(profiles.stream().allMatch(profile -> profile.getName().contains("Doe")));
    }

    private UserProfile parseProfileFromCsvLine(String line) {
        String[] fields = line.split(",");
        return new UserProfile(fields[0], fields[1], Integer.parseInt(fields[2]));
    }

    private UserProfile parseProfileFromXmlElement(Element element) {
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        String email = element.getElementsByTagName("email").item(0).getTextContent();
        int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
        return new UserProfile(name, email, age);
    }
}
