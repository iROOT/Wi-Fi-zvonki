package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.ParametersWithIV;

public class PGPCFBBlockCipher implements BlockCipher {
    private byte[] a = new byte[this.g];
    private byte[] b = new byte[this.g];
    private byte[] c = new byte[this.g];
    private byte[] d = new byte[this.g];
    private BlockCipher e;
    private int f;
    private int g;
    private boolean h;
    private boolean i;

    public PGPCFBBlockCipher(BlockCipher blockCipher, boolean z) {
        this.e = blockCipher;
        this.i = z;
        this.g = blockCipher.b();
    }

    public String a() {
        if (this.i) {
            return this.e.a() + "/PGPCFBwithIV";
        }
        return this.e.a() + "/PGPCFB";
    }

    public int b() {
        return this.e.b();
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.i ? this.h ? b(bArr, i, bArr2, i2) : c(bArr, i, bArr2, i2) : this.h ? d(bArr, i, bArr2, i2) : e(bArr, i, bArr2, i2);
    }

    public void c() {
        this.f = 0;
        for (int i = 0; i != this.b.length; i++) {
            if (this.i) {
                this.b[i] = (byte) 0;
            } else {
                this.b[i] = this.a[i];
            }
        }
        this.e.c();
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        this.h = z;
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
            this.e.a(true, parametersWithIV.b());
            return;
        }
        c();
        this.e.a(true, cipherParameters);
    }

    private byte a(byte b, int i) {
        return (byte) (this.c[i] ^ b);
    }

    private int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.g + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.f != 0) {
            if (this.f >= this.g + 2) {
                if (this.g + i2 > bArr2.length) {
                    throw new DataLengthException("output buffer too short");
                }
                this.e.a(this.b, 0, this.c, 0);
                for (r0 = 0; r0 < this.g; r0++) {
                    bArr2[i2 + r0] = a(bArr[i + r0], r0);
                }
                System.arraycopy(bArr2, i2, this.b, 0, this.g);
            }
            return this.g;
        } else if (((this.g * 2) + i2) + 2 > bArr2.length) {
            throw new DataLengthException("output buffer too short");
        } else {
            this.e.a(this.b, 0, this.c, 0);
            for (r0 = 0; r0 < this.g; r0++) {
                bArr2[i2 + r0] = a(this.a[r0], r0);
            }
            System.arraycopy(bArr2, i2, this.b, 0, this.g);
            this.e.a(this.b, 0, this.c, 0);
            bArr2[this.g + i2] = a(this.a[this.g - 2], 0);
            bArr2[(this.g + i2) + 1] = a(this.a[this.g - 1], 1);
            System.arraycopy(bArr2, i2 + 2, this.b, 0, this.g);
            this.e.a(this.b, 0, this.c, 0);
            for (r0 = 0; r0 < this.g; r0++) {
                bArr2[((this.g + i2) + 2) + r0] = a(bArr[i + r0], r0);
            }
            System.arraycopy(bArr2, (this.g + i2) + 2, this.b, 0, this.g);
            this.f += (this.g * 2) + 2;
            return (this.g * 2) + 2;
        }
    }

    private int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.g + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.g + i2 > bArr2.length) {
            throw new DataLengthException("output buffer too short");
        } else if (this.f == 0) {
            for (r0 = 0; r0 < this.g; r0++) {
                this.b[r0] = bArr[i + r0];
            }
            this.e.a(this.b, 0, this.c, 0);
            this.f += this.g;
            return 0;
        } else if (this.f == this.g) {
            System.arraycopy(bArr, i, this.d, 0, this.g);
            System.arraycopy(this.b, 2, this.b, 0, this.g - 2);
            this.b[this.g - 2] = this.d[0];
            this.b[this.g - 1] = this.d[1];
            this.e.a(this.b, 0, this.c, 0);
            for (r0 = 0; r0 < this.g - 2; r0++) {
                bArr2[i2 + r0] = a(this.d[r0 + 2], r0);
            }
            System.arraycopy(this.d, 2, this.b, 0, this.g - 2);
            this.f += 2;
            return this.g - 2;
        } else {
            if (this.f >= this.g + 2) {
                System.arraycopy(bArr, i, this.d, 0, this.g);
                bArr2[i2 + 0] = a(this.d[0], this.g - 2);
                bArr2[i2 + 1] = a(this.d[1], this.g - 1);
                System.arraycopy(this.d, 0, this.b, this.g - 2, 2);
                this.e.a(this.b, 0, this.c, 0);
                for (r0 = 0; r0 < this.g - 2; r0++) {
                    bArr2[(i2 + r0) + 2] = a(this.d[r0 + 2], r0);
                }
                System.arraycopy(this.d, 2, this.b, 0, this.g - 2);
            }
            return this.g;
        }
    }

    private int d(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        if (this.g + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.g + i2 > bArr2.length) {
            throw new DataLengthException("output buffer too short");
        } else {
            this.e.a(this.b, 0, this.c, 0);
            for (int i4 = 0; i4 < this.g; i4++) {
                bArr2[i2 + i4] = a(bArr[i + i4], i4);
            }
            while (i3 < this.g) {
                this.b[i3] = bArr2[i2 + i3];
                i3++;
            }
            return this.g;
        }
    }

    private int e(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        if (this.g + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.g + i2 > bArr2.length) {
            throw new DataLengthException("output buffer too short");
        } else {
            this.e.a(this.b, 0, this.c, 0);
            for (int i4 = 0; i4 < this.g; i4++) {
                bArr2[i2 + i4] = a(bArr[i + i4], i4);
            }
            while (i3 < this.g) {
                this.b[i3] = bArr[i + i3];
                i3++;
            }
            return this.g;
        }
    }
}
