package org.spongycastle.jcajce.provider.symmetric;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.VMPCEngine;
import org.spongycastle.crypto.macs.VMPCMac;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.BaseStreamCipher;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class VMPC {

    public static class Base extends BaseStreamCipher {
        public Base() {
            super(new VMPCEngine(), 16);
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("VMPC", NotificationCompat.FLAG_HIGH_PRIORITY, new CipherKeyGenerator());
        }
    }

    public static class Mac extends BaseMac {
        public Mac() {
            super(new VMPCMac());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = VMPC.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.VMPC", a + "$Base");
            configurableProvider.a("KeyGenerator.VMPC", a + "$KeyGen");
            configurableProvider.a("Mac.VMPCMAC", a + "$Mac");
            configurableProvider.a("Alg.Alias.Mac.VMPC", "VMPCMAC");
            configurableProvider.a("Alg.Alias.Mac.VMPC-MAC", "VMPCMAC");
        }
    }

    private VMPC() {
    }
}
