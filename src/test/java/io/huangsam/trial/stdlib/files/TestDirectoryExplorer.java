package io.huangsam.trial.stdlib.files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link DirectoryExplorer}.
 */
public class TestDirectoryExplorer {

    private final DirectoryExplorer explorer = new DirectoryExplorer();

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectory(tempDir.resolve("subdir"));
        Files.writeString(tempDir.resolve("file1.txt"), "hello");
        Files.writeString(tempDir.resolve("subdir/file2.txt"), "world");
        Files.writeString(tempDir.resolve("subdir/file3.log"), "log content");
    }

    @Test
    void testWalkDirectory() throws IOException {
        List<Path> paths = explorer.walkDirectory(tempDir);
        assertTrue(paths.size() >= 4);
        assertTrue(paths.contains(tempDir.resolve("file1.txt")));
        assertTrue(paths.contains(tempDir.resolve("subdir/file2.txt")));
    }

    @Test
    void testFindByExtension() throws IOException {
        List<Path> txtFiles = explorer.findByExtension(tempDir, ".txt");
        assertEquals(2, txtFiles.size());
        assertTrue(txtFiles.stream().allMatch(p -> p.toString().endsWith(".txt")));

        List<Path> logFiles = explorer.findByExtension(tempDir, ".log");
        assertEquals(1, logFiles.size());
    }

    @Test
    void testCalculateTotalSize() throws IOException {
        // file1: 5 bytes, file2: 5 bytes, file3: 11 bytes
        long totalSize = explorer.calculateTotalSize(tempDir);
        assertEquals(21L, totalSize);
    }

    private void assertEquals(long expected, long actual) {
      org.junit.jupiter.api.Assertions.assertEquals(expected, actual);
    }

    private void assertEquals(int expected, int actual) {
      org.junit.jupiter.api.Assertions.assertEquals(expected, actual);
    }
}
