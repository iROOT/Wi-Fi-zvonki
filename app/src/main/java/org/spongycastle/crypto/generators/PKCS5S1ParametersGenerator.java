package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class PKCS5S1ParametersGenerator extends PBEParametersGenerator {
    private Digest d;

    public PKCS5S1ParametersGenerator(Digest digest) {
        this.d = digest;
    }

    private byte[] a() {
        byte[] bArr = new byte[this.d.b()];
        this.d.a(this.a, 0, this.a.length);
        this.d.a(this.b, 0, this.b.length);
        this.d.a(bArr, 0);
        for (int i = 1; i < this.c; i++) {
            this.d.a(bArr, 0, bArr.length);
            this.d.a(bArr, 0);
        }
        return bArr;
    }

    public CipherParameters a(int i) {
        int i2 = i / 8;
        if (i2 <= this.d.b()) {
            return new KeyParameter(a(), 0, i2);
        }
        throw new IllegalArgumentException("Can't generate a derived key " + i2 + " bytes long.");
    }

    public CipherParameters a(int i, int i2) {
        int i3 = i / 8;
        int i4 = i2 / 8;
        if (i3 + i4 > this.d.b()) {
            throw new IllegalArgumentException("Can't generate a derived key " + (i3 + i4) + " bytes long.");
        }
        byte[] a = a();
        return new ParametersWithIV(new KeyParameter(a, 0, i3), a, i3, i4);
    }

    public CipherParameters b(int i) {
        return a(i);
    }
}
