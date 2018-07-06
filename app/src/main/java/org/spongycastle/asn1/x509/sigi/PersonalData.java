package org.spongycastle.asn1.x509.sigi;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x500.DirectoryString;

public class PersonalData extends ASN1Object {
    private NameOrPseudonym a;
    private BigInteger b;
    private ASN1GeneralizedTime c;
    private DirectoryString d;
    private String e;
    private DirectoryString f;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, new ASN1Integer(this.b)));
        }
        if (this.c != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.c));
        }
        if (this.d != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 2, this.d));
        }
        if (this.e != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 3, new DERPrintableString(this.e, true)));
        }
        if (this.f != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 4, this.f));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
