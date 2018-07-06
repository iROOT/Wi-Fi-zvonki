package org.spongycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

public class DLOutputStream extends ASN1OutputStream {
    public DLOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void a(ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1Encodable.a().h().a((ASN1OutputStream) this);
            return;
        }
        throw new IOException("null object detected");
    }
}
