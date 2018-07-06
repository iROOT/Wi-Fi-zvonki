package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Arrays;

public class SipHash implements Mac {
    protected final int a;
    protected final int b;
    protected long c;
    protected long d;
    protected long e;
    protected long f;
    protected long g;
    protected long h;
    protected byte[] i;
    protected int j;
    protected int k;

    public SipHash() {
        this.i = new byte[8];
        this.j = 0;
        this.k = 0;
        this.a = 2;
        this.b = 4;
    }

    public SipHash(int i, int i2) {
        this.i = new byte[8];
        this.j = 0;
        this.k = 0;
        this.a = i;
        this.b = i2;
    }

    public String a() {
        return "SipHash-" + this.a + "-" + this.b;
    }

    public int b() {
        return 8;
    }

    public void a(CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            byte[] a = ((KeyParameter) cipherParameters).a();
            if (a.length != 16) {
                throw new IllegalArgumentException("'params' must be a 128-bit key");
            }
            this.c = Pack.d(a, 0);
            this.d = Pack.d(a, 8);
            c();
            return;
        }
        throw new IllegalArgumentException("'params' must be an instance of KeyParameter");
    }

    public void a(byte b) {
        this.i[this.j] = b;
        int i = this.j + 1;
        this.j = i;
        if (i == this.i.length) {
            e();
            this.j = 0;
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.i[this.j] = bArr[i + i3];
            int i4 = this.j + 1;
            this.j = i4;
            if (i4 == this.i.length) {
                e();
                this.j = 0;
            }
        }
    }

    public long d() {
        this.i[7] = (byte) (((this.k << 3) + this.j) & 255);
        while (this.j < 7) {
            byte[] bArr = this.i;
            int i = this.j;
            this.j = i + 1;
            bArr[i] = (byte) 0;
        }
        e();
        this.g ^= 255;
        a(this.b);
        long j = ((this.e ^ this.f) ^ this.g) ^ this.h;
        c();
        return j;
    }

    public int a(byte[] bArr, int i) {
        Pack.b(d(), bArr, i);
        return 8;
    }

    public void c() {
        this.e = this.c ^ 8317987319222330741L;
        this.f = this.d ^ 7237128888997146477L;
        this.g = this.c ^ 7816392313619706465L;
        this.h = this.d ^ 8387220255154660723L;
        Arrays.a(this.i, (byte) 0);
        this.j = 0;
        this.k = 0;
    }

    protected void e() {
        this.k++;
        long d = Pack.d(this.i, 0);
        this.h ^= d;
        a(this.a);
        this.e = d ^ this.e;
    }

    protected void a(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.e += this.f;
            this.g += this.h;
            this.f = a(this.f, 13);
            this.h = a(this.h, 16);
            this.f ^= this.e;
            this.h ^= this.g;
            this.e = a(this.e, 32);
            this.g += this.f;
            this.e += this.h;
            this.f = a(this.f, 17);
            this.h = a(this.h, 21);
            this.f ^= this.g;
            this.h ^= this.e;
            this.g = a(this.g, 32);
        }
    }

    protected static long a(long j, int i) {
        return (j << i) | (j >>> (64 - i));
    }
}
