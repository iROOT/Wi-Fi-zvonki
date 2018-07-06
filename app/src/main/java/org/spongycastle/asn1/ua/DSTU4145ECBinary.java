package org.spongycastle.asn1.ua;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.util.Arrays;

public class DSTU4145ECBinary extends ASN1Object {
    BigInteger a = BigInteger.valueOf(0);
    DSTU4145BinaryField b;
    ASN1Integer c;
    ASN1OctetString d;
    ASN1Integer e;
    ASN1OctetString f;

    private DSTU4145ECBinary(ASN1Sequence aSN1Sequence) {
        int i = 0;
        if (aSN1Sequence.a(0) instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.a(0);
            if (aSN1TaggedObject.e() && aSN1TaggedObject.d() == 0) {
                this.a = DERInteger.a((Object) aSN1TaggedObject.f()).d();
                i = 1;
            } else {
                throw new IllegalArgumentException("object parse error");
            }
        }
        this.b = DSTU4145BinaryField.a(aSN1Sequence.a(i));
        i++;
        this.c = DERInteger.a((Object) aSN1Sequence.a(i));
        i++;
        this.d = ASN1OctetString.a(aSN1Sequence.a(i));
        i++;
        this.e = DERInteger.a((Object) aSN1Sequence.a(i));
        this.f = ASN1OctetString.a(aSN1Sequence.a(i + 1));
    }

    public static DSTU4145ECBinary a(Object obj) {
        if (obj instanceof DSTU4145ECBinary) {
            return (DSTU4145ECBinary) obj;
        }
        if (obj != null) {
            return new DSTU4145ECBinary(ASN1Sequence.a(obj));
        }
        return null;
    }

    public DSTU4145BinaryField d() {
        return this.b;
    }

    public BigInteger e() {
        return this.c.d();
    }

    public byte[] f() {
        return Arrays.b(this.d.e());
    }

    public BigInteger g() {
        return this.e.d();
    }

    public byte[] h() {
        return Arrays.b(this.f.e());
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a.compareTo(BigInteger.valueOf(0)) != 0) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, new ASN1Integer(this.a)));
        }
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        aSN1EncodableVector.a(this.e);
        aSN1EncodableVector.a(this.f);
        return new DERSequence(aSN1EncodableVector);
    }
}
