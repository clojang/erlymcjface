package io.github.clojang.mcjface.otp.process;

import io.github.clojang.mcjface.core.process.ProcessId;
import io.github.clojang.mcjface.etf.term.Term;

public interface Process {

  ProcessId self();

  void receive(Message message);

  void handleExit(ProcessId from, Term reason);

  void terminate(Term reason);

  record Message(ProcessId from, ProcessId to, Term content) {}
}
