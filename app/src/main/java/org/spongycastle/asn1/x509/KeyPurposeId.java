package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;

public class KeyPurposeId extends ASN1Object {
    public static final KeyPurposeId a = new KeyPurposeId(Extension.u.b("0"));
    public static final KeyPurposeId b = new KeyPurposeId(v.b("1"));
    public static final KeyPurposeId c = new KeyPurposeId(v.b("2"));
    public static final KeyPurposeId d = new KeyPurposeId(v.b("3"));
    public static final KeyPurposeId e = new KeyPurposeId(v.b("4"));
    public static final KeyPurposeId f = new KeyPurposeId(v.b("5"));
    public static final KeyPurposeId g = new KeyPurposeId(v.b("6"));
    public static final KeyPurposeId h = new KeyPurposeId(v.b("7"));
    public static final KeyPurposeId i = new KeyPurposeId(v.b("8"));
    public static final KeyPurposeId j = new KeyPurposeId(v.b("9"));
    public static final KeyPurposeId k = new KeyPurposeId(v.b("10"));
    public static final KeyPurposeId l = new KeyPurposeId(v.b("11"));
    public static final KeyPurposeId m = new KeyPurposeId(v.b("12"));
    public static final KeyPurposeId n = new KeyPurposeId(v.b("13"));
    public static final KeyPurposeId o = new KeyPurposeId(v.b("14"));
    public static final KeyPurposeId p = new KeyPurposeId(v.b("15"));
    public static final KeyPurposeId q = new KeyPurposeId(v.b("16"));
    public static final KeyPurposeId r = new KeyPurposeId(v.b("17"));
    public static final KeyPurposeId s = new KeyPurposeId(v.b("18"));
    public static final KeyPurposeId t = new KeyPurposeId(v.b("19"));
    public static final KeyPurposeId u = new KeyPurposeId(new ASN1ObjectIdentifier("1.3.6.1.4.1.311.20.2.2"));
    private static final ASN1ObjectIdentifier v = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.3");
    private ASN1ObjectIdentifier w;

    private KeyPurposeId(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.w = aSN1ObjectIdentifier;
    }

    public ASN1Primitive a() {
        return this.w;
    }
}
