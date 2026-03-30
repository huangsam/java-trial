package io.huangsam.trial.stdlib.files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestNioFile {

    private final NioFileExplorer explorer = new NioFileExplorer();

    @TempDir
    Path tempDir;

    @Test
    void testWriteAndReadText() throws IOException {
        Path file = tempDir.resolve("test.txt");
        String content = "Hello, NIO.2!";

        explorer.writeText(file, content);
        String readBack = explorer.readText(file);

        assertEquals(content, readBack);
        assertTrue(explorer.isRegularFile(file));
    }

    @Test
    void testListFiles() throws IOException {
        Path file1 = tempDir.resolve("file1.txt");
        Path file2 = tempDir.resolve("file2.txt");

        explorer.writeText(file1, "Content 1");
        explorer.writeText(file2, "Content 2");

        List<Path> files = explorer.listFiles(tempDir);

        assertEquals(2, files.size());
        assertTrue(files.contains(file1));
        assertTrue(files.contains(file2));
    }

    @Test
    void testDeleteFile() throws IOException {
        Path file = tempDir.resolve("to_delete.txt");
        explorer.writeText(file, "Goodbye!");

        assertTrue(explorer.deleteFile(file));
        assertFalse(explorer.isRegularFile(file));
        assertFalse(explorer.deleteFile(file)); // Already deleted
    }
}
