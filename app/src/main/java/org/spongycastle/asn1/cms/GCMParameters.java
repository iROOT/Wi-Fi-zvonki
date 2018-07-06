package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.util.Arrays;

public class GCMParameters extends ASN1Object {
    private byte[] a;
    private int b;

    public static GCMParameters a(Object obj) {
        if (obj instanceof GCMParameters) {
            return (GCMParameters) obj;
        }
        if (obj != null) {
            return new GCMParameters(ASN1Sequence.a(obj));
        }
        return null;
    }

    private GCMParameters(ASN1Sequence aSN1Sequence) {
        this.a = ASN1OctetString.a(aSN1Sequence.a(0)).e();
        if (aSN1Sequence.f() == 2) {
            this.b = DERInteger.a((Object) aSN1Sequence.a(1)).d().intValue();
        } else {
            this.b = 12;
        }
    }

    public GCMParameters(byte[] bArr, int i) {
        this.a = Arrays.b(bArr);
        this.b = i;
    }

    public byte[] d() {
        return Arrays.b(this.a);
    }

    public int e() {
        return this.b;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new DEROctetString(this.a));
        if (this.b != 12) {
            aSN1EncodableVector.a(new ASN1Integer((long) this.b));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
