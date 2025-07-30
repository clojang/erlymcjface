package io.github.clojang.mcjface.etf.term;

import java.util.Objects;

public record Port(String node, long id, int creation) implements Term {
    
    public Port {
        Objects.requireNonNull(node, "Node cannot be null");
        if (id < 0) throw new IllegalArgumentException("ID must be non-negative");
        if (creation < 0) throw new IllegalArgumentException("Creation must be non-negative");
    }
    
    @Override
    public byte[] encode() {
        return new byte[0]; // Stub implementation
    }
    
    public boolean isLocal(String localNode) {
        return Objects.equals(node, localNode);
    }
    
    @Override
    public String toString() {
        return String.format("#Port<%s.%d>", node, id);
    }
}
