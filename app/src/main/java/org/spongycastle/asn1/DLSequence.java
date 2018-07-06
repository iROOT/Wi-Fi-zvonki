package org.spongycastle.asn1;

import java.util.Enumeration;

public class DLSequence extends ASN1Sequence {
    private int b = -1;

    public DLSequence(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public DLSequence(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector);
    }

    private int k() {
        if (this.b < 0) {
            Enumeration e = e();
            int i = 0;
            while (e.hasMoreElements()) {
                i = ((ASN1Encodable) e.nextElement()).a().h().j() + i;
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
        ASN1OutputStream b = aSN1OutputStream.b();
        int k = k();
        aSN1OutputStream.b(48);
        aSN1OutputStream.a(k);
        Enumeration e = e();
        while (e.hasMoreElements()) {
            b.a((ASN1Encodable) e.nextElement());
        }
    }
}
