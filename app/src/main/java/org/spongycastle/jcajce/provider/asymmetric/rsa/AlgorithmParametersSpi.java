package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource.PSpecified;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSAESOAEPparams;
import org.spongycastle.asn1.pkcs.RSASSAPSSparams;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.jcajce.provider.util.DigestFactory;

public abstract class AlgorithmParametersSpi extends java.security.AlgorithmParametersSpi {

    public static class OAEP extends AlgorithmParametersSpi {
        OAEPParameterSpec a;

        protected byte[] engineGetEncoded() {
            try {
                return new RSAESOAEPparams(new AlgorithmIdentifier(DigestFactory.b(this.a.getDigestAlgorithm()), DERNull.a), new AlgorithmIdentifier(PKCSObjectIdentifiers.l_, new AlgorithmIdentifier(DigestFactory.b(((MGF1ParameterSpec) this.a.getMGFParameters()).getDigestAlgorithm()), DERNull.a)), new AlgorithmIdentifier(PKCSObjectIdentifiers.j, new DEROctetString(((PSpecified) this.a.getPSource()).getValue()))).a("DER");
            } catch (IOException e) {
                throw new RuntimeException("Error encoding OAEPParameters");
            }
        }

        protected byte[] engineGetEncoded(String str) {
            if (a(str) || str.equalsIgnoreCase("X.509")) {
                return engineGetEncoded();
            }
            return null;
        }

        protected AlgorithmParameterSpec a(Class cls) {
            if (cls == OAEPParameterSpec.class && this.a != null) {
                return this.a;
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to OAEP parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec instanceof OAEPParameterSpec) {
                this.a = (OAEPParameterSpec) algorithmParameterSpec;
                return;
            }
            throw new InvalidParameterSpecException("OAEPParameterSpec required to initialise an OAEP algorithm parameters object");
        }

        protected void engineInit(byte[] bArr) {
            try {
                RSAESOAEPparams a = RSAESOAEPparams.a(bArr);
                this.a = new OAEPParameterSpec(a.d().e().d(), a.e().e().d(), new MGF1ParameterSpec(AlgorithmIdentifier.a(a.e().f()).e().d()), new PSpecified(ASN1OctetString.a(a.f().f()).e()));
            } catch (ClassCastException e) {
                throw new IOException("Not a valid OAEP Parameter encoding.");
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new IOException("Not a valid OAEP Parameter encoding.");
            }
        }

        protected void engineInit(byte[] bArr, String str) {
            if (str.equalsIgnoreCase("X.509") || str.equalsIgnoreCase("ASN.1")) {
                engineInit(bArr);
                return;
            }
            throw new IOException("Unknown parameter format " + str);
        }

        protected String engineToString() {
            return "OAEP Parameters";
        }
    }

    public static class PSS extends AlgorithmParametersSpi {
        PSSParameterSpec a;

        protected byte[] engineGetEncoded() {
            PSSParameterSpec pSSParameterSpec = this.a;
            return new RSASSAPSSparams(new AlgorithmIdentifier(DigestFactory.b(pSSParameterSpec.getDigestAlgorithm()), DERNull.a), new AlgorithmIdentifier(PKCSObjectIdentifiers.l_, new AlgorithmIdentifier(DigestFactory.b(((MGF1ParameterSpec) pSSParameterSpec.getMGFParameters()).getDigestAlgorithm()), DERNull.a)), new ASN1Integer((long) pSSParameterSpec.getSaltLength()), new ASN1Integer((long) pSSParameterSpec.getTrailerField())).a("DER");
        }

        protected byte[] engineGetEncoded(String str) {
            if (str.equalsIgnoreCase("X.509") || str.equalsIgnoreCase("ASN.1")) {
                return engineGetEncoded();
            }
            return null;
        }

        protected AlgorithmParameterSpec a(Class cls) {
            if (cls == PSSParameterSpec.class && this.a != null) {
                return this.a;
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to PSS parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec instanceof PSSParameterSpec) {
                this.a = (PSSParameterSpec) algorithmParameterSpec;
                return;
            }
            throw new InvalidParameterSpecException("PSSParameterSpec required to initialise an PSS algorithm parameters object");
        }

        protected void engineInit(byte[] bArr) {
            try {
                RSASSAPSSparams a = RSASSAPSSparams.a(bArr);
                this.a = new PSSParameterSpec(a.d().e().d(), a.e().e().d(), new MGF1ParameterSpec(AlgorithmIdentifier.a(a.e().f()).e().d()), a.f().intValue(), a.g().intValue());
            } catch (ClassCastException e) {
                throw new IOException("Not a valid PSS Parameter encoding.");
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new IOException("Not a valid PSS Parameter encoding.");
            }
        }

        protected void engineInit(byte[] bArr, String str) {
            if (a(str) || str.equalsIgnoreCase("X.509")) {
                engineInit(bArr);
                return;
            }
            throw new IOException("Unknown parameter format " + str);
        }

        protected String engineToString() {
            return "PSS Parameters";
        }
    }

    protected abstract AlgorithmParameterSpec a(Class cls);

    protected boolean a(String str) {
        return str == null || str.equals("ASN.1");
    }

    protected AlgorithmParameterSpec engineGetParameterSpec(Class cls) {
        if (cls != null) {
            return a(cls);
        }
        throw new NullPointerException("argument to getParameterSpec must not be null");
    }
}
