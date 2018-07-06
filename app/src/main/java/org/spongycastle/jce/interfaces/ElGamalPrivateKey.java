package org.spongycastle.jce.interfaces;

import java.math.BigInteger;
import java.security.PrivateKey;

public interface ElGamalPrivateKey extends PrivateKey, ElGamalKey {
    BigInteger getX();
}
