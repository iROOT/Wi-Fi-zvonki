package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBoolean;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DERSequence;

public class X509Extensions extends ASN1Object {
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
    private Hashtable F = new Hashtable();
    private Vector G = new Vector();

    public static X509Extensions a(Object obj) {
        if (obj == null || (obj instanceof X509Extensions)) {
            return (X509Extensions) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new X509Extensions((ASN1Sequence) obj);
        }
        if (obj instanceof Extensions) {
            return new X509Extensions((ASN1Sequence) ((Extensions) obj).a());
        }
        if (obj instanceof ASN1TaggedObject) {
            return a(((ASN1TaggedObject) obj).l());
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public X509Extensions(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        while (e.hasMoreElements()) {
            ASN1Sequence a = ASN1Sequence.a(e.nextElement());
            if (a.f() == 3) {
                this.F.put(a.a(0), new X509Extension(DERBoolean.a(a.a(1)), ASN1OctetString.a(a.a(2))));
            } else if (a.f() == 2) {
                this.F.put(a.a(0), new X509Extension(false, ASN1OctetString.a(a.a(1))));
            } else {
                throw new IllegalArgumentException("Bad sequence size: " + a.f());
            }
            this.G.addElement(a.a(0));
        }
    }

    public X509Extensions(Vector vector, Vector vector2) {
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            this.G.addElement(elements.nextElement());
        }
        Enumeration elements2 = this.G.elements();
        int i = 0;
        while (elements2.hasMoreElements()) {
            this.F.put((ASN1ObjectIdentifier) elements2.nextElement(), (X509Extension) vector2.elementAt(i));
            i++;
        }
    }

    public Enumeration d() {
        return this.G.elements();
    }

    public X509Extension a(DERObjectIdentifier dERObjectIdentifier) {
        return (X509Extension) this.F.get(dERObjectIdentifier);
    }

    public X509Extension a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (X509Extension) this.F.get(aSN1ObjectIdentifier);
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Enumeration elements = this.G.elements();
        while (elements.hasMoreElements()) {
            ASN1Encodable aSN1Encodable = (ASN1ObjectIdentifier) elements.nextElement();
            X509Extension x509Extension = (X509Extension) this.F.get(aSN1Encodable);
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.a(aSN1Encodable);
            if (x509Extension.a()) {
                aSN1EncodableVector2.a(DERBoolean.b);
            }
            aSN1EncodableVector2.a(x509Extension.b());
            aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
