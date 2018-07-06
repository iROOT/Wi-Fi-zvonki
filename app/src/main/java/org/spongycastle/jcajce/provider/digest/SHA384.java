package org.spongycastle.jcajce.provider.digest;

import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.digests.SHA384Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.macs.OldHMac;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;

public class SHA384 {

    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new SHA384Digest());
        }

        public Object clone() {
            Digest digest = (Digest) super.clone();
            digest.a = new SHA384Digest((SHA384Digest) this.a);
            return digest;
        }
    }

    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new SHA384Digest()));
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACSHA384", 384, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String a = SHA384.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("MessageDigest.SHA-384", a + "$Digest");
            configurableProvider.a("Alg.Alias.MessageDigest.SHA384", "SHA-384");
            configurableProvider.a("Alg.Alias.MessageDigest." + NISTObjectIdentifiers.d, "SHA-384");
            configurableProvider.a("Mac.OLDHMACSHA384", a + "$OldSHA384");
            a(configurableProvider, "SHA384", a + "$HashMac", a + "$KeyGenerator");
            a(configurableProvider, "SHA384", PKCSObjectIdentifiers.L);
        }
    }

    public static class OldSHA384 extends BaseMac {
        public OldSHA384() {
            super(new OldHMac(new SHA384Digest()));
        }
    }

    private SHA384() {
    }
}
