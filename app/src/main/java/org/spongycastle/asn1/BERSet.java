package org.spongycastle.asn1;

import android.support.v4.app.NotificationCompat;
import java.util.Enumeration;

public class BERSet extends ASN1Set {
    public BERSet(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public BERSet(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector, false);
    }

    public BERSet(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr, false);
    }

    int j() {
        Enumeration d = d();
        int i = 0;
        while (d.hasMoreElements()) {
            i = ((ASN1Encodable) d.nextElement()).a().j() + i;
        }
        return (i + 2) + 2;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.b(49);
        aSN1OutputStream.b(NotificationCompat.FLAG_HIGH_PRIORITY);
        Enumeration d = d();
        while (d.hasMoreElements()) {
            aSN1OutputStream.a((ASN1Encodable) d.nextElement());
        }
        aSN1OutputStream.b(0);
        aSN1OutputStream.b(0);
    }
}
