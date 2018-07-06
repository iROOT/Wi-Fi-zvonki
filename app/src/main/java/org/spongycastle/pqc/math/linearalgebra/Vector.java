package org.spongycastle.pqc.math.linearalgebra;

public abstract class Vector {
    protected int a;

    public abstract Vector a(Vector vector);

    public final int e() {
        return this.a;
    }
}
