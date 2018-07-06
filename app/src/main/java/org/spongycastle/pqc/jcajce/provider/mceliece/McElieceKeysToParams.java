package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.mceliece.McEliecePrivateKeyParameters;
import org.spongycastle.pqc.crypto.mceliece.McEliecePublicKeyParameters;

public class McElieceKeysToParams {
    public static AsymmetricKeyParameter a(PublicKey publicKey) {
        if (publicKey instanceof BCMcEliecePublicKey) {
            BCMcEliecePublicKey bCMcEliecePublicKey = (BCMcEliecePublicKey) publicKey;
            return new McEliecePublicKeyParameters(bCMcEliecePublicKey.d(), bCMcEliecePublicKey.a(), bCMcEliecePublicKey.b(), bCMcEliecePublicKey.c(), bCMcEliecePublicKey.f());
        }
        throw new InvalidKeyException("can't identify McEliece public key: " + publicKey.getClass().getName());
    }

    public static AsymmetricKeyParameter a(PrivateKey privateKey) {
        if (privateKey instanceof BCMcEliecePrivateKey) {
            BCMcEliecePrivateKey bCMcEliecePrivateKey = (BCMcEliecePrivateKey) privateKey;
            return new McEliecePrivateKeyParameters(bCMcEliecePrivateKey.j(), bCMcEliecePrivateKey.a(), bCMcEliecePrivateKey.b(), bCMcEliecePrivateKey.c(), bCMcEliecePrivateKey.d(), bCMcEliecePrivateKey.e(), bCMcEliecePrivateKey.f(), bCMcEliecePrivateKey.g(), bCMcEliecePrivateKey.h(), bCMcEliecePrivateKey.i(), bCMcEliecePrivateKey.l());
        }
        throw new InvalidKeyException("can't identify McEliece private key.");
    }
}
