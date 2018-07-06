package org.spongycastle.asn1.ess;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;

public class ContentIdentifier extends ASN1Object {
    ASN1OctetString a;

    public ASN1Primitive a() {
        return this.a;
    }
}
