package com.mavenir.android.security;

import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.util.Date;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.style.IETFUtils;
import org.spongycastle.jce.PrincipalUtil;

public class a {

    public static class a {
        public static int a(X509Certificate x509Certificate) {
            return x509Certificate.getVersion();
        }

        public static BigInteger b(X509Certificate x509Certificate) {
            return x509Certificate.getSerialNumber();
        }

        public static Date c(X509Certificate x509Certificate) {
            return x509Certificate.getNotBefore();
        }

        public static Date d(X509Certificate x509Certificate) {
            return x509Certificate.getNotAfter();
        }
    }

    public static class b {
        public static String a(X509Certificate x509Certificate, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
            RDN[] a = X500Name.a(PrincipalUtil.b(x509Certificate)).a(aSN1ObjectIdentifier);
            String str = "Unknown";
            if (a.length > 0) {
                return IETFUtils.a(a[0].e().e());
            }
            return str;
        }

        public static String b(X509Certificate x509Certificate, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
            RDN[] a = X500Name.a(PrincipalUtil.a(x509Certificate)).a(aSN1ObjectIdentifier);
            String str = "Unknown";
            if (a.length > 0) {
                return IETFUtils.a(a[0].e().e());
            }
            return str;
        }
    }
}
