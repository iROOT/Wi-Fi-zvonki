package org.spongycastle.asn1.misc;

import org.spongycastle.asn1.DERBitString;

public class NetscapeCertType extends DERBitString {
    public NetscapeCertType(DERBitString dERBitString) {
        super(dERBitString.d(), dERBitString.e());
    }

    public String toString() {
        return "NetscapeCertType: 0x" + Integer.toHexString(this.a[0] & 255);
    }
}
