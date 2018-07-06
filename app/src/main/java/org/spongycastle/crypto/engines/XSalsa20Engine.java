package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.util.Pack;

public class XSalsa20Engine extends Salsa20Engine {
    public String a() {
        return "XSalsa20";
    }

    protected int e() {
        return 24;
    }

    protected void a(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException(a() + " requires a 256 bit key");
        }
        super.a(bArr, bArr2);
        this.d[8] = Pack.c(bArr2, 8);
        this.d[9] = Pack.c(bArr2, 12);
        int[] iArr = new int[this.d.length];
        Salsa20Engine.b(20, this.d, iArr);
        this.d[1] = iArr[0] - this.d[0];
        this.d[2] = iArr[5] - this.d[5];
        this.d[3] = iArr[10] - this.d[10];
        this.d[4] = iArr[15] - this.d[15];
        this.d[11] = iArr[6] - this.d[6];
        this.d[12] = iArr[7] - this.d[7];
        this.d[13] = iArr[8] - this.d[8];
        this.d[14] = iArr[9] - this.d[9];
        this.d[6] = Pack.c(bArr2, 16);
        this.d[7] = Pack.c(bArr2, 20);
        d();
    }
}
