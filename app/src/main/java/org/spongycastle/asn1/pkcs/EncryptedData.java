package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class EncryptedData extends ASN1Object {
    ASN1Sequence a;

    public static EncryptedData a(Object obj) {
        if (obj instanceof EncryptedData) {
            return (EncryptedData) obj;
        }
        if (obj != null) {
            return new EncryptedData(ASN1Sequence.a(obj));
        }
        return null;
    }

    private EncryptedData(ASN1Sequence aSN1Sequence) {
        if (((ASN1Integer) aSN1Sequence.a(0)).d().intValue() != 0) {
            throw new IllegalArgumentException("sequence not version 0");
        }
        this.a = ASN1Sequence.a(aSN1Sequence.a(1));
    }

    public EncryptedData(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1Encodable aSN1Encodable) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a((ASN1Encodable) aSN1ObjectIdentifier);
        aSN1EncodableVector.a(algorithmIdentifier.a());
        aSN1EncodableVector.a(new BERTaggedObject(false, 0, aSN1Encodable));
        this.a = new BERSequence(aSN1EncodableVector);
    }

    public AlgorithmIdentifier d() {
        return AlgorithmIdentifier.a(this.a.a(1));
    }

    public ASN1OctetString e() {
        if (this.a.f() == 3) {
            return ASN1OctetString.a(ASN1TaggedObject.a(this.a.a(2)), false);
        }
        return null;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new ASN1Integer(0));
        aSN1EncodableVector.a(this.a);
        return new BERSequence(aSN1EncodableVector);
    }
}
