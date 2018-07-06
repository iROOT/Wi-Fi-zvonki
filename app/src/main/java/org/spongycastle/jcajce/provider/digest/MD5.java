package org.spongycastle.jcajce.provider.digest;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.asn1.iana.IANAObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;

public class MD5 {

    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new MD5Digest());
        }

        public Object clone() {
            Digest digest = (Digest) super.clone();
            digest.a = new MD5Digest((MD5Digest) this.a);
            return digest;
        }
    }

    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new MD5Digest()));
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACMD5", NotificationCompat.FLAG_HIGH_PRIORITY, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String a = MD5.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("MessageDigest.MD5", a + "$Digest");
            configurableProvider.a("Alg.Alias.MessageDigest." + PKCSObjectIdentifiers.H, "MD5");
            a(configurableProvider, "MD5", a + "$HashMac", a + "$KeyGenerator");
            a(configurableProvider, "MD5", IANAObjectIdentifiers.n);
        }
    }

    private MD5() {
    }
}
