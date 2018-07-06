package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;

public class BlockCipherMac implements Mac {
    private byte[] a;
    private byte[] b;
    private int c;
    private BlockCipher d;
    private int e;

    public String a() {
        return this.d.a();
    }

    public void a(CipherParameters cipherParameters) {
        c();
        this.d.a(true, cipherParameters);
    }

    public int b() {
        return this.e;
    }

    public void a(byte b) {
        if (this.c == this.b.length) {
            this.d.a(this.b, 0, this.a, 0);
            this.c = 0;
        }
        byte[] bArr = this.b;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = b;
    }

    public void a(byte[] bArr, int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int b = this.d.b();
        int i3 = b - this.c;
        if (i2 > i3) {
            System.arraycopy(bArr, i, this.b, this.c, i3);
            int a = this.d.a(this.b, 0, this.a, 0) + 0;
            this.c = 0;
            i2 -= i3;
            i += i3;
            while (i2 > b) {
                a += this.d.a(bArr, i, this.a, 0);
                i2 -= b;
                i += b;
            }
        }
        System.arraycopy(bArr, i, this.b, this.c, i2);
        this.c += i2;
    }

    public int a(byte[] bArr, int i) {
        int b = this.d.b();
        while (this.c < b) {
            this.b[this.c] = (byte) 0;
            this.c++;
        }
        this.d.a(this.b, 0, this.a, 0);
        System.arraycopy(this.a, 0, bArr, i, this.e);
        c();
        return this.e;
    }

    public void c() {
        for (int i = 0; i < this.b.length; i++) {
            this.b[i] = (byte) 0;
        }
        this.c = 0;
        this.d.c();
    }
}
