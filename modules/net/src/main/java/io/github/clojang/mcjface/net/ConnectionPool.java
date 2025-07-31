package io.github.clojang.mcjface.net;

import io.github.clojang.mcjface.util.Logging;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;

public final class ConnectionPool {
  private static final Logger logger = Logging.getLogger(ConnectionPool.class);

  private final ConcurrentHashMap<String, Connection> connections;
  private final ScheduledExecutorService scheduler;
  private final ConnectionFactory factory;

  public ConnectionPool() {
    this.connections = new ConcurrentHashMap<>();
    this.scheduler = Executors.newScheduledThreadPool(2);
    this.factory = new ConnectionFactory();
  }

  public CompletableFuture<Connection> connect(String node) {
    Connection existing = connections.get(node);
    if (existing != null && existing.isConnected()) {
      return CompletableFuture.completedFuture(existing);
    }

    return factory
        .create(node)
        .thenApply(
            connection -> {
              connections.put(node, connection);
              return connection;
            });
  }

  public void disconnect(String node) {
    Connection connection = connections.remove(node);
    if (connection != null) {
      try {
        connection.close();
      } catch (Exception e) {
        logger.warn("Failed to close connection for node: " + node + ". " + e.getMessage());
      }
    }
  }

  public void startHealthChecker() {
    scheduler.scheduleAtFixedRate(this::checkConnections, 30, 30, TimeUnit.SECONDS);
  }

  private void checkConnections() {
    connections
        .entrySet()
        .removeIf(
            entry -> {
              Connection connection = entry.getValue();
              if (!connection.isConnected()) {
                try {
                  connection.close();
                } catch (Exception e) {
                  logger.warn("Failed to close disconnected connection: " + e.getMessage());
                }
                return true;
              }
              return false;
            });
  }

  public void shutdown() {
    connections
        .values()
        .forEach(
            connection -> {
              try {
                connection.close();
              } catch (Exception e) {
                logger.warn("Failed to close connection during shutdown: " + e.getMessage());
              }
            });
    connections.clear();
    scheduler.shutdown();
  }

  private static class ConnectionFactory {
    public CompletableFuture<Connection> create(String node) {
      // Stub implementation
      return CompletableFuture.completedFuture(null);
    }
  }
}
