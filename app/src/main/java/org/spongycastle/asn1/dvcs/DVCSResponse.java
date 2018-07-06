package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERTaggedObject;

public class DVCSResponse extends ASN1Object implements ASN1Choice {
    private DVCSCertInfo a;
    private DVCSErrorNotice b;

    public ASN1Primitive a() {
        if (this.a != null) {
            return this.a.a();
        }
        return new DERTaggedObject(0, this.b);
    }

    public String toString() {
        if (this.a != null) {
            return "DVCSResponse {\ndvCertInfo: " + this.a.toString() + "}\n";
        }
        if (this.b != null) {
            return "DVCSResponse {\ndvErrorNote: " + this.b.toString() + "}\n";
        }
        return null;
    }
}
