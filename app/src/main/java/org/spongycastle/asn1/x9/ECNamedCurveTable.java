package org.spongycastle.asn1.x9;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.nist.NISTNamedCurves;
import org.spongycastle.asn1.sec.SECNamedCurves;
import org.spongycastle.asn1.teletrust.TeleTrusTNamedCurves;

public class ECNamedCurveTable {
    public static X9ECParameters a(String str) {
        X9ECParameters a = X962NamedCurves.a(str);
        if (a == null) {
            a = SECNamedCurves.a(str);
        }
        if (a == null) {
            a = TeleTrusTNamedCurves.a(str);
        }
        if (a == null) {
            return NISTNamedCurves.a(str);
        }
        return a;
    }

    public static X9ECParameters a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        X9ECParameters a = X962NamedCurves.a(aSN1ObjectIdentifier);
        if (a == null) {
            a = SECNamedCurves.a(aSN1ObjectIdentifier);
        }
        if (a == null) {
            return TeleTrusTNamedCurves.a(aSN1ObjectIdentifier);
        }
        return a;
    }
}
