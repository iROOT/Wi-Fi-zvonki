package org.spongycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.cert.CertSelector;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.Holder;
import org.spongycastle.jce.PrincipalUtil;
import org.spongycastle.jce.X509Principal;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Selector;

public class AttributeCertificateHolder implements CertSelector, Selector {
    final Holder a;

    AttributeCertificateHolder(ASN1Sequence aSN1Sequence) {
        this.a = Holder.a(aSN1Sequence);
    }

    public int a() {
        if (this.a.f() != null) {
            return this.a.f().d().d().intValue();
        }
        return -1;
    }

    public String b() {
        if (this.a.f() != null) {
            return this.a.f().e().d().d();
        }
        return null;
    }

    public byte[] c() {
        if (this.a.f() != null) {
            return this.a.f().f().d();
        }
        return null;
    }

    private boolean a(X509Principal x509Principal, GeneralNames generalNames) {
        GeneralName[] d = generalNames.d();
        for (int i = 0; i != d.length; i++) {
            GeneralName generalName = d[i];
            if (generalName.d() == 4) {
                try {
                    if (new X509Principal(generalName.e().a().b()).equals(x509Principal)) {
                        return true;
                    }
                } catch (IOException e) {
                }
            }
        }
        return false;
    }

    private Object[] a(GeneralName[] generalNameArr) {
        List arrayList = new ArrayList(generalNameArr.length);
        for (int i = 0; i != generalNameArr.length; i++) {
            if (generalNameArr[i].d() == 4) {
                try {
                    arrayList.add(new X500Principal(generalNameArr[i].e().a().b()));
                } catch (IOException e) {
                    throw new RuntimeException("badly formed Name object");
                }
            }
        }
        return arrayList.toArray(new Object[arrayList.size()]);
    }

    private Principal[] a(GeneralNames generalNames) {
        Object[] a = a(generalNames.d());
        List arrayList = new ArrayList();
        for (int i = 0; i != a.length; i++) {
            if (a[i] instanceof Principal) {
                arrayList.add(a[i]);
            }
        }
        return (Principal[]) arrayList.toArray(new Principal[arrayList.size()]);
    }

    public Principal[] d() {
        if (this.a.e() != null) {
            return a(this.a.e());
        }
        return null;
    }

    public Principal[] e() {
        if (this.a.d() != null) {
            return a(this.a.d().d());
        }
        return null;
    }

    public BigInteger f() {
        if (this.a.d() != null) {
            return this.a.d().e().d();
        }
        return null;
    }

    public Object clone() {
        return new AttributeCertificateHolder((ASN1Sequence) this.a.c());
    }

    public boolean match(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            return false;
        }
        X509Certificate x509Certificate = (X509Certificate) certificate;
        try {
            if (this.a.d() != null) {
                boolean z = this.a.d().e().d().equals(x509Certificate.getSerialNumber()) && a(PrincipalUtil.a(x509Certificate), this.a.d().d());
                return z;
            } else if (this.a.e() != null && a(PrincipalUtil.b(x509Certificate), this.a.e())) {
                return true;
            } else {
                if (this.a.f() == null) {
                    return false;
                }
                try {
                    MessageDigest instance = MessageDigest.getInstance(b(), "SC");
                    switch (a()) {
                        case 0:
                            instance.update(certificate.getPublicKey().getEncoded());
                            break;
                        case 1:
                            instance.update(certificate.getEncoded());
                            break;
                    }
                    return !Arrays.a(instance.digest(), c()) ? false : false;
                } catch (Exception e) {
                    return false;
                }
            }
        } catch (CertificateEncodingException e2) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AttributeCertificateHolder)) {
            return false;
        }
        return this.a.equals(((AttributeCertificateHolder) obj).a);
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
