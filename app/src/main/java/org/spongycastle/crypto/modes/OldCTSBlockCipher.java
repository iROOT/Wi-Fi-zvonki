package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.DataLengthException;

public class OldCTSBlockCipher extends BufferedBlockCipher {
    public int a(int i) {
        int i2 = this.b + i;
        int length = i2 % this.a.length;
        if (length == 0) {
            return i2 - this.a.length;
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
            int length = this.a.length - this.b;
            if (i2 > length) {
                System.arraycopy(bArr, i, this.a, this.b, length);
                a = this.d.a(this.a, 0, bArr2, i3) + 0;
                System.arraycopy(this.a, b, this.a, 0, b);
                this.b = b;
                i2 -= length;
                i += length;
                while (i2 > b) {
                    System.arraycopy(bArr, i, this.a, this.b, b);
                    a += this.d.a(this.a, 0, bArr2, i3 + a);
                    System.arraycopy(this.a, b, this.a, 0, b);
                    i2 -= b;
                    i += b;
                }
            } else {
                a = 0;
            }
            System.arraycopy(bArr, i, this.a, this.b, i2);
            this.b += i2;
            return a;
        }
        throw new DataLengthException("output buffer too short");
    }

    public int a(byte[] bArr, int i) {
        if (this.b + i > bArr.length) {
            throw new DataLengthException("output buffer to small in doFinal");
        }
        int i2;
        int b = this.d.b();
        int i3 = this.b - b;
        Object obj = new byte[b];
        if (this.c) {
            this.d.a(this.a, 0, obj, 0);
            if (this.b < b) {
                throw new DataLengthException("need at least one block of input for CTS");
            }
            for (i2 = this.b; i2 != this.a.length; i2++) {
                this.a[i2] = obj[i2 - b];
            }
            for (i2 = b; i2 != this.b; i2++) {
                byte[] bArr2 = this.a;
                bArr2[i2] = (byte) (bArr2[i2] ^ obj[i2 - b]);
            }
            if (this.d instanceof CBCBlockCipher) {
                ((CBCBlockCipher) this.d).d().a(this.a, b, bArr, i);
            } else {
                this.d.a(this.a, b, bArr, i);
            }
            System.arraycopy(obj, 0, bArr, i + b, i3);
        } else {
            Object obj2 = new byte[b];
            if (this.d instanceof CBCBlockCipher) {
                ((CBCBlockCipher) this.d).d().a(this.a, 0, obj, 0);
            } else {
                this.d.a(this.a, 0, obj, 0);
            }
            for (i2 = b; i2 != this.b; i2++) {
                obj2[i2 - b] = (byte) (obj[i2 - b] ^ this.a[i2]);
            }
            System.arraycopy(this.a, b, obj, 0, i3);
            this.d.a(obj, 0, bArr, i);
            System.arraycopy(obj2, 0, bArr, i + b, i3);
        }
        i2 = this.b;
        c();
        return i2;
    }
}
