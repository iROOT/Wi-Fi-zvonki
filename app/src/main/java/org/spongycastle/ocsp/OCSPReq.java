package org.spongycastle.ocsp;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.cert.X509Extension;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OutputStream;
import org.spongycastle.asn1.ocsp.OCSPRequest;
import org.spongycastle.asn1.x509.X509Extensions;

public class OCSPReq implements X509Extension {
    private OCSPRequest a;

    public OCSPReq(OCSPRequest oCSPRequest) {
        this.a = oCSPRequest;
    }

    public X509Extensions a() {
        return X509Extensions.a(this.a.d().d());
    }

    public byte[] b() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ASN1OutputStream(byteArrayOutputStream).a(this.a);
        return byteArrayOutputStream.toByteArray();
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set criticalExtensionOIDs = getCriticalExtensionOIDs();
        if (criticalExtensionOIDs == null || criticalExtensionOIDs.isEmpty()) {
            return false;
        }
        return true;
    }

    private Set a(boolean z) {
        Set hashSet = new HashSet();
        X509Extensions a = a();
        if (a != null) {
            Enumeration d = a.d();
            while (d.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.nextElement();
                if (z == a.a(aSN1ObjectIdentifier).a()) {
                    hashSet.add(aSN1ObjectIdentifier.d());
                }
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

    public byte[] getExtensionValue(String str) {
        X509Extensions a = a();
        if (a != null) {
            org.spongycastle.asn1.x509.X509Extension a2 = a.a(new ASN1ObjectIdentifier(str));
            if (a2 != null) {
                try {
                    return a2.b().a("DER");
                } catch (Exception e) {
                    throw new RuntimeException("error encoding " + e.toString());
                }
            }
        }
        return null;
    }
}
