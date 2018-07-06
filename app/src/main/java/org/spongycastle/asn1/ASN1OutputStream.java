package org.spongycastle.asn1;

import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.io.OutputStream;

public class ASN1OutputStream {
    private OutputStream a;

    private class ImplicitOutputStream extends ASN1OutputStream {
        final /* synthetic */ ASN1OutputStream a;
        private boolean b = true;

        public ImplicitOutputStream(ASN1OutputStream aSN1OutputStream, OutputStream outputStream) {
            this.a = aSN1OutputStream;
            super(outputStream);
        }

        public void b(int i) {
            if (this.b) {
                this.b = false;
            } else {
                super.b(i);
            }
        }
    }

    public ASN1OutputStream(OutputStream outputStream) {
        this.a = outputStream;
    }

    void a(int i) {
        if (i > 127) {
            int i2 = 1;
            int i3 = i;
            while (true) {
                i3 >>>= 8;
                if (i3 == 0) {
                    break;
                }
                i2++;
            }
            b((byte) (i2 | NotificationCompat.FLAG_HIGH_PRIORITY));
            for (i3 = (i2 - 1) * 8; i3 >= 0; i3 -= 8) {
                b((byte) (i >> i3));
            }
            return;
        }
        b((byte) i);
    }

    void b(int i) {
        this.a.write(i);
    }

    void a(byte[] bArr) {
        this.a.write(bArr);
    }

    void a(byte[] bArr, int i, int i2) {
        this.a.write(bArr, i, i2);
    }

    void a(int i, byte[] bArr) {
        b(i);
        a(bArr.length);
        a(bArr);
    }

    void a(int i, int i2) {
        if (i2 < 31) {
            b(i | i2);
            return;
        }
        b(i | 31);
        if (i2 < NotificationCompat.FLAG_HIGH_PRIORITY) {
            b(i2);
            return;
        }
        byte[] bArr = new byte[5];
        int length = bArr.length - 1;
        bArr[length] = (byte) (i2 & 127);
        do {
            i2 >>= 7;
            length--;
            bArr[length] = (byte) ((i2 & 127) | NotificationCompat.FLAG_HIGH_PRIORITY);
        } while (i2 > 127);
        a(bArr, length, bArr.length - length);
    }

    void a(int i, int i2, byte[] bArr) {
        a(i, i2);
        a(bArr.length);
        a(bArr);
    }

    public void a(ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1Encodable.a().a(this);
            return;
        }
        throw new IOException("null object detected");
    }

    void a(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive != null) {
            aSN1Primitive.a(new ImplicitOutputStream(this, this.a));
            return;
        }
        throw new IOException("null object detected");
    }

    ASN1OutputStream a() {
        return new DEROutputStream(this.a);
    }

    ASN1OutputStream b() {
        return new DLOutputStream(this.a);
    }
}
