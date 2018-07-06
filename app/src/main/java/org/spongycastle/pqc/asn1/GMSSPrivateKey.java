package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;

public class GMSSPrivateKey extends ASN1Object {
    private ASN1Primitive a;

    public ASN1Primitive a() {
        return this.a;
    }
}
