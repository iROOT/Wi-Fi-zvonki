package org.spongycastle.jcajce.provider.symmetric;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.Salsa20Engine;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseStreamCipher;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class Salsa20 {

    public static class Base extends BaseStreamCipher {
        public Base() {
            super(new Salsa20Engine(), 8);
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("Salsa20", NotificationCompat.FLAG_HIGH_PRIORITY, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = Salsa20.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.SALSA20", a + "$Base");
            configurableProvider.a("KeyGenerator.SALSA20", a + "$KeyGen");
        }
    }

    private Salsa20() {
    }
}
