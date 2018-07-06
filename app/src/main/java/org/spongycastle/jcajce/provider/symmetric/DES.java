package org.spongycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.engines.DESEngine;
import org.spongycastle.crypto.engines.RFC3211WrapEngine;
import org.spongycastle.crypto.generators.DESKeyGenerator;
import org.spongycastle.crypto.macs.CBCBlockCipherMac;
import org.spongycastle.crypto.macs.CFBBlockCipherMac;
import org.spongycastle.crypto.macs.CMac;
import org.spongycastle.crypto.macs.ISO9797Alg3Mac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.paddings.ISO7816d4Padding;
import org.spongycastle.crypto.params.DESParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.spongycastle.jcajce.provider.symmetric.util.PBE.Util;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class DES {

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
            super(new CBCBlockCipher(new DESEngine()), 64);
        }
    }

    public static class CBCMAC extends BaseMac {
        public CBCMAC() {
            super(new CBCBlockCipherMac(new DESEngine()));
        }
    }

    public static class CMAC extends BaseMac {
        public CMAC() {
            super(new CMac(new DESEngine()));
        }
    }

    public static class DES64 extends BaseMac {
        public DES64() {
            super(new CBCBlockCipherMac(new DESEngine(), 64));
        }
    }

    public static class DES64with7816d4 extends BaseMac {
        public DES64with7816d4() {
            super(new CBCBlockCipherMac(new DESEngine(), 64, new ISO7816d4Padding()));
        }
    }

    public static class DES9797Alg3 extends BaseMac {
        public DES9797Alg3() {
            super(new ISO9797Alg3Mac(new DESEngine()));
        }
    }

    public static class DES9797Alg3with7816d4 extends BaseMac {
        public DES9797Alg3with7816d4() {
            super(new ISO9797Alg3Mac(new DESEngine(), new ISO7816d4Padding()));
        }
    }

    public static class DESCFB8 extends BaseMac {
        public DESCFB8() {
            super(new CFBBlockCipherMac(new DESEngine()));
        }
    }

    public static class DESPBEKeyFactory extends BaseSecretKeyFactory {
        private boolean c;
        private int d;
        private int e;
        private int f;
        private int g;

        public DESPBEKeyFactory(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, int i, int i2, int i3, int i4) {
            super(str, aSN1ObjectIdentifier);
            this.c = z;
            this.d = i;
            this.e = i2;
            this.f = i3;
            this.g = i4;
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec) {
            if (keySpec instanceof PBEKeySpec) {
                PBEKeySpec pBEKeySpec = (PBEKeySpec) keySpec;
                if (pBEKeySpec.getSalt() == null) {
                    return new BCPBEKey(this.a, this.b, this.d, this.e, this.f, this.g, pBEKeySpec, null);
                }
                CipherParameters a;
                KeyParameter keyParameter;
                if (this.c) {
                    a = Util.a(pBEKeySpec, this.d, this.e, this.f, this.g);
                } else {
                    a = Util.a(pBEKeySpec, this.d, this.e, this.f);
                }
                if (a instanceof ParametersWithIV) {
                    keyParameter = (KeyParameter) ((ParametersWithIV) a).b();
                } else {
                    keyParameter = (KeyParameter) a;
                }
                DESParameters.a(keyParameter.a());
                return new BCPBEKey(this.a, this.b, this.d, this.e, this.f, this.g, pBEKeySpec, a);
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new DESEngine());
        }
    }

    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("DES", null);
        }

        protected KeySpec engineGetKeySpec(SecretKey secretKey, Class cls) {
            if (cls == null) {
                throw new InvalidKeySpecException("keySpec parameter is null");
            } else if (secretKey == null) {
                throw new InvalidKeySpecException("key parameter is null");
            } else if (SecretKeySpec.class.isAssignableFrom(cls)) {
                return new SecretKeySpec(secretKey.getEncoded(), this.a);
            } else {
                if (DESKeySpec.class.isAssignableFrom(cls)) {
                    try {
                        return new DESKeySpec(secretKey.getEncoded());
                    } catch (Exception e) {
                        throw new InvalidKeySpecException(e.toString());
                    }
                }
                throw new InvalidKeySpecException("Invalid KeySpec");
            }
        }

        protected SecretKey engineGenerateSecret(KeySpec keySpec) {
            if (keySpec instanceof DESKeySpec) {
                return new SecretKeySpec(((DESKeySpec) keySpec).getKey(), "DES");
            }
            return super.engineGenerateSecret(keySpec);
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("DES", 64, new DESKeyGenerator());
        }

        protected void engineInit(int i, SecureRandom secureRandom) {
            super.engineInit(i, secureRandom);
        }

        protected SecretKey engineGenerateKey() {
            if (this.e) {
                this.d.a(new KeyGenerationParameters(new SecureRandom(), this.c));
                this.e = false;
            }
            return new SecretKeySpec(this.d.a(), this.a);
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = DES.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.DES", a + "$ECB");
            configurableProvider.a("Cipher." + OIWObjectIdentifiers.e, a + "$CBC");
            a(configurableProvider, OIWObjectIdentifiers.e, "DES");
            configurableProvider.a("Cipher.DESRFC3211WRAP", a + "$RFC3211");
            configurableProvider.a("KeyGenerator.DES", a + "$KeyGenerator");
            configurableProvider.a("SecretKeyFactory.DES", a + "$KeyFactory");
            configurableProvider.a("Mac.DESCMAC", a + "$CMAC");
            configurableProvider.a("Mac.DESMAC", a + "$CBCMAC");
            configurableProvider.a("Alg.Alias.Mac.DES", "DESMAC");
            configurableProvider.a("Mac.DESMAC/CFB8", a + "$DESCFB8");
            configurableProvider.a("Alg.Alias.Mac.DES/CFB8", "DESMAC/CFB8");
            configurableProvider.a("Mac.DESMAC64", a + "$DES64");
            configurableProvider.a("Alg.Alias.Mac.DES64", "DESMAC64");
            configurableProvider.a("Mac.DESMAC64WITHISO7816-4PADDING", a + "$DES64with7816d4");
            configurableProvider.a("Alg.Alias.Mac.DES64WITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
            configurableProvider.a("Alg.Alias.Mac.DESISO9797ALG1MACWITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
            configurableProvider.a("Alg.Alias.Mac.DESISO9797ALG1WITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
            configurableProvider.a("Mac.DESWITHISO9797", a + "$DES9797Alg3");
            configurableProvider.a("Alg.Alias.Mac.DESISO9797MAC", "DESWITHISO9797");
            configurableProvider.a("Mac.ISO9797ALG3MAC", a + "$DES9797Alg3");
            configurableProvider.a("Alg.Alias.Mac.ISO9797ALG3", "ISO9797ALG3MAC");
            configurableProvider.a("Mac.ISO9797ALG3WITHISO7816-4PADDING", a + "$DES9797Alg3with7816d4");
            configurableProvider.a("Alg.Alias.Mac.ISO9797ALG3MACWITHISO7816-4PADDING", "ISO9797ALG3WITHISO7816-4PADDING");
            configurableProvider.a("AlgorithmParameters.DES", "org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + OIWObjectIdentifiers.e, "DES");
            configurableProvider.a("AlgorithmParameterGenerator.DES", a + "$AlgParamGen");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + OIWObjectIdentifiers.e, "DES");
            configurableProvider.a("Cipher.PBEWITHMD2ANDDES", a + "$PBEWithMD2");
            configurableProvider.a("Cipher.PBEWITHMD5ANDDES", a + "$PBEWithMD5");
            configurableProvider.a("Cipher.PBEWITHSHA1ANDDES", a + "$PBEWithSHA1");
            configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.s_, "PBEWITHMD2ANDDES");
            configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.u, "PBEWITHMD5ANDDES");
            configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.w, "PBEWITHSHA1ANDDES");
            configurableProvider.a("SecretKeyFactory.PBEWITHMD2ANDDES", a + "$PBEWithMD2KeyFactory");
            configurableProvider.a("SecretKeyFactory.PBEWITHMD5ANDDES", a + "$PBEWithMD5KeyFactory");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHA1ANDDES", a + "$PBEWithSHA1KeyFactory");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHMD2ANDDES-CBC", "PBEWITHMD2ANDDES");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHMD5ANDDES-CBC", "PBEWITHMD5ANDDES");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA1ANDDES-CBC", "PBEWITHSHA1ANDDES");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.s_, "PBEWITHMD2ANDDES");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.u, "PBEWITHMD5ANDDES");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.w, "PBEWITHSHA1ANDDES");
        }

        private void a(ConfigurableProvider configurableProvider, ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
            configurableProvider.a("Alg.Alias.KeyGenerator." + aSN1ObjectIdentifier.d(), str);
            configurableProvider.a("Alg.Alias.KeyFactory." + aSN1ObjectIdentifier.d(), str);
        }
    }

    public static class PBEWithMD2 extends BaseBlockCipher {
        public PBEWithMD2() {
            super(new CBCBlockCipher(new DESEngine()));
        }
    }

    public static class PBEWithMD2KeyFactory extends DESPBEKeyFactory {
        public PBEWithMD2KeyFactory() {
            super("PBEwithMD2andDES", PKCSObjectIdentifiers.s_, true, 0, 5, 64, 64);
        }
    }

    public static class PBEWithMD5 extends BaseBlockCipher {
        public PBEWithMD5() {
            super(new CBCBlockCipher(new DESEngine()));
        }
    }

    public static class PBEWithMD5KeyFactory extends DESPBEKeyFactory {
        public PBEWithMD5KeyFactory() {
            super("PBEwithMD5andDES", PKCSObjectIdentifiers.u, true, 0, 0, 64, 64);
        }
    }

    public static class PBEWithSHA1 extends BaseBlockCipher {
        public PBEWithSHA1() {
            super(new CBCBlockCipher(new DESEngine()));
        }
    }

    public static class PBEWithSHA1KeyFactory extends DESPBEKeyFactory {
        public PBEWithSHA1KeyFactory() {
            super("PBEwithSHA1andDES", PKCSObjectIdentifiers.w, true, 0, 1, 64, 64);
        }
    }

    public static class RFC3211 extends BaseWrapCipher {
        public RFC3211() {
            super(new RFC3211WrapEngine(new DESEngine()), 8);
        }
    }

    private DES() {
    }
}
