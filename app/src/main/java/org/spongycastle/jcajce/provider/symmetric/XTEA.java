package org.spongycastle.jcajce.provider.symmetric;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.XTEAEngine;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class XTEA {

    public static class AlgParams extends IvAlgorithmParameters {
        protected String engineToString() {
            return "XTEA IV";
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new XTEAEngine());
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("XTEA", NotificationCompat.FLAG_HIGH_PRIORITY, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = XTEA.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.XTEA", a + "$ECB");
            configurableProvider.a("KeyGenerator.XTEA", a + "$KeyGen");
            configurableProvider.a("AlgorithmParameters.XTEA", a + "$AlgParams");
        }
    }

    private XTEA() {
    }
}
