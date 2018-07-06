package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class AuthorityInformationAccess extends ASN1Object {
    private AccessDescription[] a;

    public static AuthorityInformationAccess a(Object obj) {
        if (obj instanceof AuthorityInformationAccess) {
            return (AuthorityInformationAccess) obj;
        }
        if (obj != null) {
            return new AuthorityInformationAccess(ASN1Sequence.a(obj));
        }
        return null;
    }

    private AuthorityInformationAccess(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() < 1) {
            throw new IllegalArgumentException("sequence may not be empty");
        }
        this.a = new AccessDescription[aSN1Sequence.f()];
        for (int i = 0; i != aSN1Sequence.f(); i++) {
            this.a[i] = AccessDescription.a(aSN1Sequence.a(i));
        }
    }

    public AccessDescription[] d() {
        return this.a;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.a.length; i++) {
            aSN1EncodableVector.a(this.a[i]);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "AuthorityInformationAccess: Oid(" + this.a[0].d().d() + ")";
    }
}
