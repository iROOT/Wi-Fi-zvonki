package org.spongycastle.jcajce.provider.digest;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.digests.RIPEMD128Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;

public class RIPEMD128 {

    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new RIPEMD128Digest());
        }

        public Object clone() {
            Digest digest = (Digest) super.clone();
            digest.a = new RIPEMD128Digest((RIPEMD128Digest) this.a);
            return digest;
        }
    }

    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new RIPEMD128Digest()));
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACRIPEMD128", NotificationCompat.FLAG_HIGH_PRIORITY, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String a = RIPEMD128.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("MessageDigest.RIPEMD128", a + "$Digest");
            configurableProvider.a("Alg.Alias.MessageDigest." + TeleTrusTObjectIdentifiers.c, "RIPEMD128");
            a(configurableProvider, "RIPEMD128", a + "$HashMac", a + "$KeyGenerator");
        }
    }

    private RIPEMD128() {
    }
}
