package org.spongycastle.asn1.x509;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBoolean;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DERSequence;

public class Extension extends ASN1Object {
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
    private ASN1ObjectIdentifier F;
    private boolean G;
    private ASN1OctetString H;

    private Extension(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() == 2) {
            this.F = DERObjectIdentifier.a((Object) aSN1Sequence.a(0));
            this.G = false;
            this.H = ASN1OctetString.a(aSN1Sequence.a(1));
        } else if (aSN1Sequence.f() == 3) {
            this.F = DERObjectIdentifier.a((Object) aSN1Sequence.a(0));
            this.G = DERBoolean.a((Object) aSN1Sequence.a(1)).d();
            this.H = ASN1OctetString.a(aSN1Sequence.a(2));
        } else {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.f());
        }
    }

    public static Extension a(Object obj) {
        if (obj instanceof Extension) {
            return (Extension) obj;
        }
        if (obj != null) {
            return new Extension(ASN1Sequence.a(obj));
        }
        return null;
    }

    public ASN1ObjectIdentifier d() {
        return this.F;
    }

    public boolean e() {
        return this.G;
    }

    public ASN1OctetString f() {
        return this.H;
    }

    public ASN1Encodable g() {
        return a(this);
    }

    public int hashCode() {
        if (e()) {
            return f().hashCode() ^ d().hashCode();
        }
        return (f().hashCode() ^ d().hashCode()) ^ -1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Extension)) {
            return false;
        }
        Extension extension = (Extension) obj;
        if (extension.d().equals(d()) && extension.f().equals(f()) && extension.e() == e()) {
            return true;
        }
        return false;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.F);
        if (this.G) {
            aSN1EncodableVector.a(DERBoolean.a(true));
        }
        aSN1EncodableVector.a(this.H);
        return new DERSequence(aSN1EncodableVector);
    }

    private static ASN1Primitive a(Extension extension) {
        try {
            return ASN1Primitive.a(extension.f().e());
        } catch (IOException e) {
            throw new IllegalArgumentException("can't convert extension: " + e);
        }
    }
}
