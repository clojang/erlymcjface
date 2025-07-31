package io.github.clojang.mcjface.core.process;

import io.github.clojang.mcjface.etf.term.Pid;

public record ProcessId(String node, long id, long serial, int creation) {

  public Pid toPid() {
    return new Pid(node, id, serial, creation);
  }

  public static ProcessId fromPid(Pid pid) {
    return new ProcessId(pid.node(), pid.id(), pid.serial(), pid.creation());
  }
}
