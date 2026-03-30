package io.huangsam.trial.libs.guava;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * Demonstrates Guava's Hashing API for modern, non-cryptographic and cryptographic hashing.
 * Provides a much more consistent interface than the legacy JDK MessageDigest.
 */
public class HashingExplorer {

    /**
     * Hashes a string using Murmur3 128-bit.
     * Murmur3 is a very fast, high-quality non-cryptographic hash function.
     *
     * @param input the input string
     * @return the generated HashCode
     */
    public HashCode murmur3(String input) {
        HashFunction hf = Hashing.murmur3_128();
        return hf.hashString(input, StandardCharsets.UTF_8);
    }

    /**
     * Hashes a string using SHA-256 (Cryptographic).
     *
     * @param input the input string
     * @return the generated HashCode
     */
    public HashCode sha256(String input) {
        HashFunction hf = Hashing.sha256();
        return hf.hashString(input, StandardCharsets.UTF_8);
    }

    /**
     * Combines two HashCodes into a single one.
     *
     * @param h1 the first half
     * @param h2 the second half
     * @return the combined result
     */
    public HashCode combine(HashCode h1, HashCode h2) {
        return Hashing.combineOrdered(java.util.List.of(h1, h2));
    }
}
