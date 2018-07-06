package org.spongycastle.jcajce.provider.symmetric;

import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class SipHash {

    public static class Mac48 extends BaseMac {
        public Mac48() {
            super(new org.spongycastle.crypto.macs.SipHash(4, 8));
        }
    }

    public static class Mac extends BaseMac {
        public Mac() {
            super(new org.spongycastle.crypto.macs.SipHash());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = SipHash.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Mac.SIPHASH", a + "$Mac");
            configurableProvider.a("Alg.Alias.Mac.SIPHASH-2-4", "SIPHASH");
            configurableProvider.a("Mac.SIPHASH-4-8", a + "$Mac48");
        }
    }

    private SipHash() {
    }
}
