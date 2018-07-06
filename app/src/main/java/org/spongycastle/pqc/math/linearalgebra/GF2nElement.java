package org.spongycastle.pqc.math.linearalgebra;

public abstract class GF2nElement implements GFElement {
    protected GF2nField a;
    protected int b;

    public abstract Object clone();
}
