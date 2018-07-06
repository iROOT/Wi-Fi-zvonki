package org.spongycastle.jce.provider;

import java.io.BufferedInputStream;
import java.io.InputStream;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.x509.CertificatePair;
import org.spongycastle.x509.X509CertificatePair;
import org.spongycastle.x509.X509StreamParserSpi;
import org.spongycastle.x509.util.StreamParsingException;

public class X509CertPairParser extends X509StreamParserSpi {
    private InputStream a = null;

    private X509CertificatePair b(InputStream inputStream) {
        return new X509CertificatePair(CertificatePair.a((ASN1Sequence) new ASN1InputStream(inputStream).d()));
    }

    public void a(InputStream inputStream) {
        this.a = inputStream;
        if (!this.a.markSupported()) {
            this.a = new BufferedInputStream(this.a);
        }
    }

    public Object a() {
        try {
            this.a.mark(10);
            if (this.a.read() == -1) {
                return null;
            }
            this.a.reset();
            return b(this.a);
        } catch (Throwable e) {
            throw new StreamParsingException(e.toString(), e);
        }
    }
}
