package org.spongycastle.asn1.x500.style;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.x500.AttributeTypeAndValue;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.X500NameStyle;

public class RFC4519Style implements X500NameStyle {
    public static final ASN1ObjectIdentifier A = new ASN1ObjectIdentifier("2.5.4.26");
    public static final ASN1ObjectIdentifier B = new ASN1ObjectIdentifier("2.5.4.33");
    public static final ASN1ObjectIdentifier C = new ASN1ObjectIdentifier("2.5.4.14");
    public static final ASN1ObjectIdentifier D = new ASN1ObjectIdentifier("2.5.4.34");
    public static final ASN1ObjectIdentifier E = new ASN1ObjectIdentifier("2.5.4.5");
    public static final ASN1ObjectIdentifier F = new ASN1ObjectIdentifier("2.5.4.4");
    public static final ASN1ObjectIdentifier G = new ASN1ObjectIdentifier("2.5.4.8");
    public static final ASN1ObjectIdentifier H = new ASN1ObjectIdentifier("2.5.4.9");
    public static final ASN1ObjectIdentifier I = new ASN1ObjectIdentifier("2.5.4.20");
    public static final ASN1ObjectIdentifier J = new ASN1ObjectIdentifier("2.5.4.22");
    public static final ASN1ObjectIdentifier K = new ASN1ObjectIdentifier("2.5.4.21");
    public static final ASN1ObjectIdentifier L = new ASN1ObjectIdentifier("2.5.4.12");
    public static final ASN1ObjectIdentifier M = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
    public static final ASN1ObjectIdentifier N = new ASN1ObjectIdentifier("2.5.4.50");
    public static final ASN1ObjectIdentifier O = new ASN1ObjectIdentifier("2.5.4.35");
    public static final ASN1ObjectIdentifier P = new ASN1ObjectIdentifier("2.5.4.24");
    public static final ASN1ObjectIdentifier Q = new ASN1ObjectIdentifier("2.5.4.45");
    public static final X500NameStyle R = new RFC4519Style();
    private static final Hashtable U = new Hashtable();
    private static final Hashtable V = new Hashtable();
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier("2.5.4.15");
    public static final ASN1ObjectIdentifier b = new ASN1ObjectIdentifier("2.5.4.6");
    public static final ASN1ObjectIdentifier c = new ASN1ObjectIdentifier("2.5.4.3");
    public static final ASN1ObjectIdentifier d = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
    public static final ASN1ObjectIdentifier e = new ASN1ObjectIdentifier("2.5.4.13");
    public static final ASN1ObjectIdentifier f = new ASN1ObjectIdentifier("2.5.4.27");
    public static final ASN1ObjectIdentifier g = new ASN1ObjectIdentifier("2.5.4.49");
    public static final ASN1ObjectIdentifier h = new ASN1ObjectIdentifier("2.5.4.46");
    public static final ASN1ObjectIdentifier i = new ASN1ObjectIdentifier("2.5.4.47");
    public static final ASN1ObjectIdentifier j = new ASN1ObjectIdentifier("2.5.4.23");
    public static final ASN1ObjectIdentifier k = new ASN1ObjectIdentifier("2.5.4.44");
    public static final ASN1ObjectIdentifier l = new ASN1ObjectIdentifier("2.5.4.42");
    public static final ASN1ObjectIdentifier m = new ASN1ObjectIdentifier("2.5.4.51");
    public static final ASN1ObjectIdentifier n = new ASN1ObjectIdentifier("2.5.4.43");
    public static final ASN1ObjectIdentifier o = new ASN1ObjectIdentifier("2.5.4.25");
    public static final ASN1ObjectIdentifier p = new ASN1ObjectIdentifier("2.5.4.7");
    public static final ASN1ObjectIdentifier q = new ASN1ObjectIdentifier("2.5.4.31");
    public static final ASN1ObjectIdentifier r = new ASN1ObjectIdentifier("2.5.4.41");
    public static final ASN1ObjectIdentifier s = new ASN1ObjectIdentifier("2.5.4.10");
    public static final ASN1ObjectIdentifier t = new ASN1ObjectIdentifier("2.5.4.11");
    public static final ASN1ObjectIdentifier u = new ASN1ObjectIdentifier("2.5.4.32");
    public static final ASN1ObjectIdentifier v = new ASN1ObjectIdentifier("2.5.4.19");
    public static final ASN1ObjectIdentifier w = new ASN1ObjectIdentifier("2.5.4.16");
    public static final ASN1ObjectIdentifier x = new ASN1ObjectIdentifier("2.5.4.17");
    public static final ASN1ObjectIdentifier y = new ASN1ObjectIdentifier("2.5.4.18");
    public static final ASN1ObjectIdentifier z = new ASN1ObjectIdentifier("2.5.4.28");
    protected final Hashtable S = a(V);
    protected final Hashtable T = a(U);

    static {
        U.put(a, "businessCategory");
        U.put(b, "c");
        U.put(c, "cn");
        U.put(d, "dc");
        U.put(e, "description");
        U.put(f, "destinationIndicator");
        U.put(g, "distinguishedName");
        U.put(h, "dnQualifier");
        U.put(i, "enhancedSearchGuide");
        U.put(j, "facsimileTelephoneNumber");
        U.put(k, "generationQualifier");
        U.put(l, "givenName");
        U.put(m, "houseIdentifier");
        U.put(n, "initials");
        U.put(o, "internationalISDNNumber");
        U.put(p, "l");
        U.put(q, "member");
        U.put(r, "name");
        U.put(s, "o");
        U.put(t, "ou");
        U.put(u, "owner");
        U.put(v, "physicalDeliveryOfficeName");
        U.put(w, "postalAddress");
        U.put(x, "postalCode");
        U.put(y, "postOfficeBox");
        U.put(z, "preferredDeliveryMethod");
        U.put(A, "registeredAddress");
        U.put(B, "roleOccupant");
        U.put(C, "searchGuide");
        U.put(D, "seeAlso");
        U.put(E, "serialNumber");
        U.put(F, "sn");
        U.put(G, "st");
        U.put(H, "street");
        U.put(I, "telephoneNumber");
        U.put(J, "teletexTerminalIdentifier");
        U.put(K, "telexNumber");
        U.put(L, "title");
        U.put(M, "uid");
        U.put(N, "uniqueMember");
        U.put(O, "userPassword");
        U.put(P, "x121Address");
        U.put(Q, "x500UniqueIdentifier");
        V.put("businesscategory", a);
        V.put("c", b);
        V.put("cn", c);
        V.put("dc", d);
        V.put("description", e);
        V.put("destinationindicator", f);
        V.put("distinguishedname", g);
        V.put("dnqualifier", h);
        V.put("enhancedsearchguide", i);
        V.put("facsimiletelephonenumber", j);
        V.put("generationqualifier", k);
        V.put("givenname", l);
        V.put("houseidentifier", m);
        V.put("initials", n);
        V.put("internationalisdnnumber", o);
        V.put("l", p);
        V.put("member", q);
        V.put("name", r);
        V.put("o", s);
        V.put("ou", t);
        V.put("owner", u);
        V.put("physicaldeliveryofficename", v);
        V.put("postaladdress", w);
        V.put("postalcode", x);
        V.put("postofficebox", y);
        V.put("preferreddeliverymethod", z);
        V.put("registeredaddress", A);
        V.put("roleoccupant", B);
        V.put("searchguide", C);
        V.put("seealso", D);
        V.put("serialnumber", E);
        V.put("sn", F);
        V.put("st", G);
        V.put("street", H);
        V.put("telephonenumber", I);
        V.put("teletexterminalidentifier", J);
        V.put("telexnumber", K);
        V.put("title", L);
        V.put("uid", M);
        V.put("uniquemember", N);
        V.put("userpassword", O);
        V.put("x121address", P);
        V.put("x500uniqueidentifier", Q);
    }

    protected RFC4519Style() {
    }

    public ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (str.length() == 0 || str.charAt(0) != '#') {
            if (str.length() != 0 && str.charAt(0) == '\\') {
                str = str.substring(1);
            }
            if (aSN1ObjectIdentifier.equals(d)) {
                return new DERIA5String(str);
            }
            if (aSN1ObjectIdentifier.equals(b) || aSN1ObjectIdentifier.equals(E) || aSN1ObjectIdentifier.equals(h) || aSN1ObjectIdentifier.equals(I)) {
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
        return IETFUtils.a(str, this.S);
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
        RDN[] a = IETFUtils.a(str, (X500NameStyle) this);
        RDN[] rdnArr = new RDN[a.length];
        for (int i = 0; i != a.length; i++) {
            rdnArr[(rdnArr.length - i) - 1] = a[i];
        }
        return rdnArr;
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
        for (int length = d.length - 1; length >= 0; length--) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuffer.append(',');
            }
            IETFUtils.a(stringBuffer, d[length], this.T);
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
