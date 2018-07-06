package org.spongycastle.asn1.x509.qualified;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class QCStatement extends ASN1Object implements ETSIQCObjectIdentifiers, RFC3739QCObjectIdentifiers {
    ASN1ObjectIdentifier e;
    ASN1Encodable f;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.e);
        if (this.f != null) {
            aSN1EncodableVector.a(this.f);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
