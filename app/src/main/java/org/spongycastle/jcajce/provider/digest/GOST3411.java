package org.spongycastle.jcajce.provider.digest;

import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.digests.GOST3411Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;

public class GOST3411 {

    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new GOST3411Digest());
        }

        public Object clone() {
            Digest digest = (Digest) super.clone();
            digest.a = new GOST3411Digest((GOST3411Digest) this.a);
            return digest;
        }
    }

    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new GOST3411Digest()));
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACGOST3411", 256, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String a = GOST3411.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("MessageDigest.GOST3411", a + "$Digest");
            configurableProvider.a("Alg.Alias.MessageDigest.GOST", "GOST3411");
            configurableProvider.a("Alg.Alias.MessageDigest.GOST-3411", "GOST3411");
            configurableProvider.a("Alg.Alias.MessageDigest." + CryptoProObjectIdentifiers.b, "GOST3411");
            configurableProvider.a("SecretKeyFactory.PBEWITHHMACGOST3411", a + "$PBEWithMacKeyFactory");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + CryptoProObjectIdentifiers.b, "PBEWITHHMACGOST3411");
            a(configurableProvider, "GOST3411", a + "$HashMac", a + "$KeyGenerator");
            a(configurableProvider, "GOST3411", CryptoProObjectIdentifiers.b);
        }
    }

    public static class PBEWithMacKeyFactory extends PBESecretKeyFactory {
        public PBEWithMacKeyFactory() {
            super("PBEwithHmacGOST3411", null, false, 2, 6, 256, 0);
        }
    }

    private GOST3411() {
    }
}
