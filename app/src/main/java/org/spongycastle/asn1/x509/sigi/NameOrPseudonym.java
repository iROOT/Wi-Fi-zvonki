package org.spongycastle.asn1.x509.sigi;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x500.DirectoryString;

public class NameOrPseudonym extends ASN1Object implements ASN1Choice {
    private DirectoryString a;
    private DirectoryString b;
    private ASN1Sequence c;

    public ASN1Primitive a() {
        if (this.a != null) {
            return this.a.a();
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        return new DERSequence(aSN1EncodableVector);
    }
}
