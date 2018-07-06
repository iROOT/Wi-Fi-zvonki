package org.spongycastle.asn1.eac;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class RSAPublicKey extends PublicKeyDataObject {
    private static int d = 1;
    private static int e = 2;
    private ASN1ObjectIdentifier a;
    private BigInteger b;
    private BigInteger c;

    public BigInteger d() {
        return this.b;
    }

    public BigInteger e() {
        return this.c;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(new UnsignedInteger(1, d()));
        aSN1EncodableVector.a(new UnsignedInteger(2, e()));
        return new DERSequence(aSN1EncodableVector);
    }
}
