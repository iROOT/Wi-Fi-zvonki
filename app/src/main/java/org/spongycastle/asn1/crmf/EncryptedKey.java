package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.cms.EnvelopedData;

public class EncryptedKey extends ASN1Object implements ASN1Choice {
    private EnvelopedData a;
    private EncryptedValue b;

    public ASN1Primitive a() {
        if (this.b != null) {
            return this.b.a();
        }
        return new DERTaggedObject(false, 0, this.a);
    }
}
