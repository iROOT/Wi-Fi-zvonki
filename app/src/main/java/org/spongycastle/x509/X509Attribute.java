package org.spongycastle.x509;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.x509.Attribute;

public class X509Attribute extends ASN1Object {
    Attribute a;

    X509Attribute(ASN1Encodable aSN1Encodable) {
        this.a = Attribute.a(aSN1Encodable);
    }

    public String d() {
        return this.a.d().d();
    }

    public ASN1Primitive a() {
        return this.a.a();
    }
}
