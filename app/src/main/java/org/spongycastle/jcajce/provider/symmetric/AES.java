package org.spongycastle.jcajce.provider.symmetric;

import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.spongycastle.asn1.bc.BCObjectIdentifiers;
import org.spongycastle.asn1.cms.GCMParameters;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.AESFastEngine;
import org.spongycastle.crypto.engines.AESWrapEngine;
import org.spongycastle.crypto.engines.RFC3211WrapEngine;
import org.spongycastle.crypto.generators.Poly1305KeyGenerator;
import org.spongycastle.crypto.macs.CMac;
import org.spongycastle.crypto.macs.GMac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CFBBlockCipher;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.crypto.modes.OFBBlockCipher;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.spongycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.spongycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;
import org.spongycastle.util.Integers;

public final class AES {
    private static final Class a = a("javax.crypto.spec.GCMParameterSpec");

    public static class AESCMAC extends BaseMac {
        public AESCMAC() {
            super(new CMac(new AESFastEngine()));
        }
    }

    public static class AESGMAC extends BaseMac {
        public AESGMAC() {
            super(new GMac(new GCMBlockCipher(new AESFastEngine())));
        }
    }

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for AES parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[16];
            if (this.b == null) {
                this.b = new SecureRandom();
            }
            this.b.nextBytes(bArr);
            try {
                AlgorithmParameters instance = AlgorithmParameters.getInstance("AES", "SC");
                instance.init(new IvParameterSpec(bArr));
                return instance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static class AlgParams extends IvAlgorithmParameters {
        protected String engineToString() {
            return "AES IV";
        }
    }

    public static class AlgParamsGCM extends BaseAlgorithmParameters {
        private GCMParameters a;

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (AES.a != null) {
                try {
                    this.a = new GCMParameters((byte[]) AES.a.getDeclaredMethod("getIV", new Class[0]).invoke(algorithmParameterSpec, new Object[0]), ((Integer) AES.a.getDeclaredMethod("getTLen", new Class[0]).invoke(algorithmParameterSpec, new Object[0])).intValue());
                } catch (Exception e) {
                    throw new InvalidParameterSpecException("Cannot process GCMParameterSpec.");
                }
            }
        }

        protected void engineInit(byte[] bArr) {
            this.a = GCMParameters.a(bArr);
        }

        protected void engineInit(byte[] bArr, String str) {
            if (a(str)) {
                this.a = GCMParameters.a(bArr);
                return;
            }
            throw new IOException("unknown format specified");
        }

        protected byte[] engineGetEncoded() {
            return this.a.b();
        }

        protected byte[] engineGetEncoded(String str) {
            if (a(str)) {
                return this.a.b();
            }
            throw new IOException("unknown format specified");
        }

        protected String engineToString() {
            return "GCM";
        }

        protected AlgorithmParameterSpec a(Class cls) {
            if (AES.a != null) {
                try {
                    return (AlgorithmParameterSpec) AES.a.getConstructor(new Class[]{byte[].class, Integer.class}).newInstance(new Object[]{this.a.d(), Integers.a(this.a.e())});
                } catch (NoSuchMethodException e) {
                    throw new InvalidParameterSpecException("no constructor found!");
                } catch (Exception e2) {
                    throw new InvalidParameterSpecException("construction failed: " + e2.getMessage());
                }
            }
            throw new InvalidParameterSpecException("unknown parameter spec: " + cls.getName());
        }
    }

    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new AESFastEngine()), (int) NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class CFB extends BaseBlockCipher {
        public CFB() {
            super(new BufferedBlockCipher(new CFBBlockCipher(new AESFastEngine(), NotificationCompat.FLAG_HIGH_PRIORITY)), (int) NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() {
                public BlockCipher a() {
                    return new AESFastEngine();
                }
            });
        }
    }

    public static class GCM extends BaseBlockCipher {
        public GCM() {
            super(new GCMBlockCipher(new AESFastEngine()));
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(192);
        }

        public KeyGen(int i) {
            super("AES", i, new CipherKeyGenerator());
        }
    }

    public static class KeyGen128 extends KeyGen {
        public KeyGen128() {
            super(NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class KeyGen192 extends KeyGen {
        public KeyGen192() {
            super(192);
        }
    }

    public static class KeyGen256 extends KeyGen {
        public KeyGen256() {
            super(256);
        }
    }

    public static class Mappings extends SymmetricAlgorithmProvider {
        private static final String a = AES.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.AES", a + "$AlgParams");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.2.16.840.1.101.3.4.2", "AES");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.2.16.840.1.101.3.4.22", "AES");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.2.16.840.1.101.3.4.42", "AES");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + NISTObjectIdentifiers.k, "AES");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + NISTObjectIdentifiers.r, "AES");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + NISTObjectIdentifiers.y, "AES");
            configurableProvider.a("AlgorithmParameters.GCM", a + "$AlgParamsGCM");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + NISTObjectIdentifiers.o, "GCM");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + NISTObjectIdentifiers.v, "GCM");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + NISTObjectIdentifiers.C, "GCM");
            configurableProvider.a("AlgorithmParameterGenerator.AES", a + "$AlgParamGen");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator.2.16.840.1.101.3.4.2", "AES");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator.2.16.840.1.101.3.4.22", "AES");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator.2.16.840.1.101.3.4.42", "AES");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + NISTObjectIdentifiers.k, "AES");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + NISTObjectIdentifiers.r, "AES");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + NISTObjectIdentifiers.y, "AES");
            configurableProvider.a("Cipher.AES", a + "$ECB");
            configurableProvider.a("Alg.Alias.Cipher.2.16.840.1.101.3.4.2", "AES");
            configurableProvider.a("Alg.Alias.Cipher.2.16.840.1.101.3.4.22", "AES");
            configurableProvider.a("Alg.Alias.Cipher.2.16.840.1.101.3.4.42", "AES");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.j, a + "$ECB");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.q, a + "$ECB");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.x, a + "$ECB");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.k, a + "$CBC");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.r, a + "$CBC");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.y, a + "$CBC");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.l, a + "$OFB");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.s, a + "$OFB");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.z, a + "$OFB");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.m, a + "$CFB");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.t, a + "$CFB");
            configurableProvider.a("Cipher." + NISTObjectIdentifiers.A, a + "$CFB");
            configurableProvider.a("Cipher.AESWRAP", a + "$Wrap");
            configurableProvider.a("Alg.Alias.Cipher." + NISTObjectIdentifiers.n, "AESWRAP");
            configurableProvider.a("Alg.Alias.Cipher." + NISTObjectIdentifiers.u, "AESWRAP");
            configurableProvider.a("Alg.Alias.Cipher." + NISTObjectIdentifiers.B, "AESWRAP");
            configurableProvider.a("Cipher.AESRFC3211WRAP", a + "$RFC3211Wrap");
            configurableProvider.a("Cipher.GCM", a + "$GCM");
            configurableProvider.a("Alg.Alias.Cipher." + NISTObjectIdentifiers.o, "GCM");
            configurableProvider.a("Alg.Alias.Cipher." + NISTObjectIdentifiers.v, "GCM");
            configurableProvider.a("Alg.Alias.Cipher." + NISTObjectIdentifiers.C, "GCM");
            configurableProvider.a("KeyGenerator.AES", a + "$KeyGen");
            configurableProvider.a("KeyGenerator.2.16.840.1.101.3.4.2", a + "$KeyGen128");
            configurableProvider.a("KeyGenerator.2.16.840.1.101.3.4.22", a + "$KeyGen192");
            configurableProvider.a("KeyGenerator.2.16.840.1.101.3.4.42", a + "$KeyGen256");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.j, a + "$KeyGen128");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.k, a + "$KeyGen128");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.l, a + "$KeyGen128");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.m, a + "$KeyGen128");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.q, a + "$KeyGen192");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.r, a + "$KeyGen192");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.s, a + "$KeyGen192");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.t, a + "$KeyGen192");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.x, a + "$KeyGen256");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.y, a + "$KeyGen256");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.z, a + "$KeyGen256");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.A, a + "$KeyGen256");
            configurableProvider.a("KeyGenerator.AESWRAP", a + "$KeyGen");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.n, a + "$KeyGen128");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.u, a + "$KeyGen192");
            configurableProvider.a("KeyGenerator." + NISTObjectIdentifiers.B, a + "$KeyGen256");
            configurableProvider.a("Mac.AESCMAC", a + "$AESCMAC");
            configurableProvider.a("Alg.Alias.Cipher." + BCObjectIdentifiers.l.d(), "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher." + BCObjectIdentifiers.m.d(), "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher." + BCObjectIdentifiers.n.d(), "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher." + BCObjectIdentifiers.o.d(), "PBEWITHSHA256AND128BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher." + BCObjectIdentifiers.p.d(), "PBEWITHSHA256AND192BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher." + BCObjectIdentifiers.q.d(), "PBEWITHSHA256AND256BITAES-CBC-BC");
            configurableProvider.a("Cipher.PBEWITHSHAAND128BITAES-CBC-BC", a + "$PBEWithAESCBC");
            configurableProvider.a("Cipher.PBEWITHSHAAND192BITAES-CBC-BC", a + "$PBEWithAESCBC");
            configurableProvider.a("Cipher.PBEWITHSHAAND256BITAES-CBC-BC", a + "$PBEWithAESCBC");
            configurableProvider.a("Cipher.PBEWITHSHA256AND128BITAES-CBC-BC", a + "$PBEWithAESCBC");
            configurableProvider.a("Cipher.PBEWITHSHA256AND192BITAES-CBC-BC", a + "$PBEWithAESCBC");
            configurableProvider.a("Cipher.PBEWITHSHA256AND256BITAES-CBC-BC", a + "$PBEWithAESCBC");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA-1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA-1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA-1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA-256AND128BITAES-CBC-BC", "PBEWITHSHA256AND128BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA-256AND192BITAES-CBC-BC", "PBEWITHSHA256AND192BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA-256AND256BITAES-CBC-BC", "PBEWITHSHA256AND256BITAES-CBC-BC");
            configurableProvider.a("Cipher.PBEWITHMD5AND128BITAES-CBC-OPENSSL", a + "$PBEWithAESCBC");
            configurableProvider.a("Cipher.PBEWITHMD5AND192BITAES-CBC-OPENSSL", a + "$PBEWithAESCBC");
            configurableProvider.a("Cipher.PBEWITHMD5AND256BITAES-CBC-OPENSSL", a + "$PBEWithAESCBC");
            configurableProvider.a("SecretKeyFactory.PBEWITHMD5AND128BITAES-CBC-OPENSSL", a + "$PBEWithMD5And128BitAESCBCOpenSSL");
            configurableProvider.a("SecretKeyFactory.PBEWITHMD5AND192BITAES-CBC-OPENSSL", a + "$PBEWithMD5And192BitAESCBCOpenSSL");
            configurableProvider.a("SecretKeyFactory.PBEWITHMD5AND256BITAES-CBC-OPENSSL", a + "$PBEWithMD5And256BitAESCBCOpenSSL");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAAND128BITAES-CBC-BC", a + "$PBEWithSHAAnd128BitAESBC");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAAND192BITAES-CBC-BC", a + "$PBEWithSHAAnd192BitAESBC");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAAND256BITAES-CBC-BC", a + "$PBEWithSHAAnd256BitAESBC");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHA256AND128BITAES-CBC-BC", a + "$PBEWithSHA256And128BitAESBC");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHA256AND192BITAES-CBC-BC", a + "$PBEWithSHA256And192BitAESBC");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHA256AND256BITAES-CBC-BC", a + "$PBEWithSHA256And256BitAESBC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND128BITAES-CBC-BC", "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND192BITAES-CBC-BC", "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND256BITAES-CBC-BC", "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND128BITAES-CBC-BC", "PBEWITHSHA256AND128BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND192BITAES-CBC-BC", "PBEWITHSHA256AND192BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND256BITAES-CBC-BC", "PBEWITHSHA256AND256BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.l.d(), "PBEWITHSHAAND128BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.m.d(), "PBEWITHSHAAND192BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.n.d(), "PBEWITHSHAAND256BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.o.d(), "PBEWITHSHA256AND128BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.p.d(), "PBEWITHSHA256AND192BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.q.d(), "PBEWITHSHA256AND256BITAES-CBC-BC");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND128BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND192BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND256BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND128BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND192BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND256BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND128BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND192BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND256BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND128BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND192BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND256BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND128BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND192BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND256BITAES-CBC-BC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.l.d(), "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.m.d(), "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.n.d(), "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.o.d(), "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.p.d(), "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.q.d(), "PKCS12PBE");
            a(configurableProvider, "AES", a + "$AESGMAC", a + "$KeyGen128");
            b(configurableProvider, "AES", a + "$Poly1305", a + "$Poly1305KeyGen");
        }
    }

    public static class OFB extends BaseBlockCipher {
        public OFB() {
            super(new BufferedBlockCipher(new OFBBlockCipher(new AESFastEngine(), NotificationCompat.FLAG_HIGH_PRIORITY)), (int) NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class PBEWithAESCBC extends BaseBlockCipher {
        public PBEWithAESCBC() {
            super(new CBCBlockCipher(new AESFastEngine()));
        }
    }

    public static class PBEWithMD5And128BitAESCBCOpenSSL extends PBESecretKeyFactory {
        public PBEWithMD5And128BitAESCBCOpenSSL() {
            super("PBEWithMD5And128BitAES-CBC-OpenSSL", null, true, 3, 0, NotificationCompat.FLAG_HIGH_PRIORITY, NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class PBEWithMD5And192BitAESCBCOpenSSL extends PBESecretKeyFactory {
        public PBEWithMD5And192BitAESCBCOpenSSL() {
            super("PBEWithMD5And192BitAES-CBC-OpenSSL", null, true, 3, 0, 192, NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class PBEWithMD5And256BitAESCBCOpenSSL extends PBESecretKeyFactory {
        public PBEWithMD5And256BitAESCBCOpenSSL() {
            super("PBEWithMD5And256BitAES-CBC-OpenSSL", null, true, 3, 0, 256, NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class PBEWithSHA256And128BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHA256And128BitAESBC() {
            super("PBEWithSHA256And128BitAES-CBC-BC", null, true, 2, 4, NotificationCompat.FLAG_HIGH_PRIORITY, NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class PBEWithSHA256And192BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHA256And192BitAESBC() {
            super("PBEWithSHA256And192BitAES-CBC-BC", null, true, 2, 4, 192, NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class PBEWithSHA256And256BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHA256And256BitAESBC() {
            super("PBEWithSHA256And256BitAES-CBC-BC", null, true, 2, 4, 256, NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class PBEWithSHAAnd128BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHAAnd128BitAESBC() {
            super("PBEWithSHA1And128BitAES-CBC-BC", null, true, 2, 1, NotificationCompat.FLAG_HIGH_PRIORITY, NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class PBEWithSHAAnd192BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHAAnd192BitAESBC() {
            super("PBEWithSHA1And192BitAES-CBC-BC", null, true, 2, 1, 192, NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class PBEWithSHAAnd256BitAESBC extends PBESecretKeyFactory {
        public PBEWithSHAAnd256BitAESBC() {
            super("PBEWithSHA1And256BitAES-CBC-BC", null, true, 2, 1, 256, NotificationCompat.FLAG_HIGH_PRIORITY);
        }
    }

    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.spongycastle.crypto.macs.Poly1305(new AESFastEngine()));
        }
    }

    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-AES", 256, new Poly1305KeyGenerator());
        }
    }

    public static class RFC3211Wrap extends BaseWrapCipher {
        public RFC3211Wrap() {
            super(new RFC3211WrapEngine(new AESFastEngine()), 16);
        }
    }

    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new AESWrapEngine());
        }
    }

    private AES() {
    }

    private static Class a(String str) {
        try {
            return AES.class.getClassLoader().loadClass(str);
        } catch (Exception e) {
            return null;
        }
    }
}
