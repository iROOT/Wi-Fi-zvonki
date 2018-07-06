package com.b.a.b.a;

public class b {
    private final a a;
    private final Throwable b;

    public enum a {
        IO_ERROR,
        DECODING_ERROR,
        NETWORK_DENIED,
        OUT_OF_MEMORY,
        UNKNOWN
    }

    public b(a aVar, Throwable th) {
        this.a = aVar;
        this.b = th;
    }
}
