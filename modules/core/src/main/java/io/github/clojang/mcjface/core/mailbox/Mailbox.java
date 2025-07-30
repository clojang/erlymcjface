package io.github.clojang.mcjface.core.mailbox;

import io.github.clojang.mcjface.core.process.ProcessId;
import io.github.clojang.mcjface.etf.term.Term;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class Mailbox implements AutoCloseable {
    private final ProcessId owner;
    private final BlockingQueue<Message> messages;
    private final Set<ProcessId> links;
    private final Set<ProcessId> monitors;
    
    public Mailbox(ProcessId owner) {
        this.owner = owner;
        this.messages = new LinkedBlockingQueue<>();
        this.links = ConcurrentHashMap.newKeySet();
        this.monitors = ConcurrentHashMap.newKeySet();
    }
    
    public CompletableFuture<Term> receive() {
        return receive(Duration.ofMillis(Long.MAX_VALUE));
    }
    
    public CompletableFuture<Term> receive(Duration timeout) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Message msg = messages.take();
                return msg.content();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        });
    }
    
    public void send(ProcessId to, Term message) {
        // Stub implementation
    }
    
    public void deliver(Message message) {
        messages.offer(message);
    }
    
    @Override
    public void close() throws Exception {
        messages.clear();
    }
    
    public record Message(ProcessId from, ProcessId to, Term content) {}
}
