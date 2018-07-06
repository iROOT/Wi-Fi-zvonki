package org.spongycastle.asn1.isismtt.ocsp;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Certificate;

public class RequestedCertificate extends ASN1Object implements ASN1Choice {
    private Certificate a;
    private byte[] b;
    private byte[] c;

    public ASN1Primitive a() {
        if (this.b != null) {
            return new DERTaggedObject(0, new DEROctetString(this.b));
        }
        if (this.c != null) {
            return new DERTaggedObject(1, new DEROctetString(this.c));
        }
        return this.a.a();
    }
}
