package org.spongycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.Target;
import org.spongycastle.asn1.x509.TargetInformation;
import org.spongycastle.asn1.x509.Targets;
import org.spongycastle.asn1.x509.X509Extensions;
import org.spongycastle.util.Selector;

public class X509AttributeCertStoreSelector implements Selector {
    private AttributeCertificateHolder a;
    private AttributeCertificateIssuer b;
    private BigInteger c;
    private Date d;
    private X509AttributeCertificate e;
    private Collection f = new HashSet();
    private Collection g = new HashSet();

    public boolean a(Object obj) {
        if (!(obj instanceof X509AttributeCertificate)) {
            return false;
        }
        X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate) obj;
        if (this.e != null && !this.e.equals(x509AttributeCertificate)) {
            return false;
        }
        if (this.c != null && !x509AttributeCertificate.a().equals(this.c)) {
            return false;
        }
        if (this.a != null && !x509AttributeCertificate.c().equals(this.a)) {
            return false;
        }
        if (this.b != null && !x509AttributeCertificate.d().equals(this.b)) {
            return false;
        }
        if (this.d != null) {
            try {
                x509AttributeCertificate.a(this.d);
            } catch (CertificateExpiredException e) {
                return false;
            } catch (CertificateNotYetValidException e2) {
                return false;
            }
        }
        if (!(this.f.isEmpty() && this.g.isEmpty())) {
            byte[] extensionValue = x509AttributeCertificate.getExtensionValue(X509Extensions.E.d());
            if (extensionValue != null) {
                try {
                    boolean z;
                    Target[] d;
                    Targets[] d2 = TargetInformation.a(new ASN1InputStream(((DEROctetString) ASN1Primitive.a(extensionValue)).e()).d()).d();
                    if (!this.f.isEmpty()) {
                        z = false;
                        for (Targets d3 : d2) {
                            d = d3.d();
                            for (Target e3 : d) {
                                if (this.f.contains(GeneralName.a(e3.e()))) {
                                    z = true;
                                    break;
                                }
                            }
                        }
                        if (!z) {
                            return false;
                        }
                    }
                    if (!this.g.isEmpty()) {
                        z = false;
                        for (Targets d32 : d2) {
                            d = d32.d();
                            for (Target e32 : d) {
                                if (this.g.contains(GeneralName.a(e32.d()))) {
                                    z = true;
                                    break;
                                }
                            }
                        }
                        if (!z) {
                            return false;
                        }
                    }
                } catch (IOException e4) {
                    return false;
                } catch (IllegalArgumentException e5) {
                    return false;
                }
            }
        }
        return true;
    }

    public Object clone() {
        X509AttributeCertStoreSelector x509AttributeCertStoreSelector = new X509AttributeCertStoreSelector();
        x509AttributeCertStoreSelector.e = this.e;
        x509AttributeCertStoreSelector.d = b();
        x509AttributeCertStoreSelector.a = this.a;
        x509AttributeCertStoreSelector.b = this.b;
        x509AttributeCertStoreSelector.c = this.c;
        x509AttributeCertStoreSelector.g = f();
        x509AttributeCertStoreSelector.f = e();
        return x509AttributeCertStoreSelector;
    }

    public X509AttributeCertificate a() {
        return this.e;
    }

    public Date b() {
        if (this.d != null) {
            return new Date(this.d.getTime());
        }
        return null;
    }

    public AttributeCertificateHolder c() {
        return this.a;
    }

    public BigInteger d() {
        return this.c;
    }

    public Collection e() {
        return Collections.unmodifiableCollection(this.f);
    }

    public Collection f() {
        return Collections.unmodifiableCollection(this.g);
    }
}
