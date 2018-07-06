package org.spongycastle.asn1;

public class DERExternalParser implements ASN1Encodable, InMemoryRepresentable {
    private ASN1StreamParser a;

    public DERExternalParser(ASN1StreamParser aSN1StreamParser) {
        this.a = aSN1StreamParser;
    }

    public ASN1Primitive f() {
        try {
            return new DERExternal(this.a.b());
        } catch (Throwable e) {
            throw new ASN1Exception(e.getMessage(), e);
        }
    }

    public ASN1Primitive a() {
        try {
            return f();
        } catch (Throwable e) {
            throw new ASN1ParsingException("unable to get DER object", e);
        } catch (Throwable e2) {
            throw new ASN1ParsingException("unable to get DER object", e2);
        }
    }
}
