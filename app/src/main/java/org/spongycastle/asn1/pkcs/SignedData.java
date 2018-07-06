package org.spongycastle.asn1.pkcs;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class SignedData extends ASN1Object implements PKCSObjectIdentifiers {
    private ASN1Integer bD;
    private ASN1Set bE;
    private ContentInfo bF;
    private ASN1Set bG;
    private ASN1Set bH;
    private ASN1Set bI;

    public static SignedData a(Object obj) {
        if (obj instanceof SignedData) {
            return (SignedData) obj;
        }
        if (obj != null) {
            return new SignedData(ASN1Sequence.a(obj));
        }
        return null;
    }

    public SignedData(ASN1Integer aSN1Integer, ASN1Set aSN1Set, ContentInfo contentInfo, ASN1Set aSN1Set2, ASN1Set aSN1Set3, ASN1Set aSN1Set4) {
        this.bD = aSN1Integer;
        this.bE = aSN1Set;
        this.bF = contentInfo;
        this.bG = aSN1Set2;
        this.bH = aSN1Set3;
        this.bI = aSN1Set4;
    }

    public SignedData(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        this.bD = (ASN1Integer) e.nextElement();
        this.bE = (ASN1Set) e.nextElement();
        this.bF = ContentInfo.a(e.nextElement());
        while (e.hasMoreElements()) {
            ASN1Primitive aSN1Primitive = (ASN1Primitive) e.nextElement();
            if (aSN1Primitive instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
                switch (aSN1TaggedObject.d()) {
                    case 0:
                        this.bG = ASN1Set.a(aSN1TaggedObject, false);
                        break;
                    case 1:
                        this.bH = ASN1Set.a(aSN1TaggedObject, false);
                        break;
                    default:
                        throw new IllegalArgumentException("unknown tag value " + aSN1TaggedObject.d());
                }
            }
            this.bI = (ASN1Set) aSN1Primitive;
        }
    }

    public ASN1Set d() {
        return this.bG;
    }

    public ASN1Set e() {
        return this.bH;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.bD);
        aSN1EncodableVector.a(this.bE);
        aSN1EncodableVector.a(this.bF);
        if (this.bG != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.bG));
        }
        if (this.bH != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.bH));
        }
        aSN1EncodableVector.a(this.bI);
        return new BERSequence(aSN1EncodableVector);
    }
}
