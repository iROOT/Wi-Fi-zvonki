package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Vector;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class SubjectDirectoryAttributes extends ASN1Object {
    private Vector a;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Enumeration elements = this.a.elements();
        while (elements.hasMoreElements()) {
            aSN1EncodableVector.a((Attribute) elements.nextElement());
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
