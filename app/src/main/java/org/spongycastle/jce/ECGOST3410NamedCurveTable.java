package org.spongycastle.jce;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;

public class ECGOST3410NamedCurveTable {
    public static ECNamedCurveParameterSpec a(String str) {
        ECDomainParameters a = ECGOST3410NamedCurves.a(str);
        if (a == null) {
            try {
                a = ECGOST3410NamedCurves.a(new ASN1ObjectIdentifier(str));
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        if (a == null) {
            return null;
        }
        return new ECNamedCurveParameterSpec(str, a.a(), a.b(), a.c(), a.d(), a.e());
    }
}
