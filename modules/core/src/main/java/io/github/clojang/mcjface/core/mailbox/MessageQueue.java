package io.github.clojang.mcjface.core.mailbox;

import io.github.clojang.mcjface.etf.term.Term;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MessageQueue {
    private final BlockingQueue<Term> queue;
    
    public MessageQueue() {
        this.queue = new LinkedBlockingQueue<>();
    }
    
    public void put(Term message) {
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public Term take() throws InterruptedException {
        return queue.take();
    }
    
    public Term poll(long timeout, TimeUnit unit) throws InterruptedException {
        return queue.poll(timeout, unit);
    }
    
    public int size() {
        return queue.size();
    }
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    public void clear() {
        queue.clear();
    }
}
