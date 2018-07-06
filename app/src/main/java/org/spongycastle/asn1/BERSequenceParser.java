package org.spongycastle.asn1;

import java.io.IOException;

public class BERSequenceParser implements ASN1SequenceParser {
    private ASN1StreamParser a;

    BERSequenceParser(ASN1StreamParser aSN1StreamParser) {
        this.a = aSN1StreamParser;
    }

    public ASN1Primitive f() {
        return new BERSequence(this.a.b());
    }

    public ASN1Primitive a() {
        try {
            return f();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
