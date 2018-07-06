package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEREnumerated;
import org.spongycastle.util.Integers;

public class CRLReason extends ASN1Object {
    private static final String[] a = new String[]{"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};
    private static final Hashtable b = new Hashtable();
    private ASN1Enumerated c;

    public static CRLReason a(Object obj) {
        if (obj instanceof CRLReason) {
            return (CRLReason) obj;
        }
        if (obj != null) {
            return a(DEREnumerated.a(obj).d().intValue());
        }
        return null;
    }

    private CRLReason(int i) {
        this.c = new ASN1Enumerated(i);
    }

    public String toString() {
        String str;
        int intValue = d().intValue();
        if (intValue < 0 || intValue > 10) {
            str = "invalid";
        } else {
            str = a[intValue];
        }
        return "CRLReason: " + str;
    }

    public BigInteger d() {
        return this.c.d();
    }

    public ASN1Primitive a() {
        return this.c;
    }

    public static CRLReason a(int i) {
        Integer a = Integers.a(i);
        if (!b.containsKey(a)) {
            b.put(a, new CRLReason(i));
        }
        return (CRLReason) b.get(a);
    }
}
