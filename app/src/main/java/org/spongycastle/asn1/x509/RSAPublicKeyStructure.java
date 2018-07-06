package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class RSAPublicKeyStructure extends ASN1Object {
    private BigInteger a;
    private BigInteger b;

    public RSAPublicKeyStructure(BigInteger bigInteger, BigInteger bigInteger2) {
        this.a = bigInteger;
        this.b = bigInteger2;
    }

    public BigInteger d() {
        return this.a;
    }

    public BigInteger e() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new ASN1Integer(d()));
        aSN1EncodableVector.a(new ASN1Integer(e()));
        return new DERSequence(aSN1EncodableVector);
    }
}
