package org.spongycastle.asn1.misc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;

public class IDEACBCPar extends ASN1Object {
    ASN1OctetString a;

    public IDEACBCPar(byte[] bArr) {
        this.a = new DEROctetString(bArr);
    }

    public IDEACBCPar(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() == 1) {
            this.a = (ASN1OctetString) aSN1Sequence.a(0);
        } else {
            this.a = null;
        }
    }

    public byte[] d() {
        if (this.a != null) {
            return this.a.e();
        }
        return null;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(this.a);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
