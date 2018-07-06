package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class CertID extends ASN1Object {
    AlgorithmIdentifier a;
    ASN1OctetString b;
    ASN1OctetString c;
    ASN1Integer d;

    public CertID(AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString, ASN1OctetString aSN1OctetString2, ASN1Integer aSN1Integer) {
        this.a = algorithmIdentifier;
        this.b = aSN1OctetString;
        this.c = aSN1OctetString2;
        this.d = aSN1Integer;
    }

    private CertID(ASN1Sequence aSN1Sequence) {
        this.a = AlgorithmIdentifier.a(aSN1Sequence.a(0));
        this.b = (ASN1OctetString) aSN1Sequence.a(1);
        this.c = (ASN1OctetString) aSN1Sequence.a(2);
        this.d = (ASN1Integer) aSN1Sequence.a(3);
    }

    public static CertID a(Object obj) {
        if (obj instanceof CertID) {
            return (CertID) obj;
        }
        if (obj != null) {
            return new CertID(ASN1Sequence.a(obj));
        }
        return null;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        return new DERSequence(aSN1EncodableVector);
    }
}
