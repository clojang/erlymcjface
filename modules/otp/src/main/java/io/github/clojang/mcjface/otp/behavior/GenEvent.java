package io.github.clojang.mcjface.otp.behavior;

import io.github.clojang.mcjface.core.process.ProcessId;
import io.github.clojang.mcjface.etf.term.Term;
import java.util.List;

public abstract class GenEvent implements ProcessBehavior {
    
    protected abstract InitResult init(List<Term> args);
    
    protected abstract HandleEventResult handleEvent(
        Term event, Object state);
        
    protected abstract HandleCallResult handleCall(
        Term request, Object state);
        
    protected abstract HandleInfoResult handleInfo(
        Term info, Object state);
        
    protected void terminate(Term reason, Object state) {
        // Default empty implementation
    }
    
    public sealed interface InitResult {
        record Ok(Object state) implements InitResult {}
        record Error(Term reason) implements InitResult {}
        
        static InitResult ok(Object state) {
            return new Ok(state);
        }
        
        static InitResult error(Term reason) {
            return new Error(reason);
        }
    }
    
    public sealed interface HandleEventResult {
        record Ok(Object newState) implements HandleEventResult {}
        record Remove() implements HandleEventResult {}
        
        static HandleEventResult ok(Object newState) {
            return new Ok(newState);
        }
        
        static HandleEventResult remove() {
            return new Remove();
        }
    }
    
    public sealed interface HandleCallResult {
        record Ok(Term reply, Object newState) implements HandleCallResult {}
        record Remove(Term reply) implements HandleCallResult {}
        
        static HandleCallResult ok(Term reply, Object newState) {
            return new Ok(reply, newState);
        }
        
        static HandleCallResult remove(Term reply) {
            return new Remove(reply);
        }
    }
    
    public sealed interface HandleInfoResult {
        record Ok(Object newState) implements HandleInfoResult {}
        record Remove() implements HandleInfoResult {}
        
        static HandleInfoResult ok(Object newState) {
            return new Ok(newState);
        }
        
        static HandleInfoResult remove() {
            return new Remove();
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
