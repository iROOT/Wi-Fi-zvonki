package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.MacDerivationFunction;
import org.spongycastle.crypto.params.KDFCounterParameters;
import org.spongycastle.crypto.params.KeyParameter;

public class KDFCounterBytesGenerator implements MacDerivationFunction {
    private static final BigInteger a = BigInteger.valueOf(2147483647L);
    private static final BigInteger b = BigInteger.valueOf(2);
    private final Mac c;
    private final int d;
    private byte[] e;
    private int f;
    private byte[] g;
    private int h;
    private byte[] i;

    public void a(DerivationParameters derivationParameters) {
        if (derivationParameters instanceof KDFCounterParameters) {
            KDFCounterParameters kDFCounterParameters = (KDFCounterParameters) derivationParameters;
            this.c.a(new KeyParameter(kDFCounterParameters.a()));
            this.e = kDFCounterParameters.b();
            int c = kDFCounterParameters.c();
            this.g = new byte[(c / 8)];
            BigInteger multiply = b.pow(c).multiply(BigInteger.valueOf((long) this.d));
            this.f = multiply.compareTo(a) == 1 ? Integer.MAX_VALUE : multiply.intValue();
            this.h = 0;
            return;
        }
        throw new IllegalArgumentException("Wrong type of arguments given");
    }

    public int a(byte[] bArr, int i, int i2) {
        int i3 = this.h + i2;
        if (i3 < 0 || i3 >= this.f) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.f + " bytes");
        }
        if (this.h % this.d == 0) {
            a();
        }
        i3 = this.h % this.d;
        int min = Math.min(this.d - (this.h % this.d), i2);
        System.arraycopy(this.i, i3, bArr, i, min);
        this.h += min;
        i3 = i2 - min;
        min += i;
        while (i3 > 0) {
            a();
            int min2 = Math.min(this.d, i3);
            System.arraycopy(this.i, 0, bArr, min, min2);
            this.h += min2;
            i3 -= min2;
            min += min2;
        }
        return i2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a() {
        /*
        r5 = this;
        r4 = 0;
        r0 = r5.h;
        r1 = r5.d;
        r0 = r0 / r1;
        r0 = r0 + 1;
        r1 = r5.g;
        r1 = r1.length;
        switch(r1) {
            case 1: goto L_0x0035;
            case 2: goto L_0x0029;
            case 3: goto L_0x001d;
            case 4: goto L_0x0016;
            default: goto L_0x000e;
        };
    L_0x000e:
        r0 = new java.lang.IllegalStateException;
        r1 = "Unsupported size of counter i";
        r0.<init>(r1);
        throw r0;
    L_0x0016:
        r1 = r5.g;
        r2 = r0 >>> 24;
        r2 = (byte) r2;
        r1[r4] = r2;
    L_0x001d:
        r1 = r5.g;
        r2 = r5.g;
        r2 = r2.length;
        r2 = r2 + -3;
        r3 = r0 >>> 16;
        r3 = (byte) r3;
        r1[r2] = r3;
    L_0x0029:
        r1 = r5.g;
        r2 = r5.g;
        r2 = r2.length;
        r2 = r2 + -2;
        r3 = r0 >>> 8;
        r3 = (byte) r3;
        r1[r2] = r3;
    L_0x0035:
        r1 = r5.g;
        r2 = r5.g;
        r2 = r2.length;
        r2 = r2 + -1;
        r0 = (byte) r0;
        r1[r2] = r0;
        r0 = r5.c;
        r1 = r5.g;
        r2 = r5.g;
        r2 = r2.length;
        r0.a(r1, r4, r2);
        r0 = r5.c;
        r1 = r5.e;
        r2 = r5.e;
        r2 = r2.length;
        r0.a(r1, r4, r2);
        r0 = r5.c;
        r1 = r5.i;
        r0.a(r1, r4);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.generators.KDFCounterBytesGenerator.a():void");
    }
}
