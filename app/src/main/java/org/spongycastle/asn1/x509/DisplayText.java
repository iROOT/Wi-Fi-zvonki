package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1String;

public class DisplayText extends ASN1Object implements ASN1Choice {
    ASN1String a;

    public ASN1Primitive a() {
        return (ASN1Primitive) this.a;
    }
}
