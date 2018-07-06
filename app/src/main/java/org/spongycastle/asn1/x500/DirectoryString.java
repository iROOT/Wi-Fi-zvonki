package org.spongycastle.asn1.x500;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1String;

public class DirectoryString extends ASN1Object implements ASN1Choice, ASN1String {
    private ASN1String a;

    public String a_() {
        return this.a.a_();
    }

    public String toString() {
        return this.a.a_();
    }

    public ASN1Primitive a() {
        return ((ASN1Encodable) this.a).a();
    }
}
