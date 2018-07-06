package org.spongycastle.pqc.asn1;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;

public class ParSet extends ASN1Object {
    private static final BigInteger a = BigInteger.valueOf(0);
    private int b;
    private int[] c;
    private int[] d;
    private int[] e;

    public ParSet(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        this.b = i;
        this.c = iArr;
        this.d = iArr2;
        this.e = iArr3;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        for (int i = 0; i < this.c.length; i++) {
            aSN1EncodableVector.a(new ASN1Integer((long) this.c[i]));
            aSN1EncodableVector2.a(new ASN1Integer((long) this.d[i]));
            aSN1EncodableVector3.a(new ASN1Integer((long) this.e[i]));
        }
        ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
        aSN1EncodableVector4.a(new ASN1Integer((long) this.b));
        aSN1EncodableVector4.a(new DERSequence(aSN1EncodableVector));
        aSN1EncodableVector4.a(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector4.a(new DERSequence(aSN1EncodableVector3));
        return new DERSequence(aSN1EncodableVector4);
    }
}
