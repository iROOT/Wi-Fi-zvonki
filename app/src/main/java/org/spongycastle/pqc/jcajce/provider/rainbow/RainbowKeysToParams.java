package org.spongycastle.pqc.jcajce.provider.rainbow;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.spongycastle.pqc.crypto.rainbow.RainbowPublicKeyParameters;

public class RainbowKeysToParams {
    public static AsymmetricKeyParameter a(PublicKey publicKey) {
        if (publicKey instanceof BCRainbowPublicKey) {
            BCRainbowPublicKey bCRainbowPublicKey = (BCRainbowPublicKey) publicKey;
            return new RainbowPublicKeyParameters(bCRainbowPublicKey.a(), bCRainbowPublicKey.b(), bCRainbowPublicKey.c(), bCRainbowPublicKey.d());
        }
        throw new InvalidKeyException("can't identify Rainbow public key: " + publicKey.getClass().getName());
    }

    public static AsymmetricKeyParameter a(PrivateKey privateKey) {
        if (privateKey instanceof BCRainbowPrivateKey) {
            BCRainbowPrivateKey bCRainbowPrivateKey = (BCRainbowPrivateKey) privateKey;
            return new RainbowPrivateKeyParameters(bCRainbowPrivateKey.a(), bCRainbowPrivateKey.b(), bCRainbowPrivateKey.d(), bCRainbowPrivateKey.c(), bCRainbowPrivateKey.f(), bCRainbowPrivateKey.e());
        }
        throw new InvalidKeyException("can't identify Rainbow private key.");
    }
}
