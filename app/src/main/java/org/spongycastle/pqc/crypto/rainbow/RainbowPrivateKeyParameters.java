package org.spongycastle.pqc.crypto.rainbow;

public class RainbowPrivateKeyParameters extends RainbowKeyParameters {
    private short[][] b;
    private short[] c;
    private short[][] d;
    private short[] e;
    private int[] f;
    private Layer[] g;

    public RainbowPrivateKeyParameters(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, Layer[] layerArr) {
        super(true, iArr[iArr.length - 1] - iArr[0]);
        this.b = sArr;
        this.c = sArr2;
        this.d = sArr3;
        this.e = sArr4;
        this.f = iArr;
        this.g = layerArr;
    }

    public short[] c() {
        return this.c;
    }

    public short[][] d() {
        return this.b;
    }

    public short[] e() {
        return this.e;
    }

    public short[][] f() {
        return this.d;
    }

    public Layer[] g() {
        return this.g;
    }

    public int[] h() {
        return this.f;
    }
}
