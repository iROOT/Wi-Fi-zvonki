package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;

public class AttCertIssuer extends ASN1Object implements ASN1Choice {
    ASN1Encodable a;
    ASN1Primitive b;

    public static AttCertIssuer a(Object obj) {
        if (obj == null || (obj instanceof AttCertIssuer)) {
            return (AttCertIssuer) obj;
        }
        if (obj instanceof V2Form) {
            return new AttCertIssuer(V2Form.a(obj));
        }
        if (obj instanceof GeneralNames) {
            return new AttCertIssuer((GeneralNames) obj);
        }
        if (obj instanceof ASN1TaggedObject) {
            return new AttCertIssuer(V2Form.a((ASN1TaggedObject) obj, false));
        }
        if (obj instanceof ASN1Sequence) {
            return new AttCertIssuer(GeneralNames.a(obj));
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public AttCertIssuer(GeneralNames generalNames) {
        this.a = generalNames;
        this.b = this.a.a();
    }

    public AttCertIssuer(V2Form v2Form) {
        this.a = v2Form;
        this.b = new DERTaggedObject(false, 0, this.a);
    }

    public ASN1Encodable d() {
        return this.a;
    }

    public ASN1Primitive a() {
        return this.b;
    }
}
