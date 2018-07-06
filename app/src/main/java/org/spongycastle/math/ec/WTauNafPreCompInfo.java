package org.spongycastle.math.ec;

import org.spongycastle.math.ec.ECPoint.F2m;

class WTauNafPreCompInfo implements PreCompInfo {
    private F2m[] a = null;

    WTauNafPreCompInfo(F2m[] f2mArr) {
        this.a = f2mArr;
    }

    protected F2m[] a() {
        return this.a;
    }
}
