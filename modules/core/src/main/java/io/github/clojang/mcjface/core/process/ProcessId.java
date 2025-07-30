package io.github.clojang.mcjface.core.process;

import io.github.clojang.mcjface.etf.term.Term;

public record ProcessId(String node, long id, long serial, int creation) implements Term {
    
    @Override
    public byte[] encode() {
        return new byte[0]; // Stub implementation
    }
}
