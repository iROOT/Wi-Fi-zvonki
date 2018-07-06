package org.spongycastle.asn1;

public class DERNull extends ASN1Null {
    public static final DERNull a = new DERNull();
    private static final byte[] b = new byte[0];

    boolean i() {
        return false;
    }

    int j() {
        return 2;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.a(5, b);
    }
}
