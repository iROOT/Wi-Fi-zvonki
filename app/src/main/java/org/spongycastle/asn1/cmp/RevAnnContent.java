package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.crmf.CertId;
import org.spongycastle.asn1.x509.Extensions;

public class RevAnnContent extends ASN1Object {
    private PKIStatus a;
    private CertId b;
    private ASN1GeneralizedTime c;
    private ASN1GeneralizedTime d;
    private Extensions e;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        if (this.e != null) {
            aSN1EncodableVector.a(this.e);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
