package io.github.clojang.mcjface.core.node;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public final class ErlangNode implements AutoCloseable {
  private final NodeConfig config;

  private ErlangNode(NodeConfig config) {
    this.config = config;
  }

  public static ErlangNode create(NodeConfig config) {
    return new ErlangNode(config);
  }

  public CompletableFuture<Void> shutdown(Duration timeout) {
    return CompletableFuture.completedFuture(null);
  }

  @Override
  public void close() {
    // Stub implementation - in real implementation would shutdown resources
  }
}
