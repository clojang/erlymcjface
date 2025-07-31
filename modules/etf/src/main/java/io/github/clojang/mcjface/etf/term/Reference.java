package io.github.clojang.mcjface.etf.term;

import java.util.Arrays;
import java.util.Objects;

public record Reference(String node, int creation, long[] ids) implements Term {

  public Reference {
    Objects.requireNonNull(node, "Node cannot be null");
    Objects.requireNonNull(ids, "IDs cannot be null");
    if (creation < 0) throw new IllegalArgumentException("Creation must be non-negative");
    if (ids.length == 0) throw new IllegalArgumentException("IDs cannot be empty");
    ids = ids.clone(); // Defensive copy
  }

  @Override
  public byte[] encode() {
    return new byte[0]; // Stub implementation
  }

  @Override
  public long[] ids() {
    return ids.clone(); // Defensive copy
  }

  public boolean isLocal(String localNode) {
    return Objects.equals(node, localNode);
  }

  @Override
  public String toString() {
    return String.format("#Ref<%s.%s>", node, Arrays.toString(ids));
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Reference other)) return false;
    return Objects.equals(node, other.node)
        && creation == other.creation
        && Arrays.equals(ids, other.ids);
  }

  @Override
  public int hashCode() {
    return Objects.hash(node, creation, Arrays.hashCode(ids));
  }
}
