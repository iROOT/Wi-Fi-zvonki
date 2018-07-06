package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.paddings.ISO7816d4Padding;
import org.spongycastle.crypto.params.KeyParameter;

public class CMac implements Mac {
    private byte[] a;
    private byte[] b;
    private byte[] c;
    private int d;
    private BlockCipher e;
    private int f;
    private byte[] g;
    private byte[] h;
    private byte[] i;

    public CMac(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.b() * 8);
    }

    public CMac(BlockCipher blockCipher, int i) {
        if (i % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        } else if (i > blockCipher.b() * 8) {
            throw new IllegalArgumentException("MAC size must be less or equal to " + (blockCipher.b() * 8));
        } else if (blockCipher.b() == 8 || blockCipher.b() == 16) {
            this.e = new CBCBlockCipher(blockCipher);
            this.f = i / 8;
            this.b = new byte[blockCipher.b()];
            this.c = new byte[blockCipher.b()];
            this.a = new byte[blockCipher.b()];
            this.d = 0;
        } else {
            throw new IllegalArgumentException("Block size must be either 64 or 128 bits");
        }
    }

    public String a() {
        return this.e.a();
    }

    private static int a(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i = 0;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            int i2 = bArr[length] & 255;
            bArr2[length] = (byte) (i | (i2 << 1));
            i = (i2 >>> 7) & 1;
        }
    }

    private static byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int length = bArr.length - 1;
        bArr2[length] = (byte) ((((bArr.length == 16 ? -121 : 27) & 255) >>> ((1 - a(bArr, bArr2)) << 3)) ^ bArr2[length]);
        return bArr2;
    }

    public void a(CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.e.a(true, cipherParameters);
            this.g = new byte[this.a.length];
            this.e.a(this.a, 0, this.g, 0);
            this.h = a(this.g);
            this.i = a(this.h);
        } else if (cipherParameters != null) {
            throw new IllegalArgumentException("CMac mode only permits key to be set.");
        }
        c();
    }

    public int b() {
        return this.f;
    }

    public void a(byte b) {
        if (this.d == this.c.length) {
            this.e.a(this.c, 0, this.b, 0);
            this.d = 0;
        }
        byte[] bArr = this.c;
        int i = this.d;
        this.d = i + 1;
        bArr[i] = b;
    }

    public void a(byte[] bArr, int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int b = this.e.b();
        int i3 = b - this.d;
        if (i2 > i3) {
            System.arraycopy(bArr, i, this.c, this.d, i3);
            this.e.a(this.c, 0, this.b, 0);
            this.d = 0;
            i2 -= i3;
            i += i3;
            while (i2 > b) {
                this.e.a(bArr, i, this.b, 0);
                i2 -= b;
                i += b;
            }
        }
        System.arraycopy(bArr, i, this.c, this.d, i2);
        this.d += i2;
    }

    public int a(byte[] bArr, int i) {
        byte[] bArr2;
        if (this.d == this.e.b()) {
            bArr2 = this.h;
        } else {
            new ISO7816d4Padding().a(this.c, this.d);
            bArr2 = this.i;
        }
        for (int i2 = 0; i2 < this.b.length; i2++) {
            byte[] bArr3 = this.c;
            bArr3[i2] = (byte) (bArr3[i2] ^ bArr2[i2]);
        }
        this.e.a(this.c, 0, this.b, 0);
        System.arraycopy(this.b, 0, bArr, i, this.f);
        c();
        return this.f;
    }

    public void c() {
        for (int i = 0; i < this.c.length; i++) {
            this.c[i] = (byte) 0;
        }
        this.d = 0;
        this.e.c();
    }
}
