package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class RSAESOAEPparams extends ASN1Object {
    public static final AlgorithmIdentifier a = new AlgorithmIdentifier(OIWObjectIdentifiers.i, DERNull.a);
    public static final AlgorithmIdentifier b = new AlgorithmIdentifier(PKCSObjectIdentifiers.l_, a);
    public static final AlgorithmIdentifier c = new AlgorithmIdentifier(PKCSObjectIdentifiers.j, new DEROctetString(new byte[0]));
    private AlgorithmIdentifier d;
    private AlgorithmIdentifier e;
    private AlgorithmIdentifier f;

    public static RSAESOAEPparams a(Object obj) {
        if (obj instanceof RSAESOAEPparams) {
            return (RSAESOAEPparams) obj;
        }
        if (obj != null) {
            return new RSAESOAEPparams(ASN1Sequence.a(obj));
        }
        return null;
    }

    public RSAESOAEPparams() {
        this.d = a;
        this.e = b;
        this.f = c;
    }

    public RSAESOAEPparams(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, AlgorithmIdentifier algorithmIdentifier3) {
        this.d = algorithmIdentifier;
        this.e = algorithmIdentifier2;
        this.f = algorithmIdentifier3;
    }

    public RSAESOAEPparams(ASN1Sequence aSN1Sequence) {
        this.d = a;
        this.e = b;
        this.f = c;
        for (int i = 0; i != aSN1Sequence.f(); i++) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.a(i);
            switch (aSN1TaggedObject.d()) {
                case 0:
                    this.d = AlgorithmIdentifier.a(aSN1TaggedObject, true);
                    break;
                case 1:
                    this.e = AlgorithmIdentifier.a(aSN1TaggedObject, true);
                    break;
                case 2:
                    this.f = AlgorithmIdentifier.a(aSN1TaggedObject, true);
                    break;
                default:
                    throw new IllegalArgumentException("unknown tag");
            }
        }
    }

    public AlgorithmIdentifier d() {
        return this.d;
    }

    public AlgorithmIdentifier e() {
        return this.e;
    }

    public AlgorithmIdentifier f() {
        return this.f;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (!this.d.equals(a)) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.d));
        }
        if (!this.e.equals(b)) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 1, this.e));
        }
        if (!this.f.equals(c)) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 2, this.f));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
