package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Memoable;

public class SHA384Digest extends LongDigest {
    public SHA384Digest(SHA384Digest sHA384Digest) {
        super(sHA384Digest);
    }

    public String a() {
        return "SHA-384";
    }

    public int b() {
        return 48;
    }

    public int a(byte[] bArr, int i) {
        f();
        Pack.a(this.a, bArr, i);
        Pack.a(this.b, bArr, i + 8);
        Pack.a(this.c, bArr, i + 16);
        Pack.a(this.d, bArr, i + 24);
        Pack.a(this.e, bArr, i + 32);
        Pack.a(this.f, bArr, i + 40);
        c();
        return 48;
    }

    public void c() {
        super.c();
        this.a = -3766243637369397544L;
        this.b = 7105036623409894663L;
        this.c = -7973340178411365097L;
        this.d = 1526699215303891257L;
        this.e = 7436329637833083697L;
        this.f = -8163818279084223215L;
        this.g = -2662702644619276377L;
        this.h = 5167115440072839076L;
    }

    public Memoable e() {
        return new SHA384Digest(this);
    }

    public void a(Memoable memoable) {
        super.a((SHA384Digest) memoable);
    }
}
