package org.spongycastle.ocsp;

import java.security.cert.X509Extension;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.ocsp.ResponseData;
import org.spongycastle.asn1.x509.X509Extensions;

public class RespData implements X509Extension {
    ResponseData a;

    public X509Extensions a() {
        return X509Extensions.a(this.a.e());
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
                DERObjectIdentifier dERObjectIdentifier = (DERObjectIdentifier) d.nextElement();
                if (z == a.a(dERObjectIdentifier).a()) {
                    hashSet.add(dERObjectIdentifier.d());
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
            org.spongycastle.asn1.x509.X509Extension a2 = a.a(new DERObjectIdentifier(str));
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
