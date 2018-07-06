package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class TargetEtcChain extends ASN1Object {
    private CertEtcToken a;
    private ASN1Sequence b;
    private PathProcInput c;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        if (this.b != null) {
            aSN1EncodableVector.a(this.b);
        }
        if (this.c != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.c));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("TargetEtcChain {\n");
        stringBuffer.append("target: " + this.a + "\n");
        if (this.b != null) {
            stringBuffer.append("chain: " + this.b + "\n");
        }
        if (this.c != null) {
            stringBuffer.append("pathProcInput: " + this.c + "\n");
        }
        stringBuffer.append("}\n");
        return stringBuffer.toString();
    }
}
