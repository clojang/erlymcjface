package io.github.clojang.mcjface.core.process;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CompletableFuture;

public class ProcessScheduler {
    private final ExecutorService executor;
    
    public ProcessScheduler() {
        this.executor = Executors.newCachedThreadPool();
    }
    
    public CompletableFuture<Void> schedule(ErlangProcess process) {
        return CompletableFuture.runAsync(process::run, executor);
    }
    
    public void shutdown() {
        executor.shutdown();
    }
}
