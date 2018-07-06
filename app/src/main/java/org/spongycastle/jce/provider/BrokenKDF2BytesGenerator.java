package org.spongycastle.jce.provider;

import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.KDFParameters;

public class BrokenKDF2BytesGenerator implements DerivationFunction {
    private Digest a;
    private byte[] b;
    private byte[] c;

    public void a(DerivationParameters derivationParameters) {
        if (derivationParameters instanceof KDFParameters) {
            KDFParameters kDFParameters = (KDFParameters) derivationParameters;
            this.b = kDFParameters.a();
            this.c = kDFParameters.b();
            return;
        }
        throw new IllegalArgumentException("KDF parameters required for KDF2Generator");
    }

    public int a(byte[] bArr, int i, int i2) {
        if (bArr.length - i2 < i) {
            throw new DataLengthException("output buffer too small");
        }
        long j = (long) (i2 * 8);
        if (j > ((long) (this.a.b() * 8)) * 29) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Output length to large");
        }
        int b = (int) (j / ((long) this.a.b()));
        Object obj = new byte[this.a.b()];
        int i3 = i;
        for (int i4 = 1; i4 <= b; i4++) {
            this.a.a(this.b, 0, this.b.length);
            this.a.a((byte) (i4 & 255));
            this.a.a((byte) ((i4 >> 8) & 255));
            this.a.a((byte) ((i4 >> 16) & 255));
            this.a.a((byte) ((i4 >> 24) & 255));
            this.a.a(this.c, 0, this.c.length);
            this.a.a(obj, 0);
            if (i2 - i3 > obj.length) {
                System.arraycopy(obj, 0, bArr, i3, obj.length);
                i3 += obj.length;
            } else {
                System.arraycopy(obj, 0, bArr, i3, i2 - i3);
            }
        }
        this.a.c();
        return i2;
    }
}
