package org.spongycastle.asn1;

import android.support.v4.app.NotificationCompat;
import java.util.Enumeration;

public class BERSequence extends ASN1Sequence {
    public BERSequence(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public BERSequence(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector);
    }

    int j() {
        Enumeration e = e();
        int i = 0;
        while (e.hasMoreElements()) {
            i = ((ASN1Encodable) e.nextElement()).a().j() + i;
        }
        return (i + 2) + 2;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.b(48);
        aSN1OutputStream.b(NotificationCompat.FLAG_HIGH_PRIORITY);
        Enumeration e = e();
        while (e.hasMoreElements()) {
            aSN1OutputStream.a((ASN1Encodable) e.nextElement());
        }
        aSN1OutputStream.b(0);
        aSN1OutputStream.b(0);
    }
}
