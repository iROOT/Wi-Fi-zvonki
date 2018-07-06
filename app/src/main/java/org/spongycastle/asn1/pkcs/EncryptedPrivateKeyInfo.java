package org.spongycastle.asn1.pkcs;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class EncryptedPrivateKeyInfo extends ASN1Object {
    private AlgorithmIdentifier a;
    private ASN1OctetString b;

    private EncryptedPrivateKeyInfo(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        this.a = AlgorithmIdentifier.a(e.nextElement());
        this.b = ASN1OctetString.a(e.nextElement());
    }

    public EncryptedPrivateKeyInfo(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.a = algorithmIdentifier;
        this.b = new DEROctetString(bArr);
    }

    public static EncryptedPrivateKeyInfo a(Object obj) {
        if (obj instanceof EncryptedPrivateKeyInfo) {
            return (EncryptedPrivateKeyInfo) obj;
        }
        if (obj != null) {
            return new EncryptedPrivateKeyInfo(ASN1Sequence.a(obj));
        }
        return null;
    }

    public AlgorithmIdentifier d() {
        return this.a;
    }

    public byte[] e() {
        return this.b.e();
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        return new DERSequence(aSN1EncodableVector);
    }
}
