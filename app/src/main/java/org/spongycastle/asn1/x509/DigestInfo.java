package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;

public class DigestInfo extends ASN1Object {
    private byte[] a;
    private AlgorithmIdentifier b;

    public static DigestInfo a(Object obj) {
        if (obj instanceof DigestInfo) {
            return (DigestInfo) obj;
        }
        if (obj != null) {
            return new DigestInfo(ASN1Sequence.a(obj));
        }
        return null;
    }

    public DigestInfo(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.a = bArr;
        this.b = algorithmIdentifier;
    }

    public DigestInfo(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        this.b = AlgorithmIdentifier.a(e.nextElement());
        this.a = ASN1OctetString.a(e.nextElement()).e();
    }

    public AlgorithmIdentifier d() {
        return this.b;
    }

    public byte[] e() {
        return this.a;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(new DEROctetString(this.a));
        return new DERSequence(aSN1EncodableVector);
    }
}
