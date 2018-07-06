package org.spongycastle.pqc.crypto.ntru;

import java.util.Arrays;
import org.spongycastle.crypto.Digest;

public class NTRUEncryptionParameters implements Cloneable {
    public Digest A;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    int l;
    public int m;
    public int n;
    public int o;
    int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public boolean v;
    public byte[] w;
    public boolean x;
    public boolean y;
    public int z = 1;

    public /* synthetic */ Object clone() {
        return a();
    }

    public NTRUEncryptionParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.n = i5;
        this.q = i4;
        this.s = i6;
        this.t = i7;
        this.u = i8;
        this.v = z;
        this.w = bArr;
        this.x = z2;
        this.y = z3;
        this.A = digest;
        b();
    }

    public NTRUEncryptionParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        this.a = i;
        this.b = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        this.n = i7;
        this.q = i6;
        this.s = i8;
        this.t = i9;
        this.u = i10;
        this.v = z;
        this.w = bArr;
        this.x = z2;
        this.y = z3;
        this.A = digest;
        b();
    }

    private void b() {
        this.g = this.c;
        this.h = this.d;
        this.i = this.e;
        this.j = this.f;
        this.k = this.a / 3;
        this.l = 1;
        this.m = (((((this.a * 3) / 2) / 8) - this.l) - (this.n / 8)) - 1;
        this.o = (((((this.a * 3) / 2) + 7) / 8) * 8) + 1;
        this.p = this.a - 1;
        this.r = this.n;
    }

    public NTRUEncryptionParameters a() {
        if (this.z == 0) {
            return new NTRUEncryptionParameters(this.a, this.b, this.c, this.q, this.n, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.A);
        }
        return new NTRUEncryptionParameters(this.a, this.b, this.d, this.e, this.f, this.q, this.n, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.A);
    }

    public int hashCode() {
        int i;
        int i2 = 1231;
        int hashCode = ((this.A == null ? 0 : this.A.a().hashCode()) + (((this.y ? 1231 : 1237) + ((((((((((((((((((((((((((((((this.a + 31) * 31) + this.o) * 31) + this.p) * 31) + this.s) * 31) + this.n) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f) * 31) + this.k) * 31) + this.q) * 31) + this.g) * 31) + this.h) * 31) + this.i) * 31) + this.j) * 31)) * 31)) * 31;
        if (this.v) {
            i = 1231;
        } else {
            i = 1237;
        }
        i = (((((((((((((((((i + hashCode) * 31) + this.l) * 31) + this.m) * 31) + this.u) * 31) + this.t) * 31) + Arrays.hashCode(this.w)) * 31) + this.r) * 31) + this.z) * 31) + this.b) * 31;
        if (!this.x) {
            i2 = 1237;
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NTRUEncryptionParameters nTRUEncryptionParameters = (NTRUEncryptionParameters) obj;
        if (this.a != nTRUEncryptionParameters.a) {
            return false;
        }
        if (this.o != nTRUEncryptionParameters.o) {
            return false;
        }
        if (this.p != nTRUEncryptionParameters.p) {
            return false;
        }
        if (this.s != nTRUEncryptionParameters.s) {
            return false;
        }
        if (this.n != nTRUEncryptionParameters.n) {
            return false;
        }
        if (this.c != nTRUEncryptionParameters.c) {
            return false;
        }
        if (this.d != nTRUEncryptionParameters.d) {
            return false;
        }
        if (this.e != nTRUEncryptionParameters.e) {
            return false;
        }
        if (this.f != nTRUEncryptionParameters.f) {
            return false;
        }
        if (this.k != nTRUEncryptionParameters.k) {
            return false;
        }
        if (this.q != nTRUEncryptionParameters.q) {
            return false;
        }
        if (this.g != nTRUEncryptionParameters.g) {
            return false;
        }
        if (this.h != nTRUEncryptionParameters.h) {
            return false;
        }
        if (this.i != nTRUEncryptionParameters.i) {
            return false;
        }
        if (this.j != nTRUEncryptionParameters.j) {
            return false;
        }
        if (this.y != nTRUEncryptionParameters.y) {
            return false;
        }
        if (this.A == null) {
            if (nTRUEncryptionParameters.A != null) {
                return false;
            }
        } else if (!this.A.a().equals(nTRUEncryptionParameters.A.a())) {
            return false;
        }
        if (this.v != nTRUEncryptionParameters.v) {
            return false;
        }
        if (this.l != nTRUEncryptionParameters.l) {
            return false;
        }
        if (this.m != nTRUEncryptionParameters.m) {
            return false;
        }
        if (this.u != nTRUEncryptionParameters.u) {
            return false;
        }
        if (this.t != nTRUEncryptionParameters.t) {
            return false;
        }
        if (!Arrays.equals(this.w, nTRUEncryptionParameters.w)) {
            return false;
        }
        if (this.r != nTRUEncryptionParameters.r) {
            return false;
        }
        if (this.z != nTRUEncryptionParameters.z) {
            return false;
        }
        if (this.b != nTRUEncryptionParameters.b) {
            return false;
        }
        if (this.x != nTRUEncryptionParameters.x) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("EncryptionParameters(N=" + this.a + " q=" + this.b);
        if (this.z == 0) {
            stringBuilder.append(" polyType=SIMPLE df=" + this.c);
        } else {
            stringBuilder.append(" polyType=PRODUCT df1=" + this.d + " df2=" + this.e + " df3=" + this.f);
        }
        stringBuilder.append(" dm0=" + this.q + " db=" + this.n + " c=" + this.s + " minCallsR=" + this.t + " minCallsMask=" + this.u + " hashSeed=" + this.v + " hashAlg=" + this.A + " oid=" + Arrays.toString(this.w) + " sparse=" + this.x + ")");
        return stringBuilder.toString();
    }
}
