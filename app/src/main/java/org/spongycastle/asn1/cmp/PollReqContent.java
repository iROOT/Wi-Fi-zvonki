package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;

public class PollReqContent extends ASN1Object {
    private ASN1Sequence a;

    public ASN1Primitive a() {
        return this.a;
    }
}
