package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AttributeCertificate;
import org.spongycastle.asn1.x509.Certificate;

public class CMPCertificate extends ASN1Object implements ASN1Choice {
    private Certificate a;
    private AttributeCertificate b;

    public ASN1Primitive a() {
        if (this.b != null) {
            return new DERTaggedObject(true, 1, this.b);
        }
        return this.a.a();
    }
}
