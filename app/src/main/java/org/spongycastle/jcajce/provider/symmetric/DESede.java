package org.spongycastle.jcajce.provider.symmetric;

import android.support.v4.app.NotificationCompat;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.engines.DESedeWrapEngine;
import org.spongycastle.crypto.engines.RFC3211WrapEngine;
import org.spongycastle.crypto.generators.DESedeKeyGenerator;
import org.spongycastle.crypto.macs.CBCBlockCipherMac;
import org.spongycastle.crypto.macs.CFBBlockCipherMac;
import org.spongycastle.crypto.macs.CMac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.paddings.ISO7816d4Padding;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.DES.DESPBEKeyFactory;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class DESede {

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for DES parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[8];
            if (this.b == null) {
                this.b = new SecureRandom();
            }
            this.b.nextBytes(bArr);
            try {
                AlgorithmParameters instance = AlgorithmParameters.getInstance("DES", "SC");
                instance.init(new IvParameterSpec(bArr));
                return instance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new DESedeEngine()), 64);
        }
    }

    public static class CBCMAC extends BaseMac {
        public CBCMAC() {
            super(new CBCBlockCipherMac(new DESedeEngine()));
        }
    }

    public static class CMAC extends BaseMac {
        public CMAC() {
            super(new CMac(new DESedeEngine()));
        }
    }

    public static class DESede64 extends BaseMac {
        public DESede64() {
            super(new CBCBlockCipherMac(new DESedeEngine(), 64));
        }
    }

    public static class DESede64with7816d4 extends BaseMac {
        public DESede64with7816d4() {
            super(new CBCBlockCipherMac(new DESedeEngine(), 64, new ISO7816d4Padding()));
        }
    }

    public static class DESedeCFB8 extends BaseMac {
        public DESedeCFB8() {
            super(new CFBBlockCipherMac(new DESedeEngine()));
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new DESedeEngine());
        }
    }

    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("DESede", null);
        }

        protected KeySpec engineGetKeySpec(SecretKey secretKey, Class cls) {
            if (cls == null) {
                throw new InvalidKeySpecException("keySpec parameter is null");
            } else if (secretKey == null) {
                throw new InvalidKeySpecException("key parameter is null");
            } else if (SecretKeySpec.class.isAssignableFrom(cls)) {
                return new SecretKeySpec(secretKey.getEncoded(), this.a);
            } else {
                if (DESedeKeySpec.class.isAssignableFrom(cls)) {
                    Object encoded = secretKey.getEncoded();
                    try {
                        if (encoded.length != 16) {
                            return new DESedeKeySpec(encoded);
                        }
                        Object obj = new byte[24];
                        System.arraycopy(encoded, 0, obj, 0, 16);
                        System.arraycopy(encoded, 0, obj, 16, 8);
                        return new DESedeKeySpec(obj);
                    } catch (Exception e) {
                        throw new InvalidKeySpecException(e.toString());
                    }
                }
                throw new InvalidKeySpecException("Invalid KeySpec");
            }
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec) {
            if (keySpec instanceof DESedeKeySpec) {
                return new SecretKeySpec(((DESedeKeySpec) keySpec).getKey(), "DESede");
            }
            return super.engineGenerateSecret(keySpec);
        }
    }

    public static class KeyGenerator3 extends BaseKeyGenerator {
        public KeyGenerator3() {
            super("DESede3", 192, new DESedeKeyGenerator());
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        private boolean f = false;

        public KeyGenerator() {
            super("DESede", 192, new DESedeKeyGenerator());
        }

        protected void engineInit(int i, SecureRandom secureRandom) {
            super.engineInit(i, secureRandom);
            this.f = true;
        }

        protected SecretKey engineGenerateKey() {
            if (this.e) {
                this.d.a(new KeyGenerationParameters(new SecureRandom(), this.c));
                this.e = false;
            }
            if (this.f) {
                return new SecretKeySpec(this.d.a(), this.a);
            }
            Object a = this.d.a();
            System.arraycopy(a, 0, a, 16, 8);
            return new SecretKeySpec(a, this.a);
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = DESede.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.DESEDE", a + "$ECB");
            configurableProvider.a("Cipher." + PKCSObjectIdentifiers.B, a + "$CBC");
            configurableProvider.a("Cipher.DESEDEWRAP", a + "$Wrap");
            configurableProvider.a("Cipher." + PKCSObjectIdentifiers.bB, a + "$Wrap");
            configurableProvider.a("Cipher.DESEDERFC3211WRAP", a + "$RFC3211");
            configurableProvider.a("Alg.Alias.Cipher.TDEA", "DESEDE");
            configurableProvider.a("Alg.Alias.Cipher.TDEAWRAP", "DESEDEWRAP");
            configurableProvider.a("Alg.Alias.KeyGenerator.TDEA", "DESEDE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.TDEA", "DESEDE");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator.TDEA", "DESEDE");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.TDEA", "DESEDE");
            if (configurableProvider.b("MessageDigest", "SHA-1")) {
                configurableProvider.a("Cipher.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", a + "$PBEWithSHAAndDES3Key");
                configurableProvider.a("Cipher.BROKENPBEWITHSHAAND3-KEYTRIPLEDES-CBC", a + "$BrokePBEWithSHAAndDES3Key");
                configurableProvider.a("Cipher.OLDPBEWITHSHAAND3-KEYTRIPLEDES-CBC", a + "$OldPBEWithSHAAndDES3Key");
                configurableProvider.a("Cipher.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", a + "$PBEWithSHAAndDES2Key");
                configurableProvider.a("Cipher.BROKENPBEWITHSHAAND2-KEYTRIPLEDES-CBC", a + "$BrokePBEWithSHAAndDES2Key");
                configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.bw, "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.bx, "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1ANDDESEDE", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND3-KEYTRIPLEDES-CBC", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND2-KEYTRIPLEDES-CBC", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
            }
            configurableProvider.a("KeyGenerator.DESEDE", a + "$KeyGenerator");
            configurableProvider.a("KeyGenerator." + PKCSObjectIdentifiers.B, a + "$KeyGenerator3");
            configurableProvider.a("KeyGenerator.DESEDEWRAP", a + "$KeyGenerator");
            configurableProvider.a("SecretKeyFactory.DESEDE", a + "$KeyFactory");
            configurableProvider.a("Mac.DESEDECMAC", a + "$CMAC");
            configurableProvider.a("Mac.DESEDEMAC", a + "$CBCMAC");
            configurableProvider.a("Alg.Alias.Mac.DESEDE", "DESEDEMAC");
            configurableProvider.a("Mac.DESEDEMAC/CFB8", a + "$DESedeCFB8");
            configurableProvider.a("Alg.Alias.Mac.DESEDE/CFB8", "DESEDEMAC/CFB8");
            configurableProvider.a("Mac.DESEDEMAC64", a + "$DESede64");
            configurableProvider.a("Alg.Alias.Mac.DESEDE64", "DESEDEMAC64");
            configurableProvider.a("Mac.DESEDEMAC64WITHISO7816-4PADDING", a + "$DESede64with7816d4");
            configurableProvider.a("Alg.Alias.Mac.DESEDE64WITHISO7816-4PADDING", "DESEDEMAC64WITHISO7816-4PADDING");
            configurableProvider.a("Alg.Alias.Mac.DESEDEISO9797ALG1MACWITHISO7816-4PADDING", "DESEDEMAC64WITHISO7816-4PADDING");
            configurableProvider.a("Alg.Alias.Mac.DESEDEISO9797ALG1WITHISO7816-4PADDING", "DESEDEMAC64WITHISO7816-4PADDING");
            configurableProvider.a("AlgorithmParameters.DESEDE", "org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.B, "DESEDE");
            configurableProvider.a("AlgorithmParameterGenerator.DESEDE", a + "$AlgParamGen");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + PKCSObjectIdentifiers.B, "DESEDE");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", a + "$PBEWithSHAAndDES3KeyFactory");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", a + "$PBEWithSHAAndDES2KeyFactory");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND3-KEYTRIPLEDES", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND2-KEYTRIPLEDES", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDDES3KEY-CBC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDDES2KEY-CBC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.3", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.4", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWithSHAAnd3KeyTripleDES", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.3", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.4", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.Cipher.PBEWithSHAAnd3KeyTripleDES", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
        }
    }

    public static class PBEWithSHAAndDES2Key extends BaseBlockCipher {
        public PBEWithSHAAndDES2Key() {
            super(new CBCBlockCipher(new DESedeEngine()));
        }
    }

    public static class PBEWithSHAAndDES2KeyFactory extends DESPBEKeyFactory {
        public PBEWithSHAAndDES2KeyFactory() {
            super("PBEwithSHAandDES2Key-CBC", PKCSObjectIdentifiers.bx, true, 2, 1, NotificationCompat.FLAG_HIGH_PRIORITY, 64);
        }
    }

    public static class PBEWithSHAAndDES3Key extends BaseBlockCipher {
        public PBEWithSHAAndDES3Key() {
            super(new CBCBlockCipher(new DESedeEngine()));
        }
    }

    public static class PBEWithSHAAndDES3KeyFactory extends DESPBEKeyFactory {
        public PBEWithSHAAndDES3KeyFactory() {
            super("PBEwithSHAandDES3Key-CBC", PKCSObjectIdentifiers.bw, true, 2, 1, 192, 64);
        }
    }

    public static class RFC3211 extends BaseWrapCipher {
        public RFC3211() {
            super(new RFC3211WrapEngine(new DESedeEngine()), 8);
        }
    }

    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new DESedeWrapEngine());
        }
    }

    private DESede() {
    }
}
