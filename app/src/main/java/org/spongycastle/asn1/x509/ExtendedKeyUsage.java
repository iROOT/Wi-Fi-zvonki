package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;

public class ExtendedKeyUsage extends ASN1Object {
    ASN1Sequence a;

    public ASN1Primitive a() {
        return this.a;
    }
}
