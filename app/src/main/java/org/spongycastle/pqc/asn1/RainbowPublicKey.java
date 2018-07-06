package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.pqc.crypto.rainbow.util.RainbowUtil;

public class RainbowPublicKey extends ASN1Object {
    private ASN1Integer a;
    private ASN1ObjectIdentifier b;
    private ASN1Integer c;
    private byte[][] d;
    private byte[][] e;
    private byte[] f;

    private RainbowPublicKey(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.a(0) instanceof ASN1Integer) {
            this.a = DERInteger.a((Object) aSN1Sequence.a(0));
        } else {
            this.b = DERObjectIdentifier.a((Object) aSN1Sequence.a(0));
        }
        this.c = DERInteger.a((Object) aSN1Sequence.a(1));
        ASN1Sequence a = ASN1Sequence.a(aSN1Sequence.a(2));
        this.d = new byte[a.f()][];
        for (int i = 0; i < a.f(); i++) {
            this.d[i] = ASN1OctetString.a(a.a(i)).e();
        }
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.a(3);
        this.e = new byte[aSN1Sequence2.f()][];
        for (int i2 = 0; i2 < aSN1Sequence2.f(); i2++) {
            this.e[i2] = ASN1OctetString.a(aSN1Sequence2.a(i2)).e();
        }
        this.f = ASN1OctetString.a(((ASN1Sequence) aSN1Sequence.a(4)).a(0)).e();
    }

    public RainbowPublicKey(int i, short[][] sArr, short[][] sArr2, short[] sArr3) {
        this.a = new ASN1Integer(0);
        this.c = new ASN1Integer((long) i);
        this.d = RainbowUtil.a(sArr);
        this.e = RainbowUtil.a(sArr2);
        this.f = RainbowUtil.a(sArr3);
    }

    public static RainbowPublicKey a(Object obj) {
        if (obj instanceof RainbowPublicKey) {
            return (RainbowPublicKey) obj;
        }
        if (obj != null) {
            return new RainbowPublicKey(ASN1Sequence.a(obj));
        }
        return null;
    }

    public int d() {
        return this.c.d().intValue();
    }

    public short[][] e() {
        return RainbowUtil.a(this.d);
    }

    public short[][] f() {
        return RainbowUtil.a(this.e);
    }

    public short[] g() {
        return RainbowUtil.b(this.f);
    }

    public ASN1Primitive a() {
        int i = 0;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(this.a);
        } else {
            aSN1EncodableVector.a(this.b);
        }
        aSN1EncodableVector.a(this.c);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (byte[] dEROctetString : this.d) {
            aSN1EncodableVector2.a(new DEROctetString(dEROctetString));
        }
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector2));
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        while (i < this.e.length) {
            aSN1EncodableVector3.a(new DEROctetString(this.e[i]));
            i++;
        }
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector3));
        aSN1EncodableVector3 = new ASN1EncodableVector();
        aSN1EncodableVector3.a(new DEROctetString(this.f));
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector3));
        return new DERSequence(aSN1EncodableVector);
    }
}
