package org.spongycastle.asn1.eac;

import java.io.IOException;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DEROctetString;

public class CVCertificate extends ASN1Object {
    public static String a = "ISO-8859-1";
    private static int e = 1;
    private static int f = 2;
    private CertificateBody b;
    private byte[] c;
    private int d;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.d != (f | e)) {
            return null;
        }
        aSN1EncodableVector.a(this.b);
        try {
            aSN1EncodableVector.a(new DERApplicationSpecific(false, 55, new DEROctetString(this.c)));
            return new DERApplicationSpecific(33, aSN1EncodableVector);
        } catch (IOException e) {
            throw new IllegalStateException("unable to convert signature!");
        }
    }
}
