package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class PasswordRecipientInfo extends ASN1Object {
    private ASN1Integer a;
    private AlgorithmIdentifier b;
    private AlgorithmIdentifier c;
    private ASN1OctetString d;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.b));
        }
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        return new DERSequence(aSN1EncodableVector);
    }
}
