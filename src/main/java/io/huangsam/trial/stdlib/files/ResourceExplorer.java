package io.huangsam.trial.stdlib.files;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

/**
 * Utility for loading and parsing resources from the classpath.
 */
public class ResourceExplorer {

    /**
     * Loads a resource from the classpath as an InputStream.
     *
     * @param name the resource name
     * @return the resource stream
     */
    public InputStream getResourceStream(String name) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
        if (stream == null) {
            throw new IllegalArgumentException("Resource not found: " + name);
        }
        return stream;
    }

    /**
     * Parses a CSV resource into a list of lines.
     *
     * @param csvStream the CSV resource stream
     * @return a list of CSV lines
     * @throws IOException if the resource cannot be read
     */
    public List<String> parseCsvLines(InputStream csvStream) throws IOException {
        String content = new String(csvStream.readAllBytes());
        return List.of(content.split("\n"));
    }

    /**
     * Parses an XML resource into a DOM Document.
     *
     * @param xmlStream the XML resource stream
     * @return the parsed Document
     * @throws ParserConfigurationException if the XML builder cannot be configured
     * @throws IOException                  if the resource cannot be read
     * @throws SAXException                 if the XML is invalid
     */
    public Document parseXml(InputStream xmlStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(xmlStream);
        doc.getDocumentElement().normalize();
        return doc;
    }

    /**
     * Streamifies a NodeList for easier processing.
     *
     * @param nodeList the NodeList to streamify
     * @return a Stream of Nodes
     */
    public Stream<Node> streamify(NodeList nodeList) {
        return Stream.iterate(0, i -> i < nodeList.getLength(), i -> i + 1)
                .map(nodeList::item);
    }
}
