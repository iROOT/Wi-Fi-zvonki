package org.spongycastle.asn1.cryptopro;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class GOST3410PublicKeyAlgParameters extends ASN1Object {
    private ASN1ObjectIdentifier a;
    private ASN1ObjectIdentifier b;
    private ASN1ObjectIdentifier c;

    public static GOST3410PublicKeyAlgParameters a(Object obj) {
        if (obj instanceof GOST3410PublicKeyAlgParameters) {
            return (GOST3410PublicKeyAlgParameters) obj;
        }
        if (obj != null) {
            return new GOST3410PublicKeyAlgParameters(ASN1Sequence.a(obj));
        }
        return null;
    }

    public GOST3410PublicKeyAlgParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier2) {
        this.a = aSN1ObjectIdentifier;
        this.b = aSN1ObjectIdentifier2;
        this.c = null;
    }

    public GOST3410PublicKeyAlgParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier2, ASN1ObjectIdentifier aSN1ObjectIdentifier3) {
        this.a = aSN1ObjectIdentifier;
        this.b = aSN1ObjectIdentifier2;
        this.c = aSN1ObjectIdentifier3;
    }

    public GOST3410PublicKeyAlgParameters(ASN1Sequence aSN1Sequence) {
        this.a = (ASN1ObjectIdentifier) aSN1Sequence.a(0);
        this.b = (ASN1ObjectIdentifier) aSN1Sequence.a(1);
        if (aSN1Sequence.f() > 2) {
            this.c = (ASN1ObjectIdentifier) aSN1Sequence.a(2);
        }
    }

    public ASN1ObjectIdentifier d() {
        return this.a;
    }

    public ASN1ObjectIdentifier e() {
        return this.b;
    }

    public ASN1ObjectIdentifier f() {
        return this.c;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        if (this.c != null) {
            aSN1EncodableVector.a(this.c);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
