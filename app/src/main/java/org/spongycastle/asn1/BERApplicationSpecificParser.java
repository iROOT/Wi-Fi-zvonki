package org.spongycastle.asn1;

public class BERApplicationSpecificParser implements ASN1ApplicationSpecificParser {
    private final int a;
    private final ASN1StreamParser b;

    BERApplicationSpecificParser(int i, ASN1StreamParser aSN1StreamParser) {
        this.a = i;
        this.b = aSN1StreamParser;
    }

    public ASN1Primitive f() {
        return new BERApplicationSpecific(this.a, this.b.b());
    }

    public ASN1Primitive a() {
        try {
            return f();
        } catch (Throwable e) {
            throw new ASN1ParsingException(e.getMessage(), e);
        }
    }
}
