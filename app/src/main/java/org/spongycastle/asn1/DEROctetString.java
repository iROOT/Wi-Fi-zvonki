package org.spongycastle.asn1;

public class DEROctetString extends ASN1OctetString {
    public DEROctetString(byte[] bArr) {
        super(bArr);
    }

    boolean i() {
        return false;
    }

    int j() {
        return (StreamUtil.a(this.a.length) + 1) + this.a.length;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.a(4, this.a);
    }

    static void a(DEROutputStream dEROutputStream, byte[] bArr) {
        dEROutputStream.a(4, bArr);
    }
}
