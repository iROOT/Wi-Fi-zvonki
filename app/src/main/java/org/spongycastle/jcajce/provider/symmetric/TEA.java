package org.spongycastle.jcajce.provider.symmetric;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.TEAEngine;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class TEA {

    public static class AlgParams extends IvAlgorithmParameters {
        protected String engineToString() {
            return "TEA IV";
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new TEAEngine());
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("TEA", NotificationCompat.FLAG_HIGH_PRIORITY, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = TEA.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.TEA", a + "$ECB");
            configurableProvider.a("KeyGenerator.TEA", a + "$KeyGen");
            configurableProvider.a("AlgorithmParameters.TEA", a + "$AlgParams");
        }
    }

    private TEA() {
    }
}
