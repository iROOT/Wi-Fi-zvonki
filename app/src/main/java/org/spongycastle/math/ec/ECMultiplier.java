package org.spongycastle.math.ec;

import java.math.BigInteger;

public interface ECMultiplier {
    ECPoint a(ECPoint eCPoint, BigInteger bigInteger);
}
