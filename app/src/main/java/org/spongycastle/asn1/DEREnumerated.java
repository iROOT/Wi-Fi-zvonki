package org.spongycastle.asn1;

import java.math.BigInteger;
import org.spongycastle.util.Arrays;

public class DEREnumerated extends ASN1Primitive {
    private static ASN1Enumerated[] b = new ASN1Enumerated[12];
    byte[] a;

    public static ASN1Enumerated a(Object obj) {
        if (obj == null || (obj instanceof ASN1Enumerated)) {
            return (ASN1Enumerated) obj;
        }
        if (obj instanceof DEREnumerated) {
            return new ASN1Enumerated(((DEREnumerated) obj).d());
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1Enumerated) ASN1Primitive.a((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1Enumerated a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        Object l = aSN1TaggedObject.l();
        if (z || (l instanceof DEREnumerated)) {
            return a(l);
        }
        return b(((ASN1OctetString) l).e());
    }

    public DEREnumerated(int i) {
        this.a = BigInteger.valueOf((long) i).toByteArray();
    }

    public DEREnumerated(BigInteger bigInteger) {
        this.a = bigInteger.toByteArray();
    }

    public DEREnumerated(byte[] bArr) {
        this.a = bArr;
    }

    public BigInteger d() {
        return new BigInteger(this.a);
    }

    boolean i() {
        return false;
    }

    int j() {
        return (StreamUtil.a(this.a.length) + 1) + this.a.length;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.a(10, this.a);
    }

    boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DEREnumerated)) {
            return false;
        }
        return Arrays.a(this.a, ((DEREnumerated) aSN1Primitive).a);
    }

    public int hashCode() {
        return Arrays.a(this.a);
    }

    static ASN1Enumerated b(byte[] bArr) {
        if (bArr.length > 1) {
            return new ASN1Enumerated(Arrays.b(bArr));
        }
        if (bArr.length == 0) {
            throw new IllegalArgumentException("ENUMERATED has zero length");
        }
        int i = bArr[0] & 255;
        if (i >= b.length) {
            return new ASN1Enumerated(Arrays.b(bArr));
        }
        ASN1Enumerated aSN1Enumerated = b[i];
        if (aSN1Enumerated != null) {
            return aSN1Enumerated;
        }
        ASN1Enumerated[] aSN1EnumeratedArr = b;
        aSN1Enumerated = new ASN1Enumerated(Arrays.b(bArr));
        aSN1EnumeratedArr[i] = aSN1Enumerated;
        return aSN1Enumerated;
    }
}
