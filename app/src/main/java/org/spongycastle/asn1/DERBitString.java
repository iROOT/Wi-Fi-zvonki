package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.io.Streams;

public class DERBitString extends ASN1Primitive implements ASN1String {
    private static final char[] c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    protected byte[] a;
    protected int b;

    protected static int a(int i) {
        int i2;
        int i3 = 0;
        for (i2 = 3; i2 >= 0; i2--) {
            if (i2 != 0) {
                if ((i >> (i2 * 8)) != 0) {
                    i3 = (i >> (i2 * 8)) & 255;
                    break;
                }
            } else if (i != 0) {
                i3 = i & 255;
                break;
            }
        }
        if (i3 == 0) {
            return 7;
        }
        i2 = 1;
        while (true) {
            i3 <<= 1;
            if ((i3 & 255) == 0) {
                return 8 - i2;
            }
            i2++;
        }
    }

    protected static byte[] b(int i) {
        int i2 = 4;
        int i3 = 3;
        while (i3 >= 1 && ((255 << (i3 * 8)) & i) == 0) {
            i2--;
            i3--;
        }
        byte[] bArr = new byte[i2];
        for (i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) ((i >> (i3 * 8)) & 255);
        }
        return bArr;
    }

    public static DERBitString a(Object obj) {
        if (obj == null || (obj instanceof DERBitString)) {
            return (DERBitString) obj;
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static DERBitString a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        Object l = aSN1TaggedObject.l();
        if (z || (l instanceof DERBitString)) {
            return a(l);
        }
        return b(((ASN1OctetString) l).e());
    }

    public DERBitString(byte[] bArr, int i) {
        this.a = bArr;
        this.b = i;
    }

    public DERBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DERBitString(int i) {
        this.a = b(i);
        this.b = a(i);
    }

    public DERBitString(ASN1Encodable aSN1Encodable) {
        this.a = aSN1Encodable.a().a("DER");
        this.b = 0;
    }

    public byte[] d() {
        return this.a;
    }

    public int e() {
        return this.b;
    }

    public int f() {
        int i = 0;
        int i2 = 0;
        while (i != this.a.length && i != 4) {
            i2 |= (this.a[i] & 255) << (i * 8);
            i++;
        }
        return i2;
    }

    boolean i() {
        return false;
    }

    int j() {
        return ((StreamUtil.a(this.a.length + 1) + 1) + this.a.length) + 1;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        byte[] bArr = new byte[(d().length + 1)];
        bArr[0] = (byte) e();
        System.arraycopy(d(), 0, bArr, 1, bArr.length - 1);
        aSN1OutputStream.a(3, bArr);
    }

    public int hashCode() {
        return this.b ^ Arrays.a(this.a);
    }

    protected boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERBitString)) {
            return false;
        }
        DERBitString dERBitString = (DERBitString) aSN1Primitive;
        if (this.b == dERBitString.b && Arrays.a(this.a, dERBitString.a)) {
            return true;
        }
        return false;
    }

    public String a_() {
        StringBuffer stringBuffer = new StringBuffer("#");
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ASN1OutputStream(byteArrayOutputStream).a((ASN1Encodable) this);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            for (int i = 0; i != toByteArray.length; i++) {
                stringBuffer.append(c[(toByteArray[i] >>> 4) & 15]);
                stringBuffer.append(c[toByteArray[i] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new RuntimeException("internal error encoding BitString");
        }
    }

    public String toString() {
        return a_();
    }

    static DERBitString b(byte[] bArr) {
        if (bArr.length < 1) {
            throw new IllegalArgumentException("truncated BIT STRING detected");
        }
        byte b = bArr[0];
        Object obj = new byte[(bArr.length - 1)];
        if (obj.length != 0) {
            System.arraycopy(bArr, 1, obj, 0, bArr.length - 1);
        }
        return new DERBitString(obj, b);
    }

    static DERBitString a(int i, InputStream inputStream) {
        if (i < 1) {
            throw new IllegalArgumentException("truncated BIT STRING detected");
        }
        int read = inputStream.read();
        byte[] bArr = new byte[(i - 1)];
        if (bArr.length == 0 || Streams.a(inputStream, bArr) == bArr.length) {
            return new DERBitString(bArr, read);
        }
        throw new EOFException("EOF encountered in middle of BIT STRING");
    }
}
