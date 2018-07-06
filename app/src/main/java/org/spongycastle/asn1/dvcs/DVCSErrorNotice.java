package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.cmp.PKIStatusInfo;
import org.spongycastle.asn1.x509.GeneralName;

public class DVCSErrorNotice extends ASN1Object {
    private PKIStatusInfo a;
    private GeneralName b;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(this.b);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "DVCSErrorNotice {\ntransactionStatus: " + this.a + "\n" + (this.b != null ? "transactionIdentifier: " + this.b + "\n" : "") + "}\n";
    }
}
