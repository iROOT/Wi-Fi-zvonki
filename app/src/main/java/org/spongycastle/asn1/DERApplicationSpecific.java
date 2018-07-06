package org.spongycastle.asn1;

import android.support.v4.app.NotificationCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.spongycastle.util.Arrays;

public class DERApplicationSpecific extends ASN1Primitive {
    private final boolean a;
    private final int b;
    private final byte[] c;

    DERApplicationSpecific(boolean z, int i, byte[] bArr) {
        this.a = z;
        this.b = i;
        this.c = bArr;
    }

    public DERApplicationSpecific(boolean z, int i, ASN1Encodable aSN1Encodable) {
        ASN1Primitive a = aSN1Encodable.a();
        Object a2 = a.a("DER");
        boolean z2 = z || (a instanceof ASN1Set) || (a instanceof ASN1Sequence);
        this.a = z2;
        this.b = i;
        if (z) {
            this.c = a2;
            return;
        }
        int b = b(a2);
        Object obj = new byte[(a2.length - b)];
        System.arraycopy(a2, b, obj, 0, obj.length);
        this.c = obj;
    }

    public DERApplicationSpecific(int i, ASN1EncodableVector aSN1EncodableVector) {
        this.b = i;
        this.a = true;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        while (i2 != aSN1EncodableVector.a()) {
            try {
                byteArrayOutputStream.write(((ASN1Object) aSN1EncodableVector.a(i2)).a("DER"));
                i2++;
            } catch (Throwable e) {
                throw new ASN1ParsingException("malformed object: " + e, e);
            }
        }
        this.c = byteArrayOutputStream.toByteArray();
    }

    private int b(byte[] bArr) {
        int i = bArr[1] & 255;
        if (i == NotificationCompat.FLAG_HIGH_PRIORITY || i <= 127) {
            return 2;
        }
        int i2 = i & 127;
        if (i2 <= 4) {
            return i2 + 2;
        }
        throw new IllegalStateException("DER length more than 4 bytes: " + i2);
    }

    public boolean i() {
        return this.a;
    }

    public byte[] d() {
        return this.c;
    }

    public int e() {
        return this.b;
    }

    public ASN1Primitive a(int i) {
        if (i >= 31) {
            throw new IOException("unsupported tag number");
        }
        byte[] b = b();
        byte[] a = a(i, b);
        if ((b[0] & 32) != 0) {
            a[0] = (byte) (a[0] | 32);
        }
        return new ASN1InputStream(a).d();
    }

    int j() {
        return (StreamUtil.b(this.b) + StreamUtil.a(this.c.length)) + this.c.length;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        int i = 64;
        if (this.a) {
            i = 96;
        }
        aSN1OutputStream.a(i, this.b, this.c);
    }

    boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERApplicationSpecific)) {
            return false;
        }
        DERApplicationSpecific dERApplicationSpecific = (DERApplicationSpecific) aSN1Primitive;
        if (this.a == dERApplicationSpecific.a && this.b == dERApplicationSpecific.b && Arrays.a(this.c, dERApplicationSpecific.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.a ? 1 : 0) ^ this.b) ^ Arrays.a(this.c);
    }

    private byte[] a(int i, byte[] bArr) {
        int i2;
        if ((bArr[0] & 31) == 31) {
            i2 = bArr[1] & 255;
            if ((i2 & 127) == 0) {
                throw new ASN1ParsingException("corrupted stream - invalid high tag number found");
            }
            int i3 = 0;
            int i4 = i2;
            i2 = 2;
            while (i4 >= 0 && (i4 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
                i3 = ((i4 & 127) | i3) << 7;
                int i5 = bArr[i2] & 255;
                i2++;
                i4 = i5;
            }
            i4 = (i4 & 127) | i3;
        } else {
            i2 = 1;
        }
        Object obj = new byte[((bArr.length - i2) + 1)];
        System.arraycopy(bArr, i2, obj, 1, obj.length - 1);
        obj[0] = (byte) i;
        return obj;
    }
}
