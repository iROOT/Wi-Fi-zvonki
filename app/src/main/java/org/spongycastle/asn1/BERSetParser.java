package org.spongycastle.asn1;

public class BERSetParser implements ASN1SetParser {
    private ASN1StreamParser a;

    BERSetParser(ASN1StreamParser aSN1StreamParser) {
        this.a = aSN1StreamParser;
    }

    public ASN1Primitive f() {
        return new BERSet(this.a.b());
    }

    public ASN1Primitive a() {
        try {
            return f();
        } catch (Throwable e) {
            throw new ASN1ParsingException(e.getMessage(), e);
        }
    }
}
