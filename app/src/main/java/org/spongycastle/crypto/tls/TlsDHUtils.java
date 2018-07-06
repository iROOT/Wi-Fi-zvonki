package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.agreement.DHBasicAgreement;
import org.spongycastle.crypto.generators.DHBasicKeyPairGenerator;
import org.spongycastle.crypto.params.DHKeyGenerationParameters;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.util.BigIntegers;

public class TlsDHUtils {
    static final BigInteger a = BigInteger.valueOf(1);
    static final BigInteger b = BigInteger.valueOf(2);

    public static byte[] a(DHPublicKeyParameters dHPublicKeyParameters, DHPrivateKeyParameters dHPrivateKeyParameters) {
        DHBasicAgreement dHBasicAgreement = new DHBasicAgreement();
        dHBasicAgreement.a(dHPrivateKeyParameters);
        return BigIntegers.a(dHBasicAgreement.b(dHPublicKeyParameters));
    }

    public static AsymmetricCipherKeyPair a(SecureRandom secureRandom, DHParameters dHParameters) {
        DHBasicKeyPairGenerator dHBasicKeyPairGenerator = new DHBasicKeyPairGenerator();
        dHBasicKeyPairGenerator.a(new DHKeyGenerationParameters(secureRandom, dHParameters));
        return dHBasicKeyPairGenerator.a();
    }

    public static DHPrivateKeyParameters a(SecureRandom secureRandom, DHParameters dHParameters, OutputStream outputStream) {
        AsymmetricCipherKeyPair a = a(secureRandom, dHParameters);
        a(((DHPublicKeyParameters) a.a()).c(), outputStream);
        return (DHPrivateKeyParameters) a.b();
    }

    public static DHPrivateKeyParameters b(SecureRandom secureRandom, DHParameters dHParameters, OutputStream outputStream) {
        AsymmetricCipherKeyPair a = a(secureRandom, dHParameters);
        new ServerDHParams((DHPublicKeyParameters) a.a()).a(outputStream);
        return (DHPrivateKeyParameters) a.b();
    }

    public static DHPublicKeyParameters a(DHPublicKeyParameters dHPublicKeyParameters) {
        BigInteger c = dHPublicKeyParameters.c();
        DHParameters b = dHPublicKeyParameters.b();
        BigInteger a = b.a();
        BigInteger b2 = b.b();
        if (!a.isProbablePrime(2)) {
            throw new TlsFatalAlert((short) 47);
        } else if (b2.compareTo(b) < 0 || b2.compareTo(a.subtract(b)) > 0) {
            throw new TlsFatalAlert((short) 47);
        } else if (c.compareTo(b) >= 0 && c.compareTo(a.subtract(a)) <= 0) {
            return dHPublicKeyParameters;
        } else {
            throw new TlsFatalAlert((short) 47);
        }
    }

    public static BigInteger a(InputStream inputStream) {
        return new BigInteger(1, TlsUtils.f(inputStream));
    }

    public static void a(BigInteger bigInteger, OutputStream outputStream) {
        TlsUtils.b(BigIntegers.a(bigInteger), outputStream);
    }
}
