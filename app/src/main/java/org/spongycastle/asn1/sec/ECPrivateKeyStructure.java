package org.spongycastle.asn1.sec;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.util.BigIntegers;

public class ECPrivateKeyStructure extends ASN1Object {
    private ASN1Sequence a;

    public ECPrivateKeyStructure(BigInteger bigInteger, ASN1Encodable aSN1Encodable) {
        this(bigInteger, null, aSN1Encodable);
    }

    public ECPrivateKeyStructure(BigInteger bigInteger, DERBitString dERBitString, ASN1Encodable aSN1Encodable) {
        byte[] a = BigIntegers.a(bigInteger);
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new ASN1Integer(1));
        aSN1EncodableVector.a(new DEROctetString(a));
        if (aSN1Encodable != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, aSN1Encodable));
        }
        if (dERBitString != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 1, dERBitString));
        }
        this.a = new DERSequence(aSN1EncodableVector);
    }

    public ASN1Primitive a() {
        return this.a;
    }
}
