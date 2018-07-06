package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.GeneralName;

public class DVCSRequest extends ASN1Object {
    private DVCSRequestInformation a;
    private Data b;
    private GeneralName c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        if (this.c != null) {
            aSN1EncodableVector.a(this.c);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "DVCSRequest {\nrequestInformation: " + this.a + "\n" + "data: " + this.b + "\n" + (this.c != null ? "transactionIdentifier: " + this.c + "\n" : "") + "}\n";
    }
}
