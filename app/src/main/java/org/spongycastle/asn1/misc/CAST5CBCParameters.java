package org.spongycastle.asn1.misc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;

public class CAST5CBCParameters extends ASN1Object {
    ASN1Integer a;
    ASN1OctetString b;

    public static CAST5CBCParameters a(Object obj) {
        if (obj instanceof CAST5CBCParameters) {
            return (CAST5CBCParameters) obj;
        }
        if (obj != null) {
            return new CAST5CBCParameters(ASN1Sequence.a(obj));
        }
        return null;
    }

    public CAST5CBCParameters(byte[] bArr, int i) {
        this.b = new DEROctetString(bArr);
        this.a = new ASN1Integer((long) i);
    }

    public CAST5CBCParameters(ASN1Sequence aSN1Sequence) {
        this.b = (ASN1OctetString) aSN1Sequence.a(0);
        this.a = (ASN1Integer) aSN1Sequence.a(1);
    }

    public byte[] d() {
        return this.b.e();
    }

    public int e() {
        return this.a.d().intValue();
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.a);
        return new DERSequence(aSN1EncodableVector);
    }
}
