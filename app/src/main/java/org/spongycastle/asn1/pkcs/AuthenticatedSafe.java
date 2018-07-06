package org.spongycastle.asn1.pkcs;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.DLSequence;

public class AuthenticatedSafe extends ASN1Object {
    private ContentInfo[] a;
    private boolean b = true;

    private AuthenticatedSafe(ASN1Sequence aSN1Sequence) {
        this.a = new ContentInfo[aSN1Sequence.f()];
        for (int i = 0; i != this.a.length; i++) {
            this.a[i] = ContentInfo.a(aSN1Sequence.a(i));
        }
        this.b = aSN1Sequence instanceof BERSequence;
    }

    public static AuthenticatedSafe a(Object obj) {
        if (obj instanceof AuthenticatedSafe) {
            return (AuthenticatedSafe) obj;
        }
        if (obj != null) {
            return new AuthenticatedSafe(ASN1Sequence.a(obj));
        }
        return null;
    }

    public AuthenticatedSafe(ContentInfo[] contentInfoArr) {
        this.a = contentInfoArr;
    }

    public ContentInfo[] d() {
        return this.a;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.a.length; i++) {
            aSN1EncodableVector.a(this.a[i]);
        }
        if (this.b) {
            return new BERSequence(aSN1EncodableVector);
        }
        return new DLSequence(aSN1EncodableVector);
    }
}
