package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class PBES2Algorithms extends AlgorithmIdentifier implements PKCSObjectIdentifiers {
    private ASN1ObjectIdentifier bD;

    public ASN1ObjectIdentifier d() {
        return this.bD;
    }
}
