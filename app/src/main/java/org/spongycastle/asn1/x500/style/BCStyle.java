package org.spongycastle.asn1.x500.style;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x500.AttributeTypeAndValue;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.X500NameStyle;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;

public class BCStyle implements X500NameStyle {
    public static final ASN1ObjectIdentifier A = new ASN1ObjectIdentifier("2.5.4.16");
    public static final ASN1ObjectIdentifier B = new ASN1ObjectIdentifier("2.5.4.54");
    public static final ASN1ObjectIdentifier C = X509ObjectIdentifiers.g;
    public static final ASN1ObjectIdentifier D = X509ObjectIdentifiers.c_;
    public static final ASN1ObjectIdentifier E = PKCSObjectIdentifiers.V;
    public static final ASN1ObjectIdentifier F = PKCSObjectIdentifiers.W;
    public static final ASN1ObjectIdentifier G = PKCSObjectIdentifiers.ac;
    public static final ASN1ObjectIdentifier H = E;
    public static final ASN1ObjectIdentifier I = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
    public static final ASN1ObjectIdentifier J = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
    public static final X500NameStyle K = new BCStyle();
    private static final Hashtable N = new Hashtable();
    private static final Hashtable a = new Hashtable();
    public static final ASN1ObjectIdentifier b = new ASN1ObjectIdentifier("2.5.4.6");
    public static final ASN1ObjectIdentifier c = new ASN1ObjectIdentifier("2.5.4.10");
    public static final ASN1ObjectIdentifier d = new ASN1ObjectIdentifier("2.5.4.11");
    public static final ASN1ObjectIdentifier e = new ASN1ObjectIdentifier("2.5.4.12");
    public static final ASN1ObjectIdentifier f = new ASN1ObjectIdentifier("2.5.4.3");
    public static final ASN1ObjectIdentifier g = new ASN1ObjectIdentifier("2.5.4.5");
    public static final ASN1ObjectIdentifier h = new ASN1ObjectIdentifier("2.5.4.9");
    public static final ASN1ObjectIdentifier i = g;
    public static final ASN1ObjectIdentifier j = new ASN1ObjectIdentifier("2.5.4.7");
    public static final ASN1ObjectIdentifier k = new ASN1ObjectIdentifier("2.5.4.8");
    public static final ASN1ObjectIdentifier l = new ASN1ObjectIdentifier("2.5.4.4");
    public static final ASN1ObjectIdentifier m = new ASN1ObjectIdentifier("2.5.4.42");
    public static final ASN1ObjectIdentifier n = new ASN1ObjectIdentifier("2.5.4.43");
    public static final ASN1ObjectIdentifier o = new ASN1ObjectIdentifier("2.5.4.44");
    public static final ASN1ObjectIdentifier p = new ASN1ObjectIdentifier("2.5.4.45");
    public static final ASN1ObjectIdentifier q = new ASN1ObjectIdentifier("2.5.4.15");
    public static final ASN1ObjectIdentifier r = new ASN1ObjectIdentifier("2.5.4.17");
    public static final ASN1ObjectIdentifier s = new ASN1ObjectIdentifier("2.5.4.46");
    public static final ASN1ObjectIdentifier t = new ASN1ObjectIdentifier("2.5.4.65");
    public static final ASN1ObjectIdentifier u = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.1");
    public static final ASN1ObjectIdentifier v = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.2");
    public static final ASN1ObjectIdentifier w = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.3");
    public static final ASN1ObjectIdentifier x = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.4");
    public static final ASN1ObjectIdentifier y = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.9.5");
    public static final ASN1ObjectIdentifier z = new ASN1ObjectIdentifier("1.3.36.8.3.14");
    protected final Hashtable L = a(N);
    protected final Hashtable M = a(a);

    static {
        a.put(b, "C");
        a.put(c, "O");
        a.put(e, "T");
        a.put(d, "OU");
        a.put(f, "CN");
        a.put(j, "L");
        a.put(k, "ST");
        a.put(g, "SERIALNUMBER");
        a.put(E, "E");
        a.put(I, "DC");
        a.put(J, "UID");
        a.put(h, "STREET");
        a.put(l, "SURNAME");
        a.put(m, "GIVENNAME");
        a.put(n, "INITIALS");
        a.put(o, "GENERATION");
        a.put(G, "unstructuredAddress");
        a.put(F, "unstructuredName");
        a.put(p, "UniqueIdentifier");
        a.put(s, "DN");
        a.put(t, "Pseudonym");
        a.put(A, "PostalAddress");
        a.put(z, "NameAtBirth");
        a.put(x, "CountryOfCitizenship");
        a.put(y, "CountryOfResidence");
        a.put(w, "Gender");
        a.put(v, "PlaceOfBirth");
        a.put(u, "DateOfBirth");
        a.put(r, "PostalCode");
        a.put(q, "BusinessCategory");
        a.put(C, "TelephoneNumber");
        a.put(D, "Name");
        N.put("c", b);
        N.put("o", c);
        N.put("t", e);
        N.put("ou", d);
        N.put("cn", f);
        N.put("l", j);
        N.put("st", k);
        N.put("sn", g);
        N.put("serialnumber", g);
        N.put("street", h);
        N.put("emailaddress", H);
        N.put("dc", I);
        N.put("e", H);
        N.put("uid", J);
        N.put("surname", l);
        N.put("givenname", m);
        N.put("initials", n);
        N.put("generation", o);
        N.put("unstructuredaddress", G);
        N.put("unstructuredname", F);
        N.put("uniqueidentifier", p);
        N.put("dn", s);
        N.put("pseudonym", t);
        N.put("postaladdress", A);
        N.put("nameofbirth", z);
        N.put("countryofcitizenship", x);
        N.put("countryofresidence", y);
        N.put("gender", w);
        N.put("placeofbirth", v);
        N.put("dateofbirth", u);
        N.put("postalcode", r);
        N.put("businesscategory", q);
        N.put("telephonenumber", C);
        N.put("name", D);
    }

    protected BCStyle() {
    }

    public ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (str.length() == 0 || str.charAt(0) != '#') {
            if (str.length() != 0 && str.charAt(0) == '\\') {
                str = str.substring(1);
            }
            if (aSN1ObjectIdentifier.equals(E) || aSN1ObjectIdentifier.equals(I)) {
                return new DERIA5String(str);
            }
            if (aSN1ObjectIdentifier.equals(u)) {
                return new ASN1GeneralizedTime(str);
            }
            if (aSN1ObjectIdentifier.equals(b) || aSN1ObjectIdentifier.equals(g) || aSN1ObjectIdentifier.equals(s) || aSN1ObjectIdentifier.equals(C)) {
                return new DERPrintableString(str);
            }
            return new DERUTF8String(str);
        }
        try {
            return IETFUtils.a(str, 1);
        } catch (IOException e) {
            throw new RuntimeException("can't recode value for oid " + aSN1ObjectIdentifier.d());
        }
    }

    public ASN1ObjectIdentifier a(String str) {
        return IETFUtils.a(str, this.L);
    }

    public boolean a(X500Name x500Name, X500Name x500Name2) {
        RDN[] d = x500Name.d();
        RDN[] d2 = x500Name2.d();
        if (d.length != d2.length) {
            return false;
        }
        boolean z;
        if (d[0].e() == null || d2[0].e() == null) {
            z = false;
        } else if (d[0].e().d().equals(d2[0].e().d())) {
            z = false;
        } else {
            z = true;
        }
        for (int i = 0; i != d.length; i++) {
            if (!a(z, d[i], d2)) {
                return false;
            }
        }
        return true;
    }

    private boolean a(boolean z, RDN rdn, RDN[] rdnArr) {
        int length;
        if (z) {
            length = rdnArr.length - 1;
            while (length >= 0) {
                if (rdnArr[length] == null || !a(rdn, rdnArr[length])) {
                    length--;
                } else {
                    rdnArr[length] = null;
                    return true;
                }
            }
            return false;
        }
        length = 0;
        while (length != rdnArr.length) {
            if (rdnArr[length] == null || !a(rdn, rdnArr[length])) {
                length++;
            } else {
                rdnArr[length] = null;
                return true;
            }
        }
        return false;
    }

    protected boolean a(RDN rdn, RDN rdn2) {
        return IETFUtils.a(rdn, rdn2);
    }

    public RDN[] b(String str) {
        return IETFUtils.a(str, (X500NameStyle) this);
    }

    public int a(X500Name x500Name) {
        RDN[] d = x500Name.d();
        int i = 0;
        for (int i2 = 0; i2 != d.length; i2++) {
            if (d[i2].d()) {
                AttributeTypeAndValue[] f = d[i2].f();
                int i3 = i;
                for (i = 0; i != f.length; i++) {
                    i3 = (i3 ^ f[i].d().hashCode()) ^ a(f[i].e());
                }
                i = i3;
            } else {
                i = (i ^ d[i2].e().d().hashCode()) ^ a(d[i2].e().e());
            }
        }
        return i;
    }

    private int a(ASN1Encodable aSN1Encodable) {
        return IETFUtils.a(IETFUtils.a(aSN1Encodable)).hashCode();
    }

    public String b(X500Name x500Name) {
        StringBuffer stringBuffer = new StringBuffer();
        RDN[] d = x500Name.d();
        Object obj = 1;
        for (RDN a : d) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuffer.append(',');
            }
            IETFUtils.a(stringBuffer, a, this.M);
        }
        return stringBuffer.toString();
    }

    private static Hashtable a(Hashtable hashtable) {
        Hashtable hashtable2 = new Hashtable();
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            hashtable2.put(nextElement, hashtable.get(nextElement));
        }
        return hashtable2;
    }
}
