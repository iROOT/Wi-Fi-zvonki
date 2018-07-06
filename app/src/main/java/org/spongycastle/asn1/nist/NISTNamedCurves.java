package org.spongycastle.asn1.nist;

import java.util.Hashtable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.sec.SECNamedCurves;
import org.spongycastle.asn1.sec.SECObjectIdentifiers;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.util.Strings;

public class NISTNamedCurves {
    static final Hashtable a = new Hashtable();
    static final Hashtable b = new Hashtable();

    static {
        a("B-571", SECObjectIdentifiers.F);
        a("B-409", SECObjectIdentifiers.D);
        a("B-283", SECObjectIdentifiers.n);
        a("B-233", SECObjectIdentifiers.t);
        a("B-163", SECObjectIdentifiers.l);
        a("K-571", SECObjectIdentifiers.E);
        a("K-409", SECObjectIdentifiers.C);
        a("K-283", SECObjectIdentifiers.m);
        a("K-233", SECObjectIdentifiers.s);
        a("K-163", SECObjectIdentifiers.b);
        a("P-521", SECObjectIdentifiers.B);
        a("P-384", SECObjectIdentifiers.A);
        a("P-256", SECObjectIdentifiers.H);
        a("P-224", SECObjectIdentifiers.z);
        a("P-192", SECObjectIdentifiers.G);
    }

    static void a(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        a.put(str, aSN1ObjectIdentifier);
        b.put(aSN1ObjectIdentifier, str);
    }

    public static X9ECParameters a(String str) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) a.get(Strings.b(str));
        if (aSN1ObjectIdentifier != null) {
            return a(aSN1ObjectIdentifier);
        }
        return null;
    }

    public static X9ECParameters a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return SECNamedCurves.a(aSN1ObjectIdentifier);
    }

    public static ASN1ObjectIdentifier b(String str) {
        return (ASN1ObjectIdentifier) a.get(Strings.b(str));
    }

    public static String b(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) b.get(aSN1ObjectIdentifier);
    }
}
