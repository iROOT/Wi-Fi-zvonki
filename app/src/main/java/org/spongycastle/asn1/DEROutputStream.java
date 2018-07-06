package org.spongycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

public class DEROutputStream extends ASN1OutputStream {
    public DEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void a(ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1Encodable.a().g().a((ASN1OutputStream) this);
            return;
        }
        throw new IOException("null object detected");
    }

    ASN1OutputStream a() {
        return this;
    }

    ASN1OutputStream b() {
        return this;
    }
}
