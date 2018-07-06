package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;

public class GMSSPublicKey extends ASN1Object {
    private ASN1Integer a = new ASN1Integer(0);
    private byte[] b;

    public GMSSPublicKey(byte[] bArr) {
        this.b = bArr;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(new DEROctetString(this.b));
        return new DERSequence(aSN1EncodableVector);
    }
}
