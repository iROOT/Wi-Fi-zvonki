package org.spongycastle.asn1;

public class ASN1ObjectIdentifier extends DERObjectIdentifier {
    public ASN1ObjectIdentifier(String str) {
        super(str);
    }

    ASN1ObjectIdentifier(byte[] bArr) {
        super(bArr);
    }

    ASN1ObjectIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        super(aSN1ObjectIdentifier, str);
    }

    public ASN1ObjectIdentifier b(String str) {
        return new ASN1ObjectIdentifier(this, str);
    }

    public boolean a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String d = d();
        String d2 = aSN1ObjectIdentifier.d();
        return d.length() > d2.length() && d.charAt(d2.length()) == '.' && d.startsWith(d2);
    }
}
