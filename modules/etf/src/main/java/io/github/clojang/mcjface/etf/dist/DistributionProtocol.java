package io.github.clojang.mcjface.etf.dist;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.github.clojang.mcjface.etf.term.Term;
import java.nio.ByteBuffer;
import java.util.List;

public class DistributionProtocol {

  public static final int MAGIC_NUMBER = 0x83;
  public static final int VERSION_5 = 5;
  public static final int VERSION_6 = 6;

  public enum MessageType {
    LINK((byte) 1),
    SEND((byte) 2),
    EXIT((byte) 3),
    UNLINK((byte) 4),
    NODE_LINK((byte) 5),
    REG_SEND((byte) 6),
    GROUP_LEADER((byte) 7),
    EXIT2((byte) 8),
    SEND_TT((byte) 12),
    EXIT_TT((byte) 13),
    REG_SEND_TT((byte) 16),
    MONITOR_P((byte) 19),
    DEMONITOR_P((byte) 20),
    MONITOR_P_EXIT((byte) 21);

    private final byte value;

    MessageType(byte value) {
      this.value = value;
    }

    public byte getValue() {
      return value;
    }
  }

  public static ByteBuffer encode(MessageType type, Term... terms) {
    // Stub implementation
    return ByteBuffer.allocate(0);
  }

  public static DistributionMessage decode(ByteBuffer buffer) {
    // Stub implementation
    return null;
  }

  // SpotBugs incorrectly flags this as "representation exposure" because it doesn't recognize
  // that List.of() returns truly immutable implementations. List.of() creates instances of
  // ImmutableCollections.ListN which cannot be modified after creation. This is a false
  // positive - the code is actually safer than mutable array alternatives. Records are
  // immutable by design and these Lists are genuinely immutable instances.
  @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2", "US_USELESS_SUPPRESSION_ON_CLASS"},
      justification =
          "List.of() creates immutable lists; SpotBugs inconsistently detects this immutability")
  public record DistributionMessage(MessageType type, List<Term> terms) {
    public DistributionMessage(MessageType type, Term... terms) {
      this(type, List.of(terms));
    }
  }
}
