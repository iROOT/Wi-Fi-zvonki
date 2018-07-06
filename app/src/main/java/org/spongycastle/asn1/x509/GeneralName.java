package org.spongycastle.asn1.x509;

import java.io.IOException;
import java.util.StringTokenizer;
import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.util.IPAddress;

public class GeneralName extends ASN1Object implements ASN1Choice {
    private ASN1Encodable a;
    private int b;

    public GeneralName(X509Name x509Name) {
        this.a = X500Name.a((Object) x509Name);
        this.b = 4;
    }

    public GeneralName(X500Name x500Name) {
        this.a = x500Name;
        this.b = 4;
    }

    public GeneralName(int i, ASN1Encodable aSN1Encodable) {
        this.a = aSN1Encodable;
        this.b = i;
    }

    public GeneralName(int i, String str) {
        this.b = i;
        if (i == 1 || i == 2 || i == 6) {
            this.a = new DERIA5String(str);
        } else if (i == 8) {
            this.a = new ASN1ObjectIdentifier(str);
        } else if (i == 4) {
            this.a = new X500Name(str);
        } else if (i == 7) {
            byte[] b = b(str);
            if (b != null) {
                this.a = new DEROctetString(b);
                return;
            }
            throw new IllegalArgumentException("IP Address is invalid");
        } else {
            throw new IllegalArgumentException("can't process String for tag: " + i);
        }
    }

    public static GeneralName a(Object obj) {
        if (obj == null || (obj instanceof GeneralName)) {
            return (GeneralName) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) obj;
            int d = aSN1TaggedObject.d();
            switch (d) {
                case 0:
                    return new GeneralName(d, ASN1Sequence.a(aSN1TaggedObject, false));
                case 1:
                    return new GeneralName(d, DERIA5String.a(aSN1TaggedObject, false));
                case 2:
                    return new GeneralName(d, DERIA5String.a(aSN1TaggedObject, false));
                case 3:
                    throw new IllegalArgumentException("unknown tag: " + d);
                case 4:
                    return new GeneralName(d, X500Name.a(aSN1TaggedObject, true));
                case 5:
                    return new GeneralName(d, ASN1Sequence.a(aSN1TaggedObject, false));
                case 6:
                    return new GeneralName(d, DERIA5String.a(aSN1TaggedObject, false));
                case 7:
                    return new GeneralName(d, ASN1OctetString.a(aSN1TaggedObject, false));
                case 8:
                    return new GeneralName(d, DERObjectIdentifier.a(aSN1TaggedObject, false));
            }
        }
        if (obj instanceof byte[]) {
            try {
                return a(ASN1Primitive.a((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("unable to parse encoded general name");
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public static GeneralName a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return a(ASN1TaggedObject.a(aSN1TaggedObject, true));
    }

    public int d() {
        return this.b;
    }

    public ASN1Encodable e() {
        return this.a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.b);
        stringBuffer.append(": ");
        switch (this.b) {
            case 1:
            case 2:
            case 6:
                stringBuffer.append(DERIA5String.a(this.a).a_());
                break;
            case 4:
                stringBuffer.append(X500Name.a(this.a).toString());
                break;
            default:
                stringBuffer.append(this.a.toString());
                break;
        }
        return stringBuffer.toString();
    }

    private byte[] b(String str) {
        byte[] bArr;
        if (IPAddress.c(str) || IPAddress.d(str)) {
            int indexOf = str.indexOf(47);
            if (indexOf < 0) {
                bArr = new byte[16];
                a(d(str), bArr, 0);
                return bArr;
            }
            int[] d;
            byte[] bArr2 = new byte[32];
            a(d(str.substring(0, indexOf)), bArr2, 0);
            String substring = str.substring(indexOf + 1);
            if (substring.indexOf(58) > 0) {
                d = d(substring);
            } else {
                d = c(substring);
            }
            a(d, bArr2, 16);
            return bArr2;
        } else if (!IPAddress.b(str) && !IPAddress.a(str)) {
            return null;
        } else {
            int indexOf2 = str.indexOf(47);
            if (indexOf2 < 0) {
                bArr = new byte[4];
                b(str, bArr, 0);
                return bArr;
            }
            bArr = new byte[8];
            b(str.substring(0, indexOf2), bArr, 0);
            String substring2 = str.substring(indexOf2 + 1);
            if (substring2.indexOf(46) > 0) {
                b(substring2, bArr, 4);
                return bArr;
            }
            a(substring2, bArr, 4);
            return bArr;
        }
    }

    private void a(String str, byte[] bArr, int i) {
        int parseInt = Integer.parseInt(str);
        for (int i2 = 0; i2 != parseInt; i2++) {
            int i3 = (i2 / 8) + i;
            bArr[i3] = (byte) (bArr[i3] | (1 << (7 - (i2 % 8))));
        }
    }

    private void b(String str, byte[] bArr, int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "./");
        int i2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            int i3 = i2 + 1;
            bArr[i2 + i] = (byte) Integer.parseInt(stringTokenizer.nextToken());
            i2 = i3;
        }
    }

    private int[] c(String str) {
        int[] iArr = new int[8];
        int parseInt = Integer.parseInt(str);
        for (int i = 0; i != parseInt; i++) {
            int i2 = i / 16;
            iArr[i2] = iArr[i2] | (1 << (15 - (i % 16)));
        }
        return iArr;
    }

    private void a(int[] iArr, byte[] bArr, int i) {
        for (int i2 = 0; i2 != iArr.length; i2++) {
            bArr[(i2 * 2) + i] = (byte) (iArr[i2] >> 8);
            bArr[((i2 * 2) + 1) + i] = (byte) iArr[i2];
        }
    }

    private int[] d(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ":", true);
        Object obj = new int[8];
        if (str.charAt(0) == ':' && str.charAt(1) == ':') {
            stringTokenizer.nextToken();
        }
        int i = -1;
        int i2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            int i3;
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals(":")) {
                i = i2 + 1;
                obj[i2] = null;
                int i4 = i2;
                i2 = i;
                i = i4;
            } else if (nextToken.indexOf(46) < 0) {
                i3 = i2 + 1;
                obj[i2] = Integer.parseInt(nextToken, 16);
                if (stringTokenizer.hasMoreTokens()) {
                    stringTokenizer.nextToken();
                    i2 = i3;
                } else {
                    i2 = i3;
                }
            } else {
                StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken, ".");
                int i5 = i2 + 1;
                obj[i2] = (Integer.parseInt(stringTokenizer2.nextToken()) << 8) | Integer.parseInt(stringTokenizer2.nextToken());
                i2 = i5 + 1;
                obj[i5] = Integer.parseInt(stringTokenizer2.nextToken()) | (Integer.parseInt(stringTokenizer2.nextToken()) << 8);
            }
        }
        if (i2 != obj.length) {
            System.arraycopy(obj, i, obj, obj.length - (i2 - i), i2 - i);
            for (i3 = i; i3 != obj.length - (i2 - i); i3++) {
                obj[i3] = null;
            }
        }
        return obj;
    }

    public ASN1Primitive a() {
        if (this.b == 4) {
            return new DERTaggedObject(true, this.b, this.a);
        }
        return new DERTaggedObject(false, this.b, this.a);
    }
}
