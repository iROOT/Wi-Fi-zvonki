package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public abstract class ASN1Object implements ASN1Encodable {
    public abstract ASN1Primitive a();

    public byte[] b() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ASN1OutputStream(byteArrayOutputStream).a((ASN1Encodable) this);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] a(String str) {
        OutputStream byteArrayOutputStream;
        if (str.equals("DER")) {
            byteArrayOutputStream = new ByteArrayOutputStream();
            new DEROutputStream(byteArrayOutputStream).a(this);
            return byteArrayOutputStream.toByteArray();
        } else if (!str.equals("DL")) {
            return b();
        } else {
            byteArrayOutputStream = new ByteArrayOutputStream();
            new DLOutputStream(byteArrayOutputStream).a(this);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public int hashCode() {
        return a().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ASN1Encodable)) {
            return false;
        }
        return a().equals(((ASN1Encodable) obj).a());
    }

    public ASN1Primitive c() {
        return a();
    }
}
