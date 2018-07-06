package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.util.BigIntegers;

class DHKeyGeneratorHelper {
    static final DHKeyGeneratorHelper a = new DHKeyGeneratorHelper();
    private static final BigInteger b = BigInteger.valueOf(1);
    private static final BigInteger c = BigInteger.valueOf(2);

    private DHKeyGeneratorHelper() {
    }

    BigInteger a(DHParameters dHParameters, SecureRandom secureRandom) {
        BigInteger a = dHParameters.a();
        int e = dHParameters.e();
        if (e != 0) {
            return new BigInteger(e, secureRandom).setBit(e - 1);
        }
        BigInteger bigInteger = c;
        int d = dHParameters.d();
        if (d != 0) {
            bigInteger = b.shiftLeft(d - 1);
        }
        a = a.subtract(c);
        BigInteger c = dHParameters.c();
        if (c != null) {
            a = c.subtract(c);
        }
        return BigIntegers.a(bigInteger, a, secureRandom);
    }

    BigInteger a(DHParameters dHParameters, BigInteger bigInteger) {
        return dHParameters.b().modPow(bigInteger, dHParameters.a());
    }
}
