package org.spongycastle.crypto.ec;

import org.spongycastle.math.ec.ECPoint;

public class ECPair {
    private final ECPoint a;
    private final ECPoint b;

    public ECPoint a() {
        return this.a;
    }

    public ECPoint b() {
        return this.b;
    }

    public boolean a(ECPair eCPair) {
        return eCPair.a().a(a()) && eCPair.b().a(b());
    }

    public boolean equals(Object obj) {
        return obj instanceof ECPair ? a((ECPair) obj) : false;
    }

    public int hashCode() {
        return this.a.hashCode() + (this.b.hashCode() * 37);
    }
}
