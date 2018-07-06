package org.spongycastle.x509.extension;

import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;

public class X509ExtensionUtil {
    public static ASN1Primitive a(byte[] bArr) {
        return ASN1Primitive.a(((ASN1OctetString) ASN1Primitive.a(bArr)).e());
    }
}
