package org.spongycastle.asn1;

import android.support.v4.app.NotificationCompat;
import java.util.Enumeration;

public class BERTaggedObject extends ASN1TaggedObject {
    public BERTaggedObject(int i, ASN1Encodable aSN1Encodable) {
        super(true, i, aSN1Encodable);
    }

    public BERTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
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
        int j = this.d.a().j();
        if (this.c) {
            return j + (StreamUtil.b(this.a) + StreamUtil.a(j));
        }
        return (j - 1) + StreamUtil.b(this.a);
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.a(160, this.a);
        aSN1OutputStream.b(NotificationCompat.FLAG_HIGH_PRIORITY);
        if (!this.b) {
            if (this.c) {
                aSN1OutputStream.a(this.d);
            } else {
                Enumeration k;
                if (this.d instanceof ASN1OctetString) {
                    if (this.d instanceof BEROctetString) {
                        k = ((BEROctetString) this.d).k();
                    } else {
                        k = new BEROctetString(((ASN1OctetString) this.d).e()).k();
                    }
                } else if (this.d instanceof ASN1Sequence) {
                    k = ((ASN1Sequence) this.d).e();
                } else if (this.d instanceof ASN1Set) {
                    k = ((ASN1Set) this.d).d();
                } else {
                    throw new RuntimeException("not implemented: " + this.d.getClass().getName());
                }
                while (k.hasMoreElements()) {
                    aSN1OutputStream.a((ASN1Encodable) k.nextElement());
                }
            }
        }
        aSN1OutputStream.b(0);
        aSN1OutputStream.b(0);
    }
}
