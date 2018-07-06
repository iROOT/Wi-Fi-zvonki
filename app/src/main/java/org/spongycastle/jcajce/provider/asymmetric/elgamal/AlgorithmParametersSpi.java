package org.spongycastle.jcajce.provider.asymmetric.elgamal;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.DHParameterSpec;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.oiw.ElGamalParameter;
import org.spongycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.spongycastle.jce.spec.ElGamalParameterSpec;

public class AlgorithmParametersSpi extends BaseAlgorithmParameters {
    ElGamalParameterSpec a;

    protected byte[] engineGetEncoded() {
        try {
            return new ElGamalParameter(this.a.a(), this.a.b()).a("DER");
        } catch (IOException e) {
            throw new RuntimeException("Error encoding ElGamalParameters");
        }
    }

    protected byte[] engineGetEncoded(String str) {
        if (a(str) || str.equalsIgnoreCase("X.509")) {
            return engineGetEncoded();
        }
        return null;
    }

    protected AlgorithmParameterSpec a(Class cls) {
        if (cls == ElGamalParameterSpec.class) {
            return this.a;
        }
        if (cls == DHParameterSpec.class) {
            return new DHParameterSpec(this.a.a(), this.a.b());
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to ElGamal parameters object.");
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
        if (!(algorithmParameterSpec instanceof ElGamalParameterSpec) && !(algorithmParameterSpec instanceof DHParameterSpec)) {
            throw new InvalidParameterSpecException("DHParameterSpec required to initialise a ElGamal algorithm parameters object");
        } else if (algorithmParameterSpec instanceof ElGamalParameterSpec) {
            this.a = (ElGamalParameterSpec) algorithmParameterSpec;
        } else {
            DHParameterSpec dHParameterSpec = (DHParameterSpec) algorithmParameterSpec;
            this.a = new ElGamalParameterSpec(dHParameterSpec.getP(), dHParameterSpec.getG());
        }
    }

    protected void engineInit(byte[] bArr) {
        try {
            ElGamalParameter elGamalParameter = new ElGamalParameter((ASN1Sequence) ASN1Primitive.a(bArr));
            this.a = new ElGamalParameterSpec(elGamalParameter.d(), elGamalParameter.e());
        } catch (ClassCastException e) {
            throw new IOException("Not a valid ElGamal Parameter encoding.");
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new IOException("Not a valid ElGamal Parameter encoding.");
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
        return "ElGamal Parameters";
    }
}
