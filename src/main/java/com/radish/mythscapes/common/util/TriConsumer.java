package com.radish.mythscapes.common.util;

import java.util.Objects;

// Will these big boi consumers ever be used?
// Dunno, but they might come in handy some day.

@FunctionalInterface
public interface TriConsumer<A, B, C> {

    void accept(A a, B b, C c);

    default TriConsumer<A, B, C> andThen(TriConsumer<A, B, C> after) {
        Objects.requireNonNull(after);
        return (A a, B b, C c) -> {
            accept(a, b, c);
            after.accept(a, b, c);
        };
    }
}
