package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERTaggedObject;

public class ContentInfo extends ASN1Object implements CMSObjectIdentifiers {
    private ASN1ObjectIdentifier n;
    private ASN1Encodable o;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.n);
        if (this.o != null) {
            aSN1EncodableVector.a(new BERTaggedObject(0, this.o));
        }
        return new BERSequence(aSN1EncodableVector);
    }
}
