package io.github.clojang.mcjface.etf.term;

import java.util.Arrays;
import java.util.Objects;

public record Binary(byte[] bytes) implements Term {
    
    public Binary {
        Objects.requireNonNull(bytes, "Binary bytes cannot be null");
        bytes = bytes.clone(); // Defensive copy
    }
    
    public Binary(String string) {
        this(string.getBytes());
    }
    
    @Override
    public byte[] encode() {
        return new byte[0]; // Stub implementation
    }
    
    @Override
    public byte[] bytes() {
        return bytes.clone(); // Defensive copy
    }
    
    public int size() {
        return bytes.length;
    }
    
    public String toString() {
        return new String(bytes);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Binary other)) return false;
        return Arrays.equals(bytes, other.bytes);
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }
}
