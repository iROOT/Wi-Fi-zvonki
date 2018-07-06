package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class RSAPrivateKey extends ASN1Object {
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;
    private BigInteger d;
    private BigInteger e;
    private BigInteger f;
    private BigInteger g;
    private BigInteger h;
    private BigInteger i;
    private ASN1Sequence j;

    public static RSAPrivateKey a(Object obj) {
        if (obj instanceof RSAPrivateKey) {
            return (RSAPrivateKey) obj;
        }
        if (obj != null) {
            return new RSAPrivateKey(ASN1Sequence.a(obj));
        }
        return null;
    }

    public RSAPrivateKey(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        this.j = null;
        this.a = BigInteger.valueOf(0);
        this.b = bigInteger;
        this.c = bigInteger2;
        this.d = bigInteger3;
        this.e = bigInteger4;
        this.f = bigInteger5;
        this.g = bigInteger6;
        this.h = bigInteger7;
        this.i = bigInteger8;
    }

    private RSAPrivateKey(ASN1Sequence aSN1Sequence) {
        this.j = null;
        Enumeration e = aSN1Sequence.e();
        BigInteger d = ((ASN1Integer) e.nextElement()).d();
        if (d.intValue() == 0 || d.intValue() == 1) {
            this.a = d;
            this.b = ((ASN1Integer) e.nextElement()).d();
            this.c = ((ASN1Integer) e.nextElement()).d();
            this.d = ((ASN1Integer) e.nextElement()).d();
            this.e = ((ASN1Integer) e.nextElement()).d();
            this.f = ((ASN1Integer) e.nextElement()).d();
            this.g = ((ASN1Integer) e.nextElement()).d();
            this.h = ((ASN1Integer) e.nextElement()).d();
            this.i = ((ASN1Integer) e.nextElement()).d();
            if (e.hasMoreElements()) {
                this.j = (ASN1Sequence) e.nextElement();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("wrong version for RSA private key");
    }

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
        aSN1EncodableVector.a(new ASN1Integer(this.a));
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
