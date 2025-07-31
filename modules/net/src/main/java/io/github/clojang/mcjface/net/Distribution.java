package io.github.clojang.mcjface.net;

import io.github.clojang.mcjface.core.process.ProcessId;
import io.github.clojang.mcjface.etf.term.Term;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class Distribution {
  private final ConcurrentHashMap<String, Connection> connections;
  private final ConnectionPool connectionPool;

  public Distribution() {
    this.connections = new ConcurrentHashMap<>();
    this.connectionPool = new ConnectionPool();
  }

  public CompletableFuture<Void> send(ProcessId to, Term message) {
    String node = to.node();
    return connectionPool.connect(node).thenCompose(connection -> connection.send(message));
  }

  public CompletableFuture<Connection> connect(String node) {
    return connectionPool.connect(node);
  }

  public void disconnect(String node) {
    connections.remove(node);
  }

  public boolean isConnected(String node) {
    return connections.containsKey(node);
  }

  public void shutdown() {
    connections.clear();
    connectionPool.shutdown();
  }
}
