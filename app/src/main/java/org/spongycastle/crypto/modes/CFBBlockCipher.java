package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

public class CFBBlockCipher implements BlockCipher {
    private byte[] a;
    private byte[] b;
    private byte[] c;
    private int d;
    private BlockCipher e = null;
    private boolean f;

    public CFBBlockCipher(BlockCipher blockCipher, int i) {
        this.e = blockCipher;
        this.d = i / 8;
        this.a = new byte[blockCipher.b()];
        this.b = new byte[blockCipher.b()];
        this.c = new byte[blockCipher.b()];
    }

    public BlockCipher d() {
        return this.e;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        this.f = z;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            Object a = parametersWithIV.a();
            if (a.length < this.a.length) {
                System.arraycopy(a, 0, this.a, this.a.length - a.length, a.length);
                for (int i = 0; i < this.a.length - a.length; i++) {
                    this.a[i] = (byte) 0;
                }
            } else {
                System.arraycopy(a, 0, this.a, 0, this.a.length);
            }
            c();
            if (parametersWithIV.b() != null) {
                this.e.a(true, parametersWithIV.b());
                return;
            }
            return;
        }
        c();
        if (cipherParameters != null) {
            this.e.a(true, cipherParameters);
        }
    }

    public String a() {
        return this.e.a() + "/CFB" + (this.d * 8);
    }

    public int b() {
        return this.d;
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.f ? b(bArr, i, bArr2, i2) : c(bArr, i, bArr2, i2);
    }

    public int b(byte[] bArr, int i, byte[] bArr2, int i2) {
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

    public int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        if (this.d + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.d + i2 > bArr2.length) {
            throw new DataLengthException("output buffer too short");
        } else {
            this.e.a(this.b, 0, this.c, 0);
            System.arraycopy(this.b, this.d, this.b, 0, this.b.length - this.d);
            System.arraycopy(bArr, i, this.b, this.b.length - this.d, this.d);
            while (i3 < this.d) {
                bArr2[i2 + i3] = (byte) (this.c[i3] ^ bArr[i + i3]);
                i3++;
            }
            return this.d;
        }
    }

    public byte[] e() {
        return Arrays.b(this.b);
    }

    public void c() {
        System.arraycopy(this.a, 0, this.b, 0, this.a.length);
        this.e.c();
    }
}
