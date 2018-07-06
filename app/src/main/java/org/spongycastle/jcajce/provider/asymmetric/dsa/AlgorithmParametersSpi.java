package org.spongycastle.jcajce.provider.asymmetric.dsa;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.x509.DSAParameter;

public class AlgorithmParametersSpi extends java.security.AlgorithmParametersSpi {
    DSAParameterSpec a;

    protected boolean a(String str) {
        return str == null || str.equals("ASN.1");
    }

    protected AlgorithmParameterSpec engineGetParameterSpec(Class cls) {
        if (cls != null) {
            return a(cls);
        }
        throw new NullPointerException("argument to getParameterSpec must not be null");
    }

    protected byte[] engineGetEncoded() {
        try {
            return new DSAParameter(this.a.getP(), this.a.getQ(), this.a.getG()).a("DER");
        } catch (IOException e) {
            throw new RuntimeException("Error encoding DSAParameters");
        }
    }

    protected byte[] engineGetEncoded(String str) {
        if (a(str)) {
            return engineGetEncoded();
        }
        return null;
    }

    protected AlgorithmParameterSpec a(Class cls) {
        if (cls == DSAParameterSpec.class) {
            return this.a;
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to DSA parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec instanceof DSAParameterSpec) {
            this.a = (DSAParameterSpec) algorithmParameterSpec;
            return;
        }
        throw new InvalidParameterSpecException("DSAParameterSpec required to initialise a DSA algorithm parameters object");
    }

    protected void engineInit(byte[] bArr) {
        try {
            DSAParameter a = DSAParameter.a(ASN1Primitive.a(bArr));
            this.a = new DSAParameterSpec(a.d(), a.e(), a.f());
        } catch (ClassCastException e) {
            throw new IOException("Not a valid DSA Parameter encoding.");
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new IOException("Not a valid DSA Parameter encoding.");
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
        return "DSA Parameters";
    }
}
