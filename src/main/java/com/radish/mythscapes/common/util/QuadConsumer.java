package com.radish.mythscapes.common.util;

import java.util.Objects;

@FunctionalInterface
public interface QuadConsumer<A, B, C, D> {

    void accept(A a, B b, C c, D d);

    default QuadConsumer<A, B, C, D> andThen(QuadConsumer<A, B, C, D> after) {
        Objects.requireNonNull(after);
        return (A a, B b, C c, D d) -> {
            accept(a, b, c, d);
            after.accept(a, b, c, d);
        };
    }
}
