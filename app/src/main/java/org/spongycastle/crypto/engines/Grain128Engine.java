package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class Grain128Engine implements StreamCipher {
    private byte[] a;
    private byte[] b;
    private byte[] c;
    private int[] d;
    private int[] e;
    private int f;
    private int g = 4;
    private boolean h = false;

    public String a() {
        return "Grain-128";
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            Object a = parametersWithIV.a();
            if (a == null || a.length != 12) {
                throw new IllegalArgumentException("Grain-128  requires exactly 12 bytes of IV");
            } else if (parametersWithIV.b() instanceof KeyParameter) {
                KeyParameter keyParameter = (KeyParameter) parametersWithIV.b();
                this.b = new byte[keyParameter.a().length];
                this.a = new byte[keyParameter.a().length];
                this.d = new int[4];
                this.e = new int[4];
                this.c = new byte[4];
                System.arraycopy(a, 0, this.b, 0, a.length);
                System.arraycopy(keyParameter.a(), 0, this.a, 0, keyParameter.a().length);
                b();
                return;
            } else {
                throw new IllegalArgumentException("Grain-128 Init parameters must include a key");
            }
        }
        throw new IllegalArgumentException("Grain-128 Init parameters must include an IV");
    }

    private void c() {
        for (int i = 0; i < 8; i++) {
            this.f = f();
            this.e = a(this.e, (d() ^ this.d[0]) ^ this.f);
            this.d = a(this.d, e() ^ this.f);
        }
        this.h = true;
    }

    private int d() {
        int i = (this.e[1] >>> 24) | (this.e[2] << 8);
        int i2 = (this.e[2] >>> 27) | (this.e[3] << 5);
        return ((((((((((this.e[0] ^ ((this.e[0] >>> 26) | (this.e[1] << 6))) ^ i) ^ i2) ^ this.e[3]) ^ (((this.e[0] >>> 3) | (this.e[1] << 29)) & ((this.e[2] >>> 3) | (this.e[3] << 29)))) ^ (((this.e[0] >>> 11) | (this.e[1] << 21)) & ((this.e[0] >>> 13) | (this.e[1] << 19)))) ^ (((this.e[0] >>> 17) | (this.e[1] << 15)) & ((this.e[0] >>> 18) | (this.e[1] << 14)))) ^ (((this.e[0] >>> 27) | (this.e[1] << 5)) & ((this.e[1] >>> 27) | (this.e[2] << 5)))) ^ (((this.e[1] >>> 8) | (this.e[2] << 24)) & ((this.e[1] >>> 16) | (this.e[2] << 16)))) ^ (((this.e[1] >>> 29) | (this.e[2] << 3)) & ((this.e[2] >>> 1) | (this.e[3] << 31)))) ^ (((this.e[2] >>> 4) | (this.e[3] << 28)) & ((this.e[2] >>> 20) | (this.e[3] << 12)));
    }

    private int e() {
        int i = (this.d[1] >>> 6) | (this.d[2] << 26);
        int i2 = (this.d[2] >>> 6) | (this.d[3] << 26);
        int i3 = (this.d[2] >>> 17) | (this.d[3] << 15);
        return ((((this.d[0] ^ ((this.d[0] >>> 7) | (this.d[1] << 25))) ^ i) ^ i2) ^ i3) ^ this.d[3];
    }

    private int f() {
        int i = (this.e[0] >>> 2) | (this.e[1] << 30);
        int i2 = (this.e[0] >>> 12) | (this.e[1] << 20);
        int i3 = (this.e[0] >>> 15) | (this.e[1] << 17);
        int i4 = (this.e[1] >>> 4) | (this.e[2] << 28);
        int i5 = (this.e[1] >>> 13) | (this.e[2] << 19);
        int i6 = (this.e[2] >>> 31) | (this.e[3] << 1);
        return ((((((i ^ ((((i2 & i6) & ((this.d[2] >>> 31) | (this.d[3] << 1))) ^ ((((((this.d[0] >>> 8) | (this.d[1] << 24)) & i2) ^ (((this.d[0] >>> 13) | (this.d[1] << 19)) & ((this.d[0] >>> 20) | (this.d[1] << 12)))) ^ (i6 & ((this.d[1] >>> 10) | (this.d[2] << 22)))) ^ (((this.d[1] >>> 28) | (this.d[2] << 4)) & ((this.d[2] >>> 15) | (this.d[3] << 17))))) ^ ((this.d[2] >>> 29) | (this.d[3] << 3)))) ^ i3) ^ i4) ^ i5) ^ this.e[2]) ^ ((this.e[2] >>> 9) | (this.e[3] << 23))) ^ ((this.e[2] >>> 25) | (this.e[3] << 7));
    }

    private int[] a(int[] iArr, int i) {
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = i;
        return iArr;
    }

    private void a(byte[] bArr, byte[] bArr2) {
        int i = 0;
        bArr2[12] = (byte) -1;
        bArr2[13] = (byte) -1;
        bArr2[14] = (byte) -1;
        bArr2[15] = (byte) -1;
        this.a = bArr;
        this.b = bArr2;
        int i2 = 0;
        while (i < this.e.length) {
            this.e[i] = (((this.a[i2 + 3] << 24) | ((this.a[i2 + 2] << 16) & 16711680)) | ((this.a[i2 + 1] << 8) & 65280)) | (this.a[i2] & 255);
            this.d[i] = (((this.b[i2 + 3] << 24) | ((this.b[i2 + 2] << 16) & 16711680)) | ((this.b[i2 + 1] << 8) & 65280)) | (this.b[i2] & 255);
            i2 += 4;
            i++;
        }
    }

    public void a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (!this.h) {
            throw new IllegalStateException(a() + " not initialised");
        } else if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                bArr2[i3 + i4] = (byte) (bArr[i + i4] ^ h());
            }
        }
    }

    public void b() {
        this.g = 4;
        a(this.a, this.b);
        c();
    }

    private void g() {
        this.f = f();
        this.c[0] = (byte) this.f;
        this.c[1] = (byte) (this.f >> 8);
        this.c[2] = (byte) (this.f >> 16);
        this.c[3] = (byte) (this.f >> 24);
        this.e = a(this.e, d() ^ this.d[0]);
        this.d = a(this.d, e());
    }

    public byte a(byte b) {
        if (this.h) {
            return (byte) (h() ^ b);
        }
        throw new IllegalStateException(a() + " not initialised");
    }

    private byte h() {
        if (this.g > 3) {
            g();
            this.g = 0;
        }
        byte[] bArr = this.c;
        int i = this.g;
        this.g = i + 1;
        return bArr[i];
    }
}
