package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class CertBag extends ASN1Object {
    private ASN1ObjectIdentifier a;
    private ASN1Encodable b;

    private CertBag(ASN1Sequence aSN1Sequence) {
        this.a = (ASN1ObjectIdentifier) aSN1Sequence.a(0);
        this.b = ((DERTaggedObject) aSN1Sequence.a(1)).l();
    }

    public static CertBag a(Object obj) {
        if (obj instanceof CertBag) {
            return (CertBag) obj;
        }
        if (obj != null) {
            return new CertBag(ASN1Sequence.a(obj));
        }
        return null;
    }

    public CertBag(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.a = aSN1ObjectIdentifier;
        this.b = aSN1Encodable;
    }

    public ASN1ObjectIdentifier d() {
        return this.a;
    }

    public ASN1Encodable e() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(new DERTaggedObject(0, this.b));
        return new DERSequence(aSN1EncodableVector);
    }
}
