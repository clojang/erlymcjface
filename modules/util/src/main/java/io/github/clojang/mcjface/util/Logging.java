package io.github.clojang.mcjface.util;

import io.github.clojang.clojog.Clojog;
import io.github.clojang.clojog.ClojogOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton utility class for setting up and managing logging configuration across the application.
 * Uses clojog for enhanced logging features including colored output and structured logging.
 */
public final class Logging {
  private static volatile boolean initialized = false;
  private static final Object lock = new Object();

  private Logging() {
    throw new UnsupportedOperationException("Utility class cannot be instantiated");
  }

  /**
   * Initialize logging with default configuration if not already initialized. This method is
   * thread-safe and will only initialize once.
   */
  public static void initializeLogging() {
    if (!initialized) {
      synchronized (lock) {
        if (!initialized) {
          Clojog.setupLogging(
              new ClojogOptions()
                  .setColored(true)
                  .setLevel("DEBUG")
                  .setOutput("stdout")
                  .setReportCaller(false));
          initialized = true;
        }
      }
    }
  }

  /**
   * Initialize logging with custom options. This method is thread-safe and will only initialize
   * once.
   */
  public static void initializeLogging(ClojogOptions options) {
    if (!initialized) {
      synchronized (lock) {
        if (!initialized) {
          Clojog.setupLogging(options);
          initialized = true;
        }
      }
    }
  }

  /**
   * Get a logger for the specified class. Automatically initializes logging if not already done.
   */
  public static Logger getLogger(Class<?> clazz) {
    initializeLogging();
    return LoggerFactory.getLogger(clazz);
  }

  /**
   * Get a logger with the specified name. Automatically initializes logging if not already done.
   */
  public static Logger getLogger(String name) {
    initializeLogging();
    return LoggerFactory.getLogger(name);
  }

  /** Check if logging has been initialized. */
  public static boolean isInitialized() {
    return initialized;
  }
}
