package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.crmf.EncryptedValue;

public class CertOrEncCert extends ASN1Object implements ASN1Choice {
    private CMPCertificate a;
    private EncryptedValue b;

    public ASN1Primitive a() {
        if (this.a != null) {
            return new DERTaggedObject(true, 0, this.a);
        }
        return new DERTaggedObject(true, 1, this.b);
    }
}
