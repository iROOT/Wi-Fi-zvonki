package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;

public class RSAPublicKey extends ASN1Object {
    private BigInteger a;
    private BigInteger b;

    public static RSAPublicKey a(Object obj) {
        if (obj instanceof RSAPublicKey) {
            return (RSAPublicKey) obj;
        }
        if (obj != null) {
            return new RSAPublicKey(ASN1Sequence.a(obj));
        }
        return null;
    }

    public RSAPublicKey(BigInteger bigInteger, BigInteger bigInteger2) {
        this.a = bigInteger;
        this.b = bigInteger2;
    }

    private RSAPublicKey(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
        Enumeration e = aSN1Sequence.e();
        this.a = DERInteger.a(e.nextElement()).e();
        this.b = DERInteger.a(e.nextElement()).e();
    }

    public BigInteger d() {
        return this.a;
    }

    public BigInteger e() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new ASN1Integer(d()));
        aSN1EncodableVector.a(new ASN1Integer(e()));
        return new DERSequence(aSN1EncodableVector);
    }
}
