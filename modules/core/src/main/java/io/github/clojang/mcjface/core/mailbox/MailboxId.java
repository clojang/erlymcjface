package io.github.clojang.mcjface.core.mailbox;

import java.util.UUID;

public record MailboxId(UUID id) {
    
    public static MailboxId generate() {
        return new MailboxId(UUID.randomUUID());
    }
    
    @Override
    public String toString() {
        return id.toString();
    }
}
