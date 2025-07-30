package io.github.clojang.mcjface.otp.supervision;

import io.github.clojang.mcjface.otp.behavior.ProcessBehavior;
import io.github.clojang.mcjface.etf.term.Term;
import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

public abstract class Supervisor implements ProcessBehavior {
    
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
        RestartStrategy strategy,
        int maxRestarts,
        Duration maxTime,
        List<ChildSpec> children
    ) {}
    
    public record ChildSpec(
        String id,
        Supplier<ProcessBehavior> start,
        RestartType restart,
        Duration shutdown,
        ProcessType type
    ) {}
}
