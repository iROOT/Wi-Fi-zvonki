package org.spongycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.PBEParameterSpec;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.pkcs.PKCS12PBEParams;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;

public class PBEPKCS12 {

    public static class AlgParams extends BaseAlgorithmParameters {
        PKCS12PBEParams a;

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
                return new PBEParameterSpec(this.a.e(), this.a.d().intValue());
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to PKCS12 PBE parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec instanceof PBEParameterSpec) {
                PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
                this.a = new PKCS12PBEParams(pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
                return;
            }
            throw new InvalidParameterSpecException("PBEParameterSpec required to initialise a PKCS12 PBE parameters algorithm parameters object");
        }

        protected void engineInit(byte[] bArr) {
            this.a = PKCS12PBEParams.a(ASN1Primitive.a(bArr));
        }

        protected void engineInit(byte[] bArr, String str) {
            if (a(str)) {
                engineInit(bArr);
                return;
            }
            throw new IOException("Unknown parameters format in PKCS12 PBE parameters object");
        }

        protected String engineToString() {
            return "PKCS12 PBE Parameters";
        }
    }

    public static class Mappings extends AlgorithmProvider {
        private static final String a = PBEPKCS12.class.getName();

        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.PKCS12PBE", a + "$AlgParams");
        }
    }

    private PBEPKCS12() {
    }
}
