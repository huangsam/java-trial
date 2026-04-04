package io.huangsam.trial.stdlib.files;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Demonstrates high-performance "File Alchemy" using NIO Channels and Buffers.
 * Covers Memory-Mapped Files, File Locking, and Zero-Copy Transfers.
 */
public class NioChannelExplorer {
    /**
     * Constructs an NIO channel explorer.
     */
    public NioChannelExplorer() {
        // Default constructor
    }

    /**
     * Maps a file into memory for instant access via a MappedByteBuffer.
     * Changes to the buffer are reflected in the file on disk.
     *
     * @param path the path to the file
     * @param size the size of the mapping in bytes
     * @return the MappedByteBuffer
     * @throws IOException if an I/O error occurs
     */
    public MappedByteBuffer mapFile(Path path, long size) throws IOException {
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            return channel.map(FileChannel.MapMode.READ_WRITE, 0, size);
        }
    }

    /**
     * Acquires an exclusive lock on a file.
     *
     * @param channel the file channel to lock
     * @return the FileLock object
     * @throws IOException if an I/O error occurs
     */
    public FileLock acquireLock(FileChannel channel) throws IOException {
        return channel.lock(); // This blocks until the lock is acquired
    }

    /**
     * Performs a high-speed zero-copy transfer between two files.
     * This bypasses the Java heap and uses OS-level primitives.
     *
     * @param source the source file path
     * @param target the target file path
     * @throws IOException if an I/O error occurs
     */
    public void fastCopy(Path source, Path target) throws IOException {
        try (FileChannel srcChannel = FileChannel.open(source, StandardOpenOption.READ);
             FileChannel destChannel = FileChannel.open(target, StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {

            long size = srcChannel.size();
            long position = 0;
            while (position < size) {
                position += srcChannel.transferTo(position, size - position, destChannel);
            }
        }
    }
}
