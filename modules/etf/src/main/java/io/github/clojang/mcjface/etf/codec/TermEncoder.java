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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.slf4j.Logger;

public class TermEncoder {
  private static final Logger logger = Logging.getLogger(TermEncoder.class);

  public byte[] encode(Term term) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(VERSION_TAG);
    encodeTerm(term, baos);
    return baos.toByteArray();
  }

  private void encodeTerm(Term term, ByteArrayOutputStream out) throws IOException {
    switch (term) {
      case Atom atom -> encodeAtom(atom, out);
      case Number.Integer integer -> encodeInteger(integer, out);
      case Number.Long longNum -> encodeLong(longNum, out);
      case Number.Double doubleNum -> encodeDouble(doubleNum, out);
      case Number.BigInteger bigInt -> encodeBigInteger(bigInt, out);
      case Binary binary -> encodeBinary(binary, out);
      case List list -> encodeList(list, out);
      case Tuple tuple -> encodeTuple(tuple, out);
      case Map map -> encodeMap(map, out);
      case Pid pid -> encodePid(pid, out);
      case Port port -> encodePort(port, out);
      case Reference ref -> encodeReference(ref, out);
    }
  }

  private void encodeAtom(Atom atom, ByteArrayOutputStream out) throws IOException {
    logger.debug("encodeAtom not implemented for atom: {}", atom);
    out.write(ATOM_EXT);
  }

  private void encodeInteger(Number.Integer integer, ByteArrayOutputStream out) throws IOException {
    logger.debug("encodeInteger not implemented for integer: {}", integer);
    out.write(SMALL_INTEGER_EXT);
  }

  private void encodeLong(Number.Long longNum, ByteArrayOutputStream out) throws IOException {
    logger.debug("encodeLong not implemented for long: {}", longNum);
    out.write(INTEGER_EXT);
  }

  private void encodeDouble(Number.Double doubleNum, ByteArrayOutputStream out) throws IOException {
    logger.debug("encodeDouble not implemented for double: {}", doubleNum);
    out.write(NEW_FLOAT_EXT);
  }

  private void encodeBigInteger(Number.BigInteger bigInt, ByteArrayOutputStream out)
      throws IOException {
    logger.debug("encodeBigInteger not implemented for bigInt: {}", bigInt);
    out.write(SMALL_BIG_EXT);
  }

  private void encodeBinary(Binary binary, ByteArrayOutputStream out) throws IOException {
    logger.debug("encodeBinary not implemented for binary with {} bytes", binary.size());
    out.write(BINARY_EXT);
  }

  private void encodeList(List list, ByteArrayOutputStream out) throws IOException {
    logger.debug("encodeList not implemented for list with {} elements", list.size());
    out.write(LIST_EXT);
  }

  private void encodeTuple(Tuple tuple, ByteArrayOutputStream out) throws IOException {
    logger.debug("encodeTuple not implemented for tuple with arity: {}", tuple.arity());
    if (tuple.arity() <= MAX_BYTE_VALUE) {
      out.write(SMALL_TUPLE_EXT);
    } else {
      out.write(LARGE_TUPLE_EXT);
    }
  }

  private void encodeMap(Map map, ByteArrayOutputStream out) throws IOException {
    logger.debug("encodeMap not implemented for map with {} entries", map.size());
    out.write(MAP_EXT);
  }

  private void encodePid(Pid pid, ByteArrayOutputStream out) throws IOException {
    logger.debug("encodePid not implemented for pid: {}", pid);
    out.write(PID_EXT);
  }

  private void encodePort(Port port, ByteArrayOutputStream out) throws IOException {
    logger.debug("encodePort not implemented for port: {}", port);
    out.write(PORT_EXT);
  }

  private void encodeReference(Reference ref, ByteArrayOutputStream out) throws IOException {
    logger.debug("encodeReference not implemented for reference: {}", ref);
    out.write(NEW_REFERENCE_EXT);
  }
}
