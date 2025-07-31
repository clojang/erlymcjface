package io.github.clojang.mcjface.etf.term;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public record List(Term[] elements) implements Term, Iterable<Term> {

  public List {
    Objects.requireNonNull(elements, "List elements cannot be null");
    elements = elements.clone(); // Defensive copy
  }

  public static List of(Term... elements) {
    return new List(elements);
  }

  public static List empty() {
    return new List(new Term[0]);
  }

  @Override
  public byte[] encode() {
    return new byte[0]; // Stub implementation
  }

  @Override
  public Term[] elements() {
    return elements.clone(); // Defensive copy
  }

  public int size() {
    return elements.length;
  }

  public boolean isEmpty() {
    return elements.length == 0;
  }

  public Term get(int index) {
    return elements[index];
  }

  public Term head() {
    if (isEmpty()) {
      throw new IllegalStateException("Empty list has no head");
    }
    return elements[0];
  }

  public List tail() {
    if (isEmpty()) {
      throw new IllegalStateException("Empty list has no tail");
    }
    return new List(Arrays.copyOfRange(elements, 1, elements.length));
  }

  @Override
  public Iterator<Term> iterator() {
    return Arrays.asList(elements).iterator();
  }

  public Stream<Term> stream() {
    return Arrays.stream(elements);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof List other)) return false;
    return Arrays.equals(elements, other.elements);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(elements);
  }
}
