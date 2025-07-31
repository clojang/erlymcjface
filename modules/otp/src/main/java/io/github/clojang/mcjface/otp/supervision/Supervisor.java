package io.github.clojang.mcjface.otp.supervision;

import io.github.clojang.mcjface.core.process.ProcessId;
import io.github.clojang.mcjface.etf.term.Term;
import io.github.clojang.mcjface.otp.process.Process;
import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

public abstract class Supervisor implements Process {

  public enum RestartStrategy {
    ONE_FOR_ONE,
    ONE_FOR_ALL,
    REST_FOR_ONE,
    SIMPLE_ONE_FOR_ONE
  }

  public enum RestartType {
    PERMANENT,
    TEMPORARY,
    TRANSIENT
  }

  public enum ProcessType {
    WORKER,
    SUPERVISOR
  }

  protected abstract SupervisorSpec init(List<Term> args);

  public record SupervisorSpec(
      RestartStrategy strategy, int maxRestarts, Duration maxTime, List<ChildSpec> children) {}

  public record ChildSpec(
      String id,
      Supplier<Process> start,
      RestartType restart,
      Duration shutdown,
      ProcessType type) {}

  @Override
  public ProcessId self() {
    return null; // Stub implementation
  }

  @Override
  public void receive(Process.Message message) {
    // Stub implementation
  }

  @Override
  public void handleExit(ProcessId from, Term reason) {
    // Stub implementation
  }

  @Override
  public void terminate(Term reason) {
    // Stub implementation
  }
}
