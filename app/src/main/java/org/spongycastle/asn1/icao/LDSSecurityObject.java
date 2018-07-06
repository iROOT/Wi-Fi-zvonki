package org.spongycastle.asn1.icao;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class LDSSecurityObject extends ASN1Object implements ICAOObjectIdentifiers {
    private ASN1Integer k;
    private AlgorithmIdentifier l;
    private DataGroupHash[] m;
    private LDSVersionInfo n;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.k);
        aSN1EncodableVector.a(this.l);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (ASN1Encodable a : this.m) {
            aSN1EncodableVector2.a(a);
        }
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector2));
        if (this.n != null) {
            aSN1EncodableVector.a(this.n);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
