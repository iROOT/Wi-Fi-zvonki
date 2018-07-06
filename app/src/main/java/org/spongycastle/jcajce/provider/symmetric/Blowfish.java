package org.spongycastle.jcajce.provider.symmetric;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.BlowfishEngine;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class Blowfish {

    public static class AlgParams extends IvAlgorithmParameters {
        protected String engineToString() {
            return "Blowfish IV";
        }
    }

    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new BlowfishEngine()), 64);
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlowfishEngine());
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("Blowfish", NotificationCompat.FLAG_HIGH_PRIORITY, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = Blowfish.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.BLOWFISH", a + "$ECB");
            configurableProvider.a("Cipher.1.3.6.1.4.1.3029.1.2", a + "$CBC");
            configurableProvider.a("KeyGenerator.BLOWFISH", a + "$KeyGen");
            configurableProvider.a("Alg.Alias.KeyGenerator.1.3.6.1.4.1.3029.1.2", "BLOWFISH");
            configurableProvider.a("AlgorithmParameters.BLOWFISH", a + "$AlgParams");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.1.3.6.1.4.1.3029.1.2", "BLOWFISH");
        }
    }

    private Blowfish() {
    }
}
