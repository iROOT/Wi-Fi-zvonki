package org.spongycastle.asn1.icao;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.x509.Certificate;

public class CscaMasterList extends ASN1Object {
    private ASN1Integer a;
    private Certificate[] b;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (ASN1Encodable a : this.b) {
            aSN1EncodableVector2.a(a);
        }
        aSN1EncodableVector.a(new DERSet(aSN1EncodableVector2));
        return new DERSequence(aSN1EncodableVector);
    }
}
