package org.spongycastle.jcajce.provider.digest;

import org.spongycastle.asn1.iana.IANAObjectIdentifiers;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.digests.TigerDigest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;

public class Tiger {

    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new TigerDigest());
        }

        public Object clone() {
            Digest digest = (Digest) super.clone();
            digest.a = new TigerDigest((TigerDigest) this.a);
            return digest;
        }
    }

    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new TigerDigest()));
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACTIGER", 192, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String a = Tiger.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("MessageDigest.TIGER", a + "$Digest");
            configurableProvider.a("MessageDigest.Tiger", a + "$Digest");
            a(configurableProvider, "TIGER", a + "$HashMac", a + "$KeyGenerator");
            a(configurableProvider, "TIGER", IANAObjectIdentifiers.p);
            configurableProvider.a("SecretKeyFactory.PBEWITHHMACTIGER", a + "$PBEWithMacKeyFactory");
        }
    }

    public static class PBEWithHashMac extends BaseMac {
        public PBEWithHashMac() {
            super(new HMac(new TigerDigest()), 2, 3, 192);
        }
    }

    public static class PBEWithMacKeyFactory extends PBESecretKeyFactory {
        public PBEWithMacKeyFactory() {
            super("PBEwithHmacTiger", null, false, 2, 3, 192, 0);
        }
    }

    public static class TigerHmac extends BaseMac {
        public TigerHmac() {
            super(new HMac(new TigerDigest()));
        }
    }

    private Tiger() {
    }
}
