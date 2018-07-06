package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.MacDerivationFunction;
import org.spongycastle.crypto.params.KDFFeedbackParameters;
import org.spongycastle.crypto.params.KeyParameter;

public class KDFFeedbackBytesGenerator implements MacDerivationFunction {
    private static final BigInteger a = BigInteger.valueOf(2147483647L);
    private static final BigInteger b = BigInteger.valueOf(2);
    private final Mac c;
    private final int d;
    private byte[] e;
    private int f;
    private byte[] g;
    private byte[] h;
    private boolean i;
    private int j;
    private byte[] k;

    public void a(DerivationParameters derivationParameters) {
        int i = Integer.MAX_VALUE;
        if (derivationParameters instanceof KDFFeedbackParameters) {
            KDFFeedbackParameters kDFFeedbackParameters = (KDFFeedbackParameters) derivationParameters;
            this.c.a(new KeyParameter(kDFFeedbackParameters.a()));
            this.e = kDFFeedbackParameters.e();
            int d = kDFFeedbackParameters.d();
            this.g = new byte[(d / 8)];
            if (kDFFeedbackParameters.c()) {
                BigInteger multiply = b.pow(d).multiply(BigInteger.valueOf((long) this.d));
                if (multiply.compareTo(a) != 1) {
                    i = multiply.intValue();
                }
                this.f = i;
            } else {
                this.f = Integer.MAX_VALUE;
            }
            this.h = kDFFeedbackParameters.b();
            this.i = kDFFeedbackParameters.c();
            this.j = 0;
            return;
        }
        throw new IllegalArgumentException("Wrong type of arguments given");
    }

    public int a(byte[] bArr, int i, int i2) {
        int i3 = this.j + i2;
        if (i3 < 0 || i3 >= this.f) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.f + " bytes");
        }
        if (this.j % this.d == 0) {
            a();
        }
        i3 = this.j % this.d;
        int min = Math.min(this.d - (this.j % this.d), i2);
        System.arraycopy(this.k, i3, bArr, i, min);
        this.j += min;
        i3 = i2 - min;
        min += i;
        while (i3 > 0) {
            a();
            int min2 = Math.min(this.d, i3);
            System.arraycopy(this.k, 0, bArr, min, min2);
            this.j += min2;
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
        r0 = r5.j;
        if (r0 != 0) goto L_0x0028;
    L_0x0005:
        r0 = r5.c;
        r1 = r5.h;
        r2 = r5.h;
        r2 = r2.length;
        r0.a(r1, r4, r2);
    L_0x000f:
        r0 = r5.i;
        if (r0 == 0) goto L_0x0066;
    L_0x0013:
        r0 = r5.j;
        r1 = r5.d;
        r0 = r0 / r1;
        r0 = r0 + 1;
        r1 = r5.g;
        r1 = r1.length;
        switch(r1) {
            case 1: goto L_0x0052;
            case 2: goto L_0x0046;
            case 3: goto L_0x003a;
            case 4: goto L_0x0033;
            default: goto L_0x0020;
        };
    L_0x0020:
        r0 = new java.lang.IllegalStateException;
        r1 = "Unsupported size of counter i";
        r0.<init>(r1);
        throw r0;
    L_0x0028:
        r0 = r5.c;
        r1 = r5.k;
        r2 = r5.k;
        r2 = r2.length;
        r0.a(r1, r4, r2);
        goto L_0x000f;
    L_0x0033:
        r1 = r5.g;
        r2 = r0 >>> 24;
        r2 = (byte) r2;
        r1[r4] = r2;
    L_0x003a:
        r1 = r5.g;
        r2 = r5.g;
        r2 = r2.length;
        r2 = r2 + -3;
        r3 = r0 >>> 16;
        r3 = (byte) r3;
        r1[r2] = r3;
    L_0x0046:
        r1 = r5.g;
        r2 = r5.g;
        r2 = r2.length;
        r2 = r2 + -2;
        r3 = r0 >>> 8;
        r3 = (byte) r3;
        r1[r2] = r3;
    L_0x0052:
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
    L_0x0066:
        r0 = r5.c;
        r1 = r5.e;
        r2 = r5.e;
        r2 = r2.length;
        r0.a(r1, r4, r2);
        r0 = r5.c;
        r1 = r5.k;
        r0.a(r1, r4);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.generators.KDFFeedbackBytesGenerator.a():void");
    }
}
