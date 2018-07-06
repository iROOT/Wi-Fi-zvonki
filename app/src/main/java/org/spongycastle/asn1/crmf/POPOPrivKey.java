package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERTaggedObject;

public class POPOPrivKey extends ASN1Object implements ASN1Choice {
    private int a;
    private ASN1Encodable b;

    public ASN1Primitive a() {
        return new DERTaggedObject(false, this.a, this.b);
    }
}
