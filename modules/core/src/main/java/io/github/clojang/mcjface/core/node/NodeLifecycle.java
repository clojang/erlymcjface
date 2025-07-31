package io.github.clojang.mcjface.core.node;

import java.util.concurrent.CompletableFuture;

public class NodeLifecycle {
  private volatile boolean running = false;

  public CompletableFuture<Void> start() {
    running = true;
    return CompletableFuture.completedFuture(null);
  }

  public CompletableFuture<Void> stop() {
    running = false;
    return CompletableFuture.completedFuture(null);
  }

  public boolean isRunning() {
    return running;
  }
}
