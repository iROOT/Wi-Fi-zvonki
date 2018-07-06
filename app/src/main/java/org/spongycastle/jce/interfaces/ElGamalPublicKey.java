package org.spongycastle.jce.interfaces;

import java.math.BigInteger;
import java.security.PublicKey;

public interface ElGamalPublicKey extends PublicKey, ElGamalKey {
    BigInteger getY();
}
