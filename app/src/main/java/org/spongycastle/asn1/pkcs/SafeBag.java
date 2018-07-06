package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DLSequence;
import org.spongycastle.asn1.DLTaggedObject;

public class SafeBag extends ASN1Object {
    private ASN1ObjectIdentifier a;
    private ASN1Encodable b;
    private ASN1Set c;

    public SafeBag(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable, ASN1Set aSN1Set) {
        this.a = aSN1ObjectIdentifier;
        this.b = aSN1Encodable;
        this.c = aSN1Set;
    }

    public static SafeBag a(Object obj) {
        if (obj instanceof SafeBag) {
            return (SafeBag) obj;
        }
        if (obj != null) {
            return new SafeBag(ASN1Sequence.a(obj));
        }
        return null;
    }

    private SafeBag(ASN1Sequence aSN1Sequence) {
        this.a = (ASN1ObjectIdentifier) aSN1Sequence.a(0);
        this.b = ((ASN1TaggedObject) aSN1Sequence.a(1)).l();
        if (aSN1Sequence.f() == 3) {
            this.c = (ASN1Set) aSN1Sequence.a(2);
        }
    }

    public ASN1ObjectIdentifier d() {
        return this.a;
    }

    public ASN1Encodable e() {
        return this.b;
    }

    public ASN1Set f() {
        return this.c;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(new DLTaggedObject(true, 0, this.b));
        if (this.c != null) {
            aSN1EncodableVector.a(this.c);
        }
        return new DLSequence(aSN1EncodableVector);
    }
}
