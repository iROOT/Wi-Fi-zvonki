package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.DERBitString;

public class PKIFailureInfo extends DERBitString {
    public String toString() {
        return "PKIFailureInfo: 0x" + Integer.toHexString(f());
    }
}
