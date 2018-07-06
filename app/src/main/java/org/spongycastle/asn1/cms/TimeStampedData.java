package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.DERIA5String;

public class TimeStampedData extends ASN1Object {
    private ASN1Integer a;
    private DERIA5String b;
    private MetaData c;
    private ASN1OctetString d;
    private Evidence e;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(this.b);
        }
        if (this.c != null) {
            aSN1EncodableVector.a(this.c);
        }
        if (this.d != null) {
            aSN1EncodableVector.a(this.d);
        }
        aSN1EncodableVector.a(this.e);
        return new BERSequence(aSN1EncodableVector);
    }
}
