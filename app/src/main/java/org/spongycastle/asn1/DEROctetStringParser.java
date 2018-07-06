package org.spongycastle.asn1;

import java.io.InputStream;

public class DEROctetStringParser implements ASN1OctetStringParser {
    private DefiniteLengthInputStream a;

    DEROctetStringParser(DefiniteLengthInputStream definiteLengthInputStream) {
        this.a = definiteLengthInputStream;
    }

    public InputStream d() {
        return this.a;
    }

    public ASN1Primitive f() {
        return new DEROctetString(this.a.b());
    }

    public ASN1Primitive a() {
        try {
            return f();
        } catch (Throwable e) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e.getMessage(), e);
        }
    }
}
