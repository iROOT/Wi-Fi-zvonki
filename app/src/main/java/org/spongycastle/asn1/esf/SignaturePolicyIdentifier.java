package org.spongycastle.asn1.esf;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERNull;

public class SignaturePolicyIdentifier extends ASN1Object {
    private SignaturePolicyId a;
    private boolean b = true;

    public ASN1Primitive a() {
        if (this.b) {
            return DERNull.a;
        }
        return this.a.a();
    }
}
