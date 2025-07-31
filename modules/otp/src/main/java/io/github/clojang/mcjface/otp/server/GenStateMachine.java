package io.github.clojang.mcjface.otp.server;

import io.github.clojang.mcjface.core.process.ProcessId;
import io.github.clojang.mcjface.etf.term.Term;
import io.github.clojang.mcjface.otp.process.Process;
import java.util.List;

public abstract class GenStateMachine implements Process {

  protected abstract InitResult init(List<Term> args);

  public enum EventType {
    CALL,
    CAST,
    INFO,
    TIMEOUT,
    INTERNAL
  }

  public record Event(EventType type, Term content, ProcessId from) {}

  public sealed interface InitResult {
    record Ok(Object state, Object data) implements InitResult {}

    record Stop(Term reason) implements InitResult {}

    static InitResult ok(Object state, Object data) {
      return new Ok(state, data);
    }

    static InitResult stop(Term reason) {
      return new Stop(reason);
    }
  }

  public sealed interface StateResult {
    record KeepState(Object data) implements StateResult {}

    record NextState(Object nextState, Object data) implements StateResult {}

    record Stop(Term reason, Object data) implements StateResult {}

    static StateResult keepState(Object data) {
      return new KeepState(data);
    }

    static StateResult nextState(Object nextState, Object data) {
      return new NextState(nextState, data);
    }

    static StateResult stop(Term reason, Object data) {
      return new Stop(reason, data);
    }
  }

  protected abstract StateResult handleEvent(
      EventType eventType, Term event, Object state, Object data);

  protected void terminate(Term reason, Object state, Object data) {
    // Default empty implementation
  }

  @Override
  public ProcessId self() {
    return null; // Stub implementation
  }

  @Override
  public void receive(Message message) {
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
