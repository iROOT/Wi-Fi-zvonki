package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.RSAKeyGenerationParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;

public class RSAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger a = BigInteger.valueOf(1);
    private RSAKeyGenerationParameters b;

    public void a(KeyGenerationParameters keyGenerationParameters) {
        this.b = (RSAKeyGenerationParameters) keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair a() {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger multiply;
        BigInteger bigInteger3;
        int b = this.b.b();
        int i = (b + 1) / 2;
        int i2 = b - i;
        int i3 = b / 3;
        BigInteger c = this.b.c();
        while (true) {
            bigInteger = new BigInteger(i, 1, this.b.a());
            if (!bigInteger.mod(c).equals(a) && bigInteger.isProbablePrime(this.b.d()) && c.gcd(bigInteger.subtract(a)).equals(a)) {
                break;
            }
        }
        while (true) {
            bigInteger2 = new BigInteger(i2, 1, this.b.a());
            if (bigInteger2.subtract(bigInteger).abs().bitLength() >= i3 && !bigInteger2.mod(c).equals(a) && bigInteger2.isProbablePrime(this.b.d()) && c.gcd(bigInteger2.subtract(a)).equals(a)) {
                multiply = bigInteger.multiply(bigInteger2);
                if (multiply.bitLength() == this.b.b()) {
                    break;
                }
                bigInteger = bigInteger.max(bigInteger2);
            }
        }
        if (bigInteger.compareTo(bigInteger2) < 0) {
            bigInteger3 = bigInteger2;
            bigInteger2 = bigInteger;
        } else {
            bigInteger3 = bigInteger;
        }
        bigInteger = bigInteger3.subtract(a);
        BigInteger subtract = bigInteger2.subtract(a);
        BigInteger modInverse = c.modInverse(bigInteger.multiply(subtract));
        return new AsymmetricCipherKeyPair(new RSAKeyParameters(false, multiply, c), new RSAPrivateCrtKeyParameters(multiply, c, modInverse, bigInteger3, bigInteger2, modInverse.remainder(bigInteger), modInverse.remainder(subtract), bigInteger2.modInverse(bigInteger3)));
    }
}
