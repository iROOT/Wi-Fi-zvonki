package org.spongycastle.asn1.esf;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Attribute;
import org.spongycastle.asn1.x509.AttributeCertificate;

public class SignerAttribute extends ASN1Object {
    private Object[] a;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.a.length; i++) {
            if (this.a[i] instanceof Attribute[]) {
                aSN1EncodableVector.a(new DERTaggedObject(0, new DERSequence((ASN1Encodable[]) (Attribute[]) this.a[i])));
            } else {
                aSN1EncodableVector.a(new DERTaggedObject(1, (AttributeCertificate) this.a[i]));
            }
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
