package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;

public class X509CertificateStructure extends ASN1Object implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    ASN1Sequence bD;
    TBSCertificateStructure bE;
    AlgorithmIdentifier bF;
    DERBitString bG;

    public X509CertificateStructure(ASN1Sequence aSN1Sequence) {
        this.bD = aSN1Sequence;
        if (aSN1Sequence.f() == 3) {
            this.bE = TBSCertificateStructure.a(aSN1Sequence.a(0));
            this.bF = AlgorithmIdentifier.a(aSN1Sequence.a(1));
            this.bG = DERBitString.a(aSN1Sequence.a(2));
            return;
        }
        throw new IllegalArgumentException("sequence wrong size for a certificate");
    }

    public ASN1Primitive a() {
        return this.bD;
    }
}
