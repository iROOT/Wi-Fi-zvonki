package org.spongycastle.jcajce.provider.asymmetric.gost;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.jce.spec.GOST3410ParameterSpec;
import org.spongycastle.jce.spec.GOST3410PublicKeyParameterSetSpec;

public class AlgorithmParametersSpi extends java.security.AlgorithmParametersSpi {
    GOST3410ParameterSpec a;

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
            return new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(this.a.a()), new ASN1ObjectIdentifier(this.a.b()), new ASN1ObjectIdentifier(this.a.c())).a("DER");
        } catch (IOException e) {
            throw new RuntimeException("Error encoding GOST3410Parameters");
        }
    }

    protected byte[] engineGetEncoded(String str) {
        if (a(str) || str.equalsIgnoreCase("X.509")) {
            return engineGetEncoded();
        }
        return null;
    }

    protected AlgorithmParameterSpec a(Class cls) {
        if (cls == GOST3410PublicKeyParameterSetSpec.class) {
            return this.a;
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to GOST3410 parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec instanceof GOST3410ParameterSpec) {
            this.a = (GOST3410ParameterSpec) algorithmParameterSpec;
            return;
        }
        throw new InvalidParameterSpecException("GOST3410ParameterSpec required to initialise a GOST3410 algorithm parameters object");
    }

    protected void engineInit(byte[] bArr) {
        try {
            this.a = GOST3410ParameterSpec.a(new GOST3410PublicKeyAlgParameters((ASN1Sequence) ASN1Primitive.a(bArr)));
        } catch (ClassCastException e) {
            throw new IOException("Not a valid GOST3410 Parameter encoding.");
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new IOException("Not a valid GOST3410 Parameter encoding.");
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
        return "GOST3410 Parameters";
    }
}
