package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class McElieceCCA2PublicKey extends ASN1Object {
    private ASN1ObjectIdentifier a;
    private int b;
    private int c;
    private byte[] d;

    public McElieceCCA2PublicKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, int i2, GF2Matrix gF2Matrix) {
        this.a = aSN1ObjectIdentifier;
        this.b = i;
        this.c = i2;
        this.d = gF2Matrix.a();
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(new ASN1Integer((long) this.b));
        aSN1EncodableVector.a(new ASN1Integer((long) this.c));
        aSN1EncodableVector.a(new DEROctetString(this.d));
        return new DERSequence(aSN1EncodableVector);
    }
}
