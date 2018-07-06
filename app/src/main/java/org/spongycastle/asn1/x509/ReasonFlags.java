package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.DERBitString;

public class ReasonFlags extends DERBitString {
    public ReasonFlags(DERBitString dERBitString) {
        super(dERBitString.d(), dERBitString.e());
    }
}
