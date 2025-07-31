package io.github.clojang.mcjface.etf.term;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public record Tuple(Term[] elements) implements Term {

  public Tuple {
    Objects.requireNonNull(elements, "Tuple elements cannot be null");
    elements = elements.clone(); // Defensive copy
  }

  public static Tuple of(Term... elements) {
    return new Tuple(elements);
  }

  @Override
  public byte[] encode() {
    return new byte[0]; // Stub implementation
  }

  @Override
  public Term[] elements() {
    return elements.clone(); // Defensive copy
  }

  public int arity() {
    return elements.length;
  }

  public Term get(int index) {
    return elements[index];
  }

  public Stream<Term> stream() {
    return Arrays.stream(elements);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Tuple other)) return false;
    return Arrays.equals(elements, other.elements);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(elements);
  }
}
