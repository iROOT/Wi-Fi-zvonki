package org.spongycastle.jce.provider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertSelector;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.x509.CertificatePair;
import org.spongycastle.jce.X509LDAPCertStoreParameters;

public class X509LDAPCertStoreSpi extends CertStoreSpi {
    private static String b = "com.sun.jndi.ldap.LdapCtxFactory";
    private static String c = "ignore";
    private X509LDAPCertStoreParameters a;

    private DirContext a() {
        Hashtable properties = new Properties();
        properties.setProperty("java.naming.factory.initial", b);
        properties.setProperty("java.naming.batchsize", "0");
        properties.setProperty("java.naming.provider.url", this.a.F());
        properties.setProperty("java.naming.factory.url.pkgs", "com.sun.jndi.url");
        properties.setProperty("java.naming.referral", c);
        properties.setProperty("java.naming.security.authentication", "none");
        return new InitialDirContext(properties);
    }

    private String a(String str, String str2) {
        String substring = str.substring(str.toLowerCase().indexOf(str2.toLowerCase()) + str2.length());
        int indexOf = substring.indexOf(44);
        if (indexOf == -1) {
            indexOf = substring.length();
        }
        while (substring.charAt(indexOf - 1) == '\\') {
            indexOf = substring.indexOf(44, indexOf + 1);
            if (indexOf == -1) {
                indexOf = substring.length();
            }
        }
        String substring2 = substring.substring(0, indexOf);
        substring2 = substring2.substring(substring2.indexOf(61) + 1);
        if (substring2.charAt(0) == ' ') {
            substring2 = substring2.substring(1);
        }
        if (substring2.startsWith("\"")) {
            substring2 = substring2.substring(1);
        }
        if (substring2.endsWith("\"")) {
            return substring2.substring(0, substring2.length() - 1);
        }
        return substring2;
    }

    public Collection engineGetCertificates(CertSelector certSelector) {
        if (certSelector instanceof X509CertSelector) {
            X509CertSelector x509CertSelector = (X509CertSelector) certSelector;
            Collection hashSet = new HashSet();
            Set<byte[]> a = a(x509CertSelector);
            a.addAll(b(x509CertSelector));
            a.addAll(c(x509CertSelector));
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509", "SC");
                for (byte[] bArr : a) {
                    if (!(bArr == null || bArr.length == 0)) {
                        List<byte[]> arrayList = new ArrayList();
                        arrayList.add(bArr);
                        try {
                            CertificatePair a2 = CertificatePair.a(new ASN1InputStream(bArr).d());
                            arrayList.clear();
                            if (a2.d() != null) {
                                arrayList.add(a2.d().b());
                            }
                            if (a2.e() != null) {
                                arrayList.add(a2.e().b());
                            }
                        } catch (IOException e) {
                        } catch (IllegalArgumentException e2) {
                        }
                        for (byte[] bArr2 : arrayList) {
                            try {
                                Certificate generateCertificate = instance.generateCertificate(new ByteArrayInputStream(bArr2));
                                if (x509CertSelector.match(generateCertificate)) {
                                    hashSet.add(generateCertificate);
                                }
                            } catch (Exception e3) {
                            }
                        }
                    }
                }
                return hashSet;
            } catch (Exception e4) {
                throw new CertStoreException("certificate cannot be constructed from LDAP result: " + e4);
            }
        }
        throw new CertStoreException("selector is not a X509CertSelector");
    }

    private Set a(X509CertSelector x509CertSelector, String[] strArr, String str, String str2) {
        Set hashSet = new HashSet();
        try {
            if (x509CertSelector.getSubjectAsBytes() == null && x509CertSelector.getSubjectAsString() == null && x509CertSelector.getCertificate() == null) {
                hashSet.addAll(a(str, "*", strArr));
            } else {
                String name;
                String str3 = null;
                if (x509CertSelector.getCertificate() != null) {
                    name = x509CertSelector.getCertificate().getSubjectX500Principal().getName("RFC1779");
                    str3 = x509CertSelector.getCertificate().getSerialNumber().toString();
                } else if (x509CertSelector.getSubjectAsBytes() != null) {
                    name = new X500Principal(x509CertSelector.getSubjectAsBytes()).getName("RFC1779");
                } else {
                    name = x509CertSelector.getSubjectAsString();
                }
                hashSet.addAll(a(str, "*" + a(name, str2) + "*", strArr));
                if (!(str3 == null || this.a.H() == null)) {
                    hashSet.addAll(a(this.a.H(), "*" + str3 + "*", strArr));
                }
            }
            return hashSet;
        } catch (IOException e) {
            throw new CertStoreException("exception processing selector: " + e);
        }
    }

    private Set a(X509CertSelector x509CertSelector) {
        return a(x509CertSelector, new String[]{this.a.I()}, this.a.G(), this.a.J());
    }

    private Set b(X509CertSelector x509CertSelector) {
        String[] strArr = new String[]{this.a.n()};
        Set a = a(x509CertSelector, strArr, this.a.B(), this.a.o());
        if (a.isEmpty()) {
            a.addAll(a(null, "*", strArr));
        }
        return a;
    }

    private Set c(X509CertSelector x509CertSelector) {
        String[] strArr = new String[]{this.a.r()};
        Set a = a(x509CertSelector, strArr, this.a.D(), this.a.s());
        if (a.isEmpty()) {
            a.addAll(a(null, "*", strArr));
        }
        return a;
    }

    public Collection engineGetCRLs(CRLSelector cRLSelector) {
        String[] strArr = new String[]{this.a.p()};
        if (cRLSelector instanceof X509CRLSelector) {
            X509CRLSelector x509CRLSelector = (X509CRLSelector) cRLSelector;
            Collection hashSet = new HashSet();
            String C = this.a.C();
            Set<byte[]> hashSet2 = new HashSet();
            if (x509CRLSelector.getIssuerNames() != null) {
                for (Object next : x509CRLSelector.getIssuerNames()) {
                    String a;
                    if (next instanceof String) {
                        a = a((String) next, this.a.q());
                    } else {
                        a = a(new X500Principal((byte[]) next).getName("RFC1779"), this.a.q());
                    }
                    hashSet2.addAll(a(C, "*" + a + "*", strArr));
                }
            } else {
                hashSet2.addAll(a(C, "*", strArr));
            }
            hashSet2.addAll(a(null, "*", strArr));
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509", "SC");
                for (byte[] byteArrayInputStream : hashSet2) {
                    CRL generateCRL = instance.generateCRL(new ByteArrayInputStream(byteArrayInputStream));
                    if (x509CRLSelector.match(generateCRL)) {
                        hashSet.add(generateCRL);
                    }
                }
                return hashSet;
            } catch (Exception e) {
                throw new CertStoreException("CRL cannot be constructed from LDAP result " + e);
            }
        }
        throw new CertStoreException("selector is not a X509CRLSelector");
    }

    private Set a(String str, String str2, String[] strArr) {
        Object e;
        DirContext dirContext;
        Throwable th;
        String str3 = str + "=" + str2;
        if (str == null) {
            str3 = null;
        }
        Set hashSet = new HashSet();
        DirContext a;
        try {
            a = a();
            try {
                SearchControls searchControls = new SearchControls();
                searchControls.setSearchScope(2);
                searchControls.setCountLimit(0);
                for (int i = 0; i < strArr.length; i++) {
                    String[] strArr2 = new String[]{strArr[i]};
                    searchControls.setReturningAttributes(strArr2);
                    String str4 = "(&(" + str3 + ")(" + strArr2[0] + "=*))";
                    if (str3 == null) {
                        str4 = "(" + strArr2[0] + "=*)";
                    }
                    NamingEnumeration search = a.search(this.a.m(), str4, searchControls);
                    while (search.hasMoreElements()) {
                        NamingEnumeration all = ((Attribute) ((SearchResult) search.next()).getAttributes().getAll().next()).getAll();
                        while (all.hasMore()) {
                            hashSet.add(all.next());
                        }
                    }
                }
                if (a != null) {
                    try {
                        a.close();
                    } catch (Exception e2) {
                    }
                }
                return hashSet;
            } catch (Exception e3) {
                e = e3;
                dirContext = a;
                try {
                    throw new CertStoreException("Error getting results from LDAP directory " + e);
                } catch (Throwable th2) {
                    th = th2;
                    a = dirContext;
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (a != null) {
                    a.close();
                }
                throw th;
            }
        } catch (Exception e5) {
            Exception exception = e5;
            dirContext = null;
            Exception e6 = exception;
            throw new CertStoreException("Error getting results from LDAP directory " + e6);
        } catch (Throwable th4) {
            a = null;
            th = th4;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }
}
