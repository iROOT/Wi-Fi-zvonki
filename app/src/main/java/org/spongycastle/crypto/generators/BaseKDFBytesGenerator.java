package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.DigestDerivationFunction;
import org.spongycastle.crypto.params.ISO18033KDFParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.util.Pack;

public class BaseKDFBytesGenerator implements DigestDerivationFunction {
    private int a;
    private Digest b;
    private byte[] c;
    private byte[] d;

    protected BaseKDFBytesGenerator(int i, Digest digest) {
        this.a = i;
        this.b = digest;
    }

    public void a(DerivationParameters derivationParameters) {
        if (derivationParameters instanceof KDFParameters) {
            KDFParameters kDFParameters = (KDFParameters) derivationParameters;
            this.c = kDFParameters.a();
            this.d = kDFParameters.b();
        } else if (derivationParameters instanceof ISO18033KDFParameters) {
            this.c = ((ISO18033KDFParameters) derivationParameters).a();
            this.d = null;
        } else {
            throw new IllegalArgumentException("KDF parameters required for KDF2Generator");
        }
    }

    public int a(byte[] bArr, int i, int i2) {
        if (bArr.length - i2 < i) {
            throw new DataLengthException("output buffer too small");
        }
        long j = (long) i2;
        int b = this.b.b();
        if (j > 8589934591L) {
            throw new IllegalArgumentException("Output length too large");
        }
        int i3 = (int) (((((long) b) + j) - 1) / ((long) b));
        Object obj = new byte[this.b.b()];
        byte[] bArr2 = new byte[4];
        Pack.a(this.a, bArr2, 0);
        int i4 = this.a & -256;
        int i5 = i;
        int i6 = i2;
        for (int i7 = 0; i7 < i3; i7++) {
            this.b.a(this.c, 0, this.c.length);
            this.b.a(bArr2, 0, bArr2.length);
            if (this.d != null) {
                this.b.a(this.d, 0, this.d.length);
            }
            this.b.a(obj, 0);
            if (i6 > b) {
                System.arraycopy(obj, 0, bArr, i5, b);
                i5 += b;
                i6 -= b;
            } else {
                System.arraycopy(obj, 0, bArr, i5, i6);
            }
            byte b2 = (byte) (bArr2[3] + 1);
            bArr2[3] = b2;
            if (b2 == (byte) 0) {
                i4 += 256;
                Pack.a(i4, bArr2, 0);
            }
        }
        this.b.c();
        return (int) j;
    }
}
