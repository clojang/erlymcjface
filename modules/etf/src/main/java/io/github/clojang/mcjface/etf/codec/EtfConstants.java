package io.github.clojang.mcjface.etf.codec;

/**
 * Constants for Erlang External Term Format (ETF).
 *
 * <p>This class contains all the tag values defined in the Erlang External Term Format
 * specification. These constants are used by both {@link TermEncoder} and {@link TermDecoder} to
 * encode and decode Erlang terms.
 *
 * @see <a href="https://www.erlang.org/doc/apps/erts/erl_ext_dist.html">Erlang External Term
 *     Format</a>
 */
public final class EtfConstants {

  /** Magic version tag for external format. */
  public static final int VERSION_TAG = 131;

  // Erlang External Term Format tag constants

  /** Tag for small integers (0-255). */
  public static final int SMALL_INTEGER_EXT = 97;

  /** Tag for 32-bit signed integers. */
  public static final int INTEGER_EXT = 98;

  /** Tag for atoms. */
  public static final int ATOM_EXT = 100;

  /** Tag for ports. */
  public static final int PORT_EXT = 102;

  /** Tag for process identifiers (PIDs). */
  public static final int PID_EXT = 103;

  /** Tag for small tuples (arity 0-255). */
  public static final int SMALL_TUPLE_EXT = 104;

  /** Tag for large tuples (arity > 255). */
  public static final int LARGE_TUPLE_EXT = 105;

  /** Tag for lists. */
  public static final int LIST_EXT = 108;

  /** Tag for binaries. */
  public static final int BINARY_EXT = 109;

  /** Tag for small big integers. */
  public static final int SMALL_BIG_EXT = 110;

  /** Tag for new-style references. */
  public static final int NEW_REFERENCE_EXT = 114;

  /** Tag for maps. */
  public static final int MAP_EXT = 116;

  /** Tag for IEEE 754 double precision floats. */
  public static final int NEW_FLOAT_EXT = 70;

  /** Maximum value for a single byte (unsigned 8-bit). */
  public static final int MAX_BYTE_VALUE = 255;

  private EtfConstants() {
    // Utility class - prevent instantiation
  }
}
