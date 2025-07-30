package io.github.clojang.mcjface.otp.supervision;

import io.github.clojang.mcjface.otp.behavior.ProcessBehavior;
import java.time.Duration;
import java.util.function.Supplier;

public record ChildSpec(
    String id,
    Supplier<ProcessBehavior> startFunction,
    Supervisor.RestartType restartType,
    Duration shutdownTimeout,
    Supervisor.ProcessType processType
) {
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private String id;
        private Supplier<ProcessBehavior> startFunction;
        private Supervisor.RestartType restartType = Supervisor.RestartType.PERMANENT;
        private Duration shutdownTimeout = Duration.ofSeconds(5);
        private Supervisor.ProcessType processType = Supervisor.ProcessType.WORKER;
        
        public Builder id(String id) {
            this.id = id;
            return this;
        }
        
        public Builder startFunction(Supplier<ProcessBehavior> startFunction) {
            this.startFunction = startFunction;
            return this;
        }
        
        public Builder restartType(Supervisor.RestartType restartType) {
            this.restartType = restartType;
            return this;
        }
        
        public Builder shutdownTimeout(Duration shutdownTimeout) {
            this.shutdownTimeout = shutdownTimeout;
            return this;
        }
        
        public Builder processType(Supervisor.ProcessType processType) {
            this.processType = processType;
            return this;
        }
        
        public ChildSpec build() {
            return new ChildSpec(id, startFunction, restartType, shutdownTimeout, processType);
        }
    }
}
