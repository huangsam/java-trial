package io.huangsam.trial.stdlib.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Demonstrates modern Java NIO.2 (java.nio.file) features.
 * Focuses on Path and Files utility methods introduced in Java 7 and 11.
 */
public class NioFileExplorer {
    /**
     * Constructs an NIO file explorer.
     */
    public NioFileExplorer() {
        // Default constructor
    }

    /**
     * Writes content to a file using UTF-8 encoding.
     *
     * @param path    the path to the file
     * @param content the content to write
     * @throws IOException if an I/O error occurs
     */
    public void writeText(Path path, String content) throws IOException {
        Files.writeString(path, content);
    }

    /**
     * Reads the entire content of a file as a string.
     *
     * @param path the path to the file
     * @return the file content
     * @throws IOException if an I/O error occurs
     */
    public String readText(Path path) throws IOException {
        return Files.readString(path);
    }

    /**
     * Lists files in a directory (non-recursive).
     *
     * @param dir the directory to list
     * @return a list of paths in the directory
     * @throws IOException if an I/O error occurs
     */
    public List<Path> listFiles(Path dir) throws IOException {
        try (Stream<Path> stream = Files.list(dir)) {
            return stream.toList();
        }
    }

    /**
     * Deletes a file if it exists.
     *
     * @param path the path to the file
     * @return true if the file was deleted, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean deleteFile(Path path) throws IOException {
        return Files.deleteIfExists(path);
    }

    /**
     * Checks if a path represents a regular file.
     *
     * @param path the path to check
     * @return true if it is a regular file
     */
    public boolean isRegularFile(Path path) {
        return Files.isRegularFile(path);
    }
}
