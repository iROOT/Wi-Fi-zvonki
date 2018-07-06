package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERTaggedObject;

public class PKIArchiveOptions extends ASN1Object implements ASN1Choice {
    private ASN1Encodable a;

    public ASN1Primitive a() {
        if (this.a instanceof EncryptedKey) {
            return new DERTaggedObject(true, 0, this.a);
        }
        if (this.a instanceof ASN1OctetString) {
            return new DERTaggedObject(false, 1, this.a);
        }
        return new DERTaggedObject(false, 2, this.a);
    }
}
