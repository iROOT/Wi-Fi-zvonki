package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.GeneralName;

public class PKIHeader extends ASN1Object {
    public static final GeneralName a = new GeneralName(X500Name.a(new DERSequence()));
    private ASN1Integer b;
    private GeneralName c;
    private GeneralName d;
    private DERGeneralizedTime e;
    private AlgorithmIdentifier f;
    private ASN1OctetString g;
    private ASN1OctetString h;
    private ASN1OctetString i;
    private ASN1OctetString j;
    private ASN1OctetString k;
    private PKIFreeText l;
    private ASN1Sequence m;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        a(aSN1EncodableVector, 0, this.e);
        a(aSN1EncodableVector, 1, this.f);
        a(aSN1EncodableVector, 2, this.g);
        a(aSN1EncodableVector, 3, this.h);
        a(aSN1EncodableVector, 4, this.i);
        a(aSN1EncodableVector, 5, this.j);
        a(aSN1EncodableVector, 6, this.k);
        a(aSN1EncodableVector, 7, this.l);
        a(aSN1EncodableVector, 8, this.m);
        return new DERSequence(aSN1EncodableVector);
    }

    private void a(ASN1EncodableVector aSN1EncodableVector, int i, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, i, aSN1Encodable));
        }
    }
}
