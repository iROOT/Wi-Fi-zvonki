package org.spongycastle.asn1.x9;

import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;

public class DHPublicKey extends ASN1Object {
    private ASN1Integer a;

    public static DHPublicKey a(Object obj) {
        if (obj == null || (obj instanceof DHPublicKey)) {
            return (DHPublicKey) obj;
        }
        if (obj instanceof ASN1Integer) {
            return new DHPublicKey((ASN1Integer) obj);
        }
        throw new IllegalArgumentException("Invalid DHPublicKey: " + obj.getClass().getName());
    }

    public DHPublicKey(ASN1Integer aSN1Integer) {
        if (aSN1Integer == null) {
            throw new IllegalArgumentException("'y' cannot be null");
        }
        this.a = aSN1Integer;
    }

    public ASN1Integer d() {
        return this.a;
    }

    public ASN1Primitive a() {
        return this.a;
    }
}
