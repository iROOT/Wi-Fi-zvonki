package org.spongycastle.math.ec;

import java.math.BigInteger;

public abstract class AbstractECMultiplier implements ECMultiplier {
    protected abstract ECPoint b(ECPoint eCPoint, BigInteger bigInteger);

    public ECPoint a(ECPoint eCPoint, BigInteger bigInteger) {
        int signum = bigInteger.signum();
        if (signum == 0 || eCPoint.l()) {
            return eCPoint.a().e();
        }
        ECPoint b = b(eCPoint, bigInteger.abs());
        return signum <= 0 ? b.o() : b;
    }
}
