package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class EncryptionScheme extends ASN1Object {
    private AlgorithmIdentifier a;

    private EncryptionScheme(ASN1Sequence aSN1Sequence) {
        this.a = AlgorithmIdentifier.a(aSN1Sequence);
    }

    public static final EncryptionScheme a(Object obj) {
        if (obj instanceof EncryptionScheme) {
            return (EncryptionScheme) obj;
        }
        if (obj != null) {
            return new EncryptionScheme(ASN1Sequence.a(obj));
        }
        return null;
    }

    public ASN1ObjectIdentifier d() {
        return this.a.e();
    }

    public ASN1Encodable e() {
        return this.a.f();
    }

    public ASN1Primitive a() {
        return this.a.a();
    }
}
