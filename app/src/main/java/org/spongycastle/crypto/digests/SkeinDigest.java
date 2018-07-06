package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.params.SkeinParameters;
import org.spongycastle.util.Memoable;

public class SkeinDigest implements ExtendedDigest, Memoable {
    private SkeinEngine a;

    public SkeinDigest(int i, int i2) {
        this.a = new SkeinEngine(i, i2);
        a(null);
    }

    public SkeinDigest(SkeinDigest skeinDigest) {
        this.a = new SkeinEngine(skeinDigest.a);
    }

    public void a(Memoable memoable) {
        this.a.a(((SkeinDigest) memoable).a);
    }

    public Memoable e() {
        return new SkeinDigest(this);
    }

    public String a() {
        return "Skein-" + (this.a.b() * 8) + "-" + (this.a.a() * 8);
    }

    public int b() {
        return this.a.a();
    }

    public int d() {
        return this.a.b();
    }

    public void a(SkeinParameters skeinParameters) {
        this.a.a(skeinParameters);
    }

    public void c() {
        this.a.c();
    }

    public void a(byte b) {
        this.a.a(b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }

    public int a(byte[] bArr, int i) {
        return this.a.a(bArr, i);
    }
}
