package org.spongycastle.jcajce.provider.digest;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.digests.MD4Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;

public class MD4 {

    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new MD4Digest());
        }

        public Object clone() {
            Digest digest = (Digest) super.clone();
            digest.a = new MD4Digest((MD4Digest) this.a);
            return digest;
        }
    }

    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new MD4Digest()));
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACMD4", NotificationCompat.FLAG_HIGH_PRIORITY, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String a = MD4.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("MessageDigest.MD4", a + "$Digest");
            configurableProvider.a("Alg.Alias.MessageDigest." + PKCSObjectIdentifiers.G, "MD4");
            a(configurableProvider, "MD4", a + "$HashMac", a + "$KeyGenerator");
        }
    }

    private MD4() {
    }
}
