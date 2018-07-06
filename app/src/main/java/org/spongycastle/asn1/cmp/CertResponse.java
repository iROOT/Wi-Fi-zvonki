package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class CertResponse extends ASN1Object {
    private ASN1Integer a;
    private PKIStatusInfo b;
    private CertifiedKeyPair c;
    private ASN1OctetString d;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        if (this.c != null) {
            aSN1EncodableVector.a(this.c);
        }
        if (this.d != null) {
            aSN1EncodableVector.a(this.d);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
