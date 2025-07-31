package io.github.clojang.mcjface.core.process;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ProcessRegistry {
  private final ConcurrentHashMap<String, ProcessId> namedProcesses = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<ProcessId, ErlangProcess> processes = new ConcurrentHashMap<>();

  public void register(String name, ProcessId processId) {
    namedProcesses.put(name, processId);
  }

  public Optional<ProcessId> whereis(String name) {
    return Optional.ofNullable(namedProcesses.get(name));
  }

  public void unregister(String name) {
    namedProcesses.remove(name);
  }

  public void addProcess(ProcessId processId, ErlangProcess process) {
    processes.put(processId, process);
  }

  public Optional<ErlangProcess> getProcess(ProcessId processId) {
    return Optional.ofNullable(processes.get(processId));
  }

  public void removeProcess(ProcessId processId) {
    processes.remove(processId);
  }
}
