package org.spongycastle.jcajce.provider.asymmetric.util;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.GOST3410Parameters;
import org.spongycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.spongycastle.crypto.params.GOST3410PublicKeyParameters;
import org.spongycastle.jce.interfaces.GOST3410PrivateKey;
import org.spongycastle.jce.interfaces.GOST3410PublicKey;
import org.spongycastle.jce.spec.GOST3410PublicKeyParameterSetSpec;

public class GOST3410Util {
    public static AsymmetricKeyParameter a(PublicKey publicKey) {
        if (publicKey instanceof GOST3410PublicKey) {
            GOST3410PublicKey gOST3410PublicKey = (GOST3410PublicKey) publicKey;
            GOST3410PublicKeyParameterSetSpec d = gOST3410PublicKey.b().d();
            return new GOST3410PublicKeyParameters(gOST3410PublicKey.a(), new GOST3410Parameters(d.a(), d.b(), d.c()));
        }
        throw new InvalidKeyException("can't identify GOST3410 public key: " + publicKey.getClass().getName());
    }

    public static AsymmetricKeyParameter a(PrivateKey privateKey) {
        if (privateKey instanceof GOST3410PrivateKey) {
            GOST3410PrivateKey gOST3410PrivateKey = (GOST3410PrivateKey) privateKey;
            GOST3410PublicKeyParameterSetSpec d = gOST3410PrivateKey.b().d();
            return new GOST3410PrivateKeyParameters(gOST3410PrivateKey.c(), new GOST3410Parameters(d.a(), d.b(), d.c()));
        }
        throw new InvalidKeyException("can't identify GOST3410 private key.");
    }
}
