package io.github.clojang.mcjface.etf.dist;

import io.github.clojang.mcjface.etf.term.Term;
import java.nio.ByteBuffer;
import java.util.ArrayList;
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

  public record DistributionMessage(MessageType type, List<Term> terms) {
    public DistributionMessage {
      terms = new ArrayList<>(terms);
    }

    public DistributionMessage(MessageType type, Term... terms) {
      this(type, List.of(terms));
    }

    @Override
    public List<Term> terms() {
      return new ArrayList<>(terms);
    }
  }
}
