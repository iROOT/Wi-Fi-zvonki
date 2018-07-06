package org.spongycastle.x509;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.CertSelector;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.x509.AttCertIssuer;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.V2Form;
import org.spongycastle.util.Selector;

public class AttributeCertificateIssuer implements CertSelector, Selector {
    final ASN1Encodable a;

    public AttributeCertificateIssuer(AttCertIssuer attCertIssuer) {
        this.a = attCertIssuer.d();
    }

    private Object[] b() {
        GeneralNames d;
        if (this.a instanceof V2Form) {
            d = ((V2Form) this.a).d();
        } else {
            d = (GeneralNames) this.a;
        }
        GeneralName[] d2 = d.d();
        List arrayList = new ArrayList(d2.length);
        for (int i = 0; i != d2.length; i++) {
            if (d2[i].d() == 4) {
                try {
                    arrayList.add(new X500Principal(d2[i].e().a().b()));
                } catch (IOException e) {
                    throw new RuntimeException("badly formed Name object");
                }
            }
        }
        return arrayList.toArray(new Object[arrayList.size()]);
    }

    public Principal[] a() {
        Object[] b = b();
        List arrayList = new ArrayList();
        for (int i = 0; i != b.length; i++) {
            if (b[i] instanceof Principal) {
                arrayList.add(b[i]);
            }
        }
        return (Principal[]) arrayList.toArray(new Principal[arrayList.size()]);
    }

    private boolean a(X500Principal x500Principal, GeneralNames generalNames) {
        GeneralName[] d = generalNames.d();
        for (int i = 0; i != d.length; i++) {
            GeneralName generalName = d[i];
            if (generalName.d() == 4) {
                try {
                    if (new X500Principal(generalName.e().a().b()).equals(x500Principal)) {
                        return true;
                    }
                } catch (IOException e) {
                }
            }
        }
        return false;
    }

    public Object clone() {
        return new AttributeCertificateIssuer(AttCertIssuer.a(this.a));
    }

    public boolean match(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            return false;
        }
        X509Certificate x509Certificate = (X509Certificate) certificate;
        if (this.a instanceof V2Form) {
            V2Form v2Form = (V2Form) this.a;
            if (v2Form.e() != null) {
                boolean z = v2Form.e().e().d().equals(x509Certificate.getSerialNumber()) && a(x509Certificate.getIssuerX500Principal(), v2Form.e().d());
                return z;
            }
            if (a(x509Certificate.getSubjectX500Principal(), v2Form.d())) {
                return true;
            }
            return false;
        }
        if (a(x509Certificate.getSubjectX500Principal(), (GeneralNames) this.a)) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AttributeCertificateIssuer)) {
            return false;
        }
        return this.a.equals(((AttributeCertificateIssuer) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean a(Object obj) {
        if (obj instanceof X509Certificate) {
            return match((Certificate) obj);
        }
        return false;
    }
}
