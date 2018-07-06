package org.spongycastle.jce;

import java.io.IOException;
import java.security.Principal;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.X509Name;

public class X509Principal extends X509Name implements Principal {
    private static ASN1Sequence a(ASN1InputStream aSN1InputStream) {
        try {
            return ASN1Sequence.a(aSN1InputStream.d());
        } catch (IllegalArgumentException e) {
            throw new IOException("not an ASN.1 Sequence: " + e);
        }
    }

    public X509Principal(byte[] bArr) {
        super(a(new ASN1InputStream(bArr)));
    }

    public X509Principal(X509Name x509Name) {
        super((ASN1Sequence) x509Name.a());
    }

    public X509Principal(X500Name x500Name) {
        super((ASN1Sequence) x500Name.a());
    }

    public String getName() {
        return toString();
    }

    public byte[] b() {
        try {
            return a("DER");
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }
}
