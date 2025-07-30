package io.github.clojang.mcjface.core.process;

public abstract class ErlangProcess {
    private final ProcessId processId;
    
    protected ErlangProcess(ProcessId processId) {
        this.processId = processId;
    }
    
    public ProcessId getProcessId() {
        return processId;
    }
    
    public abstract void run();
    
    public void terminate() {
        // Default termination logic
    }
}
