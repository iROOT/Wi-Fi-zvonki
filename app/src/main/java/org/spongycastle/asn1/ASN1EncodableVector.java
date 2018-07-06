package org.spongycastle.asn1;

import java.util.Vector;

public class ASN1EncodableVector {
    Vector a = new Vector();

    public void a(ASN1Encodable aSN1Encodable) {
        this.a.addElement(aSN1Encodable);
    }

    public ASN1Encodable a(int i) {
        return (ASN1Encodable) this.a.elementAt(i);
    }

    public int a() {
        return this.a.size();
    }
}
