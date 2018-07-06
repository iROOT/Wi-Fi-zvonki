package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;
import org.spongycastle.pqc.crypto.rainbow.Layer;

public class RainbowPrivateKeySpec implements KeySpec {
    private short[][] a;
    private short[] b;
    private short[][] c;
    private short[] d;
    private int[] e;
    private Layer[] f;

    public RainbowPrivateKeySpec(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, Layer[] layerArr) {
        this.a = sArr;
        this.b = sArr2;
        this.c = sArr3;
        this.d = sArr4;
        this.e = iArr;
        this.f = layerArr;
    }

    public short[] a() {
        return this.b;
    }

    public short[][] b() {
        return this.a;
    }

    public short[] c() {
        return this.d;
    }

    public short[][] d() {
        return this.c;
    }

    public Layer[] e() {
        return this.f;
    }

    public int[] f() {
        return this.e;
    }
}
