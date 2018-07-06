package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;

public class RainbowPublicKeySpec implements KeySpec {
    private short[][] a;
    private short[][] b;
    private short[] c;
    private int d;

    public RainbowPublicKeySpec(int i, short[][] sArr, short[][] sArr2, short[] sArr3) {
        this.d = i;
        this.a = sArr;
        this.b = sArr2;
        this.c = sArr3;
    }

    public int a() {
        return this.d;
    }

    public short[][] b() {
        return this.a;
    }

    public short[][] c() {
        return this.b;
    }

    public short[] d() {
        return this.c;
    }
}
