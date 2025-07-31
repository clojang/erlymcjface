package io.github.clojang.mcjface.util;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.concurrent.Callable;

/**
 * Utility class for converting exceptions to Result types.
 * Provides a safe way to handle operations that might throw exceptions.
 */
public final class Try {
    
    private Try() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    /**
     * Execute a supplier and return a Result with the value or exception.
     */
    public static <T> Result<T, Exception> of(Supplier<T> supplier) {
        try {
            return Result.ok(supplier.get());
        } catch (Exception e) {
            return Result.error(e);
        }
    }
    
    /**
     * Execute a callable and return a Result with the value or exception.
     */
    public static <T> Result<T, Exception> of(Callable<T> callable) {
        try {
            return Result.ok(callable.call());
        } catch (Exception e) {
            return Result.error(e);
        }
    }
    
    /**
     * Execute a runnable and return a Result with void success or exception.
     */
    public static Result<Void, Exception> run(Runnable runnable) {
        try {
            runnable.run();
            return Result.ok(null);
        } catch (Exception e) {
            return Result.error(e);
        }
    }
    
    /**
     * Execute a supplier with a specific exception type.
     * If an unexpected exception type occurs, returns a Result with Exception instead of the specific type.
     */
    public static <T, E extends Exception> Result<T, Exception> ofType(
            Class<E> exceptionType, 
            Supplier<T> supplier) {
        try {
            return Result.ok(supplier.get());
        } catch (Exception e) {
            if (exceptionType.isInstance(e)) {
                // Expected exception type - cast and return
                return Result.error(exceptionType.cast(e));
            }
            // Unexpected exception type - return as generic Exception
            return Result.error(e);
        }
    }
    
    /**
     * Execute a supplier and map the exception to a different type.
     */
    public static <T, E> Result<T, E> withMapping(
            Supplier<T> supplier, 
            Function<Exception, E> errorMapper) {
        try {
            return Result.ok(supplier.get());
        } catch (Exception e) {
            return Result.error(errorMapper.apply(e));
        }
    }
}
