package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.ParametersWithIV;

public class GOFBBlockCipher implements BlockCipher {
    boolean a = true;
    int b;
    int c;
    private byte[] d;
    private byte[] e;
    private byte[] f;
    private final int g;
    private final BlockCipher h;

    public GOFBBlockCipher(BlockCipher blockCipher) {
        this.h = blockCipher;
        this.g = blockCipher.b();
        if (this.g != 8) {
            throw new IllegalArgumentException("GCTR only for 64 bit block ciphers");
        }
        this.d = new byte[blockCipher.b()];
        this.e = new byte[blockCipher.b()];
        this.f = new byte[blockCipher.b()];
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        this.a = true;
        this.b = 0;
        this.c = 0;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            Object a = parametersWithIV.a();
            if (a.length < this.d.length) {
                System.arraycopy(a, 0, this.d, this.d.length - a.length, a.length);
                for (int i = 0; i < this.d.length - a.length; i++) {
                    this.d[i] = (byte) 0;
                }
            } else {
                System.arraycopy(a, 0, this.d, 0, this.d.length);
            }
            c();
            if (parametersWithIV.b() != null) {
                this.h.a(true, parametersWithIV.b());
                return;
            }
            return;
        }
        c();
        if (cipherParameters != null) {
            this.h.a(true, cipherParameters);
        }
    }

    public String a() {
        return this.h.a() + "/GCTR";
    }

    public int b() {
        return this.g;
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.g + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.g + i2 > bArr2.length) {
            throw new DataLengthException("output buffer too short");
        } else {
            if (this.a) {
                this.a = false;
                this.h.a(this.e, 0, this.f, 0);
                this.b = a(this.f, 0);
                this.c = a(this.f, 4);
            }
            this.b += 16843009;
            this.c += 16843012;
            a(this.b, this.e, 0);
            a(this.c, this.e, 4);
            this.h.a(this.e, 0, this.f, 0);
            for (int i3 = 0; i3 < this.g; i3++) {
                bArr2[i2 + i3] = (byte) (this.f[i3] ^ bArr[i + i3]);
            }
            System.arraycopy(this.e, this.g, this.e, 0, this.e.length - this.g);
            System.arraycopy(this.f, 0, this.e, this.e.length - this.g, this.g);
            return this.g;
        }
    }

    public void c() {
        this.a = true;
        this.b = 0;
        this.c = 0;
        System.arraycopy(this.d, 0, this.e, 0, this.d.length);
        this.h.c();
    }

    private int a(byte[] bArr, int i) {
        return ((((bArr[i + 3] << 24) & -16777216) + ((bArr[i + 2] << 16) & 16711680)) + ((bArr[i + 1] << 8) & 65280)) + (bArr[i] & 255);
    }

    private void a(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >>> 24);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2] = (byte) i;
    }
}
