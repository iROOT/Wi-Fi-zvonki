package org.spongycastle.asn1;

import java.io.InputStream;
import org.spongycastle.util.io.Streams;

public class BEROctetStringParser implements ASN1OctetStringParser {
    private ASN1StreamParser a;

    BEROctetStringParser(ASN1StreamParser aSN1StreamParser) {
        this.a = aSN1StreamParser;
    }

    public InputStream d() {
        return new ConstructedOctetStream(this.a);
    }

    public ASN1Primitive f() {
        return new BEROctetString(Streams.a(d()));
    }

    public ASN1Primitive a() {
        try {
            return f();
        } catch (Throwable e) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e.getMessage(), e);
        }
    }
}
