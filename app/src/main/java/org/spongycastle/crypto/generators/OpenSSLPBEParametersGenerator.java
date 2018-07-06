package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class OpenSSLPBEParametersGenerator extends PBEParametersGenerator {
    private Digest d = new MD5Digest();

    private byte[] c(int i) {
        Object obj = new byte[this.d.b()];
        Object obj2 = new byte[i];
        int i2 = 0;
        while (true) {
            this.d.a(this.a, 0, this.a.length);
            this.d.a(this.b, 0, this.b.length);
            this.d.a(obj, 0);
            int length = i > obj.length ? obj.length : i;
            System.arraycopy(obj, 0, obj2, i2, length);
            i2 += length;
            i -= length;
            if (i == 0) {
                return obj2;
            }
            this.d.c();
            this.d.a(obj, 0, obj.length);
        }
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
