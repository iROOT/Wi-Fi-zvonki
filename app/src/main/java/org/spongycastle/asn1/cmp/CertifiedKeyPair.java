package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.crmf.EncryptedValue;
import org.spongycastle.asn1.crmf.PKIPublicationInfo;

public class CertifiedKeyPair extends ASN1Object {
    private CertOrEncCert a;
    private EncryptedValue b;
    private PKIPublicationInfo c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.b));
        }
        if (this.c != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 1, this.c));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
