package io.github.clojang.mcjface.etf.codec;

import io.github.clojang.mcjface.etf.term.Atom;
import io.github.clojang.mcjface.etf.term.Binary;
import io.github.clojang.mcjface.etf.term.List;
import io.github.clojang.mcjface.etf.term.Map;
import io.github.clojang.mcjface.etf.term.Number;
import io.github.clojang.mcjface.etf.term.Pid;
import io.github.clojang.mcjface.etf.term.Port;
import io.github.clojang.mcjface.etf.term.Reference;
import io.github.clojang.mcjface.etf.term.Term;
import io.github.clojang.mcjface.etf.term.Tuple;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TermDecoder {
  private static final int VERSION_TAG = 131; // Magic number for external format

  public Term decode(byte[] data) throws IOException, DecodeException {
    if (data.length == 0) {
      throw new DecodeException("Empty data");
    }

    ByteArrayInputStream bais = new ByteArrayInputStream(data);
    int version = bais.read();
    if (version != VERSION_TAG) {
      throw new DecodeException("Invalid version tag: " + version);
    }

    return decodeTerm(bais);
  }

  private Term decodeTerm(ByteArrayInputStream in) throws IOException, DecodeException {
    int tag = in.read();
    if (tag == -1) {
      throw new DecodeException("Unexpected end of stream");
    }

    return switch (tag) {
      case 97 -> decodeSmallInteger(in); // SMALL_INTEGER_EXT
      case 98 -> decodeInteger(in); // INTEGER_EXT
      case 100 -> decodeAtom(in); // ATOM_EXT
      case 102 -> decodePort(in); // PORT_EXT
      case 103 -> decodePid(in); // PID_EXT
      case 104 -> decodeSmallTuple(in); // SMALL_TUPLE_EXT
      case 105 -> decodeLargeTuple(in); // LARGE_TUPLE_EXT
      case 108 -> decodeList(in); // LIST_EXT
      case 109 -> decodeBinary(in); // BINARY_EXT
      case 110 -> decodeSmallBig(in); // SMALL_BIG_EXT
      case 114 -> decodeNewReference(in); // NEW_REFERENCE_EXT
      case 116 -> decodeMap(in); // MAP_EXT
      case 70 -> decodeNewFloat(in); // NEW_FLOAT_EXT
      default -> throw new DecodeException("Unknown tag: " + tag);
    };
  }

  private Term decodeSmallInteger(ByteArrayInputStream in) throws IOException {
    int value = in.read();
    return new Number.Integer(value);
  }

  private Term decodeInteger(ByteArrayInputStream in) throws IOException {
    // Stub implementation - read 4 bytes big endian
    return new Number.Integer(0);
  }

  private Term decodeAtom(ByteArrayInputStream in) throws IOException {
    // Stub implementation - read length then string
    return new Atom("stub");
  }

  private Term decodePort(ByteArrayInputStream in) throws IOException, DecodeException {
    // Stub implementation
    return new Port("node", 0, 0);
  }

  private Term decodePid(ByteArrayInputStream in) throws IOException, DecodeException {
    // Stub implementation
    return new Pid("node", 0, 0, 0);
  }

  private Term decodeSmallTuple(ByteArrayInputStream in) throws IOException, DecodeException {
    int arity = in.read();
    Term[] elements = new Term[arity];
    for (int i = 0; i < arity; i++) {
      elements[i] = decodeTerm(in);
    }
    return Tuple.of(elements);
  }

  private Term decodeLargeTuple(ByteArrayInputStream in) throws IOException, DecodeException {
    // Stub implementation - read 4 bytes for arity
    return Tuple.of();
  }

  private Term decodeList(ByteArrayInputStream in) throws IOException, DecodeException {
    // Stub implementation
    return List.empty();
  }

  private Term decodeBinary(ByteArrayInputStream in) throws IOException {
    // Stub implementation
    return new Binary(new byte[0]);
  }

  private Term decodeSmallBig(ByteArrayInputStream in) throws IOException {
    // Stub implementation
    return new Number.BigInteger(java.math.BigInteger.ZERO);
  }

  private Term decodeNewReference(ByteArrayInputStream in) throws IOException {
    // Stub implementation
    return new Reference("node", 0, new long[] {0});
  }

  private Term decodeMap(ByteArrayInputStream in) throws IOException, DecodeException {
    // Stub implementation
    return Map.of();
  }

  private Term decodeNewFloat(ByteArrayInputStream in) throws IOException {
    // Stub implementation
    return new Number.Double(0.0);
  }

  public static class DecodeException extends Exception {
    private static final long serialVersionUID = 1L;

    public DecodeException(String message) {
      super(message);
    }

    public DecodeException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
