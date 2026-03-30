package io.huangsam.trial.stdlib.files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestNioChannel {

    private final NioChannelExplorer explorer = new NioChannelExplorer();

    @TempDir
    Path tempDir;

    @Test
    void testMemoryMappedFile() throws IOException {
        Path file = tempDir.resolve("mmap.txt");
        String initialData = "Hello, Mapped Memory!";
        Files.writeString(file, initialData);

        // Map the first 20 bytes for reading and writing
        MappedByteBuffer buffer = explorer.mapFile(file, 20);

        // Modify the buffer directly (Alchemy!)
        buffer.put(0, (byte) 'J'); // Replace 'H' with 'J'

        // Force changes to disk (though the OS would eventually do this)
        buffer.force();

        // Read back via standard Files API to prove the file was changed
        String result = Files.readString(file).substring(0, 20);
        assertEquals("Jello, Mapped Memory", result);
    }

    @Test
    void testFastCopy() throws IOException {
        Path source = tempDir.resolve("source.bin");
        Path target = tempDir.resolve("target.bin");
        String content = "Zero-Copy Data Transfer Example";
        Files.writeString(source, content);

        explorer.fastCopy(source, target);

        assertEquals(content, Files.readString(target));
    }

    @Test
    void testFileLock() throws IOException {
        Path file = tempDir.resolve("lock.txt");
        Files.writeString(file, "Locked Content");

        try (FileChannel channel = FileChannel.open(file, StandardOpenOption.WRITE)) {
            FileLock lock = explorer.acquireLock(channel);
            assertTrue(lock.isValid());
            assertFalse(lock.isShared());
            lock.release();
        }
    }

    private void assertFalse(boolean condition) {
        if (condition) {
            throw new AssertionError("Condition should be false");
        }
    }
}
