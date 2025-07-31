package io.github.clojang.mcjface.otp.supervision;

import java.time.Duration;

public class SupervisionStrategy {
  private final Supervisor.RestartStrategy restartStrategy;
  private final int maxRestarts;
  private final Duration timeWindow;

  public SupervisionStrategy(
      Supervisor.RestartStrategy restartStrategy, int maxRestarts, Duration timeWindow) {
    this.restartStrategy = restartStrategy;
    this.maxRestarts = maxRestarts;
    this.timeWindow = timeWindow;
  }

  public Supervisor.RestartStrategy getRestartStrategy() {
    return restartStrategy;
  }

  public int getMaxRestarts() {
    return maxRestarts;
  }

  public Duration getTimeWindow() {
    return timeWindow;
  }

  public boolean shouldRestart(int currentRestarts, Duration timeSinceFirstRestart) {
    return currentRestarts < maxRestarts && timeSinceFirstRestart.compareTo(timeWindow) < 0;
  }
}
