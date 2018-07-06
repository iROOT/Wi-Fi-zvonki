package org.spongycastle.asn1;

import java.io.IOException;

public abstract class ASN1Primitive extends ASN1Object {
    abstract void a(ASN1OutputStream aSN1OutputStream);

    abstract boolean a(ASN1Primitive aSN1Primitive);

    public abstract int hashCode();

    abstract boolean i();

    abstract int j();

    ASN1Primitive() {
    }

    public static ASN1Primitive a(byte[] bArr) {
        try {
            return new ASN1InputStream(bArr).d();
        } catch (ClassCastException e) {
            throw new IOException("cannot recognise object in stream");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ASN1Encodable) && a(((ASN1Encodable) obj).a())) {
            return true;
        }
        return false;
    }

    public ASN1Primitive a() {
        return this;
    }

    ASN1Primitive g() {
        return this;
    }

    ASN1Primitive h() {
        return this;
    }
}
