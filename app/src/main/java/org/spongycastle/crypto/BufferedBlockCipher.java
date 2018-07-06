package org.spongycastle.crypto;

public class BufferedBlockCipher {
    protected byte[] a;
    protected int b;
    protected boolean c;
    protected BlockCipher d;
    protected boolean e;
    protected boolean f;

    protected BufferedBlockCipher() {
    }

    public BufferedBlockCipher(BlockCipher blockCipher) {
        boolean z = true;
        this.d = blockCipher;
        this.a = new byte[blockCipher.b()];
        this.b = 0;
        String a = blockCipher.a();
        int indexOf = a.indexOf(47) + 1;
        boolean z2 = indexOf > 0 && a.startsWith("PGP", indexOf);
        this.f = z2;
        if (this.f) {
            this.e = true;
            return;
        }
        if (indexOf <= 0 || !(a.startsWith("CFB", indexOf) || a.startsWith("GCFB", indexOf) || a.startsWith("OFB", indexOf) || a.startsWith("OpenPGP", indexOf) || a.startsWith("SIC", indexOf) || a.startsWith("GCTR", indexOf))) {
            z = false;
        }
        this.e = z;
    }

    public BlockCipher a() {
        return this.d;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        this.c = z;
        c();
        this.d.a(z, cipherParameters);
    }

    public int b() {
        return this.d.b();
    }

    public int a(int i) {
        int length;
        int i2 = i + this.b;
        if (this.f) {
            length = (i2 % this.a.length) - (this.d.b() + 2);
        } else {
            length = i2 % this.a.length;
        }
        return i2 - length;
    }

    public int b(int i) {
        return this.b + i;
    }

    public int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int b = b();
        int a = a(i2);
        if (a <= 0 || a + i3 <= bArr2.length) {
            int i4;
            int length = this.a.length - this.b;
            if (i2 > length) {
                System.arraycopy(bArr, i, this.a, this.b, length);
                a = this.d.a(this.a, 0, bArr2, i3) + 0;
                this.b = 0;
                i4 = i2 - length;
                length += i;
                while (i4 > this.a.length) {
                    a += this.d.a(bArr, length, bArr2, i3 + a);
                    i4 -= b;
                    length += b;
                }
            } else {
                a = 0;
                i4 = i2;
                length = i;
            }
            System.arraycopy(bArr, length, this.a, this.b, i4);
            this.b = i4 + this.b;
            if (this.b != this.a.length) {
                return a;
            }
            a += this.d.a(this.a, 0, bArr2, i3 + a);
            this.b = 0;
            return a;
        }
        throw new OutputLengthException("output buffer too short");
    }

    public int a(byte[] bArr, int i) {
        int i2 = 0;
        try {
            if (this.b + i > bArr.length) {
                throw new OutputLengthException("output buffer too short for doFinal()");
            }
            if (this.b != 0) {
                if (this.e) {
                    this.d.a(this.a, 0, this.a, 0);
                    i2 = this.b;
                    this.b = 0;
                    System.arraycopy(this.a, 0, bArr, i, i2);
                } else {
                    throw new DataLengthException("data not block size aligned");
                }
            }
            c();
            return i2;
        } catch (Throwable th) {
            c();
        }
    }

    public void c() {
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = (byte) 0;
        }
        this.b = 0;
        this.d.c();
    }
}
