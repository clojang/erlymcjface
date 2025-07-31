package io.github.clojang.mcjface.etf.codec;

import static io.github.clojang.mcjface.etf.codec.EtfConstants.*;

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
import io.github.clojang.mcjface.util.Logging;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.slf4j.Logger;

public class TermDecoder {
  private static final Logger logger = Logging.getLogger(TermDecoder.class);

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
      case SMALL_INTEGER_EXT -> decodeSmallInteger(in);
      case INTEGER_EXT -> decodeInteger(in);
      case ATOM_EXT -> decodeAtom(in);
      case PORT_EXT -> decodePort(in);
      case PID_EXT -> decodePid(in);
      case SMALL_TUPLE_EXT -> decodeSmallTuple(in);
      case LARGE_TUPLE_EXT -> decodeLargeTuple(in);
      case LIST_EXT -> decodeList(in);
      case BINARY_EXT -> decodeBinary(in);
      case SMALL_BIG_EXT -> decodeSmallBig(in);
      case NEW_REFERENCE_EXT -> decodeNewReference(in);
      case MAP_EXT -> decodeMap(in);
      case NEW_FLOAT_EXT -> decodeNewFloat(in);
      default -> throw new DecodeException("Unknown tag: " + tag);
    };
  }

  private Term decodeSmallInteger(ByteArrayInputStream in) throws IOException {
    int value = in.read();
    return new Number.Integer(value);
  }

  private Term decodeInteger(ByteArrayInputStream in) throws IOException {
    logger.debug("decodeInteger not implemented, available bytes: {}", in.available());
    return new Number.Integer(0);
  }

  private Term decodeAtom(ByteArrayInputStream in) throws IOException {
    logger.debug("decodeAtom not implemented, available bytes: {}", in.available());
    return new Atom("stub");
  }

  private Term decodePort(ByteArrayInputStream in) throws IOException, DecodeException {
    logger.debug("decodePort not implemented, available bytes: {}", in.available());
    return new Port("node", 0, 0);
  }

  private Term decodePid(ByteArrayInputStream in) throws IOException, DecodeException {
    logger.debug("decodePid not implemented, available bytes: {}", in.available());
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
    logger.debug("decodeLargeTuple not implemented, available bytes: {}", in.available());
    return Tuple.of();
  }

  private Term decodeList(ByteArrayInputStream in) throws IOException, DecodeException {
    logger.debug("decodeList not implemented, available bytes: {}", in.available());
    return List.empty();
  }

  private Term decodeBinary(ByteArrayInputStream in) throws IOException {
    logger.debug("decodeBinary not implemented, available bytes: {}", in.available());
    return new Binary(new byte[0]);
  }

  private Term decodeSmallBig(ByteArrayInputStream in) throws IOException {
    logger.debug("decodeSmallBig not implemented, available bytes: {}", in.available());
    return new Number.BigInteger(java.math.BigInteger.ZERO);
  }

  private Term decodeNewReference(ByteArrayInputStream in) throws IOException {
    logger.debug("decodeNewReference not implemented, available bytes: {}", in.available());
    return new Reference("node", 0, new long[] {0});
  }

  private Term decodeMap(ByteArrayInputStream in) throws IOException, DecodeException {
    logger.debug("decodeMap not implemented, available bytes: {}", in.available());
    return Map.of();
  }

  private Term decodeNewFloat(ByteArrayInputStream in) throws IOException {
    logger.debug("decodeNewFloat not implemented, available bytes: {}", in.available());
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
