package org.spongycastle.math.ec;

public class WNafPreCompInfo implements PreCompInfo {
    private ECPoint[] a = null;
    private ECPoint[] b = null;
    private ECPoint c = null;

    protected ECPoint[] a() {
        return this.a;
    }

    protected ECPoint[] b() {
        return this.b;
    }

    protected void a(ECPoint[] eCPointArr) {
        this.a = eCPointArr;
    }

    protected void b(ECPoint[] eCPointArr) {
        this.b = eCPointArr;
    }

    protected ECPoint c() {
        return this.c;
    }

    protected void a(ECPoint eCPoint) {
        this.c = eCPoint;
    }
}
