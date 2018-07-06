package org.spongycastle.jcajce.provider.digest;

import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.digests.RIPEMD320Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;

public class RIPEMD320 {

    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new RIPEMD320Digest());
        }

        public Object clone() {
            Digest digest = (Digest) super.clone();
            digest.a = new RIPEMD320Digest((RIPEMD320Digest) this.a);
            return digest;
        }
    }

    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new RIPEMD320Digest()));
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACRIPEMD320", 320, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String a = RIPEMD320.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("MessageDigest.RIPEMD320", a + "$Digest");
            a(configurableProvider, "RIPEMD320", a + "$HashMac", a + "$KeyGenerator");
        }
    }

    private RIPEMD320() {
    }
}
