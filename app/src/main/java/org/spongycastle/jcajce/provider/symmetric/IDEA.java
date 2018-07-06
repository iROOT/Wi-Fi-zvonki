package org.spongycastle.jcajce.provider.symmetric;

import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.misc.IDEACBCPar;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.engines.IDEAEngine;
import org.spongycastle.crypto.macs.CBCBlockCipherMac;
import org.spongycastle.crypto.macs.CFBBlockCipherMac;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.spongycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.spongycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.spongycastle.jcajce.provider.symmetric.util.BaseMac;
import org.spongycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public final class IDEA {

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for IDEA parameter generation.");
        }

        protected AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[8];
            if (this.b == null) {
                this.b = new SecureRandom();
            }
            this.b.nextBytes(bArr);
            try {
                AlgorithmParameters instance = AlgorithmParameters.getInstance("IDEA", "SC");
                instance.init(new IvParameterSpec(bArr));
                return instance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static class AlgParams extends BaseAlgorithmParameters {
        private byte[] a;

        protected byte[] engineGetEncoded() {
            return engineGetEncoded("ASN.1");
        }

        protected byte[] engineGetEncoded(String str) {
            if (a(str)) {
                return new IDEACBCPar(engineGetEncoded("RAW")).b();
            }
            if (!str.equals("RAW")) {
                return null;
            }
            byte[] bArr = new byte[this.a.length];
            System.arraycopy(this.a, 0, bArr, 0, this.a.length);
            return bArr;
        }

        protected AlgorithmParameterSpec a(Class cls) {
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.a);
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to IV parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                this.a = ((IvParameterSpec) algorithmParameterSpec).getIV();
                return;
            }
            throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
        }

        protected void engineInit(byte[] bArr) {
            this.a = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.a, 0, this.a.length);
        }

        protected void engineInit(byte[] bArr, String str) {
            if (str.equals("RAW")) {
                engineInit(bArr);
            } else if (str.equals("ASN.1")) {
                engineInit(new IDEACBCPar((ASN1Sequence) new ASN1InputStream(bArr).d()).d());
            } else {
                throw new IOException("Unknown parameters format in IV parameters object");
            }
        }

        protected String engineToString() {
            return "IDEA Parameters";
        }
    }

    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new IDEAEngine()), 64);
        }
    }

    public static class CFB8Mac extends BaseMac {
        public CFB8Mac() {
            super(new CFBBlockCipherMac(new IDEAEngine()));
        }
    }

    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new IDEAEngine());
        }
    }

    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("IDEA", NotificationCompat.FLAG_HIGH_PRIORITY, new CipherKeyGenerator());
        }
    }

    public static class Mac extends BaseMac {
        public Mac() {
            super(new CBCBlockCipherMac(new IDEAEngine()));
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = IDEA.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameterGenerator.IDEA", a + "$AlgParamGen");
            configurableProvider.a("AlgorithmParameterGenerator.1.3.6.1.4.1.188.7.1.1.2", a + "$AlgParamGen");
            configurableProvider.a("AlgorithmParameters.IDEA", a + "$AlgParams");
            configurableProvider.a("AlgorithmParameters.1.3.6.1.4.1.188.7.1.1.2", a + "$AlgParams");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDIDEA", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDIDEA-CBC", "PKCS12PBE");
            configurableProvider.a("Cipher.IDEA", a + "$ECB");
            configurableProvider.a("Cipher.1.3.6.1.4.1.188.7.1.1.2", a + "$CBC");
            configurableProvider.a("Cipher.PBEWITHSHAANDIDEA-CBC", a + "$PBEWithSHAAndIDEA");
            configurableProvider.a("KeyGenerator.IDEA", a + "$KeyGen");
            configurableProvider.a("KeyGenerator.1.3.6.1.4.1.188.7.1.1.2", a + "$KeyGen");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAANDIDEA-CBC", a + "$PBEWithSHAAndIDEAKeyGen");
            configurableProvider.a("Mac.IDEAMAC", a + "$Mac");
            configurableProvider.a("Alg.Alias.Mac.IDEA", "IDEAMAC");
            configurableProvider.a("Mac.IDEAMAC/CFB8", a + "$CFB8Mac");
            configurableProvider.a("Alg.Alias.Mac.IDEA/CFB8", "IDEAMAC/CFB8");
        }
    }

    public static class PBEWithSHAAndIDEA extends BaseBlockCipher {
        public PBEWithSHAAndIDEA() {
            super(new CBCBlockCipher(new IDEAEngine()));
        }
    }

    public static class PBEWithSHAAndIDEAKeyGen extends PBESecretKeyFactory {
        public PBEWithSHAAndIDEAKeyGen() {
            super("PBEwithSHAandIDEA-CBC", null, true, 2, 1, NotificationCompat.FLAG_HIGH_PRIORITY, 64);
        }
    }

    private IDEA() {
    }
}
