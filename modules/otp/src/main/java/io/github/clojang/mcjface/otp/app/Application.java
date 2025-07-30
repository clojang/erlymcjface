package io.github.clojang.mcjface.otp.behavior;

import io.github.clojang.mcjface.core.process.ProcessId;
import io.github.clojang.mcjface.core.supervision.Supervisor;
import io.github.clojang.mcjface.etf.term.Term;
import java.util.List;

public abstract class Application implements ProcessBehavior {
    
    protected abstract StartResult start(StartType startType, List<Term> args);
    
    protected void stop(Object state) {
        // Default empty implementation
    }
    
    public enum StartType {
        NORMAL,
        TAKEOVER,
        FAILOVER
    }
    
    public sealed interface StartResult {
        record Ok(ProcessId pid, Object state) implements StartResult {}
        record Error(Term reason) implements StartResult {}
        
        static StartResult ok(ProcessId pid, Object state) {
            return new Ok(pid, state);
        }
        
        static StartResult error(Term reason) {
            return new Error(reason);
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
