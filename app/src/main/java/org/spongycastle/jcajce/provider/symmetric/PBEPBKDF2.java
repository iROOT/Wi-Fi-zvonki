package org.spongycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PBKDF2Params;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BCPBEKey;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.spongycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.spongycastle.jcajce.provider.symmetric.util.PBE.Util;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;
import org.spongycastle.jcajce.spec.PBKDF2KeySpec;

public class PBEPBKDF2 {

    public static class AlgParams extends BaseAlgorithmParameters {
        PBKDF2Params a;

        protected byte[] engineGetEncoded() {
            try {
                return this.a.a("DER");
            } catch (IOException e) {
                throw new RuntimeException("Oooops! " + e.toString());
            }
        }

        protected byte[] engineGetEncoded(String str) {
            if (a(str)) {
                return engineGetEncoded();
            }
            return null;
        }

        protected AlgorithmParameterSpec a(Class cls) {
            if (cls == PBEParameterSpec.class) {
                return new PBEParameterSpec(this.a.d(), this.a.e().intValue());
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to PBKDF2 PBE parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec instanceof PBEParameterSpec) {
                PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
                this.a = new PBKDF2Params(pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
                return;
            }
            throw new InvalidParameterSpecException("PBEParameterSpec required to initialise a PBKDF2 PBE parameters algorithm parameters object");
        }

        protected void engineInit(byte[] bArr) {
            this.a = PBKDF2Params.a(ASN1Primitive.a(bArr));
        }

        protected void engineInit(byte[] bArr, String str) {
            if (a(str)) {
                engineInit(bArr);
                return;
            }
            throw new IOException("Unknown parameters format in PBKDF2 parameters object");
        }

        protected String engineToString() {
            return "PBKDF2 Parameters";
        }
    }

    public static class BasePBKDF2 extends BaseSecretKeyFactory {
        private int c;

        public BasePBKDF2(String str, int i) {
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
                } else if (pBEKeySpec instanceof PBKDF2KeySpec) {
                    int a = a(((PBKDF2KeySpec) pBEKeySpec).a().e());
                    r5 = pBEKeySpec.getKeyLength();
                    return new BCPBEKey(this.a, this.b, this.c, a, r5, -1, pBEKeySpec, Util.a(pBEKeySpec, this.c, a, r5));
                } else {
                    r5 = pBEKeySpec.getKeyLength();
                    return new BCPBEKey(this.a, this.b, this.c, 1, r5, -1, pBEKeySpec, Util.a(pBEKeySpec, this.c, 1, r5));
                }
            }
            throw new InvalidKeySpecException("Invalid KeySpec");
        }

        private int a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
            if (aSN1ObjectIdentifier.equals(CryptoProObjectIdentifiers.c)) {
                return 6;
            }
            if (aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.I)) {
                return 1;
            }
            throw new InvalidKeySpecException("Invalid KeySpec: unknown PRF algorithm " + aSN1ObjectIdentifier);
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = PBEPBKDF2.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.PBKDF2", a + "$AlgParams");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.z, "PBKDF2");
            configurableProvider.a("SecretKeyFactory.PBKDF2", a + "$PBKDF2withUTF8");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.z, "PBKDF2");
        }
    }

    public static class PBKDF2withUTF8 extends BasePBKDF2 {
        public PBKDF2withUTF8() {
            super("PBKDF2", 5);
        }
    }

    private PBEPBKDF2() {
    }
}
