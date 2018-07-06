package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x500.X500Name;

public class ResponderID extends ASN1Object implements ASN1Choice {
    private ASN1Encodable a;

    public ResponderID(ASN1OctetString aSN1OctetString) {
        this.a = aSN1OctetString;
    }

    public ResponderID(X500Name x500Name) {
        this.a = x500Name;
    }

    public static ResponderID a(Object obj) {
        if (obj instanceof ResponderID) {
            return (ResponderID) obj;
        }
        if (obj instanceof DEROctetString) {
            return new ResponderID((DEROctetString) obj);
        }
        if (!(obj instanceof ASN1TaggedObject)) {
            return new ResponderID(X500Name.a(obj));
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) obj;
        if (aSN1TaggedObject.d() == 1) {
            return new ResponderID(X500Name.a(aSN1TaggedObject, true));
        }
        return new ResponderID(ASN1OctetString.a(aSN1TaggedObject, true));
    }

    public ASN1Primitive a() {
        if (this.a instanceof ASN1OctetString) {
            return new DERTaggedObject(true, 2, this.a);
        }
        return new DERTaggedObject(true, 1, this.a);
    }
}
