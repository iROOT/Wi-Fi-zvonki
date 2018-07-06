package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.cms.ContentInfo;

public class DVCSTime extends ASN1Object implements ASN1Choice {
    private ASN1GeneralizedTime a;
    private ContentInfo b;

    public ASN1Primitive a() {
        if (this.a != null) {
            return this.a;
        }
        if (this.b != null) {
            return this.b.a();
        }
        return null;
    }

    public String toString() {
        if (this.a != null) {
            return this.a.toString();
        }
        if (this.b != null) {
            return this.b.toString();
        }
        return null;
    }
}
