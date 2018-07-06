package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Memoable;

public class SHA512Digest extends LongDigest {
    public SHA512Digest(SHA512Digest sHA512Digest) {
        super(sHA512Digest);
    }

    public String a() {
        return "SHA-512";
    }

    public int b() {
        return 64;
    }

    public int a(byte[] bArr, int i) {
        f();
        Pack.a(this.a, bArr, i);
        Pack.a(this.b, bArr, i + 8);
        Pack.a(this.c, bArr, i + 16);
        Pack.a(this.d, bArr, i + 24);
        Pack.a(this.e, bArr, i + 32);
        Pack.a(this.f, bArr, i + 40);
        Pack.a(this.g, bArr, i + 48);
        Pack.a(this.h, bArr, i + 56);
        c();
        return 64;
    }

    public void c() {
        super.c();
        this.a = 7640891576956012808L;
        this.b = -4942790177534073029L;
        this.c = 4354685564936845355L;
        this.d = -6534734903238641935L;
        this.e = 5840696475078001361L;
        this.f = -7276294671716946913L;
        this.g = 2270897969802886507L;
        this.h = 6620516959819538809L;
    }

    public Memoable e() {
        return new SHA512Digest(this);
    }

    public void a(Memoable memoable) {
        a((LongDigest) (SHA512Digest) memoable);
    }
}
