package org.spongycastle.jcajce.provider.symmetric;

import android.support.v4.app.NotificationCompat;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.spongycastle.asn1.ntt.NTTObjectIdentifiers;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.CamelliaEngine;
import org.spongycastle.crypto.engines.CamelliaWrapEngine;
import org.spongycastle.crypto.engines.RFC3211WrapEngine;
import org.spongycastle.crypto.generators.Poly1305KeyGenerator;
import org.spongycastle.crypto.macs.GMac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;

public final class Camellia {

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for Camellia parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[16];
            if (this.b == null) {
                this.b = new SecureRandom();
            }
            this.b.nextBytes(bArr);
            try {
                AlgorithmParameters instance = AlgorithmParameters.getInstance("Camellia", "SC");
                instance.init(new IvParameterSpec(bArr));
                return instance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static class AlgParams extends IvAlgorithmParameters {
        protected String engineToString() {
            return "Camellia IV";
        }
    }

    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new CamelliaEngine()), (int) NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() {
                public BlockCipher a() {
                    return new CamelliaEngine();
                }
            });
        }
    }

    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new GMac(new GCMBlockCipher(new CamelliaEngine())));
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i) {
            super("Camellia", i, new CipherKeyGenerator());
        }
    }

    public static class KeyGen128 extends KeyGen {
        public KeyGen128() {
            super(NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class KeyGen192 extends KeyGen {
        public KeyGen192() {
            super(192);
        }
    }

    public static class KeyGen256 extends KeyGen {
        public KeyGen256() {
            super(256);
        }
    }

    public static class Mappings extends SymmetricAlgorithmProvider {
        private static final String a = Camellia.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.CAMELLIA", a + "$AlgParams");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + NTTObjectIdentifiers.a, "CAMELLIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + NTTObjectIdentifiers.b, "CAMELLIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + NTTObjectIdentifiers.c, "CAMELLIA");
            configurableProvider.a("AlgorithmParameterGenerator.CAMELLIA", a + "$AlgParamGen");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + NTTObjectIdentifiers.a, "CAMELLIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + NTTObjectIdentifiers.b, "CAMELLIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + NTTObjectIdentifiers.c, "CAMELLIA");
            configurableProvider.a("Cipher.CAMELLIA", a + "$ECB");
            configurableProvider.a("Cipher." + NTTObjectIdentifiers.a, a + "$CBC");
            configurableProvider.a("Cipher." + NTTObjectIdentifiers.b, a + "$CBC");
            configurableProvider.a("Cipher." + NTTObjectIdentifiers.c, a + "$CBC");
            configurableProvider.a("Cipher.CAMELLIARFC3211WRAP", a + "$RFC3211Wrap");
            configurableProvider.a("Cipher.CAMELLIAWRAP", a + "$Wrap");
            configurableProvider.a("Alg.Alias.Cipher." + NTTObjectIdentifiers.d, "CAMELLIAWRAP");
            configurableProvider.a("Alg.Alias.Cipher." + NTTObjectIdentifiers.e, "CAMELLIAWRAP");
            configurableProvider.a("Alg.Alias.Cipher." + NTTObjectIdentifiers.f, "CAMELLIAWRAP");
            configurableProvider.a("KeyGenerator.CAMELLIA", a + "$KeyGen");
            configurableProvider.a("KeyGenerator." + NTTObjectIdentifiers.d, a + "$KeyGen128");
            configurableProvider.a("KeyGenerator." + NTTObjectIdentifiers.e, a + "$KeyGen192");
            configurableProvider.a("KeyGenerator." + NTTObjectIdentifiers.f, a + "$KeyGen256");
            configurableProvider.a("KeyGenerator." + NTTObjectIdentifiers.a, a + "$KeyGen128");
            configurableProvider.a("KeyGenerator." + NTTObjectIdentifiers.b, a + "$KeyGen192");
            configurableProvider.a("KeyGenerator." + NTTObjectIdentifiers.c, a + "$KeyGen256");
            a(configurableProvider, "CAMELLIA", a + "$GMAC", a + "$KeyGen");
            b(configurableProvider, "CAMELLIA", a + "$Poly1305", a + "$Poly1305KeyGen");
        }
    }

    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.spongycastle.crypto.macs.Poly1305(new CamelliaEngine()));
        }
    }

    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-Camellia", 256, new Poly1305KeyGenerator());
        }
    }

    public static class RFC3211Wrap extends BaseWrapCipher {
        public RFC3211Wrap() {
            super(new RFC3211WrapEngine(new CamelliaEngine()), 16);
        }
    }

    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new CamelliaWrapEngine());
        }
    }

    private Camellia() {
    }
}
