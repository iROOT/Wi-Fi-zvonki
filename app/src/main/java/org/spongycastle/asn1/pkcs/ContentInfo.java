package org.spongycastle.asn1.pkcs;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERTaggedObject;
import org.spongycastle.asn1.DLSequence;

public class ContentInfo extends ASN1Object implements PKCSObjectIdentifiers {
    private ASN1ObjectIdentifier bD;
    private ASN1Encodable bE;
    private boolean bF = true;

    public static ContentInfo a(Object obj) {
        if (obj instanceof ContentInfo) {
            return (ContentInfo) obj;
        }
        if (obj != null) {
            return new ContentInfo(ASN1Sequence.a(obj));
        }
        return null;
    }

    private ContentInfo(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        this.bD = (ASN1ObjectIdentifier) e.nextElement();
        if (e.hasMoreElements()) {
            this.bE = ((ASN1TaggedObject) e.nextElement()).l();
        }
        this.bF = aSN1Sequence instanceof BERSequence;
    }

    public ContentInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.bD = aSN1ObjectIdentifier;
        this.bE = aSN1Encodable;
    }

    public ASN1ObjectIdentifier d() {
        return this.bD;
    }

    public ASN1Encodable e() {
        return this.bE;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.bD);
        if (this.bE != null) {
            aSN1EncodableVector.a(new BERTaggedObject(true, 0, this.bE));
        }
        if (this.bF) {
            return new BERSequence(aSN1EncodableVector);
        }
        return new DLSequence(aSN1EncodableVector);
    }
}
