package org.spongycastle.asn1;

import java.math.BigInteger;

public class ASN1Integer extends DERInteger {
    ASN1Integer(byte[] bArr) {
        super(bArr);
    }

    public ASN1Integer(BigInteger bigInteger) {
        super(bigInteger);
    }

    public ASN1Integer(long j) {
        super(j);
    }
}
