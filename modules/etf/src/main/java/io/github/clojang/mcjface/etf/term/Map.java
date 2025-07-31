package io.github.clojang.mcjface.etf.term;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public record Map(java.util.Map<Term, Term> entries) implements Term {

  public Map {
    Objects.requireNonNull(entries, "Map entries cannot be null");
    entries = new HashMap<>(entries); // Defensive copy
  }

  public static Map of() {
    return new Map(new HashMap<>());
  }

  public static Map of(Term k1, Term v1) {
    java.util.Map<Term, Term> map = new HashMap<>();
    map.put(k1, v1);
    return new Map(map);
  }

  public static Map of(Term k1, Term v1, Term k2, Term v2) {
    java.util.Map<Term, Term> map = new HashMap<>();
    map.put(k1, v1);
    map.put(k2, v2);
    return new Map(map);
  }

  @Override
  public byte[] encode() {
    return new byte[0]; // Stub implementation
  }

  @Override
  public java.util.Map<Term, Term> entries() {
    return new HashMap<>(entries); // Defensive copy
  }

  public int size() {
    return entries.size();
  }

  public boolean isEmpty() {
    return entries.isEmpty();
  }

  public Optional<Term> get(Term key) {
    return Optional.ofNullable(entries.get(key));
  }

  public boolean containsKey(Term key) {
    return entries.containsKey(key);
  }

  public Set<Term> keySet() {
    return entries.keySet();
  }

  public java.util.Collection<Term> values() {
    return entries.values();
  }

  public Set<java.util.Map.Entry<Term, Term>> entrySet() {
    return entries.entrySet();
  }

  public Map put(Term key, Term value) {
    java.util.Map<Term, Term> newEntries = new HashMap<>(entries);
    newEntries.put(key, value);
    return new Map(newEntries);
  }

  public Map remove(Term key) {
    java.util.Map<Term, Term> newEntries = new HashMap<>(entries);
    newEntries.remove(key);
    return new Map(newEntries);
  }
}
