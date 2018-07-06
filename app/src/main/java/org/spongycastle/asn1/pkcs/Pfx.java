package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.BERSequence;

public class Pfx extends ASN1Object implements PKCSObjectIdentifiers {
    private ContentInfo bD;
    private MacData bE = null;

    private Pfx(ASN1Sequence aSN1Sequence) {
        if (((ASN1Integer) aSN1Sequence.a(0)).d().intValue() != 3) {
            throw new IllegalArgumentException("wrong version for PFX PDU");
        }
        this.bD = ContentInfo.a(aSN1Sequence.a(1));
        if (aSN1Sequence.f() == 3) {
            this.bE = MacData.a(aSN1Sequence.a(2));
        }
    }

    public static Pfx a(Object obj) {
        if (obj instanceof Pfx) {
            return (Pfx) obj;
        }
        if (obj != null) {
            return new Pfx(ASN1Sequence.a(obj));
        }
        return null;
    }

    public Pfx(ContentInfo contentInfo, MacData macData) {
        this.bD = contentInfo;
        this.bE = macData;
    }

    public ContentInfo d() {
        return this.bD;
    }

    public MacData e() {
        return this.bE;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(new ASN1Integer(3));
        aSN1EncodableVector.a(this.bD);
        if (this.bE != null) {
            aSN1EncodableVector.a(this.bE);
        }
        return new BERSequence(aSN1EncodableVector);
    }
}
