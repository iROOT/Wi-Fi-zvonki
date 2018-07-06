package org.spongycastle.asn1;

import java.util.Enumeration;

public class DERSet extends ASN1Set {
    private int a = -1;

    public DERSet(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public DERSet(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector, true);
    }

    public DERSet(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr, true);
    }

    DERSet(ASN1EncodableVector aSN1EncodableVector, boolean z) {
        super(aSN1EncodableVector, z);
    }

    private int k() {
        if (this.a < 0) {
            Enumeration d = d();
            int i = 0;
            while (d.hasMoreElements()) {
                i = ((ASN1Encodable) d.nextElement()).a().g().j() + i;
            }
            this.a = i;
        }
        return this.a;
    }

    int j() {
        int k = k();
        return k + (StreamUtil.a(k) + 1);
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        ASN1OutputStream a = aSN1OutputStream.a();
        int k = k();
        aSN1OutputStream.b(49);
        aSN1OutputStream.a(k);
        Enumeration d = d();
        while (d.hasMoreElements()) {
            a.a((ASN1Encodable) d.nextElement());
        }
    }
}
