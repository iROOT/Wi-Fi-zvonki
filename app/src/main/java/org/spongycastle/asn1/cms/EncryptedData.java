package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERTaggedObject;

public class EncryptedData extends ASN1Object {
    private ASN1Integer a;
    private EncryptedContentInfo b;
    private ASN1Set c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        if (this.c != null) {
            aSN1EncodableVector.a(new BERTaggedObject(false, 1, this.c));
        }
        return new BERSequence(aSN1EncodableVector);
    }
}
