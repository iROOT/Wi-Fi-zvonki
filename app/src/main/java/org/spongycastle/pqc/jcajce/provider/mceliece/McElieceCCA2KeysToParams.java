package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2PublicKeyParameters;

public class McElieceCCA2KeysToParams {
    public static AsymmetricKeyParameter a(PublicKey publicKey) {
        if (publicKey instanceof BCMcElieceCCA2PublicKey) {
            BCMcElieceCCA2PublicKey bCMcElieceCCA2PublicKey = (BCMcElieceCCA2PublicKey) publicKey;
            return new McElieceCCA2PublicKeyParameters(bCMcElieceCCA2PublicKey.d(), bCMcElieceCCA2PublicKey.a(), bCMcElieceCCA2PublicKey.b(), bCMcElieceCCA2PublicKey.c(), bCMcElieceCCA2PublicKey.f());
        }
        throw new InvalidKeyException("can't identify McElieceCCA2 public key: " + publicKey.getClass().getName());
    }

    public static AsymmetricKeyParameter a(PrivateKey privateKey) {
        if (privateKey instanceof BCMcElieceCCA2PrivateKey) {
            BCMcElieceCCA2PrivateKey bCMcElieceCCA2PrivateKey = (BCMcElieceCCA2PrivateKey) privateKey;
            return new McElieceCCA2PrivateKeyParameters(bCMcElieceCCA2PrivateKey.h(), bCMcElieceCCA2PrivateKey.a(), bCMcElieceCCA2PrivateKey.b(), bCMcElieceCCA2PrivateKey.c(), bCMcElieceCCA2PrivateKey.d(), bCMcElieceCCA2PrivateKey.e(), bCMcElieceCCA2PrivateKey.f(), bCMcElieceCCA2PrivateKey.g(), bCMcElieceCCA2PrivateKey.j());
        }
        throw new InvalidKeyException("can't identify McElieceCCA2 private key.");
    }
}
