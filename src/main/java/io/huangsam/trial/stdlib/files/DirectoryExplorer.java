package io.huangsam.trial.stdlib.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Explores directory operations and recursive file system traversal.
 * Demonstrates Files.walk and Files.find.
 */
public class DirectoryExplorer {
    /**
     * Constructs a directory explorer.
     */
    public DirectoryExplorer() {
        // Default constructor
    }

    /**
     * Lists all files in a directory recursively.
     *
     * @param start the starting path
     * @return a list of all paths found
     * @throws IOException if an I/O error occurs
     */
    public List<Path> walkDirectory(Path start) throws IOException {
        try (Stream<Path> stream = Files.walk(start)) {
            return stream.toList();
        }
    }

    /**
     * Finds files with a specific extension in a directory recursively.
     *
     * @param start     the starting path
     * @param extension the file extension to search for (e.g., ".txt")
     * @return a list of paths matching the extension
     * @throws IOException if an I/O error occurs
     */
    public List<Path> findByExtension(Path start, String extension) throws IOException {
        try (Stream<Path> stream = Files.find(start, Integer.MAX_VALUE,
                (path, attr) -> path.toString().endsWith(extension))) {
            return stream.toList();
        }
    }

    /**
     * Calculates the total size of all files in a directory recursively.
     *
     * @param start the starting path
     * @return total size in bytes
     * @throws IOException if an I/O error occurs
     */
    public long calculateTotalSize(Path start) throws IOException {
        try (Stream<Path> stream = Files.walk(start)) {
            return stream.filter(Files::isRegularFile)
                    .mapToLong(path -> {
                        try {
                            return Files.size(path);
                        } catch (IOException e) {
                            return 0L;
                        }
                    }).sum();
        }
    }
}
