package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class RSASSAPSSparams extends ASN1Object {
    public static final AlgorithmIdentifier a = new AlgorithmIdentifier(OIWObjectIdentifiers.i, DERNull.a);
    public static final AlgorithmIdentifier b = new AlgorithmIdentifier(PKCSObjectIdentifiers.l_, a);
    public static final ASN1Integer c = new ASN1Integer(20);
    public static final ASN1Integer d = new ASN1Integer(1);
    private AlgorithmIdentifier e;
    private AlgorithmIdentifier f;
    private ASN1Integer g;
    private ASN1Integer h;

    public static RSASSAPSSparams a(Object obj) {
        if (obj instanceof RSASSAPSSparams) {
            return (RSASSAPSSparams) obj;
        }
        if (obj != null) {
            return new RSASSAPSSparams(ASN1Sequence.a(obj));
        }
        return null;
    }

    public RSASSAPSSparams() {
        this.e = a;
        this.f = b;
        this.g = c;
        this.h = d;
    }

    public RSASSAPSSparams(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, ASN1Integer aSN1Integer, ASN1Integer aSN1Integer2) {
        this.e = algorithmIdentifier;
        this.f = algorithmIdentifier2;
        this.g = aSN1Integer;
        this.h = aSN1Integer2;
    }

    private RSASSAPSSparams(ASN1Sequence aSN1Sequence) {
        this.e = a;
        this.f = b;
        this.g = c;
        this.h = d;
        for (int i = 0; i != aSN1Sequence.f(); i++) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.a(i);
            switch (aSN1TaggedObject.d()) {
                case 0:
                    this.e = AlgorithmIdentifier.a(aSN1TaggedObject, true);
                    break;
                case 1:
                    this.f = AlgorithmIdentifier.a(aSN1TaggedObject, true);
                    break;
                case 2:
                    this.g = DERInteger.a(aSN1TaggedObject, true);
                    break;
                case 3:
                    this.h = DERInteger.a(aSN1TaggedObject, true);
                    break;
                default:
                    throw new IllegalArgumentException("unknown tag");
            }
        }
    }

    public AlgorithmIdentifier d() {
        return this.e;
    }

    public AlgorithmIdentifier e() {
        return this.f;
    }

    public BigInteger f() {
        return this.g.d();
    }

    public BigInteger g() {
        return this.h.d();
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (!this.e.equals(a)) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.e));
        }
        if (!this.f.equals(b)) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 1, this.f));
        }
        if (!this.g.equals(c)) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 2, this.g));
        }
        if (!this.h.equals(d)) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 3, this.h));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
