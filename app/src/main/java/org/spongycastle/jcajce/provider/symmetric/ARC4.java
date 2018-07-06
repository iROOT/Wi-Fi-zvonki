package org.spongycastle.jcajce.provider.symmetric;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.RC4Engine;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseStreamCipher;
import org.spongycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class ARC4 {

    public static class Base extends BaseStreamCipher {
        public Base() {
            super(new RC4Engine(), 0);
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("RC4", NotificationCompat.FLAG_HIGH_PRIORITY, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = ARC4.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.ARC4", a + "$Base");
            configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.D, "ARC4");
            configurableProvider.a("Alg.Alias.Cipher.ARCFOUR", "ARC4");
            configurableProvider.a("Alg.Alias.Cipher.RC4", "ARC4");
            configurableProvider.a("KeyGenerator.ARC4", a + "$KeyGen");
            configurableProvider.a("Alg.Alias.KeyGenerator.RC4", "ARC4");
            configurableProvider.a("Alg.Alias.KeyGenerator.1.2.840.113549.3.4", "ARC4");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAAND128BITRC4", a + "$PBEWithSHAAnd128BitKeyFactory");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAAND40BITRC4", a + "$PBEWithSHAAnd40BitKeyFactory");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.bu, "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.bv, "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND40BITRC4", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND128BITRC4", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDRC4", "PKCS12PBE");
            configurableProvider.a("Cipher.PBEWITHSHAAND128BITRC4", a + "$PBEWithSHAAnd128Bit");
            configurableProvider.a("Cipher.PBEWITHSHAAND40BITRC4", a + "$PBEWithSHAAnd40Bit");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.bu, "PBEWITHSHAAND128BITRC4");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.bv, "PBEWITHSHAAND40BITRC4");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND128BITRC4", "PBEWITHSHAAND128BITRC4");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND40BITRC4", "PBEWITHSHAAND40BITRC4");
            configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.bu, "PBEWITHSHAAND128BITRC4");
            configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.bv, "PBEWITHSHAAND40BITRC4");
        }
    }

    public static class PBEWithSHAAnd128Bit extends BaseStreamCipher {
        public PBEWithSHAAnd128Bit() {
            super(new RC4Engine(), 0);
        }
    }

    public static class PBEWithSHAAnd128BitKeyFactory extends PBESecretKeyFactory {
        public PBEWithSHAAnd128BitKeyFactory() {
            super("PBEWithSHAAnd128BitRC4", PKCSObjectIdentifiers.bu, true, 2, 1, NotificationCompat.FLAG_HIGH_PRIORITY, 0);
        }
    }

    public static class PBEWithSHAAnd40Bit extends BaseStreamCipher {
        public PBEWithSHAAnd40Bit() {
            super(new RC4Engine(), 0);
        }
    }

    public static class PBEWithSHAAnd40BitKeyFactory extends PBESecretKeyFactory {
        public PBEWithSHAAnd40BitKeyFactory() {
            super("PBEWithSHAAnd128BitRC4", PKCSObjectIdentifiers.bu, true, 2, 1, 40, 0);
        }
    }

    private ARC4() {
    }
}
