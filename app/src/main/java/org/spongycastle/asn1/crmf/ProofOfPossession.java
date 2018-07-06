package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERTaggedObject;

public class ProofOfPossession extends ASN1Object implements ASN1Choice {
    private int a = 0;
    private ASN1Encodable b = DERNull.a;

    public ASN1Primitive a() {
        return new DERTaggedObject(false, this.a, this.b);
    }
}
