package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2KeyGenerationParameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2KeyPairGenerator;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2Parameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2PublicKeyParameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceKeyGenerationParameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceKeyPairGenerator;
import org.spongycastle.pqc.crypto.mceliece.McElieceParameters;
import org.spongycastle.pqc.crypto.mceliece.McEliecePrivateKeyParameters;
import org.spongycastle.pqc.crypto.mceliece.McEliecePublicKeyParameters;
import org.spongycastle.pqc.jcajce.spec.ECCKeyGenParameterSpec;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2ParameterSpec;

public abstract class McElieceKeyPairGeneratorSpi extends KeyPairGenerator {

    public static class McEliece extends McElieceKeyPairGeneratorSpi {
        McElieceKeyPairGenerator a;

        public McEliece() {
            super("McEliece");
        }

        public void initialize(AlgorithmParameterSpec algorithmParameterSpec) {
            this.a = new McElieceKeyPairGenerator();
            super.initialize(algorithmParameterSpec);
            ECCKeyGenParameterSpec eCCKeyGenParameterSpec = (ECCKeyGenParameterSpec) algorithmParameterSpec;
            this.a.a(new McElieceKeyGenerationParameters(new SecureRandom(), new McElieceParameters(eCCKeyGenParameterSpec.a(), eCCKeyGenParameterSpec.b())));
        }

        public void initialize(int i, SecureRandom secureRandom) {
            try {
                initialize(new ECCKeyGenParameterSpec());
            } catch (InvalidAlgorithmParameterException e) {
            }
        }

        public KeyPair generateKeyPair() {
            AsymmetricCipherKeyPair a = this.a.a();
            return new KeyPair(new BCMcEliecePublicKey((McEliecePublicKeyParameters) a.a()), new BCMcEliecePrivateKey((McEliecePrivateKeyParameters) a.b()));
        }
    }

    public static class McElieceCCA2 extends McElieceKeyPairGeneratorSpi {
        McElieceCCA2KeyPairGenerator a;

        public McElieceCCA2() {
            super("McElieceCCA-2");
        }

        public void initialize(AlgorithmParameterSpec algorithmParameterSpec) {
            this.a = new McElieceCCA2KeyPairGenerator();
            super.initialize(algorithmParameterSpec);
            ECCKeyGenParameterSpec eCCKeyGenParameterSpec = (ECCKeyGenParameterSpec) algorithmParameterSpec;
            this.a.a(new McElieceCCA2KeyGenerationParameters(new SecureRandom(), new McElieceCCA2Parameters(eCCKeyGenParameterSpec.a(), eCCKeyGenParameterSpec.b())));
        }

        public void initialize(int i, SecureRandom secureRandom) {
            try {
                initialize(new McElieceCCA2ParameterSpec());
            } catch (InvalidAlgorithmParameterException e) {
            }
        }

        public KeyPair generateKeyPair() {
            AsymmetricCipherKeyPair a = this.a.a();
            return new KeyPair(new BCMcElieceCCA2PublicKey((McElieceCCA2PublicKeyParameters) a.a()), new BCMcElieceCCA2PrivateKey((McElieceCCA2PrivateKeyParameters) a.b()));
        }
    }

    public McElieceKeyPairGeneratorSpi(String str) {
        super(str);
    }
}
