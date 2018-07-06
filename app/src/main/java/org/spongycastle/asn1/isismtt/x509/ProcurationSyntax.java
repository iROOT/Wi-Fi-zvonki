package org.spongycastle.asn1.isismtt.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x500.DirectoryString;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.IssuerSerial;

public class ProcurationSyntax extends ASN1Object {
    private String a;
    private DirectoryString b;
    private GeneralName c;
    private IssuerSerial d;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 1, new DERPrintableString(this.a, true)));
        }
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 2, this.b));
        }
        if (this.c != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 3, this.c));
        } else {
            aSN1EncodableVector.a(new DERTaggedObject(true, 3, this.d));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
