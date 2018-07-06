package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class CertificatePolicies extends ASN1Object {
    private final PolicyInformation[] a;

    public ASN1Primitive a() {
        return new DERSequence(this.a);
    }

    public String toString() {
        String str = null;
        int i = 0;
        while (i < this.a.length) {
            if (str != null) {
                str = str + ", ";
            }
            String str2 = str + this.a[i];
            i++;
            str = str2;
        }
        return "CertificatePolicies: " + str;
    }
}
