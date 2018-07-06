package org.spongycastle.asn1.cryptopro;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class GOST28147Parameters extends ASN1Object {
    private ASN1OctetString a;
    private ASN1ObjectIdentifier b;

    public static GOST28147Parameters a(Object obj) {
        if (obj instanceof GOST28147Parameters) {
            return (GOST28147Parameters) obj;
        }
        if (obj != null) {
            return new GOST28147Parameters(ASN1Sequence.a(obj));
        }
        return null;
    }

    public GOST28147Parameters(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        this.a = (ASN1OctetString) e.nextElement();
        this.b = (ASN1ObjectIdentifier) e.nextElement();
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        return new DERSequence(aSN1EncodableVector);
    }

    public ASN1ObjectIdentifier d() {
        return this.b;
    }

    public byte[] e() {
        return this.a.e();
    }
}
