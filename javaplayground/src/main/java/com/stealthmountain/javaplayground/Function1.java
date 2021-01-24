package com.stealthmountain.javaplayground;

public interface Function1<
        T,
        R
        > {
    R apply(T t);

    static <T> Function1<
            T,
            T
            > identity() {
        return t -> t;
    }
}
