package org.spongycastle.asn1.dvcs;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;

public class ServiceType extends ASN1Object {
    public static final ServiceType a = new ServiceType(1);
    public static final ServiceType b = new ServiceType(2);
    public static final ServiceType c = new ServiceType(3);
    public static final ServiceType d = new ServiceType(4);
    private ASN1Enumerated e;

    public ServiceType(int i) {
        this.e = new ASN1Enumerated(i);
    }

    public BigInteger d() {
        return this.e.d();
    }

    public ASN1Primitive a() {
        return this.e;
    }

    public String toString() {
        int intValue = this.e.d().intValue();
        StringBuilder append = new StringBuilder().append("").append(intValue);
        String str = intValue == a.d().intValue() ? "(CPD)" : intValue == b.d().intValue() ? "(VSD)" : intValue == c.d().intValue() ? "(VPKC)" : intValue == d.d().intValue() ? "(CCPD)" : "?";
        return append.append(str).toString();
    }
}
