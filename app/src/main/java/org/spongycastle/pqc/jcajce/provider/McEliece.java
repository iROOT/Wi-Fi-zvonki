package org.spongycastle.pqc.jcajce.provider;

import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;

public class McEliece {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("KeyPairGenerator.McElieceKobaraImai", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKeyPairGeneratorSpi$McElieceCCA2");
            configurableProvider.a("KeyPairGenerator.McEliecePointcheval", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKeyPairGeneratorSpi$McElieceCCA2");
            configurableProvider.a("KeyPairGenerator.McElieceFujisaki", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKeyPairGeneratorSpi$McElieceCCA2");
            configurableProvider.a("KeyPairGenerator.McEliecePKCS", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKeyPairGeneratorSpi$McEliece");
            configurableProvider.a("KeyPairGenerator." + PQCObjectIdentifiers.m, "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKeyPairGeneratorSpi$McEliece");
            configurableProvider.a("KeyPairGenerator." + PQCObjectIdentifiers.n, "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKeyPairGeneratorSpi$McElieceCCA2");
            configurableProvider.a("Cipher.McEliecePointcheval", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePointchevalCipherSpi$McEliecePointcheval");
            configurableProvider.a("Cipher.McEliecePointchevalWithSHA1", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePointchevalCipherSpi$McEliecePointcheval");
            configurableProvider.a("Cipher.McEliecePointchevalWithSHA224", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePointchevalCipherSpi$McEliecePointcheval224");
            configurableProvider.a("Cipher.McEliecePointchevalWithSHA256", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePointchevalCipherSpi$McEliecePointcheval256");
            configurableProvider.a("Cipher.McEliecePointchevalWithSHA384", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePointchevalCipherSpi$McEliecePointcheval384");
            configurableProvider.a("Cipher.McEliecePointchevalWithSHA512", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePointchevalCipherSpi$McEliecePointcheval512");
            configurableProvider.a("Cipher.McEliecePKCS", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePKCSCipherSpi$McEliecePKCS");
            configurableProvider.a("Cipher.McEliecePKCSWithSHA1", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePKCSCipherSpi$McEliecePKCS");
            configurableProvider.a("Cipher.McEliecePKCSWithSHA224", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePKCSCipherSpi$McEliecePKCS224");
            configurableProvider.a("Cipher.McEliecePKCSWithSHA256", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePKCSCipherSpi$McEliecePKCS256");
            configurableProvider.a("Cipher.McEliecePKCSWithSHA384", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePKCSCipherSpi$McEliecePKCS384");
            configurableProvider.a("Cipher.McEliecePKCSWithSHA512", "org.spongycastle.pqc.jcajce.provider.mceliece.McEliecePKCSCipherSpi$McEliecePKCS512");
            configurableProvider.a("Cipher.McElieceKobaraImai", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKobaraImaiCipherSpi$McElieceKobaraImai");
            configurableProvider.a("Cipher.McElieceKobaraImaiWithSHA1", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKobaraImaiCipherSpi$McElieceKobaraImai");
            configurableProvider.a("Cipher.McElieceKobaraImaiWithSHA224", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKobaraImaiCipherSpi$McElieceKobaraImai224");
            configurableProvider.a("Cipher.McElieceKobaraImaiWithSHA256", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKobaraImaiCipherSpi$McElieceKobaraImai256");
            configurableProvider.a("Cipher.McElieceKobaraImaiWithSHA384", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKobaraImaiCipherSpi$McElieceKobaraImai384");
            configurableProvider.a("Cipher.McElieceKobaraImaiWithSHA512", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceKobaraImaiCipherSpi$McElieceKobaraImai512");
            configurableProvider.a("Cipher.McElieceFujisaki", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceFujisakiCipherSpi$McElieceFujisaki");
            configurableProvider.a("Cipher.McElieceFujisakiWithSHA1", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceFujisakiCipherSpi$McElieceFujisaki");
            configurableProvider.a("Cipher.McElieceFujisakiWithSHA224", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceFujisakiCipherSpi$McElieceFujisaki224");
            configurableProvider.a("Cipher.McElieceFujisakiWithSHA256", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceFujisakiCipherSpi$McElieceFujisaki256");
            configurableProvider.a("Cipher.McElieceFujisakiWithSHA384", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceFujisakiCipherSpi$McElieceFujisaki384");
            configurableProvider.a("Cipher.McElieceFujisakiWithSHA512", "org.spongycastle.pqc.jcajce.provider.mceliece.McElieceFujisakiCipherSpi$McElieceFujisaki512");
        }
    }
}
