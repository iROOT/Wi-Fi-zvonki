package org.spongycastle.asn1;

import org.spongycastle.util.Arrays;

public class DERBoolean extends ASN1Primitive {
    public static final ASN1Boolean a = new ASN1Boolean(false);
    public static final ASN1Boolean b = new ASN1Boolean(true);
    private static final byte[] c = new byte[]{(byte) -1};
    private static final byte[] d = new byte[]{(byte) 0};
    private byte[] e;

    public static ASN1Boolean a(Object obj) {
        if (obj == null || (obj instanceof ASN1Boolean)) {
            return (ASN1Boolean) obj;
        }
        if (obj instanceof DERBoolean) {
            return ((DERBoolean) obj).d() ? b : a;
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Boolean a(boolean z) {
        return z ? b : a;
    }

    public static ASN1Boolean a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        Object l = aSN1TaggedObject.l();
        if (z || (l instanceof DERBoolean)) {
            return a(l);
        }
        return b(((ASN1OctetString) l).e());
    }

    DERBoolean(byte[] bArr) {
        if (bArr.length != 1) {
            throw new IllegalArgumentException("byte value should have 1 byte in it");
        } else if (bArr[0] == (byte) 0) {
            this.e = d;
        } else if (bArr[0] == (byte) -1) {
            this.e = c;
        } else {
            this.e = Arrays.b(bArr);
        }
    }

    public DERBoolean(boolean z) {
        this.e = z ? c : d;
    }

    public boolean d() {
        return this.e[0] != (byte) 0;
    }

    boolean i() {
        return false;
    }

    int j() {
        return 3;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.a(1, this.e);
    }

    protected boolean a(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive != null && (aSN1Primitive instanceof DERBoolean) && this.e[0] == ((DERBoolean) aSN1Primitive).e[0]) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.e[0];
    }

    public String toString() {
        return this.e[0] != (byte) 0 ? "TRUE" : "FALSE";
    }

    static ASN1Boolean b(byte[] bArr) {
        if (bArr.length != 1) {
            throw new IllegalArgumentException("BOOLEAN value should have 1 byte in it");
        } else if (bArr[0] == (byte) 0) {
            return a;
        } else {
            if (bArr[0] == (byte) -1) {
                return b;
            }
            return new ASN1Boolean(bArr);
        }
    }
}
