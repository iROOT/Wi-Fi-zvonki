package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class RSAPrivateKeyStructure extends ASN1Object {
    private int a;
    private BigInteger b;
    private BigInteger c;
    private BigInteger d;
    private BigInteger e;
    private BigInteger f;
    private BigInteger g;
    private BigInteger h;
    private BigInteger i;
    private ASN1Sequence j;

    public BigInteger d() {
        return this.b;
    }

    public BigInteger e() {
        return this.c;
    }

    public BigInteger f() {
        return this.d;
    }

    public BigInteger g() {
        return this.e;
    }

    public BigInteger h() {
        return this.f;
    }

    public BigInteger i() {
        return this.g;
    }

    public BigInteger j() {
        return this.h;
    }

    public BigInteger k() {
        return this.i;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new ASN1Integer((long) this.a));
        aSN1EncodableVector.a(new ASN1Integer(d()));
        aSN1EncodableVector.a(new ASN1Integer(e()));
        aSN1EncodableVector.a(new ASN1Integer(f()));
        aSN1EncodableVector.a(new ASN1Integer(g()));
        aSN1EncodableVector.a(new ASN1Integer(h()));
        aSN1EncodableVector.a(new ASN1Integer(i()));
        aSN1EncodableVector.a(new ASN1Integer(j()));
        aSN1EncodableVector.a(new ASN1Integer(k()));
        if (this.j != null) {
            aSN1EncodableVector.a(this.j);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
