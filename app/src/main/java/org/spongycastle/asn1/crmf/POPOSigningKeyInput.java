package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;

public class POPOSigningKeyInput extends ASN1Object {
    private GeneralName a;
    private PKMACValue b;
    private SubjectPublicKeyInfo c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.a));
        } else {
            aSN1EncodableVector.a(this.b);
        }
        aSN1EncodableVector.a(this.c);
        return new DERSequence(aSN1EncodableVector);
    }
}
