package io.github.clojang.mcjface.net;

import io.github.clojang.mcjface.core.process.ProcessId;
import io.github.clojang.mcjface.etf.term.Term;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public final class Connection implements AutoCloseable {
  private final ProcessId localPid;
  private final ProcessId remotePid;
  private final String remoteNode;
  private final AtomicBoolean closed;

  public Connection(ProcessId localPid, ProcessId remotePid, String remoteNode) {
    this.localPid = localPid;
    this.remotePid = remotePid;
    this.remoteNode = remoteNode;
    this.closed = new AtomicBoolean(false);
  }

  public CompletableFuture<Void> send(Term message) {
    if (closed.get()) {
      return CompletableFuture.failedFuture(new IllegalStateException("Connection is closed"));
    }

    // Stub implementation
    return CompletableFuture.completedFuture(null);
  }

  public CompletableFuture<Term> receive() {
    if (closed.get()) {
      return CompletableFuture.failedFuture(new IllegalStateException("Connection is closed"));
    }

    // Stub implementation
    return CompletableFuture.completedFuture(null);
  }

  public boolean isConnected() {
    return !closed.get();
  }

  public String getRemoteNode() {
    return remoteNode;
  }

  public ProcessId getLocalPid() {
    return localPid;
  }

  public ProcessId getRemotePid() {
    return remotePid;
  }

  @Override
  public void close() {
    closed.set(true);
  }
}
