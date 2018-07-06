package org.spongycastle.crypto;

public class BufferedAsymmetricBlockCipher {
    protected byte[] a;
    protected int b;
    private final AsymmetricBlockCipher c;

    public BufferedAsymmetricBlockCipher(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.c = asymmetricBlockCipher;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        int i;
        d();
        this.c.a(z, cipherParameters);
        int a = this.c.a();
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        this.a = new byte[(i + a)];
        this.b = 0;
    }

    public int a() {
        return this.c.a();
    }

    public int b() {
        return this.c.b();
    }

    public void a(byte[] bArr, int i, int i2) {
        if (i2 != 0) {
            if (i2 < 0) {
                throw new IllegalArgumentException("Can't have a negative input length!");
            } else if (this.b + i2 > this.a.length) {
                throw new DataLengthException("attempt to process message too long for cipher");
            } else {
                System.arraycopy(bArr, i, this.a, this.b, i2);
                this.b += i2;
            }
        }
    }

    public byte[] c() {
        byte[] a = this.c.a(this.a, 0, this.b);
        d();
        return a;
    }

    public void d() {
        if (this.a != null) {
            for (int i = 0; i < this.a.length; i++) {
                this.a[i] = (byte) 0;
            }
        }
        this.b = 0;
    }
}
