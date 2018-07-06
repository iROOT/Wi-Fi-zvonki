package org.spongycastle.x509;

import java.security.cert.X509Certificate;
import org.spongycastle.asn1.x509.CertificatePair;
import org.spongycastle.jce.provider.X509CertificateObject;

public class X509CertificatePair {
    private X509Certificate a;
    private X509Certificate b;

    public X509CertificatePair(CertificatePair certificatePair) {
        if (certificatePair.d() != null) {
            this.a = new X509CertificateObject(certificatePair.d());
        }
        if (certificatePair.e() != null) {
            this.b = new X509CertificateObject(certificatePair.e());
        }
    }

    public X509Certificate a() {
        return this.a;
    }

    public X509Certificate b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null || !(obj instanceof X509CertificatePair)) {
            return false;
        }
        boolean equals;
        X509CertificatePair x509CertificatePair = (X509CertificatePair) obj;
        if (this.a != null) {
            equals = this.a.equals(x509CertificatePair.a);
        } else if (x509CertificatePair.a != null) {
            equals = false;
        } else {
            equals = true;
        }
        boolean equals2;
        if (this.b != null) {
            equals2 = this.b.equals(x509CertificatePair.b);
        } else if (x509CertificatePair.b != null) {
            equals2 = false;
        } else {
            equals2 = true;
        }
        if (!(equals && equals2)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = -1;
        if (this.a != null) {
            i = -1 ^ this.a.hashCode();
        }
        if (this.b != null) {
            return (i * 17) ^ this.b.hashCode();
        }
        return i;
    }
}
