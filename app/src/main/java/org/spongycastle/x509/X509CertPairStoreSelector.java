package org.spongycastle.x509;

import org.spongycastle.util.Selector;

public class X509CertPairStoreSelector implements Selector {
    private X509CertStoreSelector a;
    private X509CertStoreSelector b;
    private X509CertificatePair c;

    public X509CertificatePair a() {
        return this.c;
    }

    public void a(X509CertStoreSelector x509CertStoreSelector) {
        this.a = x509CertStoreSelector;
    }

    public void b(X509CertStoreSelector x509CertStoreSelector) {
        this.b = x509CertStoreSelector;
    }

    public Object clone() {
        X509CertPairStoreSelector x509CertPairStoreSelector = new X509CertPairStoreSelector();
        x509CertPairStoreSelector.c = this.c;
        if (this.a != null) {
            x509CertPairStoreSelector.a((X509CertStoreSelector) this.a.clone());
        }
        if (this.b != null) {
            x509CertPairStoreSelector.b((X509CertStoreSelector) this.b.clone());
        }
        return x509CertPairStoreSelector;
    }

    public boolean a(Object obj) {
        try {
            if (!(obj instanceof X509CertificatePair)) {
                return false;
            }
            X509CertificatePair x509CertificatePair = (X509CertificatePair) obj;
            if (this.a != null && !this.a.a(x509CertificatePair.a())) {
                return false;
            }
            if (this.b != null && !this.b.a(x509CertificatePair.b())) {
                return false;
            }
            if (this.c != null) {
                return this.c.equals(obj);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public X509CertStoreSelector b() {
        return this.a;
    }
}
