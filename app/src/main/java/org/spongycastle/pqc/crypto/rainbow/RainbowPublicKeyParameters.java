package org.spongycastle.pqc.crypto.rainbow;

public class RainbowPublicKeyParameters extends RainbowKeyParameters {
    private short[][] b;
    private short[][] c;
    private short[] d;

    public RainbowPublicKeyParameters(int i, short[][] sArr, short[][] sArr2, short[] sArr3) {
        super(false, i);
        this.b = sArr;
        this.c = sArr2;
        this.d = sArr3;
    }

    public short[][] c() {
        return this.b;
    }

    public short[][] d() {
        return this.c;
    }

    public short[] e() {
        return this.d;
    }
}
