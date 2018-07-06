package org.spongycastle.asn1;

public class ASN1GeneralizedTime extends DERGeneralizedTime {
    ASN1GeneralizedTime(byte[] bArr) {
        super(bArr);
    }

    public ASN1GeneralizedTime(String str) {
        super(str);
    }
}
