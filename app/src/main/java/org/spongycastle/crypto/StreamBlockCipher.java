package org.spongycastle.crypto;

public class StreamBlockCipher implements StreamCipher {
    private BlockCipher a;
    private byte[] b = new byte[1];

    public StreamBlockCipher(BlockCipher blockCipher) {
        if (blockCipher.b() != 1) {
            throw new IllegalArgumentException("block cipher block size != 1.");
        }
        this.a = blockCipher;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        this.a.a(z, cipherParameters);
    }

    public String a() {
        return this.a.a();
    }

    public byte a(byte b) {
        this.b[0] = b;
        this.a.a(this.b, 0, this.b, 0);
        return this.b[0];
    }

    public void a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i3 + i2 > bArr2.length) {
            throw new DataLengthException("output buffer too small in processBytes()");
        }
        for (int i4 = 0; i4 != i2; i4++) {
            this.a.a(bArr, i + i4, bArr2, i3 + i4);
        }
    }

    public void b() {
        this.a.c();
    }
}
