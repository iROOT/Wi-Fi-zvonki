package org.spongycastle.jcajce.provider.symmetric.util;

import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.util.Arrays;

public class IvAlgorithmParameters extends BaseAlgorithmParameters {
    private byte[] a;

    protected byte[] engineGetEncoded() {
        return engineGetEncoded("ASN.1");
    }

    protected byte[] engineGetEncoded(String str) {
        if (a(str)) {
            return new DEROctetString(engineGetEncoded("RAW")).b();
        }
        if (str.equals("RAW")) {
            return Arrays.b(this.a);
        }
        return null;
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
        if (bArr.length % 8 != 0 && bArr[0] == (byte) 4 && bArr[1] == bArr.length - 2) {
            bArr = ((ASN1OctetString) ASN1Primitive.a(bArr)).e();
        }
        this.a = Arrays.b(bArr);
    }

    protected void engineInit(byte[] bArr, String str) {
        if (a(str)) {
            try {
                engineInit(((ASN1OctetString) ASN1Primitive.a(bArr)).e());
            } catch (Exception e) {
                throw new IOException("Exception decoding: " + e);
            }
        } else if (str.equals("RAW")) {
            engineInit(bArr);
        } else {
            throw new IOException("Unknown parameters format in IV parameters object");
        }
    }

    protected String engineToString() {
        return "IV Parameters";
    }
}
