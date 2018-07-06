package org.spongycastle.jce;

import java.security.cert.CertStoreParameters;
import org.spongycastle.x509.X509StoreParameters;

public class X509LDAPCertStoreParameters implements CertStoreParameters, X509StoreParameters {
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public static class Builder {
        private String A;
        private String B;
        private String C;
        private String D;
        private String E;
        private String F;
        private String G;
        private String H;
        private String I;
        private String J;
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String n;
        private String o;
        private String p;
        private String q;
        private String r;
        private String s;
        private String t;
        private String u;
        private String v;
        private String w;
        private String x;
        private String y;
        private String z;

        public Builder() {
            this("ldap://localhost:389", "");
        }

        public Builder(String str, String str2) {
            this.a = str;
            if (str2 == null) {
                this.b = "";
            } else {
                this.b = str2;
            }
            this.c = "userCertificate";
            this.d = "cACertificate";
            this.e = "crossCertificatePair";
            this.f = "certificateRevocationList";
            this.g = "deltaRevocationList";
            this.h = "authorityRevocationList";
            this.i = "attributeCertificateAttribute";
            this.j = "aACertificate";
            this.k = "attributeDescriptorCertificate";
            this.l = "attributeCertificateRevocationList";
            this.m = "attributeAuthorityRevocationList";
            this.n = "cn";
            this.o = "cn ou o";
            this.p = "cn ou o";
            this.q = "cn ou o";
            this.r = "cn ou o";
            this.s = "cn ou o";
            this.t = "cn";
            this.u = "cn o ou";
            this.v = "cn o ou";
            this.w = "cn o ou";
            this.x = "cn o ou";
            this.y = "cn";
            this.z = "o ou";
            this.A = "o ou";
            this.B = "o ou";
            this.C = "o ou";
            this.D = "o ou";
            this.E = "cn";
            this.F = "o ou";
            this.G = "o ou";
            this.H = "o ou";
            this.I = "o ou";
            this.J = "uid serialNumber cn";
        }

        public X509LDAPCertStoreParameters a() {
            if (this.n != null && this.o != null && this.p != null && this.q != null && this.r != null && this.s != null && this.t != null && this.u != null && this.v != null && this.w != null && this.x != null && this.y != null && this.z != null && this.A != null && this.B != null && this.C != null && this.D != null && this.E != null && this.F != null && this.G != null && this.H != null && this.I != null) {
                return new X509LDAPCertStoreParameters();
            }
            throw new IllegalArgumentException("Necessary parameters not specified.");
        }
    }

    private X509LDAPCertStoreParameters(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
        this.l = builder.l;
        this.m = builder.m;
        this.n = builder.n;
        this.o = builder.o;
        this.p = builder.p;
        this.q = builder.q;
        this.r = builder.r;
        this.s = builder.s;
        this.t = builder.t;
        this.u = builder.u;
        this.v = builder.v;
        this.w = builder.w;
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
        this.A = builder.A;
        this.B = builder.B;
        this.C = builder.C;
        this.D = builder.D;
        this.E = builder.E;
        this.F = builder.F;
        this.G = builder.G;
        this.H = builder.H;
        this.I = builder.I;
        this.J = builder.J;
    }

    public Object clone() {
        return this;
    }

    public int hashCode() {
        return a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(a(0, this.c), this.d), this.e), this.f), this.g), this.h), this.i), this.j), this.k), this.l), this.m), this.n), this.o), this.p), this.q), this.r), this.s), this.t), this.u), this.v), this.w), this.x), this.y), this.z), this.A), this.B), this.C), this.D), this.E), this.F), this.G), this.H), this.I), this.J);
    }

    private int a(int i, Object obj) {
        return (obj == null ? 0 : obj.hashCode()) + (i * 29);
    }

    public String a() {
        return this.j;
    }

    public String b() {
        return this.F;
    }

    public String c() {
        return this.m;
    }

    public String d() {
        return this.I;
    }

    public String e() {
        return this.i;
    }

    public String f() {
        return this.E;
    }

    public String g() {
        return this.l;
    }

    public String h() {
        return this.H;
    }

    public String i() {
        return this.k;
    }

    public String j() {
        return this.G;
    }

    public String k() {
        return this.h;
    }

    public String l() {
        return this.D;
    }

    public String m() {
        return this.b;
    }

    public String n() {
        return this.d;
    }

    public String o() {
        return this.z;
    }

    public String p() {
        return this.f;
    }

    public String q() {
        return this.B;
    }

    public String r() {
        return this.e;
    }

    public String s() {
        return this.A;
    }

    public String t() {
        return this.g;
    }

    public String u() {
        return this.C;
    }

    public String v() {
        return this.u;
    }

    public String w() {
        return this.x;
    }

    public String x() {
        return this.t;
    }

    public String y() {
        return this.w;
    }

    public String z() {
        return this.v;
    }

    public String A() {
        return this.s;
    }

    public String B() {
        return this.o;
    }

    public String C() {
        return this.q;
    }

    public String D() {
        return this.p;
    }

    public String E() {
        return this.r;
    }

    public String F() {
        return this.a;
    }

    public String G() {
        return this.n;
    }

    public String H() {
        return this.J;
    }

    public String I() {
        return this.c;
    }

    public String J() {
        return this.y;
    }
}
