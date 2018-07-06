package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public class PolicyQualifierId extends ASN1ObjectIdentifier {
    public static final PolicyQualifierId b = new PolicyQualifierId("1.3.6.1.5.5.7.2.1");
    public static final PolicyQualifierId c = new PolicyQualifierId("1.3.6.1.5.5.7.2.2");

    private PolicyQualifierId(String str) {
        super(str);
    }
}
