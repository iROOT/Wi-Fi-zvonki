package org.spongycastle.ocsp;

import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.ocsp.BasicOCSPResponse;
import org.spongycastle.asn1.ocsp.ResponseData;
import org.spongycastle.asn1.ocsp.SingleResponse;
import org.spongycastle.asn1.x509.X509Extensions;

public class BasicOCSPResp implements X509Extension {
    BasicOCSPResponse a;
    ResponseData b;
    X509Certificate[] c = null;

    public BasicOCSPResp(BasicOCSPResponse basicOCSPResponse) {
        this.a = basicOCSPResponse;
        this.b = basicOCSPResponse.d();
    }

    public SingleResp[] a() {
        ASN1Sequence d = this.b.d();
        SingleResp[] singleRespArr = new SingleResp[d.f()];
        for (int i = 0; i != singleRespArr.length; i++) {
            singleRespArr[i] = new SingleResp(SingleResponse.a(d.a(i)));
        }
        return singleRespArr;
    }

    public X509Extensions b() {
        return X509Extensions.a(this.b.e());
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

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BasicOCSPResp)) {
            return false;
        }
        return this.a.equals(((BasicOCSPResp) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
