package org.spongycastle.jcajce.provider.symmetric;

import net.hockeyapp.android.k;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.ThreefishEngine;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class Threefish {

    public static class AlgParams_1024 extends IvAlgorithmParameters {
        protected String engineToString() {
            return "Threefish-1024 IV";
        }
    }

    public static class AlgParams_256 extends IvAlgorithmParameters {
        protected String engineToString() {
            return "Threefish-256 IV";
        }
    }

    public static class AlgParams_512 extends IvAlgorithmParameters {
        protected String engineToString() {
            return "Threefish-512 IV";
        }
    }

    public static class ECB_1024 extends BaseBlockCipher {
        public ECB_1024() {
            super(new ThreefishEngine(k.FEEDBACK_FAILED_TITLE_ID));
        }
    }

    public static class ECB_256 extends BaseBlockCipher {
        public ECB_256() {
            super(new ThreefishEngine(256));
        }
    }

    public static class ECB_512 extends BaseBlockCipher {
        public ECB_512() {
            super(new ThreefishEngine(512));
        }
    }

    public static class KeyGen_1024 extends BaseKeyGenerator {
        public KeyGen_1024() {
            super("Threefish-1024", k.FEEDBACK_FAILED_TITLE_ID, new CipherKeyGenerator());
        }
    }

    public static class KeyGen_256 extends BaseKeyGenerator {
        public KeyGen_256() {
            super("Threefish-256", 256, new CipherKeyGenerator());
        }
    }

    public static class KeyGen_512 extends BaseKeyGenerator {
        public KeyGen_512() {
            super("Threefish-512", 512, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = Threefish.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.Threefish-256", a + "$ECB_256");
            configurableProvider.a("Cipher.Threefish-512", a + "$ECB_512");
            configurableProvider.a("Cipher.Threefish-1024", a + "$ECB_1024");
            configurableProvider.a("KeyGenerator.Threefish-256", a + "$KeyGen_256");
            configurableProvider.a("KeyGenerator.Threefish-512", a + "$KeyGen_512");
            configurableProvider.a("KeyGenerator.Threefish-1024", a + "$KeyGen_1024");
            configurableProvider.a("AlgorithmParameters.Threefish-256", a + "$AlgParams_256");
            configurableProvider.a("AlgorithmParameters.Threefish-512", a + "$AlgParams_512");
            configurableProvider.a("AlgorithmParameters.Threefish-1024", a + "$AlgParams_1024");
        }
    }

    private Threefish() {
    }
}
