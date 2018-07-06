package org.spongycastle.jcajce.provider.digest;

import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;

public class SHA256 {

    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new SHA256Digest());
        }

        public Object clone() {
            Digest digest = (Digest) super.clone();
            digest.a = new SHA256Digest((SHA256Digest) this.a);
            return digest;
        }
    }

    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new SHA256Digest()));
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACSHA256", 256, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String a = SHA256.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("MessageDigest.SHA-256", a + "$Digest");
            configurableProvider.a("Alg.Alias.MessageDigest.SHA256", "SHA-256");
            configurableProvider.a("Alg.Alias.MessageDigest." + NISTObjectIdentifiers.c, "SHA-256");
            configurableProvider.a("SecretKeyFactory.PBEWITHHMACSHA256", a + "$PBEWithMacKeyFactory");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHHMACSHA-256", "PBEWITHHMACSHA256");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + NISTObjectIdentifiers.c, "PBEWITHHMACSHA256");
            a(configurableProvider, "SHA256", a + "$HashMac", a + "$KeyGenerator");
            a(configurableProvider, "SHA256", PKCSObjectIdentifiers.K);
            a(configurableProvider, "SHA256", NISTObjectIdentifiers.c);
        }
    }

    public static class PBEWithMacKeyFactory extends PBESecretKeyFactory {
        public PBEWithMacKeyFactory() {
            super("PBEwithHmacSHA256", null, false, 2, 4, 256, 0);
        }
    }

    private SHA256() {
    }
}
