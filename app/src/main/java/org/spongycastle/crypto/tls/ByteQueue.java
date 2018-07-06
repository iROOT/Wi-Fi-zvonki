package org.spongycastle.crypto.tls;

import net.hockeyapp.android.k;

public class ByteQueue {
    private byte[] a;
    private int b;
    private int c;

    public static final int a(int i) {
        int i2 = (i >> 1) | i;
        i2 |= i2 >> 2;
        i2 |= i2 >> 4;
        i2 |= i2 >> 8;
        return (i2 | (i2 >> 16)) + 1;
    }

    public ByteQueue() {
        this(k.FEEDBACK_FAILED_TITLE_ID);
    }

    public ByteQueue(int i) {
        this.b = 0;
        this.c = 0;
        this.a = new byte[i];
    }

    public void a(byte[] bArr, int i, int i2, int i3) {
        if (this.c - i3 < i2) {
            throw new TlsRuntimeException("Not enough data to read");
        } else if (bArr.length - i < i2) {
            throw new TlsRuntimeException("Buffer size of " + bArr.length + " is too small for a read of " + i2 + " bytes");
        } else {
            System.arraycopy(this.a, this.b + i3, bArr, i, i2);
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        if ((this.b + this.c) + i2 > this.a.length) {
            int a = a(this.c + i2);
            if (a > this.a.length) {
                Object obj = new byte[a];
                System.arraycopy(this.a, this.b, obj, 0, this.c);
                this.a = obj;
            } else {
                System.arraycopy(this.a, this.b, this.a, 0, this.c);
            }
            this.b = 0;
        }
        System.arraycopy(bArr, i, this.a, this.b + this.c, i2);
        this.c += i2;
    }

    public void b(int i) {
        if (i > this.c) {
            throw new TlsRuntimeException("Cannot remove " + i + " bytes, only got " + this.c);
        }
        this.c -= i;
        this.b += i;
    }

    public void b(byte[] bArr, int i, int i2, int i3) {
        a(bArr, i, i2, i3);
        b(i3 + i2);
    }

    public byte[] a(int i, int i2) {
        byte[] bArr = new byte[i];
        b(bArr, 0, i, i2);
        return bArr;
    }

    public int a() {
        return this.c;
    }
}
