package org.spongycastle.asn1.x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DERSequence;

public class X9FieldID extends ASN1Object implements X9ObjectIdentifiers {
    private ASN1ObjectIdentifier al;
    private ASN1Primitive am;

    public X9FieldID(BigInteger bigInteger) {
        this.al = c;
        this.am = new ASN1Integer(bigInteger);
    }

    public X9FieldID(int i, int i2, int i3, int i4) {
        this.al = d;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new ASN1Integer((long) i));
        if (i3 == 0) {
            aSN1EncodableVector.a(f);
            aSN1EncodableVector.a(new ASN1Integer((long) i2));
        } else {
            aSN1EncodableVector.a(g);
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.a(new ASN1Integer((long) i2));
            aSN1EncodableVector2.a(new ASN1Integer((long) i3));
            aSN1EncodableVector2.a(new ASN1Integer((long) i4));
            aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector2));
        }
        this.am = new DERSequence(aSN1EncodableVector);
    }

    private X9FieldID(ASN1Sequence aSN1Sequence) {
        this.al = DERObjectIdentifier.a((Object) aSN1Sequence.a(0));
        this.am = aSN1Sequence.a(1).a();
    }

    public static X9FieldID a(Object obj) {
        if (obj instanceof X9FieldID) {
            return (X9FieldID) obj;
        }
        if (obj != null) {
            return new X9FieldID(ASN1Sequence.a(obj));
        }
        return null;
    }

    public ASN1ObjectIdentifier d() {
        return this.al;
    }

    public ASN1Primitive e() {
        return this.am;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.al);
        aSN1EncodableVector.a(this.am);
        return new DERSequence(aSN1EncodableVector);
    }
}
