package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class AuthorityKeyIdentifier extends ASN1Object {
    ASN1OctetString a = null;
    GeneralNames b = null;
    ASN1Integer c = null;

    public static AuthorityKeyIdentifier a(Object obj) {
        if (obj instanceof AuthorityKeyIdentifier) {
            return (AuthorityKeyIdentifier) obj;
        }
        if (obj != null) {
            return new AuthorityKeyIdentifier(ASN1Sequence.a(obj));
        }
        return null;
    }

    protected AuthorityKeyIdentifier(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        while (e.hasMoreElements()) {
            ASN1TaggedObject a = ASN1TaggedObject.a(e.nextElement());
            switch (a.d()) {
                case 0:
                    this.a = ASN1OctetString.a(a, false);
                    break;
                case 1:
                    this.b = GeneralNames.a(a, false);
                    break;
                case 2:
                    this.c = DERInteger.a(a, false);
                    break;
                default:
                    throw new IllegalArgumentException("illegal tag");
            }
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
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.a));
        }
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.b));
        }
        if (this.c != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 2, this.c));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "AuthorityKeyIdentifier: KeyID(" + this.a.e() + ")";
    }
}
