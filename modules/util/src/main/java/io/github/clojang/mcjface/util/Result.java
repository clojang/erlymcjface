package io.github.clojang.mcjface.util;

import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.Optional;

/**
 * A type that represents either success (Ok) or failure (Error).
 * Inspired by Rust's Result type and functional programming patterns.
 */
public sealed interface Result<T, E> {
    
    record Ok<T, E>(T value) implements Result<T, E> {}
    record Error<T, E>(E errorValue) implements Result<T, E> {}
    
    static <T, E> Result<T, E> ok(T value) {
        return new Ok<>(value);
    }
    
    static <T, E> Result<T, E> error(E error) {
        return new Error<>(error);
    }
    
    default boolean isOk() {
        return this instanceof Ok;
    }
    
    default boolean isError() {
        return this instanceof Error;
    }
    
    default T unwrap() {
        return switch (this) {
            case Ok(var value) -> value;
            case Error(var errorValue) -> throw new RuntimeException("Called unwrap on Error: " + errorValue);
        };
    }
    
    default T unwrapOr(T defaultValue) {
        return switch (this) {
            case Ok(var value) -> value;
            case Error(var errorValue) -> defaultValue;
        };
    }
    
    default T unwrapOrElse(Function<E, T> fn) {
        return switch (this) {
            case Ok(var value) -> value;
            case Error(var errorValue) -> fn.apply(errorValue);
        };
    }
    
    default E unwrapError() {
        return switch (this) {
            case Ok(var value) -> throw new RuntimeException("Called unwrapError on Ok: " + value);
            case Error(var errorValue) -> errorValue;
        };
    }
    
    default <U> Result<U, E> map(Function<T, U> fn) {
        return switch (this) {
            case Ok(var value) -> new Ok<>(fn.apply(value));
            case Error(var errorValue) -> new Error<>(errorValue);
        };
    }
    
    default <F> Result<T, F> mapError(Function<E, F> fn) {
        return switch (this) {
            case Ok(var value) -> new Ok<>(value);
            case Error(var errorValue) -> new Error<>(fn.apply(errorValue));
        };
    }
    
    default <U> Result<U, E> flatMap(Function<T, Result<U, E>> fn) {
        return switch (this) {
            case Ok(var value) -> fn.apply(value);
            case Error(var errorValue) -> new Error<>(errorValue);
        };
    }
    
    default Result<T, E> filter(Predicate<T> predicate, E errorValue) {
        return switch (this) {
            case Ok(var value) -> predicate.test(value) ? this : new Error<>(errorValue);
            case Error(var err) -> this;
        };
    }
    
    default Optional<T> ok() {
        return switch (this) {
            case Ok(var value) -> Optional.of(value);
            case Error(var errorValue) -> Optional.empty();
        };
    }
    
    default Optional<E> error() {
        return switch (this) {
            case Ok(var value) -> Optional.empty();
            case Error(var errorValue) -> Optional.of(errorValue);
        };
    }
    
    default Result<T, E> ifOk(Consumer<T> consumer) {
        if (this instanceof Ok(var value)) {
            consumer.accept(value);
        }
        return this;
    }
    
    default Result<T, E> ifError(Consumer<E> consumer) {
        if (this instanceof Error(var error)) {
            consumer.accept(error);
        }
        return this;
    }
}
