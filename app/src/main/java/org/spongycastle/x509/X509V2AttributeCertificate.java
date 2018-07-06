package org.spongycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.x509.AttributeCertificate;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.util.Arrays;

public class X509V2AttributeCertificate implements X509AttributeCertificate {
    private AttributeCertificate a;
    private Date b;
    private Date c;

    private static AttributeCertificate a(InputStream inputStream) {
        try {
            return AttributeCertificate.a(new ASN1InputStream(inputStream).d());
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            throw new IOException("exception decoding certificate structure: " + e2.toString());
        }
    }

    public X509V2AttributeCertificate(InputStream inputStream) {
        this(a(inputStream));
    }

    public X509V2AttributeCertificate(byte[] bArr) {
        this(new ByteArrayInputStream(bArr));
    }

    X509V2AttributeCertificate(AttributeCertificate attributeCertificate) {
        this.a = attributeCertificate;
        try {
            this.c = attributeCertificate.d().g().e().e();
            this.b = attributeCertificate.d().g().d().e();
        } catch (ParseException e) {
            throw new IOException("invalid data structure in certificate!");
        }
    }

    public BigInteger a() {
        return this.a.d().f().d();
    }

    public AttributeCertificateHolder c() {
        return new AttributeCertificateHolder((ASN1Sequence) this.a.d().d().c());
    }

    public AttributeCertificateIssuer d() {
        return new AttributeCertificateIssuer(this.a.d().e());
    }

    public Date f() {
        return this.b;
    }

    public Date b() {
        return this.c;
    }

    public void a(Date date) {
        if (date.after(b())) {
            throw new CertificateExpiredException("certificate expired on " + b());
        } else if (date.before(f())) {
            throw new CertificateNotYetValidException("certificate not valid till " + f());
        }
    }

    public byte[] e() {
        return this.a.b();
    }

    public byte[] getExtensionValue(String str) {
        Extensions i = this.a.d().i();
        if (i != null) {
            Extension a = i.a(new ASN1ObjectIdentifier(str));
            if (a != null) {
                try {
                    return a.f().a("DER");
                } catch (Exception e) {
                    throw new RuntimeException("error encoding " + e.toString());
                }
            }
        }
        return null;
    }

    private Set a(boolean z) {
        Extensions i = this.a.d().i();
        if (i == null) {
            return null;
        }
        Set hashSet = new HashSet();
        Enumeration d = i.d();
        while (d.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.nextElement();
            if (i.a(aSN1ObjectIdentifier).e() == z) {
                hashSet.add(aSN1ObjectIdentifier.d());
            }
        }
        return hashSet;
    }

    public Set getNonCriticalExtensionOIDs() {
        return a(false);
    }

    public Set getCriticalExtensionOIDs() {
        return a(true);
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set criticalExtensionOIDs = getCriticalExtensionOIDs();
        return (criticalExtensionOIDs == null || criticalExtensionOIDs.isEmpty()) ? false : true;
    }

    public X509Attribute[] a(String str) {
        ASN1Sequence h = this.a.d().h();
        List arrayList = new ArrayList();
        for (int i = 0; i != h.f(); i++) {
            X509Attribute x509Attribute = new X509Attribute(h.a(i));
            if (x509Attribute.d().equals(str)) {
                arrayList.add(x509Attribute);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return (X509Attribute[]) arrayList.toArray(new X509Attribute[arrayList.size()]);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof X509AttributeCertificate)) {
            return z;
        }
        try {
            return Arrays.a(e(), ((X509AttributeCertificate) obj).e());
        } catch (IOException e) {
            return z;
        }
    }

    public int hashCode() {
        try {
            return Arrays.a(e());
        } catch (IOException e) {
            return 0;
        }
    }
}
