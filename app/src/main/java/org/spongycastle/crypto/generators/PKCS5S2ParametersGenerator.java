package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class PKCS5S2ParametersGenerator extends PBEParametersGenerator {
    private Mac d;
    private byte[] e;

    public PKCS5S2ParametersGenerator() {
        this(new SHA1Digest());
    }

    public PKCS5S2ParametersGenerator(Digest digest) {
        this.d = new HMac(digest);
        this.e = new byte[this.d.b()];
    }

    private void a(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2) {
        if (i == 0) {
            throw new IllegalArgumentException("iteration count must be at least 1.");
        }
        if (bArr != null) {
            this.d.a(bArr, 0, bArr.length);
        }
        this.d.a(bArr2, 0, bArr2.length);
        this.d.a(this.e, 0);
        System.arraycopy(this.e, 0, bArr3, i2, this.e.length);
        for (int i3 = 1; i3 < i; i3++) {
            this.d.a(this.e, 0, this.e.length);
            this.d.a(this.e, 0);
            for (int i4 = 0; i4 != this.e.length; i4++) {
                int i5 = i2 + i4;
                bArr3[i5] = (byte) (bArr3[i5] ^ this.e[i4]);
            }
        }
    }

    private byte[] c(int i) {
        int b = this.d.b();
        int i2 = ((i + b) - 1) / b;
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[(i2 * b)];
        int i3 = 0;
        this.d.a(new KeyParameter(this.a));
        for (int i4 = 1; i4 <= i2; i4++) {
            int i5 = 3;
            while (true) {
                byte b2 = (byte) (bArr[i5] + 1);
                bArr[i5] = b2;
                if (b2 != (byte) 0) {
                    break;
                }
                i5--;
            }
            a(this.b, this.c, bArr, bArr2, i3);
            i3 += b;
        }
        return bArr2;
    }

    public CipherParameters a(int i) {
        int i2 = i / 8;
        return new KeyParameter(c(i2), 0, i2);
    }

    public CipherParameters a(int i, int i2) {
        int i3 = i / 8;
        int i4 = i2 / 8;
        byte[] c = c(i3 + i4);
        return new ParametersWithIV(new KeyParameter(c, 0, i3), c, i3, i4);
    }

    public CipherParameters b(int i) {
        return a(i);
    }
}
