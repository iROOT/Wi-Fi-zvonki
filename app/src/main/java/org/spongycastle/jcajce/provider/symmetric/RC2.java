package org.spongycastle.jcajce.provider.symmetric;

import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RC2CBCParameter;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.RC2Engine;
import org.spongycastle.crypto.engines.RC2WrapEngine;
import org.spongycastle.crypto.macs.CBCBlockCipherMac;
import org.spongycastle.crypto.macs.CFBBlockCipherMac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.spongycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;
import org.spongycastle.util.Arrays;

public final class RC2 {

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        RC2ParameterSpec a = null;

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            if (algorithmParameterSpec instanceof RC2ParameterSpec) {
                this.a = (RC2ParameterSpec) algorithmParameterSpec;
                return;
            }
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for RC2 parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters() {
            if (this.a == null) {
                byte[] bArr = new byte[8];
                if (this.b == null) {
                    this.b = new SecureRandom();
                }
                this.b.nextBytes(bArr);
                try {
                    AlgorithmParameters instance = AlgorithmParameters.getInstance("RC2", "SC");
                    instance.init(new IvParameterSpec(bArr));
                    return instance;
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            try {
                instance = AlgorithmParameters.getInstance("RC2", "SC");
                instance.init(this.a);
                return instance;
            } catch (Exception e2) {
                throw new RuntimeException(e2.getMessage());
            }
        }
    }

    public static class AlgParams extends BaseAlgorithmParameters {
        private static final short[] a = new short[]{(short) 189, (short) 86, (short) 234, (short) 242, (short) 162, (short) 241, (short) 172, (short) 42, (short) 176, (short) 147, (short) 209, (short) 156, (short) 27, (short) 51, (short) 253, (short) 208, (short) 48, (short) 4, (short) 182, (short) 220, (short) 125, (short) 223, (short) 50, (short) 75, (short) 247, (short) 203, (short) 69, (short) 155, (short) 49, (short) 187, (short) 33, (short) 90, (short) 65, (short) 159, (short) 225, (short) 217, (short) 74, (short) 77, (short) 158, (short) 218, (short) 160, (short) 104, (short) 44, (short) 195, (short) 39, (short) 95, (short) 128, (short) 54, (short) 62, (short) 238, (short) 251, (short) 149, (short) 26, (short) 254, (short) 206, (short) 168, (short) 52, (short) 169, (short) 19, (short) 240, (short) 166, (short) 63, (short) 216, (short) 12, (short) 120, (short) 36, (short) 175, (short) 35, (short) 82, (short) 193, (short) 103, (short) 23, (short) 245, (short) 102, (short) 144, (short) 231, (short) 232, (short) 7, (short) 184, (short) 96, (short) 72, (short) 230, (short) 30, (short) 83, (short) 243, (short) 146, (short) 164, (short) 114, (short) 140, (short) 8, (short) 21, (short) 110, (short) 134, (short) 0, (short) 132, (short) 250, (short) 244, (short) 127, (short) 138, (short) 66, (short) 25, (short) 246, (short) 219, (short) 205, (short) 20, (short) 141, (short) 80, (short) 18, (short) 186, (short) 60, (short) 6, (short) 78, (short) 236, (short) 179, (short) 53, (short) 17, (short) 161, (short) 136, (short) 142, (short) 43, (short) 148, (short) 153, (short) 183, (short) 113, (short) 116, (short) 211, (short) 228, (short) 191, (short) 58, (short) 222, (short) 150, (short) 14, (short) 188, (short) 10, (short) 237, (short) 119, (short) 252, (short) 55, (short) 107, (short) 3, (short) 121, (short) 137, (short) 98, (short) 198, (short) 215, (short) 192, (short) 210, (short) 124, (short) 106, (short) 139, (short) 34, (short) 163, (short) 91, (short) 5, (short) 93, (short) 2, (short) 117, (short) 213, (short) 97, (short) 227, (short) 24, (short) 143, (short) 85, (short) 81, (short) 173, (short) 31, (short) 11, (short) 94, (short) 133, (short) 229, (short) 194, (short) 87, (short) 99, (short) 202, (short) 61, (short) 108, (short) 180, (short) 197, (short) 204, (short) 112, (short) 178, (short) 145, (short) 89, (short) 13, (short) 71, (short) 32, (short) 200, (short) 79, (short) 88, (short) 224, (short) 1, (short) 226, (short) 22, (short) 56, (short) 196, (short) 111, (short) 59, (short) 15, (short) 101, (short) 70, (short) 190, (short) 126, (short) 45, (short) 123, (short) 130, (short) 249, (short) 64, (short) 181, (short) 29, (short) 115, (short) 248, (short) 235, (short) 38, (short) 199, (short) 135, (short) 151, (short) 37, (short) 84, (short) 177, (short) 40, (short) 170, (short) 152, (short) 157, (short) 165, (short) 100, (short) 109, (short) 122, (short) 212, (short) 16, (short) 129, (short) 68, (short) 239, (short) 73, (short) 214, (short) 174, (short) 46, (short) 221, (short) 118, (short) 92, (short) 47, (short) 167, (short) 28, (short) 201, (short) 9, (short) 105, (short) 154, (short) 131, (short) 207, (short) 41, (short) 57, (short) 185, (short) 233, (short) 76, (short) 255, (short) 67, (short) 171};
        private static final short[] b = new short[]{(short) 93, (short) 190, (short) 155, (short) 139, (short) 17, (short) 153, (short) 110, (short) 77, (short) 89, (short) 243, (short) 133, (short) 166, (short) 63, (short) 183, (short) 131, (short) 197, (short) 228, (short) 115, (short) 107, (short) 58, (short) 104, (short) 90, (short) 192, (short) 71, (short) 160, (short) 100, (short) 52, (short) 12, (short) 241, (short) 208, (short) 82, (short) 165, (short) 185, (short) 30, (short) 150, (short) 67, (short) 65, (short) 216, (short) 212, (short) 44, (short) 219, (short) 248, (short) 7, (short) 119, (short) 42, (short) 202, (short) 235, (short) 239, (short) 16, (short) 28, (short) 22, (short) 13, (short) 56, (short) 114, (short) 47, (short) 137, (short) 193, (short) 249, (short) 128, (short) 196, (short) 109, (short) 174, (short) 48, (short) 61, (short) 206, (short) 32, (short) 99, (short) 254, (short) 230, (short) 26, (short) 199, (short) 184, (short) 80, (short) 232, (short) 36, (short) 23, (short) 252, (short) 37, (short) 111, (short) 187, (short) 106, (short) 163, (short) 68, (short) 83, (short) 217, (short) 162, (short) 1, (short) 171, (short) 188, (short) 182, (short) 31, (short) 152, (short) 238, (short) 154, (short) 167, (short) 45, (short) 79, (short) 158, (short) 142, (short) 172, (short) 224, (short) 198, (short) 73, (short) 70, (short) 41, (short) 244, (short) 148, (short) 138, (short) 175, (short) 225, (short) 91, (short) 195, (short) 179, (short) 123, (short) 87, (short) 209, (short) 124, (short) 156, (short) 237, (short) 135, (short) 64, (short) 140, (short) 226, (short) 203, (short) 147, (short) 20, (short) 201, (short) 97, (short) 46, (short) 229, (short) 204, (short) 246, (short) 94, (short) 168, (short) 92, (short) 214, (short) 117, (short) 141, (short) 98, (short) 149, (short) 88, (short) 105, (short) 118, (short) 161, (short) 74, (short) 181, (short) 85, (short) 9, (short) 120, (short) 51, (short) 130, (short) 215, (short) 221, (short) 121, (short) 245, (short) 27, (short) 11, (short) 222, (short) 38, (short) 33, (short) 40, (short) 116, (short) 4, (short) 151, (short) 86, (short) 223, (short) 60, (short) 240, (short) 55, (short) 57, (short) 220, (short) 255, (short) 6, (short) 164, (short) 234, (short) 66, (short) 8, (short) 218, (short) 180, (short) 113, (short) 176, (short) 207, (short) 18, (short) 122, (short) 78, (short) 250, (short) 108, (short) 29, (short) 132, (short) 0, (short) 200, (short) 127, (short) 145, (short) 69, (short) 170, (short) 43, (short) 194, (short) 177, (short) 143, (short) 213, (short) 186, (short) 242, (short) 173, (short) 25, (short) 178, (short) 103, (short) 54, (short) 247, (short) 15, (short) 10, (short) 146, (short) 125, (short) 227, (short) 157, (short) 233, (short) 144, (short) 62, (short) 35, (short) 39, (short) 102, (short) 19, (short) 236, (short) 129, (short) 21, (short) 189, (short) 34, (short) 191, (short) 159, (short) 126, (short) 169, (short) 81, (short) 75, (short) 76, (short) 251, (short) 2, (short) 211, (short) 112, (short) 134, (short) 49, (short) 231, (short) 59, (short) 5, (short) 3, (short) 84, (short) 96, (short) 72, (short) 101, (short) 24, (short) 210, (short) 205, (short) 95, (short) 50, (short) 136, (short) 14, (short) 53, (short) 253};
        private byte[] c;
        private int d = 58;

        protected byte[] engineGetEncoded() {
            return Arrays.b(this.c);
        }

        protected byte[] engineGetEncoded(String str) {
            if (a(str)) {
                if (this.d == -1) {
                    return new RC2CBCParameter(engineGetEncoded()).b();
                }
                return new RC2CBCParameter(this.d, engineGetEncoded()).b();
            } else if (str.equals("RAW")) {
                return engineGetEncoded();
            } else {
                return null;
            }
        }

        protected AlgorithmParameterSpec a(Class cls) {
            if (cls != RC2ParameterSpec.class || this.d == -1) {
                if (cls == IvParameterSpec.class) {
                    return new IvParameterSpec(this.c);
                }
                throw new InvalidParameterSpecException("unknown parameter spec passed to RC2 parameters object.");
            } else if (this.d < 256) {
                return new RC2ParameterSpec(b[this.d], this.c);
            } else {
                return new RC2ParameterSpec(this.d, this.c);
            }
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                this.c = ((IvParameterSpec) algorithmParameterSpec).getIV();
            } else if (algorithmParameterSpec instanceof RC2ParameterSpec) {
                int effectiveKeyBits = ((RC2ParameterSpec) algorithmParameterSpec).getEffectiveKeyBits();
                if (effectiveKeyBits != -1) {
                    if (effectiveKeyBits < 256) {
                        this.d = a[effectiveKeyBits];
                    } else {
                        this.d = effectiveKeyBits;
                    }
                }
                this.c = ((RC2ParameterSpec) algorithmParameterSpec).getIV();
            } else {
                throw new InvalidParameterSpecException("IvParameterSpec or RC2ParameterSpec required to initialise a RC2 parameters algorithm parameters object");
            }
        }

        protected void engineInit(byte[] bArr) {
            this.c = Arrays.b(bArr);
        }

        protected void engineInit(byte[] bArr, String str) {
            if (a(str)) {
                RC2CBCParameter a = RC2CBCParameter.a(ASN1Primitive.a(bArr));
                if (a.d() != null) {
                    this.d = a.d().intValue();
                }
                this.c = a.e();
            } else if (str.equals("RAW")) {
                engineInit(bArr);
            } else {
                throw new IOException("Unknown parameters format in IV parameters object");
            }
        }

        protected String engineToString() {
            return "RC2 Parameters";
        }
    }

    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new RC2Engine()), 64);
        }
    }

    public static class CBCMAC extends BaseMac {
        public CBCMAC() {
            super(new CBCBlockCipherMac(new RC2Engine()));
        }
    }

    public static class CFB8MAC extends BaseMac {
        public CFB8MAC() {
            super(new CFBBlockCipherMac(new RC2Engine()));
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new RC2Engine());
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("RC2", NotificationCompat.FLAG_HIGH_PRIORITY, new CipherKeyGenerator());
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = RC2.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameterGenerator.RC2", a + "$AlgParamGen");
            configurableProvider.a("AlgorithmParameterGenerator.1.2.840.113549.3.2", a + "$AlgParamGen");
            configurableProvider.a("KeyGenerator.RC2", a + "$KeyGenerator");
            configurableProvider.a("KeyGenerator.1.2.840.113549.3.2", a + "$KeyGenerator");
            configurableProvider.a("AlgorithmParameters.RC2", a + "$AlgParams");
            configurableProvider.a("AlgorithmParameters.1.2.840.113549.3.2", a + "$AlgParams");
            configurableProvider.a("Cipher.RC2", a + "$ECB");
            configurableProvider.a("Cipher.RC2WRAP", a + "$Wrap");
            configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.bC, "RC2WRAP");
            configurableProvider.a("Cipher.1.2.840.113549.3.2", a + "$CBC");
            configurableProvider.a("Mac.RC2MAC", a + "$CBCMAC");
            configurableProvider.a("Alg.Alias.Mac.RC2", "RC2MAC");
            configurableProvider.a("Mac.RC2MAC/CFB8", a + "$CFB8MAC");
            configurableProvider.a("Alg.Alias.Mac.RC2/CFB8", "RC2MAC/CFB8");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHMD2ANDRC2-CBC", "PBEWITHMD2ANDRC2");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHMD5ANDRC2-CBC", "PBEWITHMD5ANDRC2");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA1ANDRC2-CBC", "PBEWITHSHA1ANDRC2");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.t_, "PBEWITHMD2ANDRC2");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.v, "PBEWITHMD5ANDRC2");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.x, "PBEWITHSHA1ANDRC2");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.5", "PBEWITHSHAAND128BITRC2-CBC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.6", "PBEWITHSHAAND40BITRC2-CBC");
            configurableProvider.a("SecretKeyFactory.PBEWITHMD2ANDRC2", a + "$PBEWithMD2KeyFactory");
            configurableProvider.a("SecretKeyFactory.PBEWITHMD5ANDRC2", a + "$PBEWithMD5KeyFactory");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHA1ANDRC2", a + "$PBEWithSHA1KeyFactory");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAAND128BITRC2-CBC", a + "$PBEWithSHAAnd128BitKeyFactory");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAAND40BITRC2-CBC", a + "$PBEWithSHAAnd40BitKeyFactory");
            configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.t_, "PBEWITHMD2ANDRC2");
            configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.v, "PBEWITHMD5ANDRC2");
            configurableProvider.a("Alg.Alias.Cipher." + PKCSObjectIdentifiers.x, "PBEWITHSHA1ANDRC2");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.5", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.6", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWithSHAAnd3KeyTripleDES", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.Cipher.1.2.840.113549.1.12.1.5", "PBEWITHSHAAND128BITRC2-CBC");
            configurableProvider.a("Alg.Alias.Cipher.1.2.840.113549.1.12.1.6", "PBEWITHSHAAND40BITRC2-CBC");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND128BITRC2-CBC", "PBEWITHSHAAND128BITRC2-CBC");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND40BITRC2-CBC", "PBEWITHSHAAND40BITRC2-CBC");
            configurableProvider.a("Cipher.PBEWITHSHA1ANDRC2", a + "$PBEWithSHA1AndRC2");
            configurableProvider.a("Cipher.PBEWITHSHAAND128BITRC2-CBC", a + "$PBEWithSHAAnd128BitRC2");
            configurableProvider.a("Cipher.PBEWITHSHAAND40BITRC2-CBC", a + "$PBEWithSHAAnd40BitRC2");
            configurableProvider.a("Cipher.PBEWITHMD5ANDRC2", a + "$PBEWithMD5AndRC2");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA1ANDRC2", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDRC2", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA1ANDRC2-CBC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND40BITRC2-CBC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND128BITRC2-CBC", "PKCS12PBE");
        }
    }

    public static class PBEWithMD2KeyFactory extends PBESecretKeyFactory {
        public PBEWithMD2KeyFactory() {
            super("PBEwithMD2andRC2", PKCSObjectIdentifiers.t_, true, 0, 5, 64, 64);
        }
    }

    public static class PBEWithMD5AndRC2 extends BaseBlockCipher {
        public PBEWithMD5AndRC2() {
            super(new CBCBlockCipher(new RC2Engine()));
        }
    }

    public static class PBEWithMD5KeyFactory extends PBESecretKeyFactory {
        public PBEWithMD5KeyFactory() {
            super("PBEwithMD5andRC2", PKCSObjectIdentifiers.v, true, 0, 0, 64, 64);
        }
    }

    public static class PBEWithSHA1AndRC2 extends BaseBlockCipher {
        public PBEWithSHA1AndRC2() {
            super(new CBCBlockCipher(new RC2Engine()));
        }
    }

    public static class PBEWithSHA1KeyFactory extends PBESecretKeyFactory {
        public PBEWithSHA1KeyFactory() {
            super("PBEwithSHA1andRC2", PKCSObjectIdentifiers.x, true, 0, 1, 64, 64);
        }
    }

    public static class PBEWithSHAAnd128BitKeyFactory extends PBESecretKeyFactory {
        public PBEWithSHAAnd128BitKeyFactory() {
            super("PBEwithSHAand128BitRC2-CBC", PKCSObjectIdentifiers.by, true, 2, 1, NotificationCompat.FLAG_HIGH_PRIORITY, 64);
        }
    }

    public static class PBEWithSHAAnd128BitRC2 extends BaseBlockCipher {
        public PBEWithSHAAnd128BitRC2() {
            super(new CBCBlockCipher(new RC2Engine()));
        }
    }

    public static class PBEWithSHAAnd40BitKeyFactory extends PBESecretKeyFactory {
        public PBEWithSHAAnd40BitKeyFactory() {
            super("PBEwithSHAand40BitRC2-CBC", PKCSObjectIdentifiers.bz, true, 2, 1, 40, 64);
        }
    }

    public static class PBEWithSHAAnd40BitRC2 extends BaseBlockCipher {
        public PBEWithSHAAnd40BitRC2() {
            super(new CBCBlockCipher(new RC2Engine()));
        }
    }

    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new RC2WrapEngine());
        }
    }

    private RC2() {
    }
}
