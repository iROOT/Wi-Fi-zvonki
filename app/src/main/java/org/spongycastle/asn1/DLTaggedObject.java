package org.spongycastle.asn1;

import android.support.v4.app.NotificationCompat;

public class DLTaggedObject extends ASN1TaggedObject {
    private static final byte[] e = new byte[0];

    public DLTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    boolean i() {
        if (this.b || this.c) {
            return true;
        }
        return this.d.a().h().i();
    }

    int j() {
        if (this.b) {
            return StreamUtil.b(this.a) + 1;
        }
        int j = this.d.a().h().j();
        if (this.c) {
            return j + (StreamUtil.b(this.a) + StreamUtil.a(j));
        }
        return (j - 1) + StreamUtil.b(this.a);
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        int i = 160;
        if (this.b) {
            aSN1OutputStream.a(160, this.a, e);
            return;
        }
        ASN1Primitive h = this.d.a().h();
        if (this.c) {
            aSN1OutputStream.a(160, this.a);
            aSN1OutputStream.a(h.j());
            aSN1OutputStream.a((ASN1Encodable) h);
            return;
        }
        if (!h.i()) {
            i = NotificationCompat.FLAG_HIGH_PRIORITY;
        }
        aSN1OutputStream.a(i, this.a);
        aSN1OutputStream.a(h);
    }
}
