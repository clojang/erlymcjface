package io.github.clojang.mcjface.etf.term;

import java.util.Optional;

public sealed interface Term permits Atom, Number, Binary, List, Tuple, Map, Pid, Port, Reference {

  byte[] encode();

  default <T> Optional<T> match(Pattern<T> pattern) {
    return pattern.match(this);
  }

  interface Pattern<T> {
    Optional<T> match(Term term);
  }
}
