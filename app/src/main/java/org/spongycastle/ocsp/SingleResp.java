package org.spongycastle.ocsp;

import java.security.cert.X509Extension;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.ocsp.CertStatus;
import org.spongycastle.asn1.ocsp.RevokedInfo;
import org.spongycastle.asn1.ocsp.SingleResponse;
import org.spongycastle.asn1.x509.X509Extensions;

public class SingleResp implements X509Extension {
    SingleResponse a;

    public SingleResp(SingleResponse singleResponse) {
        this.a = singleResponse;
    }

    public Object a() {
        CertStatus d = this.a.d();
        if (d.d() == 0) {
            return null;
        }
        if (d.d() == 1) {
            return new RevokedStatus(RevokedInfo.a(d.e()));
        }
        return new UnknownStatus();
    }

    public X509Extensions b() {
        return X509Extensions.a(this.a.e());
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set criticalExtensionOIDs = getCriticalExtensionOIDs();
        return (criticalExtensionOIDs == null || criticalExtensionOIDs.isEmpty()) ? false : true;
    }

    private Set a(boolean z) {
        Set hashSet = new HashSet();
        X509Extensions b = b();
        if (b != null) {
            Enumeration d = b.d();
            while (d.hasMoreElements()) {
                DERObjectIdentifier dERObjectIdentifier = (DERObjectIdentifier) d.nextElement();
                if (z == b.a(dERObjectIdentifier).a()) {
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
        X509Extensions b = b();
        if (b != null) {
            org.spongycastle.asn1.x509.X509Extension a = b.a(new DERObjectIdentifier(str));
            if (a != null) {
                try {
                    return a.b().a("DER");
                } catch (Exception e) {
                    throw new RuntimeException("error encoding " + e.toString());
                }
            }
        }
        return null;
    }
}
