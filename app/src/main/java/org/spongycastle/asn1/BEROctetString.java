package org.spongycastle.asn1;

import android.support.v4.app.NotificationCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public class BEROctetString extends ASN1OctetString {
    private ASN1OctetString[] b;

    private static byte[] a(ASN1OctetString[] aSN1OctetStringArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i != aSN1OctetStringArr.length) {
            try {
                byteArrayOutputStream.write(((DEROctetString) aSN1OctetStringArr[i]).e());
                i++;
            } catch (ClassCastException e) {
                throw new IllegalArgumentException(aSN1OctetStringArr[i].getClass().getName() + " found in input should only contain DEROctetString");
            } catch (IOException e2) {
                throw new IllegalArgumentException("exception converting octets " + e2.toString());
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public BEROctetString(byte[] bArr) {
        super(bArr);
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr) {
        super(a(aSN1OctetStringArr));
        this.b = aSN1OctetStringArr;
    }

    public byte[] e() {
        return this.a;
    }

    public Enumeration k() {
        if (this.b == null) {
            return l().elements();
        }
        return new Enumeration(this) {
            int a = 0;
            final /* synthetic */ BEROctetString b;

            {
                this.b = r2;
            }

            public boolean hasMoreElements() {
                return this.a < this.b.b.length;
            }

            public Object nextElement() {
                ASN1OctetString[] a = this.b.b;
                int i = this.a;
                this.a = i + 1;
                return a[i];
            }
        };
    }

    private Vector l() {
        Vector vector = new Vector();
        for (int i = 0; i < this.a.length; i += 1000) {
            int length;
            if (i + 1000 > this.a.length) {
                length = this.a.length;
            } else {
                length = i + 1000;
            }
            Object obj = new byte[(length - i)];
            System.arraycopy(this.a, i, obj, 0, obj.length);
            vector.addElement(new DEROctetString(obj));
        }
        return vector;
    }

    boolean i() {
        return true;
    }

    int j() {
        Enumeration k = k();
        int i = 0;
        while (k.hasMoreElements()) {
            i = ((ASN1Encodable) k.nextElement()).a().j() + i;
        }
        return (i + 2) + 2;
    }

    public void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.b(36);
        aSN1OutputStream.b(NotificationCompat.FLAG_HIGH_PRIORITY);
        Enumeration k = k();
        while (k.hasMoreElements()) {
            aSN1OutputStream.a((ASN1Encodable) k.nextElement());
        }
        aSN1OutputStream.b(0);
        aSN1OutputStream.b(0);
    }

    static BEROctetString a(ASN1Sequence aSN1Sequence) {
        ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[aSN1Sequence.f()];
        Enumeration e = aSN1Sequence.e();
        int i = 0;
        while (e.hasMoreElements()) {
            int i2 = i + 1;
            aSN1OctetStringArr[i] = (ASN1OctetString) e.nextElement();
            i = i2;
        }
        return new BEROctetString(aSN1OctetStringArr);
    }
}
