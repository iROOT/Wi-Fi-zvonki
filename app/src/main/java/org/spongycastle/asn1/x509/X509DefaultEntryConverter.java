package org.spongycastle.asn1.x509;

import java.io.IOException;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERUTF8String;

public class X509DefaultEntryConverter extends X509NameEntryConverter {
    public ASN1Primitive a(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (str.length() == 0 || str.charAt(0) != '#') {
            if (str.length() != 0 && str.charAt(0) == '\\') {
                str = str.substring(1);
            }
            if (aSN1ObjectIdentifier.equals(X509Name.D) || aSN1ObjectIdentifier.equals(X509Name.H)) {
                return new DERIA5String(str);
            }
            if (aSN1ObjectIdentifier.equals(X509Name.t)) {
                return new DERGeneralizedTime(str);
            }
            if (aSN1ObjectIdentifier.equals(X509Name.a) || aSN1ObjectIdentifier.equals(X509Name.f) || aSN1ObjectIdentifier.equals(X509Name.r) || aSN1ObjectIdentifier.equals(X509Name.B)) {
                return new DERPrintableString(str);
            }
            return new DERUTF8String(str);
        }
        try {
            return a(str, 1);
        } catch (IOException e) {
            throw new RuntimeException("can't recode value for oid " + aSN1ObjectIdentifier.d());
        }
    }
}
