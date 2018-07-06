package org.spongycastle.asn1;

import java.math.BigInteger;

public class ASN1Enumerated extends DEREnumerated {
    ASN1Enumerated(byte[] bArr) {
        super(bArr);
    }

    public ASN1Enumerated(BigInteger bigInteger) {
        super(bigInteger);
    }

    public ASN1Enumerated(int i) {
        super(i);
    }
}
