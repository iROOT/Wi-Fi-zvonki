package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.PolicyInformation;

public class PathProcInput extends ASN1Object {
    private PolicyInformation[] a;
    private boolean b;
    private boolean c;
    private boolean d;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (int i = 0; i != this.a.length; i++) {
            aSN1EncodableVector2.a(this.a[i]);
        }
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector2));
        if (this.b) {
            aSN1EncodableVector.a(new ASN1Boolean(this.b));
        }
        if (this.c) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, new ASN1Boolean(this.c)));
        }
        if (this.d) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, new ASN1Boolean(this.d)));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "PathProcInput: {\nacceptablePolicySet: " + this.a + "\n" + "inhibitPolicyMapping: " + this.b + "\n" + "explicitPolicyReqd: " + this.c + "\n" + "inhibitAnyPolicy: " + this.d + "\n" + "}\n";
    }
}
