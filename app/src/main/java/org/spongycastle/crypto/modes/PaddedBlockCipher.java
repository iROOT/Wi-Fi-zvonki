package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;

public class PaddedBlockCipher extends BufferedBlockCipher {
    public int b(int i) {
        int i2 = this.b + i;
        int length = i2 % this.a.length;
        if (length != 0) {
            return (i2 - length) + this.a.length;
        }
        if (this.c) {
            return i2 + this.a.length;
        }
        return i2;
    }

    public int a(int i) {
        int i2 = this.b + i;
        int length = i2 % this.a.length;
        if (length == 0) {
            return i2 - this.a.length;
        }
        return i2 - length;
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
                length = i;
                i4 = i2;
            }
            System.arraycopy(bArr, length, this.a, this.b, i4);
            this.b = i4 + this.b;
            return a;
        }
        throw new DataLengthException("output buffer too short");
    }

    public int a(byte[] bArr, int i) {
        int i2;
        int b = this.d.b();
        if (this.c) {
            if (this.b != b) {
                i2 = 0;
            } else if ((b * 2) + i > bArr.length) {
                throw new DataLengthException("output buffer too short");
            } else {
                i2 = this.d.a(this.a, 0, bArr, i);
                this.b = 0;
            }
            byte b2 = (byte) (b - this.b);
            while (this.b < b) {
                this.a[this.b] = b2;
                this.b++;
            }
            i2 += this.d.a(this.a, 0, bArr, i + i2);
        } else if (this.b == b) {
            i2 = this.d.a(this.a, 0, this.a, 0);
            this.b = 0;
            int i3 = this.a[b - 1] & 255;
            if (i3 < 0 || i3 > b) {
                throw new InvalidCipherTextException("pad block corrupted");
            }
            i2 -= i3;
            System.arraycopy(this.a, 0, bArr, i, i2);
        } else {
            throw new DataLengthException("last block incomplete in decryption");
        }
        c();
        return i2;
    }
}
