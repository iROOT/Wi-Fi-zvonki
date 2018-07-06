package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.DigestInfo;

public class Data extends ASN1Object implements ASN1Choice {
    private ASN1OctetString a;
    private DigestInfo b;
    private ASN1Sequence c;

    public ASN1Primitive a() {
        if (this.a != null) {
            return this.a.a();
        }
        if (this.b != null) {
            return this.b.a();
        }
        return new DERTaggedObject(false, 0, this.c);
    }

    public String toString() {
        if (this.a != null) {
            return "Data {\n" + this.a + "}\n";
        }
        if (this.b != null) {
            return "Data {\n" + this.b + "}\n";
        }
        return "Data {\n" + this.c + "}\n";
    }
}
