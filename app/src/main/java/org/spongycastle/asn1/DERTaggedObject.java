package org.spongycastle.asn1;

import android.support.v4.app.NotificationCompat;

public class DERTaggedObject extends ASN1TaggedObject {
    private static final byte[] e = new byte[0];

    public DERTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    public DERTaggedObject(int i, ASN1Encodable aSN1Encodable) {
        super(true, i, aSN1Encodable);
    }

    boolean i() {
        if (this.b || this.c) {
            return true;
        }
        return this.d.a().g().i();
    }

    int j() {
        if (this.b) {
            return StreamUtil.b(this.a) + 1;
        }
        int j = this.d.a().g().j();
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
        ASN1Primitive g = this.d.a().g();
        if (this.c) {
            aSN1OutputStream.a(160, this.a);
            aSN1OutputStream.a(g.j());
            aSN1OutputStream.a((ASN1Encodable) g);
            return;
        }
        if (!g.i()) {
            i = NotificationCompat.FLAG_HIGH_PRIORITY;
        }
        aSN1OutputStream.a(i, this.a);
        aSN1OutputStream.a(g);
    }
}
