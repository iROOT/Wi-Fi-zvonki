package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class McElieceCCA2PrivateKey extends ASN1Object {
    private ASN1ObjectIdentifier a;
    private int b;
    private int c;
    private byte[] d;
    private byte[] e;
    private byte[] f;
    private byte[] g;
    private byte[][] h;

    public McElieceCCA2PrivateKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, int i2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, Permutation permutation, GF2Matrix gF2Matrix, PolynomialGF2mSmallM[] polynomialGF2mSmallMArr) {
        this.a = aSN1ObjectIdentifier;
        this.b = i;
        this.c = i2;
        this.d = gF2mField.b();
        this.e = polynomialGF2mSmallM.c();
        this.f = permutation.a();
        this.g = gF2Matrix.a();
        this.h = new byte[polynomialGF2mSmallMArr.length][];
        for (int i3 = 0; i3 != polynomialGF2mSmallMArr.length; i3++) {
            this.h[i3] = polynomialGF2mSmallMArr[i3].c();
        }
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(new ASN1Integer((long) this.b));
        aSN1EncodableVector.a(new ASN1Integer((long) this.c));
        aSN1EncodableVector.a(new DEROctetString(this.d));
        aSN1EncodableVector.a(new DEROctetString(this.e));
        aSN1EncodableVector.a(new DEROctetString(this.f));
        aSN1EncodableVector.a(new DEROctetString(this.g));
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (byte[] dEROctetString : this.h) {
            aSN1EncodableVector2.a(new DEROctetString(dEROctetString));
        }
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector2));
        return new DERSequence(aSN1EncodableVector);
    }
}
