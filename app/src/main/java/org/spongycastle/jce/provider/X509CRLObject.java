package org.spongycastle.jce.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.util.ASN1Dump;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.CRLNumber;
import org.spongycastle.asn1.x509.CertificateList;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.IssuingDistributionPoint;
import org.spongycastle.asn1.x509.TBSCertList.CRLEntry;
import org.spongycastle.jce.X509Principal;
import org.spongycastle.util.encoders.Hex;

public class X509CRLObject extends X509CRL {
    private CertificateList a;
    private String b;
    private byte[] c;
    private boolean d;
    private boolean e = false;
    private int f;

    static boolean a(X509CRL x509crl) {
        try {
            Object extensionValue = x509crl.getExtensionValue(Extension.m.d());
            return extensionValue != null && IssuingDistributionPoint.a(ASN1OctetString.a(extensionValue).e()).f();
        } catch (Throwable e) {
            throw new ExtCRLException("Exception reading IssuingDistributionPoint", e);
        }
    }

    public X509CRLObject(CertificateList certificateList) {
        this.a = certificateList;
        try {
            this.b = X509SignatureUtil.a(certificateList.f());
            if (certificateList.f().f() != null) {
                this.c = certificateList.f().f().a().a("DER");
            } else {
                this.c = null;
            }
            this.d = a((X509CRL) this);
        } catch (Exception e) {
            throw new CRLException("CRL contents invalid: " + e);
        }
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set criticalExtensionOIDs = getCriticalExtensionOIDs();
        if (criticalExtensionOIDs == null) {
            return false;
        }
        criticalExtensionOIDs.remove(RFC3280CertPathUtilities.d);
        criticalExtensionOIDs.remove(RFC3280CertPathUtilities.f);
        if (criticalExtensionOIDs.isEmpty()) {
            return false;
        }
        return true;
    }

    private Set a(boolean z) {
        if (getVersion() == 2) {
            Extensions j = this.a.d().j();
            if (j != null) {
                Set hashSet = new HashSet();
                Enumeration d = j.d();
                while (d.hasMoreElements()) {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.nextElement();
                    if (z == j.a(aSN1ObjectIdentifier).e()) {
                        hashSet.add(aSN1ObjectIdentifier.d());
                    }
                }
                return hashSet;
            }
        }
        return null;
    }

    public Set getCriticalExtensionOIDs() {
        return a(true);
    }

    public Set getNonCriticalExtensionOIDs() {
        return a(false);
    }

    public byte[] getExtensionValue(String str) {
        Extensions j = this.a.d().j();
        if (j != null) {
            Extension a = j.a(new ASN1ObjectIdentifier(str));
            if (a != null) {
                try {
                    return a.f().b();
                } catch (Exception e) {
                    throw new IllegalStateException("error parsing " + e.toString());
                }
            }
        }
        return null;
    }

    public byte[] getEncoded() {
        try {
            return this.a.a("DER");
        } catch (IOException e) {
            throw new CRLException(e.toString());
        }
    }

    public void verify(PublicKey publicKey) {
        verify(publicKey, "SC");
    }

    public void verify(PublicKey publicKey, String str) {
        if (this.a.f().equals(this.a.d().e())) {
            Signature instance;
            if (str != null) {
                instance = Signature.getInstance(getSigAlgName(), str);
            } else {
                instance = Signature.getInstance(getSigAlgName());
            }
            instance.initVerify(publicKey);
            instance.update(getTBSCertList());
            if (!instance.verify(getSignature())) {
                throw new SignatureException("CRL does not verify with supplied public key.");
            }
            return;
        }
        throw new CRLException("Signature algorithm on CertificateList does not match TBSCertList.");
    }

    public int getVersion() {
        return this.a.h();
    }

    public Principal getIssuerDN() {
        return new X509Principal(X500Name.a(this.a.i().a()));
    }

    public X500Principal getIssuerX500Principal() {
        try {
            return new X500Principal(this.a.i().b());
        } catch (IOException e) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    public Date getThisUpdate() {
        return this.a.j().e();
    }

    public Date getNextUpdate() {
        if (this.a.k() != null) {
            return this.a.k().e();
        }
        return null;
    }

    private Set a() {
        Set hashSet = new HashSet();
        Enumeration e = this.a.e();
        X500Name x500Name = null;
        while (e.hasMoreElements()) {
            X500Name a;
            CRLEntry cRLEntry = (CRLEntry) e.nextElement();
            hashSet.add(new X509CRLEntryObject(cRLEntry, this.d, x500Name));
            if (this.d && cRLEntry.g()) {
                Extension a2 = cRLEntry.f().a(Extension.n);
                if (a2 != null) {
                    a = X500Name.a(GeneralNames.a(a2.g()).d()[0].e());
                    x500Name = a;
                }
            }
            a = x500Name;
            x500Name = a;
        }
        return hashSet;
    }

    public X509CRLEntry getRevokedCertificate(BigInteger bigInteger) {
        Enumeration e = this.a.e();
        X500Name x500Name = null;
        while (e.hasMoreElements()) {
            CRLEntry cRLEntry = (CRLEntry) e.nextElement();
            if (bigInteger.equals(cRLEntry.d().d())) {
                return new X509CRLEntryObject(cRLEntry, this.d, x500Name);
            }
            X500Name a;
            if (this.d && cRLEntry.g()) {
                Extension a2 = cRLEntry.f().a(Extension.n);
                if (a2 != null) {
                    a = X500Name.a(GeneralNames.a(a2.g()).d()[0].e());
                    x500Name = a;
                }
            }
            a = x500Name;
            x500Name = a;
        }
        return null;
    }

    public Set getRevokedCertificates() {
        Set a = a();
        if (a.isEmpty()) {
            return null;
        }
        return Collections.unmodifiableSet(a);
    }

    public byte[] getTBSCertList() {
        try {
            return this.a.d().a("DER");
        } catch (IOException e) {
            throw new CRLException(e.toString());
        }
    }

    public byte[] getSignature() {
        return this.a.g().d();
    }

    public String getSigAlgName() {
        return this.b;
    }

    public String getSigAlgOID() {
        return this.a.f().e().d();
    }

    public byte[] getSigAlgParams() {
        if (this.c == null) {
            return null;
        }
        Object obj = new byte[this.c.length];
        System.arraycopy(this.c, 0, obj, 0, obj.length);
        return obj;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("              Version: ").append(getVersion()).append(property);
        stringBuffer.append("             IssuerDN: ").append(getIssuerDN()).append(property);
        stringBuffer.append("          This update: ").append(getThisUpdate()).append(property);
        stringBuffer.append("          Next update: ").append(getNextUpdate()).append(property);
        stringBuffer.append("  Signature Algorithm: ").append(getSigAlgName()).append(property);
        byte[] signature = getSignature();
        stringBuffer.append("            Signature: ").append(new String(Hex.a(signature, 0, 20))).append(property);
        for (int i = 20; i < signature.length; i += 20) {
            if (i < signature.length - 20) {
                stringBuffer.append("                       ").append(new String(Hex.a(signature, i, 20))).append(property);
            } else {
                stringBuffer.append("                       ").append(new String(Hex.a(signature, i, signature.length - i))).append(property);
            }
        }
        Extensions j = this.a.d().j();
        if (j != null) {
            Enumeration d = j.d();
            if (d.hasMoreElements()) {
                stringBuffer.append("           Extensions: ").append(property);
            }
            while (d.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.nextElement();
                Extension a = j.a(aSN1ObjectIdentifier);
                if (a.f() != null) {
                    ASN1InputStream aSN1InputStream = new ASN1InputStream(a.f().e());
                    stringBuffer.append("                       critical(").append(a.e()).append(") ");
                    try {
                        if (aSN1ObjectIdentifier.equals(Extension.h)) {
                            stringBuffer.append(new CRLNumber(DERInteger.a((Object) aSN1InputStream.d()).e())).append(property);
                        } else if (aSN1ObjectIdentifier.equals(Extension.l)) {
                            stringBuffer.append("Base CRL: " + new CRLNumber(DERInteger.a((Object) aSN1InputStream.d()).e())).append(property);
                        } else if (aSN1ObjectIdentifier.equals(Extension.m)) {
                            stringBuffer.append(IssuingDistributionPoint.a(aSN1InputStream.d())).append(property);
                        } else if (aSN1ObjectIdentifier.equals(Extension.p)) {
                            stringBuffer.append(CRLDistPoint.a(aSN1InputStream.d())).append(property);
                        } else if (aSN1ObjectIdentifier.equals(Extension.v)) {
                            stringBuffer.append(CRLDistPoint.a(aSN1InputStream.d())).append(property);
                        } else {
                            stringBuffer.append(aSN1ObjectIdentifier.d());
                            stringBuffer.append(" value = ").append(ASN1Dump.a(aSN1InputStream.d())).append(property);
                        }
                    } catch (Exception e) {
                        stringBuffer.append(aSN1ObjectIdentifier.d());
                        stringBuffer.append(" value = ").append("*****").append(property);
                    }
                } else {
                    stringBuffer.append(property);
                }
            }
        }
        Set<Object> revokedCertificates = getRevokedCertificates();
        if (revokedCertificates != null) {
            for (Object append : revokedCertificates) {
                stringBuffer.append(append);
                stringBuffer.append(property);
            }
        }
        return stringBuffer.toString();
    }

    public boolean isRevoked(Certificate certificate) {
        if (certificate.getType().equals("X.509")) {
            Enumeration e = this.a.e();
            X500Name i = this.a.i();
            if (e != null) {
                BigInteger serialNumber = ((X509Certificate) certificate).getSerialNumber();
                X500Name x500Name = i;
                while (e.hasMoreElements()) {
                    CRLEntry a = CRLEntry.a(e.nextElement());
                    if (this.d && a.g()) {
                        Extension a2 = a.f().a(Extension.n);
                        if (a2 != null) {
                            x500Name = X500Name.a(GeneralNames.a(a2.g()).d()[0].e());
                        }
                    }
                    if (a.d().d().equals(serialNumber)) {
                        Object a3;
                        if (certificate instanceof X509Certificate) {
                            a3 = X500Name.a(((X509Certificate) certificate).getIssuerX500Principal().getEncoded());
                        } else {
                            try {
                                a3 = org.spongycastle.asn1.x509.Certificate.a(certificate.getEncoded()).g();
                            } catch (CertificateEncodingException e2) {
                                throw new RuntimeException("Cannot process certificate");
                            }
                        }
                        if (x500Name.equals(a3)) {
                            return true;
                        }
                        return false;
                    }
                }
            }
            return false;
        }
        throw new RuntimeException("X.509 CRL used with non X.509 Cert");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof X509CRL)) {
            return false;
        }
        if (!(obj instanceof X509CRLObject)) {
            return super.equals(obj);
        }
        X509CRLObject x509CRLObject = (X509CRLObject) obj;
        if (this.e && x509CRLObject.e && x509CRLObject.f != this.f) {
            return false;
        }
        return this.a.equals(x509CRLObject.a);
    }

    public int hashCode() {
        if (!this.e) {
            this.e = true;
            this.f = super.hashCode();
        }
        return this.f;
    }
}
