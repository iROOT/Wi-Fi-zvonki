package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;

public class CCMParameters extends ASN1Object {
    private byte[] a;
    private int b;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new DEROctetString(this.a));
        if (this.b != 12) {
            aSN1EncodableVector.a(new ASN1Integer((long) this.b));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
