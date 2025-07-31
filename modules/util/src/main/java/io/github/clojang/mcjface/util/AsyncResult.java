package io.github.clojang.mcjface.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents an asynchronous computation that may succeed or fail. Combines CompletableFuture with
 * Result semantics.
 */
public class AsyncResult<T, E> {
  private final CompletableFuture<Result<T, E>> future;

  private AsyncResult(CompletableFuture<Result<T, E>> future) {
    this.future = future;
  }

  public static <T, E> AsyncResult<T, E> of(CompletableFuture<Result<T, E>> future) {
    return new AsyncResult<>(future);
  }

  public static <T, E> AsyncResult<T, E> completed(Result<T, E> result) {
    return new AsyncResult<>(CompletableFuture.completedFuture(result));
  }

  public static <T, E> AsyncResult<T, E> completedOk(T value) {
    return new AsyncResult<>(CompletableFuture.completedFuture(Result.ok(value)));
  }

  public static <T, E> AsyncResult<T, E> completedError(E error) {
    return new AsyncResult<>(CompletableFuture.completedFuture(Result.error(error)));
  }

  public static <T> AsyncResult<T, Exception> fromFuture(CompletableFuture<T> future) {
    CompletableFuture<Result<T, Exception>> resultFuture =
        future
            .thenApply(Result::<T, Exception>ok)
            .exceptionally(throwable -> Result.error((Exception) throwable));
    return new AsyncResult<>(resultFuture);
  }

  public <U> AsyncResult<U, E> map(Function<T, U> mapper) {
    CompletableFuture<Result<U, E>> mappedFuture = future.thenApply(result -> result.map(mapper));
    return new AsyncResult<>(mappedFuture);
  }

  public <F> AsyncResult<T, F> mapError(Function<E, F> mapper) {
    CompletableFuture<Result<T, F>> mappedFuture =
        future.thenApply(result -> result.mapError(mapper));
    return new AsyncResult<>(mappedFuture);
  }

  public <U> AsyncResult<U, E> flatMap(Function<T, AsyncResult<U, E>> mapper) {
    CompletableFuture<Result<U, E>> flatMappedFuture =
        future.thenCompose(
            result -> {
              if (result instanceof Result.Ok<T, E> okResult) {
                return mapper.apply(okResult.value()).future;
              } else if (result instanceof Result.Error<T, E> errorResult) {
                return CompletableFuture.completedFuture(Result.error(errorResult.errorValue()));
              } else {
                throw new IllegalStateException("Unknown Result type");
              }
            });
    return new AsyncResult<>(flatMappedFuture);
  }

  public AsyncResult<T, E> whenComplete(Consumer<Result<T, E>> action) {
    CompletableFuture<Result<T, E>> newFuture =
        future.whenComplete(
            (result, throwable) -> {
              if (throwable == null) {
                action.accept(result);
              }
            });
    return new AsyncResult<>(newFuture);
  }

  public AsyncResult<T, E> whenCompleteOk(Consumer<T> action) {
    return whenComplete(
        result -> {
          if (result instanceof Result.Ok<T, E> okResult) {
            action.accept(okResult.value());
          }
        });
  }

  public AsyncResult<T, E> whenCompleteError(Consumer<E> action) {
    return whenComplete(
        result -> {
          if (result instanceof Result.Error<T, E> errorResult) {
            action.accept(errorResult.errorValue());
          }
        });
  }

  public CompletableFuture<Result<T, E>> toCompletableFuture() {
    return future.thenApply(result -> result);
  }

  public CompletableFuture<Result<T, String>> toCompletableFutureUnwrapped() {
    return future.thenApply(Result::unwrap);
  }

  public CompletionStage<Result<T, E>> toCompletionStage() {
    return future.thenApply(result -> result);
  }
}
