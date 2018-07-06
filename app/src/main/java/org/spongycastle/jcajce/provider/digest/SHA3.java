package org.spongycastle.jcajce.provider.digest;

import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.digests.SHA3Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;

public class SHA3 {

    public static class DigestSHA3 extends BCMessageDigest implements Cloneable {
        public DigestSHA3(int i) {
            super(new SHA3Digest(i));
        }

        public Object clone() {
            BCMessageDigest bCMessageDigest = (BCMessageDigest) super.clone();
            bCMessageDigest.a = new SHA3Digest((SHA3Digest) this.a);
            return bCMessageDigest;
        }
    }

    public static class Digest224 extends DigestSHA3 {
        public Digest224() {
            super(224);
        }
    }

    public static class Digest256 extends DigestSHA3 {
        public Digest256() {
            super(256);
        }
    }

    public static class Digest384 extends DigestSHA3 {
        public Digest384() {
            super(384);
        }
    }

    public static class Digest512 extends DigestSHA3 {
        public Digest512() {
            super(512);
        }
    }

    public static class HashMac224 extends BaseMac {
        public HashMac224() {
            super(new HMac(new SHA3Digest(224)));
        }
    }

    public static class HashMac256 extends BaseMac {
        public HashMac256() {
            super(new HMac(new SHA3Digest(256)));
        }
    }

    public static class HashMac384 extends BaseMac {
        public HashMac384() {
            super(new HMac(new SHA3Digest(384)));
        }
    }

    public static class HashMac512 extends BaseMac {
        public HashMac512() {
            super(new HMac(new SHA3Digest(512)));
        }
    }

    public static class KeyGenerator224 extends BaseKeyGenerator {
        public KeyGenerator224() {
            super("HMACSHA3-224", 224, new CipherKeyGenerator());
        }
    }

    public static class KeyGenerator256 extends BaseKeyGenerator {
        public KeyGenerator256() {
            super("HMACSHA3-256", 256, new CipherKeyGenerator());
        }
    }

    public static class KeyGenerator384 extends BaseKeyGenerator {
        public KeyGenerator384() {
            super("HMACSHA3-384", 384, new CipherKeyGenerator());
        }
    }

    public static class KeyGenerator512 extends BaseKeyGenerator {
        public KeyGenerator512() {
            super("HMACSHA3-512", 512, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String a = SHA3.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("MessageDigest.SHA3-224", a + "$Digest224");
            configurableProvider.a("MessageDigest.SHA3-256", a + "$Digest256");
            configurableProvider.a("MessageDigest.SHA3-384", a + "$Digest384");
            configurableProvider.a("MessageDigest.SHA3-512", a + "$Digest512");
            a(configurableProvider, "SHA3-224", a + "$HashMac224", a + "$KeyGenerator224");
            a(configurableProvider, "SHA3-256", a + "$HashMac256", a + "$KeyGenerator256");
            a(configurableProvider, "SHA3-384", a + "$HashMac384", a + "$KeyGenerator384");
            a(configurableProvider, "SHA3-512", a + "$HashMac512", a + "$KeyGenerator512");
        }
    }

    private SHA3() {
    }
}
