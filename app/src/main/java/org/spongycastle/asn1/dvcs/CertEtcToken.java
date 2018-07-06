package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Extension;

public class CertEtcToken extends ASN1Object implements ASN1Choice {
    private static final boolean[] a = new boolean[]{false, true, false, true, false, true, false, false, true};
    private int b;
    private ASN1Encodable c;
    private Extension d;

    public ASN1Primitive a() {
        if (this.d == null) {
            return new DERTaggedObject(a[this.b], this.b, this.c);
        }
        return this.d.a();
    }

    public String toString() {
        return "CertEtcToken {\n" + this.c + "}\n";
    }
}
