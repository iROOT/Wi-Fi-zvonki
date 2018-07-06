package org.spongycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;

public interface DSAEncoder {
    byte[] a(BigInteger bigInteger, BigInteger bigInteger2);

    BigInteger[] a(byte[] bArr);
}
