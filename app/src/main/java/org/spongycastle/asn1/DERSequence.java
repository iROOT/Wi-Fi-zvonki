package org.spongycastle.asn1;

import java.util.Enumeration;

public class DERSequence extends ASN1Sequence {
    private int b = -1;

    public DERSequence(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public DERSequence(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector);
    }

    public DERSequence(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr);
    }

    private int k() {
        if (this.b < 0) {
            Enumeration e = e();
            int i = 0;
            while (e.hasMoreElements()) {
                i = ((ASN1Encodable) e.nextElement()).a().g().j() + i;
            }
            this.b = i;
        }
        return this.b;
    }

    int j() {
        int k = k();
        return k + (StreamUtil.a(k) + 1);
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        ASN1OutputStream a = aSN1OutputStream.a();
        int k = k();
        aSN1OutputStream.b(48);
        aSN1OutputStream.a(k);
        Enumeration e = e();
        while (e.hasMoreElements()) {
            a.a((ASN1Encodable) e.nextElement());
        }
    }
}
