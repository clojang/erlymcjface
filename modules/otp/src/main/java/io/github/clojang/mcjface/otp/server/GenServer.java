package io.github.clojang.mcjface.otp.server;

import io.github.clojang.mcjface.core.process.ProcessId;
import io.github.clojang.mcjface.etf.term.Term;
import io.github.clojang.mcjface.otp.process.Process;
import java.util.List;

public abstract class GenServer implements Process {

  protected abstract InitResult init(List<Term> args);

  protected abstract CallResult handleCall(Term request, ProcessId from, Object state);

  protected abstract CastResult handleCast(Term message, Object state);

  protected abstract InfoResult handleInfo(Term info, Object state);

  protected void terminate(Term reason, Object state) {
    // Default empty implementation
  }

  public sealed interface InitResult {
    record Ok(Object state) implements InitResult {}

    record Stop(Term reason) implements InitResult {}

    static InitResult ok(Object state) {
      return new Ok(state);
    }

    static InitResult stop(Term reason) {
      return new Stop(reason);
    }
  }

  public sealed interface CallResult {
    record Reply(Term reply, Object newState) implements CallResult {}

    record NoReply(Object newState) implements CallResult {}

    record Stop(Term reason, Object newState) implements CallResult {}

    static CallResult reply(Term reply, Object newState) {
      return new Reply(reply, newState);
    }

    static CallResult noReply(Object newState) {
      return new NoReply(newState);
    }

    static CallResult stop(Term reason, Object newState) {
      return new Stop(reason, newState);
    }
  }

  public sealed interface CastResult {
    record NoReply(Object newState) implements CastResult {}

    record Stop(Term reason, Object newState) implements CastResult {}

    static CastResult noReply(Object newState) {
      return new NoReply(newState);
    }

    static CastResult stop(Term reason, Object newState) {
      return new Stop(reason, newState);
    }
  }

  public sealed interface InfoResult {
    record NoReply(Object newState) implements InfoResult {}

    record Stop(Term reason, Object newState) implements InfoResult {}

    static InfoResult noReply(Object newState) {
      return new NoReply(newState);
    }

    static InfoResult stop(Term reason, Object newState) {
      return new Stop(reason, newState);
    }
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
