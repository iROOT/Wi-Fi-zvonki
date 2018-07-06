package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class Grainv1Engine implements StreamCipher {
    private byte[] a;
    private byte[] b;
    private byte[] c;
    private int[] d;
    private int[] e;
    private int f;
    private int g = 2;
    private boolean h = false;

    public String a() {
        return "Grain v1";
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            Object a = parametersWithIV.a();
            if (a == null || a.length != 8) {
                throw new IllegalArgumentException("Grain v1 requires exactly 8 bytes of IV");
            } else if (parametersWithIV.b() instanceof KeyParameter) {
                KeyParameter keyParameter = (KeyParameter) parametersWithIV.b();
                this.b = new byte[keyParameter.a().length];
                this.a = new byte[keyParameter.a().length];
                this.d = new int[5];
                this.e = new int[5];
                this.c = new byte[2];
                System.arraycopy(a, 0, this.b, 0, a.length);
                System.arraycopy(keyParameter.a(), 0, this.a, 0, keyParameter.a().length);
                b();
                return;
            } else {
                throw new IllegalArgumentException("Grain v1 Init parameters must include a key");
            }
        }
        throw new IllegalArgumentException("Grain v1 Init parameters must include an IV");
    }

    private void c() {
        for (int i = 0; i < 10; i++) {
            this.f = f();
            this.e = a(this.e, (d() ^ this.d[0]) ^ this.f);
            this.d = a(this.d, e() ^ this.f);
        }
        this.h = true;
    }

    private int d() {
        int i = (this.e[0] >>> 9) | (this.e[1] << 7);
        int i2 = (this.e[0] >>> 15) | (this.e[1] << 1);
        int i3 = (this.e[1] >>> 5) | (this.e[2] << 11);
        int i4 = (this.e[1] >>> 12) | (this.e[2] << 4);
        int i5 = (this.e[2] >>> 1) | (this.e[3] << 15);
        int i6 = (this.e[2] >>> 5) | (this.e[3] << 11);
        int i7 = (this.e[2] >>> 13) | (this.e[3] << 3);
        int i8 = (this.e[3] >>> 4) | (this.e[4] << 12);
        int i9 = (this.e[3] >>> 12) | (this.e[4] << 4);
        int i10 = (this.e[3] >>> 15) | (this.e[4] << 1);
        return ((((((((((((this.e[0] ^ ((((this.e[0] >>> 14) | (this.e[1] << 2)) ^ (((((((((this.e[3] >>> 14) | (this.e[4] << 2)) ^ i9) ^ i8) ^ i7) ^ i6) ^ i5) ^ i4) ^ i3)) ^ i)) ^ (i10 & i9)) ^ (i6 & i5)) ^ (i2 & i)) ^ ((i9 & i8) & i7)) ^ ((i5 & i4) & i3)) ^ (((i10 & i7) & i4) & i)) ^ (((i9 & i8) & i6) & i5)) ^ (((i10 & i9) & i3) & i2)) ^ ((((i10 & i9) & i8) & i7) & i6)) ^ (i & (((i5 & i4) & i3) & i2))) ^ (((((i8 & i7) & i6) & i5) & i4) & i3)) & 65535;
    }

    private int e() {
        int i = (this.d[1] >>> 7) | (this.d[2] << 9);
        int i2 = (this.d[2] >>> 6) | (this.d[3] << 10);
        int i3 = (this.d[3] >>> 3) | (this.d[4] << 13);
        return (((((this.d[0] ^ ((this.d[0] >>> 13) | (this.d[1] << 3))) ^ i) ^ i2) ^ i3) ^ ((this.d[3] >>> 14) | (this.d[4] << 2))) & 65535;
    }

    private int f() {
        int i = (this.e[0] >>> 1) | (this.e[1] << 15);
        int i2 = (this.e[0] >>> 2) | (this.e[1] << 14);
        int i3 = (this.e[0] >>> 4) | (this.e[1] << 12);
        int i4 = (this.e[0] >>> 10) | (this.e[1] << 6);
        int i5 = (this.e[1] >>> 15) | (this.e[2] << 1);
        int i6 = (this.e[2] >>> 11) | (this.e[3] << 5);
        int i7 = (this.e[3] >>> 8) | (this.e[4] << 8);
        int i8 = (this.e[3] >>> 15) | (this.e[4] << 1);
        int i9 = (this.d[0] >>> 3) | (this.d[1] << 13);
        int i10 = (this.d[1] >>> 9) | (this.d[2] << 7);
        int i11 = (this.d[2] >>> 14) | (this.d[3] << 2);
        int i12 = this.d[4];
        return (((((((i ^ ((i8 & (i11 & i12)) ^ ((((i9 & i11) & i8) ^ ((((((i10 ^ i8) ^ (i9 & i12)) ^ (i11 & i12)) ^ (i12 & i8)) ^ ((i9 & i10) & i11)) ^ ((i9 & i11) & i12))) ^ ((i10 & i11) & i8)))) ^ i2) ^ i3) ^ i4) ^ i5) ^ i6) ^ i7) & 65535;
    }

    private int[] a(int[] iArr, int i) {
        iArr[0] = iArr[1];
        iArr[1] = iArr[2];
        iArr[2] = iArr[3];
        iArr[3] = iArr[4];
        iArr[4] = i;
        return iArr;
    }

    private void a(byte[] bArr, byte[] bArr2) {
        int i = 0;
        bArr2[8] = (byte) -1;
        bArr2[9] = (byte) -1;
        this.a = bArr;
        this.b = bArr2;
        int i2 = 0;
        while (i < this.e.length) {
            this.e[i] = ((this.a[i2 + 1] << 8) | (this.a[i2] & 255)) & 65535;
            this.d[i] = ((this.b[i2 + 1] << 8) | (this.b[i2] & 255)) & 65535;
            i2 += 2;
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
        this.g = 2;
        a(this.a, this.b);
        c();
    }

    private void g() {
        this.f = f();
        this.c[0] = (byte) this.f;
        this.c[1] = (byte) (this.f >> 8);
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
        if (this.g > 1) {
            g();
            this.g = 0;
        }
        byte[] bArr = this.c;
        int i = this.g;
        this.g = i + 1;
        return bArr[i];
    }
}
