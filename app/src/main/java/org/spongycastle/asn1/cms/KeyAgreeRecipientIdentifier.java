package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERTaggedObject;

public class KeyAgreeRecipientIdentifier extends ASN1Object implements ASN1Choice {
    private IssuerAndSerialNumber a;
    private RecipientKeyIdentifier b;

    public ASN1Primitive a() {
        if (this.a != null) {
            return this.a.a();
        }
        return new DERTaggedObject(false, 0, this.b);
    }
}
