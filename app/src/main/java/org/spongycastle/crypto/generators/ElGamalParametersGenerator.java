package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.params.ElGamalParameters;

public class ElGamalParametersGenerator {
    private int a;
    private int b;
    private SecureRandom c;

    public void a(int i, int i2, SecureRandom secureRandom) {
        this.a = i;
        this.b = i2;
        this.c = secureRandom;
    }

    public ElGamalParameters a() {
        BigInteger[] a = DHParametersHelper.a(this.a, this.b, this.c);
        BigInteger bigInteger = a[0];
        return new ElGamalParameters(bigInteger, DHParametersHelper.a(bigInteger, a[1], this.c));
    }
}
