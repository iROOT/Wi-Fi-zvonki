package org.spongycastle.jcajce.provider.digest;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import org.spongycastle.asn1.iana.IANAObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.spongycastle.jcajce.provider.symmetric.util.PBE.Util;
import org.spongycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;

public class SHA1 {

    public static class BasePBKDF2WithHmacSHA1 extends BaseSecretKeyFactory {
        private int c;

        public BasePBKDF2WithHmacSHA1(String str, int i) {
            super(str, PKCSObjectIdentifiers.z);
            this.c = i;
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec) {
            if (keySpec instanceof PBEKeySpec) {
                PBEKeySpec pBEKeySpec = (PBEKeySpec) keySpec;
                if (pBEKeySpec.getSalt() == null) {
                    throw new InvalidKeySpecException("missing required salt");
                } else if (pBEKeySpec.getIterationCount() <= 0) {
                    throw new InvalidKeySpecException("positive iteration count required: " + pBEKeySpec.getIterationCount());
                } else if (pBEKeySpec.getKeyLength() <= 0) {
                    throw new InvalidKeySpecException("positive key length required: " + pBEKeySpec.getKeyLength());
                } else if (pBEKeySpec.getPassword().length == 0) {
                    throw new IllegalArgumentException("password empty");
                } else {
                    int keyLength = pBEKeySpec.getKeyLength();
                    return new BCPBEKey(this.a, this.b, this.c, 1, keyLength, -1, pBEKeySpec, Util.a(pBEKeySpec, this.c, 1, keyLength));
                }
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new SHA1Digest());
        }

        public Object clone() {
            Digest digest = (Digest) super.clone();
            digest.a = new SHA1Digest((SHA1Digest) this.a);
            return digest;
        }
    }

    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new SHA1Digest()));
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACSHA1", 160, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends DigestAlgorithmProvider {
        private static final String a = SHA1.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("MessageDigest.SHA-1", a + "$Digest");
            configurableProvider.a("Alg.Alias.MessageDigest.SHA1", "SHA-1");
            configurableProvider.a("Alg.Alias.MessageDigest.SHA", "SHA-1");
            configurableProvider.a("Alg.Alias.MessageDigest." + OIWObjectIdentifiers.i, "SHA-1");
            a(configurableProvider, "SHA1", a + "$HashMac", a + "$KeyGenerator");
            a(configurableProvider, "SHA1", PKCSObjectIdentifiers.I);
            a(configurableProvider, "SHA1", IANAObjectIdentifiers.o);
            configurableProvider.a("Mac.PBEWITHHMACSHA", a + "$SHA1Mac");
            configurableProvider.a("Mac.PBEWITHHMACSHA1", a + "$SHA1Mac");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHHMACSHA", "PBEWITHHMACSHA1");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + OIWObjectIdentifiers.i, "PBEWITHHMACSHA1");
            configurableProvider.a("Alg.Alias.Mac." + OIWObjectIdentifiers.i, "PBEWITHHMACSHA");
            configurableProvider.a("SecretKeyFactory.PBEWITHHMACSHA1", a + "$PBEWithMacKeyFactory");
            configurableProvider.a("SecretKeyFactory.PBKDF2WithHmacSHA1", a + "$PBKDF2WithHmacSHA1UTF8");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBKDF2WithHmacSHA1AndUTF8", "PBKDF2WithHmacSHA1");
            configurableProvider.a("SecretKeyFactory.PBKDF2WithHmacSHA1And8BIT", a + "$PBKDF2WithHmacSHA18BIT");
        }
    }

    public static class PBEWithMacKeyFactory extends PBESecretKeyFactory {
        public PBEWithMacKeyFactory() {
            super("PBEwithHmacSHA", null, false, 2, 1, 160, 0);
        }
    }

    public static class PBKDF2WithHmacSHA18BIT extends BasePBKDF2WithHmacSHA1 {
        public PBKDF2WithHmacSHA18BIT() {
            super("PBKDF2WithHmacSHA1And8bit", 1);
        }
    }

    public static class PBKDF2WithHmacSHA1UTF8 extends BasePBKDF2WithHmacSHA1 {
        public PBKDF2WithHmacSHA1UTF8() {
            super("PBKDF2WithHmacSHA1", 5);
        }
    }

    public static class SHA1Mac extends BaseMac {
        public SHA1Mac() {
            super(new HMac(new SHA1Digest()));
        }
    }

    private SHA1() {
    }
}
