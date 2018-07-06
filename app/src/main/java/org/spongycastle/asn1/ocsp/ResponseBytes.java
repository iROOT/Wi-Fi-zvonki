package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class ResponseBytes extends ASN1Object {
    ASN1ObjectIdentifier a;
    ASN1OctetString b;

    public ResponseBytes(ASN1Sequence aSN1Sequence) {
        this.a = (ASN1ObjectIdentifier) aSN1Sequence.a(0);
        this.b = (ASN1OctetString) aSN1Sequence.a(1);
    }

    public static ResponseBytes a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return a(ASN1Sequence.a(aSN1TaggedObject, z));
    }

    public static ResponseBytes a(Object obj) {
        if (obj == null || (obj instanceof ResponseBytes)) {
            return (ResponseBytes) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new ResponseBytes((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public ASN1ObjectIdentifier d() {
        return this.a;
    }

    public ASN1OctetString e() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        return new DERSequence(aSN1EncodableVector);
    }
}
