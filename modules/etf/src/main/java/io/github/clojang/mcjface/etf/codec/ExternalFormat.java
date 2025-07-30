package io.github.clojang.mcjface.etf.codec;

/**
 * Constants for Erlang external format tags and versions.
 * Based on the Erlang distribution protocol specification.
 */
public final class ExternalFormat {
    
    // Version tag
    public static final int VERSION_MAGIC = 131;
    
    // Atom tags
    public static final int ATOM_EXT = 100;
    public static final int SMALL_ATOM_EXT = 115;
    public static final int ATOM_UTF8_EXT = 118;
    public static final int SMALL_ATOM_UTF8_EXT = 119;
    
    // Integer tags
    public static final int SMALL_INTEGER_EXT = 97;
    public static final int INTEGER_EXT = 98;
    public static final int SMALL_BIG_EXT = 110;
    public static final int LARGE_BIG_EXT = 111;
    
    // Float tags
    public static final int FLOAT_EXT = 99;
    public static final int NEW_FLOAT_EXT = 70;
    
    // Binary tags
    public static final int BINARY_EXT = 109;
    public static final int BIT_BINARY_EXT = 77;
    
    // String tags
    public static final int STRING_EXT = 107;
    
    // List tags
    public static final int LIST_EXT = 108;
    public static final int NIL_EXT = 106;
    
    // Tuple tags
    public static final int SMALL_TUPLE_EXT = 104;
    public static final int LARGE_TUPLE_EXT = 105;
    
    // Map tag
    public static final int MAP_EXT = 116;
    
    // PID tags
    public static final int PID_EXT = 103;
    public static final int NEW_PID_EXT = 88;
    
    // Port tags
    public static final int PORT_EXT = 102;
    public static final int NEW_PORT_EXT = 89;
    
    // Reference tags
    public static final int REFERENCE_EXT = 101;
    public static final int NEW_REFERENCE_EXT = 114;
    public static final int NEWER_REFERENCE_EXT = 90;
    
    // Fun tags
    public static final int FUN_EXT = 117;
    public static final int NEW_FUN_EXT = 112;
    public static final int EXPORT_EXT = 113;
    
    // Misc tags
    public static final int COMPRESSED = 80;
    
    // Private constructor to prevent instantiation
    private ExternalFormat() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    public static String tagName(int tag) {
        return switch (tag) {
            case ATOM_EXT -> "ATOM_EXT";
            case SMALL_ATOM_EXT -> "SMALL_ATOM_EXT";
            case ATOM_UTF8_EXT -> "ATOM_UTF8_EXT";
            case SMALL_ATOM_UTF8_EXT -> "SMALL_ATOM_UTF8_EXT";
            case SMALL_INTEGER_EXT -> "SMALL_INTEGER_EXT";
            case INTEGER_EXT -> "INTEGER_EXT";
            case SMALL_BIG_EXT -> "SMALL_BIG_EXT";
            case LARGE_BIG_EXT -> "LARGE_BIG_EXT";
            case FLOAT_EXT -> "FLOAT_EXT";
            case NEW_FLOAT_EXT -> "NEW_FLOAT_EXT";
            case BINARY_EXT -> "BINARY_EXT";
            case BIT_BINARY_EXT -> "BIT_BINARY_EXT";
            case STRING_EXT -> "STRING_EXT";
            case LIST_EXT -> "LIST_EXT";
            case NIL_EXT -> "NIL_EXT";
            case SMALL_TUPLE_EXT -> "SMALL_TUPLE_EXT";
            case LARGE_TUPLE_EXT -> "LARGE_TUPLE_EXT";
            case MAP_EXT -> "MAP_EXT";
            case PID_EXT -> "PID_EXT";
            case NEW_PID_EXT -> "NEW_PID_EXT";
            case PORT_EXT -> "PORT_EXT";
            case NEW_PORT_EXT -> "NEW_PORT_EXT";
            case REFERENCE_EXT -> "REFERENCE_EXT";
            case NEW_REFERENCE_EXT -> "NEW_REFERENCE_EXT";
            case NEWER_REFERENCE_EXT -> "NEWER_REFERENCE_EXT";
            case FUN_EXT -> "FUN_EXT";
            case NEW_FUN_EXT -> "NEW_FUN_EXT";
            case EXPORT_EXT -> "EXPORT_EXT";
            case COMPRESSED -> "COMPRESSED";
            default -> "UNKNOWN(" + tag + ")";
        };
    }
}
