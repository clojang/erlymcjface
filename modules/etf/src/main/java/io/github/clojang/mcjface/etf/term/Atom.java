package io.github.clojang.mcjface.etf.term;

import java.util.Objects;

public record Atom(String value) implements Term {
  private static final int MAX_ATOM_LENGTH = 255;

  public static final Atom TRUE = new Atom("true");
  public static final Atom FALSE = new Atom("false");
  public static final Atom OK = new Atom("ok");
  public static final Atom ERROR = new Atom("error");
  public static final Atom NIL = new Atom("nil");
  public static final Atom UNDEFINED = new Atom("undefined");

  public Atom {
    Objects.requireNonNull(value, "Atom value cannot be null");
    if (value.length() > MAX_ATOM_LENGTH) {
      throw new IllegalArgumentException("Atom too long: " + value.length());
    }
  }

  @Override
  public byte[] encode() {
    return new byte[0]; // Stub implementation
  }

  public boolean isBoolean() {
    return this.equals(TRUE) || this.equals(FALSE);
  }

  public boolean booleanValue() {
    if (this.equals(TRUE)) {
      return true;
    }
    if (this.equals(FALSE)) {
      return false;
    }
    throw new IllegalStateException("Atom is not a boolean: " + value);
  }
}
