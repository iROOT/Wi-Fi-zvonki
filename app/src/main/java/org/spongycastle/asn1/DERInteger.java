package org.spongycastle.asn1;

import java.math.BigInteger;
import org.spongycastle.util.Arrays;

public class DERInteger extends ASN1Primitive {
    byte[] a;

    public static ASN1Integer a(Object obj) {
        if (obj == null || (obj instanceof ASN1Integer)) {
            return (ASN1Integer) obj;
        }
        if (obj instanceof DERInteger) {
            return new ASN1Integer(((DERInteger) obj).d());
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1Integer) ASN1Primitive.a((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1Integer a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        Object l = aSN1TaggedObject.l();
        if (z || (l instanceof DERInteger)) {
            return a(l);
        }
        return new ASN1Integer(ASN1OctetString.a(aSN1TaggedObject.l()).e());
    }

    public DERInteger(long j) {
        this.a = BigInteger.valueOf(j).toByteArray();
    }

    public DERInteger(BigInteger bigInteger) {
        this.a = bigInteger.toByteArray();
    }

    public DERInteger(byte[] bArr) {
        this.a = bArr;
    }

    public BigInteger d() {
        return new BigInteger(this.a);
    }

    public BigInteger e() {
        return new BigInteger(1, this.a);
    }

    boolean i() {
        return false;
    }

    int j() {
        return (StreamUtil.a(this.a.length) + 1) + this.a.length;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.a(2, this.a);
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (i != this.a.length) {
            i2 ^= (this.a[i] & 255) << (i % 4);
            i++;
        }
        return i2;
    }

    boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERInteger)) {
            return false;
        }
        return Arrays.a(this.a, ((DERInteger) aSN1Primitive).a);
    }

    public String toString() {
        return d().toString();
    }
}
