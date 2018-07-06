package org.spongycastle.asn1.x509;

import java.io.IOException;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERBoolean;

public class X509Extension {
    public static final ASN1ObjectIdentifier A = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.2");
    public static final ASN1ObjectIdentifier B = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.3");
    public static final ASN1ObjectIdentifier C = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.4");
    public static final ASN1ObjectIdentifier D = new ASN1ObjectIdentifier("2.5.29.56");
    public static final ASN1ObjectIdentifier E = new ASN1ObjectIdentifier("2.5.29.55");
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("2.5.29.9");
    public static final ASN1ObjectIdentifier b = new ASN1ObjectIdentifier("2.5.29.14");
    public static final ASN1ObjectIdentifier c = new ASN1ObjectIdentifier("2.5.29.15");
    public static final ASN1ObjectIdentifier d = new ASN1ObjectIdentifier("2.5.29.16");
    public static final ASN1ObjectIdentifier e = new ASN1ObjectIdentifier("2.5.29.17");
    public static final ASN1ObjectIdentifier f = new ASN1ObjectIdentifier("2.5.29.18");
    public static final ASN1ObjectIdentifier g = new ASN1ObjectIdentifier("2.5.29.19");
    public static final ASN1ObjectIdentifier h = new ASN1ObjectIdentifier("2.5.29.20");
    public static final ASN1ObjectIdentifier i = new ASN1ObjectIdentifier("2.5.29.21");
    public static final ASN1ObjectIdentifier j = new ASN1ObjectIdentifier("2.5.29.23");
    public static final ASN1ObjectIdentifier k = new ASN1ObjectIdentifier("2.5.29.24");
    public static final ASN1ObjectIdentifier l = new ASN1ObjectIdentifier("2.5.29.27");
    public static final ASN1ObjectIdentifier m = new ASN1ObjectIdentifier("2.5.29.28");
    public static final ASN1ObjectIdentifier n = new ASN1ObjectIdentifier("2.5.29.29");
    public static final ASN1ObjectIdentifier o = new ASN1ObjectIdentifier("2.5.29.30");
    public static final ASN1ObjectIdentifier p = new ASN1ObjectIdentifier("2.5.29.31");
    public static final ASN1ObjectIdentifier q = new ASN1ObjectIdentifier("2.5.29.32");
    public static final ASN1ObjectIdentifier r = new ASN1ObjectIdentifier("2.5.29.33");
    public static final ASN1ObjectIdentifier s = new ASN1ObjectIdentifier("2.5.29.35");
    public static final ASN1ObjectIdentifier t = new ASN1ObjectIdentifier("2.5.29.36");
    public static final ASN1ObjectIdentifier u = new ASN1ObjectIdentifier("2.5.29.37");
    public static final ASN1ObjectIdentifier v = new ASN1ObjectIdentifier("2.5.29.46");
    public static final ASN1ObjectIdentifier w = new ASN1ObjectIdentifier("2.5.29.54");
    public static final ASN1ObjectIdentifier x = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.1");
    public static final ASN1ObjectIdentifier y = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.11");
    public static final ASN1ObjectIdentifier z = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.1.12");
    boolean F;
    ASN1OctetString G;

    public X509Extension(DERBoolean dERBoolean, ASN1OctetString aSN1OctetString) {
        this.F = dERBoolean.d();
        this.G = aSN1OctetString;
    }

    public X509Extension(boolean z, ASN1OctetString aSN1OctetString) {
        this.F = z;
        this.G = aSN1OctetString;
    }

    public boolean a() {
        return this.F;
    }

    public ASN1OctetString b() {
        return this.G;
    }

    public int hashCode() {
        if (a()) {
            return b().hashCode();
        }
        return b().hashCode() ^ -1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof X509Extension)) {
            return false;
        }
        X509Extension x509Extension = (X509Extension) obj;
        if (x509Extension.b().equals(b()) && x509Extension.a() == a()) {
            return true;
        }
        return false;
    }

    public static ASN1Primitive a(X509Extension x509Extension) {
        try {
            return ASN1Primitive.a(x509Extension.b().e());
        } catch (IOException e) {
            throw new IllegalArgumentException("can't convert extension: " + e);
        }
    }
}
