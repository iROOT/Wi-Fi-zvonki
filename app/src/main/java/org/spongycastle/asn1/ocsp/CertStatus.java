package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERTaggedObject;

public class CertStatus extends ASN1Object implements ASN1Choice {
    private int a;
    private ASN1Encodable b;

    public CertStatus() {
        this.a = 0;
        this.b = DERNull.a;
    }

    public CertStatus(ASN1TaggedObject aSN1TaggedObject) {
        this.a = aSN1TaggedObject.d();
        switch (aSN1TaggedObject.d()) {
            case 0:
                this.b = DERNull.a;
                return;
            case 1:
                this.b = RevokedInfo.a(aSN1TaggedObject, false);
                return;
            case 2:
                this.b = DERNull.a;
                return;
            default:
                return;
        }
    }

    public static CertStatus a(Object obj) {
        if (obj == null || (obj instanceof CertStatus)) {
            return (CertStatus) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new CertStatus((ASN1TaggedObject) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public int d() {
        return this.a;
    }

    public ASN1Encodable e() {
        return this.b;
    }

    public ASN1Primitive a() {
        return new DERTaggedObject(false, this.a, this.b);
    }
}
