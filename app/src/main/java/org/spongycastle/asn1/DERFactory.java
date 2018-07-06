package org.spongycastle.asn1;

class DERFactory {
    static final ASN1Sequence a = new DERSequence();
    static final ASN1Set b = new DERSet();

    DERFactory() {
    }

    static ASN1Sequence a(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.a() < 1 ? a : new DLSequence(aSN1EncodableVector);
    }

    static ASN1Set b(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.a() < 1 ? b : new DLSet(aSN1EncodableVector);
    }
}
