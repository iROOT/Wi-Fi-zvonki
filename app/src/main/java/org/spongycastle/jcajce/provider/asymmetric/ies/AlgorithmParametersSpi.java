package org.spongycastle.jcajce.provider.asymmetric.ies;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.jce.spec.IESParameterSpec;

public class AlgorithmParametersSpi extends java.security.AlgorithmParametersSpi {
    IESParameterSpec a;

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
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            aSN1EncodableVector.a(new DEROctetString(this.a.a()));
            aSN1EncodableVector.a(new DEROctetString(this.a.b()));
            aSN1EncodableVector.a(new DERInteger((long) this.a.c()));
            return new DERSequence(aSN1EncodableVector).a("DER");
        } catch (IOException e) {
            throw new RuntimeException("Error encoding IESParameters");
        }
    }

    protected byte[] engineGetEncoded(String str) {
        if (a(str) || str.equalsIgnoreCase("X.509")) {
            return engineGetEncoded();
        }
        return null;
    }

    protected AlgorithmParameterSpec a(Class cls) {
        if (cls == IESParameterSpec.class) {
            return this.a;
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to ElGamal parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec instanceof IESParameterSpec) {
            this.a = (IESParameterSpec) algorithmParameterSpec;
            return;
        }
        throw new InvalidParameterSpecException("IESParameterSpec required to initialise a IES algorithm parameters object");
    }

    protected void engineInit(byte[] bArr) {
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) ASN1Primitive.a(bArr);
            this.a = new IESParameterSpec(((ASN1OctetString) aSN1Sequence.a(0)).e(), ((ASN1OctetString) aSN1Sequence.a(0)).e(), ((DERInteger) aSN1Sequence.a(0)).d().intValue());
        } catch (ClassCastException e) {
            throw new IOException("Not a valid IES Parameter encoding.");
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new IOException("Not a valid IES Parameter encoding.");
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
        return "IES Parameters";
    }
}
