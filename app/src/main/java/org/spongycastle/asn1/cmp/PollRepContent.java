package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class PollRepContent extends ASN1Object {
    private ASN1Integer[] a;
    private ASN1Integer[] b;
    private PKIFreeText[] c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.a.length; i++) {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.a(this.a[i]);
            aSN1EncodableVector2.a(this.b[i]);
            if (this.c[i] != null) {
                aSN1EncodableVector2.a(this.c[i]);
            }
            aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
