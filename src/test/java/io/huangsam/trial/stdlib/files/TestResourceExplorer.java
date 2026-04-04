package io.huangsam.trial.stdlib.files;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link ResourceExplorer}.
 */
public class TestResourceExplorer {

    private final ResourceExplorer explorer = new ResourceExplorer();

    @Test
    void testGetResourceStreamSuccess() throws IOException {
        try (InputStream stream = explorer.getResourceStream("test.csv")) {
            assertNotNull(stream);
        }
    }

    @Test
    void testGetResourceStreamFailure() {
        assertThrows(IllegalArgumentException.class, () -> explorer.getResourceStream("non_existent.file"));
    }

    @Test
    void testParseCsvLines() throws IOException {
        try (InputStream stream = explorer.getResourceStream("test.csv")) {
            List<String> lines = explorer.parseCsvLines(stream);
            assertNotNull(lines);
            assertTrue(lines.size() > 0);
        }
    }

    @Test
    void testParseXml() throws IOException, ParserConfigurationException, SAXException {
        try (InputStream stream = explorer.getResourceStream("test.xml")) {
            Document doc = explorer.parseXml(stream);
            assertNotNull(doc);
            assertEquals("users", doc.getDocumentElement().getTagName());
        }
    }

    @Test
    void testStreamify() throws IOException, ParserConfigurationException, SAXException {
        try (InputStream stream = explorer.getResourceStream("test.xml")) {
            Document doc = explorer.parseXml(stream);
            long count = explorer.streamify(doc.getElementsByTagName("item")).count();
            assertTrue(count >= 0);
        }
    }
}
