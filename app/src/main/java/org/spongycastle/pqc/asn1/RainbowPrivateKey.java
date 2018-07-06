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
import org.spongycastle.pqc.crypto.rainbow.Layer;
import org.spongycastle.pqc.crypto.rainbow.util.RainbowUtil;

public class RainbowPrivateKey extends ASN1Object {
    private ASN1Integer a;
    private ASN1ObjectIdentifier b;
    private byte[][] c;
    private byte[] d;
    private byte[][] e;
    private byte[] f;
    private byte[] g;
    private Layer[] h;

    private RainbowPrivateKey(ASN1Sequence aSN1Sequence) {
        int i;
        if (aSN1Sequence.a(0) instanceof ASN1Integer) {
            this.a = DERInteger.a((Object) aSN1Sequence.a(0));
        } else {
            this.b = DERObjectIdentifier.a((Object) aSN1Sequence.a(0));
        }
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.a(1);
        this.c = new byte[aSN1Sequence2.f()][];
        for (i = 0; i < aSN1Sequence2.f(); i++) {
            this.c[i] = ((ASN1OctetString) aSN1Sequence2.a(i)).e();
        }
        this.d = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.a(2)).a(0)).e();
        aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.a(3);
        this.e = new byte[aSN1Sequence2.f()][];
        for (i = 0; i < aSN1Sequence2.f(); i++) {
            this.e[i] = ((ASN1OctetString) aSN1Sequence2.a(i)).e();
        }
        this.f = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.a(4)).a(0)).e();
        this.g = ((ASN1OctetString) ((ASN1Sequence) aSN1Sequence.a(5)).a(0)).e();
        aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.a(6);
        byte[][][][] bArr = new byte[aSN1Sequence2.f()][][][];
        byte[][][][] bArr2 = new byte[aSN1Sequence2.f()][][][];
        byte[][][] bArr3 = new byte[aSN1Sequence2.f()][][];
        byte[][] bArr4 = new byte[aSN1Sequence2.f()][];
        for (int i2 = 0; i2 < aSN1Sequence2.f(); i2++) {
            int i3;
            ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence2.a(i2);
            ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence3.a(0);
            bArr[i2] = new byte[aSN1Sequence4.f()][][];
            for (i3 = 0; i3 < aSN1Sequence4.f(); i3++) {
                int i4;
                ASN1Sequence aSN1Sequence5 = (ASN1Sequence) aSN1Sequence4.a(i3);
                bArr[i2][i3] = new byte[aSN1Sequence5.f()][];
                for (i4 = 0; i4 < aSN1Sequence5.f(); i4++) {
                    bArr[i2][i3][i4] = ((ASN1OctetString) aSN1Sequence5.a(i4)).e();
                }
            }
            aSN1Sequence4 = (ASN1Sequence) aSN1Sequence3.a(1);
            bArr2[i2] = new byte[aSN1Sequence4.f()][][];
            for (i3 = 0; i3 < aSN1Sequence4.f(); i3++) {
                aSN1Sequence5 = (ASN1Sequence) aSN1Sequence4.a(i3);
                bArr2[i2][i3] = new byte[aSN1Sequence5.f()][];
                for (i4 = 0; i4 < aSN1Sequence5.f(); i4++) {
                    bArr2[i2][i3][i4] = ((ASN1OctetString) aSN1Sequence5.a(i4)).e();
                }
            }
            aSN1Sequence4 = (ASN1Sequence) aSN1Sequence3.a(2);
            bArr3[i2] = new byte[aSN1Sequence4.f()][];
            for (int i5 = 0; i5 < aSN1Sequence4.f(); i5++) {
                bArr3[i2][i5] = ((ASN1OctetString) aSN1Sequence4.a(i5)).e();
            }
            bArr4[i2] = ((ASN1OctetString) aSN1Sequence3.a(3)).e();
        }
        int length = this.g.length - 1;
        this.h = new Layer[length];
        for (i4 = 0; i4 < length; i4++) {
            this.h[i4] = new Layer(this.g[i4], this.g[i4 + 1], RainbowUtil.a(bArr[i4]), RainbowUtil.a(bArr2[i4]), RainbowUtil.a(bArr3[i4]), RainbowUtil.b(bArr4[i4]));
        }
    }

    public RainbowPrivateKey(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, Layer[] layerArr) {
        this.a = new ASN1Integer(1);
        this.c = RainbowUtil.a(sArr);
        this.d = RainbowUtil.a(sArr2);
        this.e = RainbowUtil.a(sArr3);
        this.f = RainbowUtil.a(sArr4);
        this.g = RainbowUtil.a(iArr);
        this.h = layerArr;
    }

    public static RainbowPrivateKey a(Object obj) {
        if (obj instanceof RainbowPrivateKey) {
            return (RainbowPrivateKey) obj;
        }
        if (obj != null) {
            return new RainbowPrivateKey(ASN1Sequence.a(obj));
        }
        return null;
    }

    public short[][] d() {
        return RainbowUtil.a(this.c);
    }

    public short[] e() {
        return RainbowUtil.b(this.d);
    }

    public short[] f() {
        return RainbowUtil.b(this.f);
    }

    public short[][] g() {
        return RainbowUtil.a(this.e);
    }

    public Layer[] h() {
        return this.h;
    }

    public int[] i() {
        return RainbowUtil.a(this.g);
    }

    public ASN1Primitive a() {
        int i;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(this.a);
        } else {
            aSN1EncodableVector.a(this.b);
        }
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (byte[] dEROctetString : this.c) {
            aSN1EncodableVector2.a(new DEROctetString(dEROctetString));
        }
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector2));
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        aSN1EncodableVector3.a(new DEROctetString(this.d));
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector3));
        aSN1EncodableVector2 = new ASN1EncodableVector();
        for (byte[] dEROctetString2 : this.e) {
            aSN1EncodableVector2.a(new DEROctetString(dEROctetString2));
        }
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector3 = new ASN1EncodableVector();
        aSN1EncodableVector3.a(new DEROctetString(this.f));
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector3));
        aSN1EncodableVector3 = new ASN1EncodableVector();
        aSN1EncodableVector3.a(new DEROctetString(this.g));
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector3));
        ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
        for (i = 0; i < this.h.length; i++) {
            int i2;
            ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
            byte[][][] a = RainbowUtil.a(this.h[i].d());
            ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
            for (i2 = 0; i2 < a.length; i2++) {
                ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
                for (byte[] dEROctetString3 : a[i2]) {
                    aSN1EncodableVector7.a(new DEROctetString(dEROctetString3));
                }
                aSN1EncodableVector6.a(new DERSequence(aSN1EncodableVector7));
            }
            aSN1EncodableVector5.a(new DERSequence(aSN1EncodableVector6));
            a = RainbowUtil.a(this.h[i].e());
            aSN1EncodableVector6 = new ASN1EncodableVector();
            for (i2 = 0; i2 < a.length; i2++) {
                aSN1EncodableVector7 = new ASN1EncodableVector();
                for (byte[] dEROctetString32 : a[i2]) {
                    aSN1EncodableVector7.a(new DEROctetString(dEROctetString32));
                }
                aSN1EncodableVector6.a(new DERSequence(aSN1EncodableVector7));
            }
            aSN1EncodableVector5.a(new DERSequence(aSN1EncodableVector6));
            byte[][] a2 = RainbowUtil.a(this.h[i].f());
            ASN1EncodableVector aSN1EncodableVector8 = new ASN1EncodableVector();
            for (byte[] dEROctetString4 : a2) {
                aSN1EncodableVector8.a(new DEROctetString(dEROctetString4));
            }
            aSN1EncodableVector5.a(new DERSequence(aSN1EncodableVector8));
            aSN1EncodableVector5.a(new DEROctetString(RainbowUtil.a(this.h[i].g())));
            aSN1EncodableVector4.a(new DERSequence(aSN1EncodableVector5));
        }
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector4));
        return new DERSequence(aSN1EncodableVector);
    }
}
