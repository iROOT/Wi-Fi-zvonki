package org.spongycastle.jce.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DEREnumerated;
import org.spongycastle.asn1.util.ASN1Dump;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.CRLReason;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.TBSCertList.CRLEntry;
import org.spongycastle.asn1.x509.X509Extension;

public class X509CRLEntryObject extends X509CRLEntry {
    private CRLEntry a;
    private X500Name b;
    private int c;
    private boolean d;

    public X509CRLEntryObject(CRLEntry cRLEntry, boolean z, X500Name x500Name) {
        this.a = cRLEntry;
        this.b = a(z, x500Name);
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set criticalExtensionOIDs = getCriticalExtensionOIDs();
        return (criticalExtensionOIDs == null || criticalExtensionOIDs.isEmpty()) ? false : true;
    }

    private X500Name a(boolean z, X500Name x500Name) {
        if (!z) {
            return null;
        }
        Extension a = a(Extension.n);
        if (a == null) {
            return x500Name;
        }
        try {
            GeneralName[] d = GeneralNames.a(a.g()).d();
            for (int i = 0; i < d.length; i++) {
                if (d[i].d() == 4) {
                    return X500Name.a(d[i].e());
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public X500Principal getCertificateIssuer() {
        if (this.b == null) {
            return null;
        }
        try {
            return new X500Principal(this.b.b());
        } catch (IOException e) {
            return null;
        }
    }

    private Set a(boolean z) {
        Extensions f = this.a.f();
        if (f == null) {
            return null;
        }
        Set hashSet = new HashSet();
        Enumeration d = f.d();
        while (d.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.nextElement();
            if (z == f.a(aSN1ObjectIdentifier).e()) {
                hashSet.add(aSN1ObjectIdentifier.d());
            }
        }
        return hashSet;
    }

    public Set getCriticalExtensionOIDs() {
        return a(true);
    }

    public Set getNonCriticalExtensionOIDs() {
        return a(false);
    }

    private Extension a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions f = this.a.f();
        if (f != null) {
            return f.a(aSN1ObjectIdentifier);
        }
        return null;
    }

    public byte[] getExtensionValue(String str) {
        Extension a = a(new ASN1ObjectIdentifier(str));
        if (a == null) {
            return null;
        }
        try {
            return a.f().b();
        } catch (Exception e) {
            throw new RuntimeException("error encoding " + e.toString());
        }
    }

    public int hashCode() {
        if (!this.d) {
            this.c = super.hashCode();
            this.d = true;
        }
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof X509CRLEntryObject)) {
            return super.equals(this);
        }
        return this.a.equals(((X509CRLEntryObject) obj).a);
    }

    public byte[] getEncoded() {
        try {
            return this.a.a("DER");
        } catch (IOException e) {
            throw new CRLException(e.toString());
        }
    }

    public BigInteger getSerialNumber() {
        return this.a.d().d();
    }

    public Date getRevocationDate() {
        return this.a.e().e();
    }

    public boolean hasExtensions() {
        return this.a.f() != null;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("      userCertificate: ").append(getSerialNumber()).append(property);
        stringBuffer.append("       revocationDate: ").append(getRevocationDate()).append(property);
        stringBuffer.append("       certificateIssuer: ").append(getCertificateIssuer()).append(property);
        Extensions f = this.a.f();
        if (f != null) {
            Enumeration d = f.d();
            if (d.hasMoreElements()) {
                stringBuffer.append("   crlEntryExtensions:").append(property);
                while (d.hasMoreElements()) {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.nextElement();
                    Extension a = f.a(aSN1ObjectIdentifier);
                    if (a.f() != null) {
                        ASN1InputStream aSN1InputStream = new ASN1InputStream(a.f().e());
                        stringBuffer.append("                       critical(").append(a.e()).append(") ");
                        try {
                            if (aSN1ObjectIdentifier.equals(X509Extension.i)) {
                                stringBuffer.append(CRLReason.a(DEREnumerated.a((Object) aSN1InputStream.d()))).append(property);
                            } else if (aSN1ObjectIdentifier.equals(X509Extension.n)) {
                                stringBuffer.append("Certificate issuer: ").append(GeneralNames.a(aSN1InputStream.d())).append(property);
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
        }
        return stringBuffer.toString();
    }
}
