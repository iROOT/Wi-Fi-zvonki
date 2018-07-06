package org.spongycastle.asn1.x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECCurve.F2m;
import org.spongycastle.math.ec.ECCurve.Fp;
import org.spongycastle.math.ec.ECPoint;

public class X9ECParameters extends ASN1Object implements X9ObjectIdentifiers {
    private static final BigInteger al = BigInteger.valueOf(1);
    private X9FieldID am;
    private ECCurve an;
    private ECPoint ao;
    private BigInteger ap;
    private BigInteger aq;
    private byte[] ar;

    private X9ECParameters(ASN1Sequence aSN1Sequence) {
        if ((aSN1Sequence.a(0) instanceof ASN1Integer) && ((ASN1Integer) aSN1Sequence.a(0)).d().equals(al)) {
            X9Curve x9Curve = new X9Curve(X9FieldID.a(aSN1Sequence.a(1)), ASN1Sequence.a(aSN1Sequence.a(2)));
            this.an = x9Curve.d();
            ASN1Encodable a = aSN1Sequence.a(3);
            if (a instanceof X9ECPoint) {
                this.ao = ((X9ECPoint) a).d();
            } else {
                this.ao = new X9ECPoint(this.an, (ASN1OctetString) a).d();
            }
            this.ap = ((ASN1Integer) aSN1Sequence.a(4)).d();
            this.ar = x9Curve.e();
            if (aSN1Sequence.f() == 6) {
                this.aq = ((ASN1Integer) aSN1Sequence.a(5)).d();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("bad version in X9ECParameters");
    }

    public static X9ECParameters a(Object obj) {
        if (obj instanceof X9ECParameters) {
            return (X9ECParameters) obj;
        }
        if (obj != null) {
            return new X9ECParameters(ASN1Sequence.a(obj));
        }
        return null;
    }

    public X9ECParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this(eCCurve, eCPoint, bigInteger, bigInteger2, null);
    }

    public X9ECParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.an = eCCurve;
        this.ao = eCPoint.k();
        this.ap = bigInteger;
        this.aq = bigInteger2;
        this.ar = bArr;
        if (eCCurve instanceof Fp) {
            this.am = new X9FieldID(((Fp) eCCurve).j());
        } else if (eCCurve instanceof F2m) {
            F2m f2m = (F2m) eCCurve;
            this.am = new X9FieldID(f2m.m(), f2m.o(), f2m.p(), f2m.q());
        }
    }

    public ECCurve d() {
        return this.an;
    }

    public ECPoint e() {
        return this.ao;
    }

    public BigInteger f() {
        return this.ap;
    }

    public BigInteger g() {
        if (this.aq == null) {
            return al;
        }
        return this.aq;
    }

    public byte[] h() {
        return this.ar;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new ASN1Integer(1));
        aSN1EncodableVector.a(this.am);
        aSN1EncodableVector.a(new X9Curve(this.an, this.ar));
        aSN1EncodableVector.a(new X9ECPoint(this.ao));
        aSN1EncodableVector.a(new ASN1Integer(this.ap));
        if (this.aq != null) {
            aSN1EncodableVector.a(new ASN1Integer(this.aq));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
