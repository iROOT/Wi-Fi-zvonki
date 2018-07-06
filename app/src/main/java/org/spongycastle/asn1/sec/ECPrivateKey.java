package org.spongycastle.asn1.sec;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.util.BigIntegers;

public class ECPrivateKey extends ASN1Object {
    private ASN1Sequence a;

    private ECPrivateKey(ASN1Sequence aSN1Sequence) {
        this.a = aSN1Sequence;
    }

    public static ECPrivateKey a(Object obj) {
        if (obj instanceof ECPrivateKey) {
            return (ECPrivateKey) obj;
        }
        if (obj != null) {
            return new ECPrivateKey(ASN1Sequence.a(obj));
        }
        return null;
    }

    public ECPrivateKey(BigInteger bigInteger, ASN1Encodable aSN1Encodable) {
        this(bigInteger, null, aSN1Encodable);
    }

    public ECPrivateKey(BigInteger bigInteger, DERBitString dERBitString, ASN1Encodable aSN1Encodable) {
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

    public BigInteger d() {
        return new BigInteger(1, ((ASN1OctetString) this.a.a(1)).e());
    }

    public DERBitString e() {
        return (DERBitString) a(1);
    }

    private ASN1Primitive a(int i) {
        Enumeration e = this.a.e();
        while (e.hasMoreElements()) {
            ASN1Encodable aSN1Encodable = (ASN1Encodable) e.nextElement();
            if (aSN1Encodable instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Encodable;
                if (aSN1TaggedObject.d() == i) {
                    return aSN1TaggedObject.l().a();
                }
            }
        }
        return null;
    }

    public ASN1Primitive a() {
        return this.a;
    }
}
