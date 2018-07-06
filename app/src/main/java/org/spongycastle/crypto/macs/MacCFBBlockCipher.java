package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.ParametersWithIV;

class MacCFBBlockCipher {
    private byte[] a;
    private byte[] b;
    private byte[] c;
    private int d;
    private BlockCipher e = null;

    public MacCFBBlockCipher(BlockCipher blockCipher, int i) {
        this.e = blockCipher;
        this.d = i / 8;
        this.a = new byte[blockCipher.b()];
        this.b = new byte[blockCipher.b()];
        this.c = new byte[blockCipher.b()];
    }

    public void a(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            Object a = parametersWithIV.a();
            if (a.length < this.a.length) {
                System.arraycopy(a, 0, this.a, this.a.length - a.length, a.length);
            } else {
                System.arraycopy(a, 0, this.a, 0, this.a.length);
            }
            c();
            this.e.a(true, parametersWithIV.b());
            return;
        }
        c();
        this.e.a(true, cipherParameters);
    }

    public String a() {
        return this.e.a() + "/CFB" + (this.d * 8);
    }

    public int b() {
        return this.d;
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.d + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.d + i2 > bArr2.length) {
            throw new DataLengthException("output buffer too short");
        } else {
            this.e.a(this.b, 0, this.c, 0);
            for (int i3 = 0; i3 < this.d; i3++) {
                bArr2[i2 + i3] = (byte) (this.c[i3] ^ bArr[i + i3]);
            }
            System.arraycopy(this.b, this.d, this.b, 0, this.b.length - this.d);
            System.arraycopy(bArr2, i2, this.b, this.b.length - this.d, this.d);
            return this.d;
        }
    }

    public void c() {
        System.arraycopy(this.a, 0, this.b, 0, this.a.length);
        this.e.c();
    }

    void a(byte[] bArr) {
        this.e.a(this.b, 0, bArr, 0);
    }
}
