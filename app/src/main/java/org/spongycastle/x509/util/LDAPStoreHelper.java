package org.spongycastle.x509.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.asn1.x509.CertificatePair;
import org.spongycastle.jce.X509LDAPCertStoreParameters;
import org.spongycastle.jce.provider.X509AttrCertParser;
import org.spongycastle.jce.provider.X509CRLParser;
import org.spongycastle.jce.provider.X509CertPairParser;
import org.spongycastle.jce.provider.X509CertParser;
import org.spongycastle.util.StoreException;
import org.spongycastle.x509.X509AttributeCertStoreSelector;
import org.spongycastle.x509.X509AttributeCertificate;
import org.spongycastle.x509.X509CRLStoreSelector;
import org.spongycastle.x509.X509CertPairStoreSelector;
import org.spongycastle.x509.X509CertStoreSelector;
import org.spongycastle.x509.X509CertificatePair;

public class LDAPStoreHelper {
    private static String b = "com.sun.jndi.ldap.LdapCtxFactory";
    private static String c = "ignore";
    private static int e = 32;
    private static long f = 60000;
    private X509LDAPCertStoreParameters a;
    private Map d = new HashMap(e);

    public LDAPStoreHelper(X509LDAPCertStoreParameters x509LDAPCertStoreParameters) {
        this.a = x509LDAPCertStoreParameters;
    }

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
        int indexOf = str.toLowerCase().indexOf(str2.toLowerCase() + "=");
        if (indexOf == -1) {
            return "";
        }
        String substring = str.substring(indexOf + str2.length());
        indexOf = substring.indexOf(44);
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

    private Set a(List list, X509CertStoreSelector x509CertStoreSelector) {
        Set hashSet = new HashSet();
        X509CertParser x509CertParser = new X509CertParser();
        for (byte[] byteArrayInputStream : list) {
            try {
                x509CertParser.a(new ByteArrayInputStream(byteArrayInputStream));
                Object obj = (X509Certificate) x509CertParser.a();
                if (x509CertStoreSelector.a(obj)) {
                    hashSet.add(obj);
                }
            } catch (Exception e) {
            }
        }
        return hashSet;
    }

    private List a(X509CertStoreSelector x509CertStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) {
        String name;
        List arrayList = new ArrayList();
        String str = null;
        String c = c(x509CertStoreSelector);
        if (x509CertStoreSelector.getSerialNumber() != null) {
            str = x509CertStoreSelector.getSerialNumber().toString();
        }
        if (x509CertStoreSelector.getCertificate() != null) {
            name = x509CertStoreSelector.getCertificate().getSubjectX500Principal().getName("RFC1779");
            c = x509CertStoreSelector.getCertificate().getSerialNumber().toString();
        } else {
            name = c;
            c = str;
        }
        if (name != null) {
            for (String a : strArr3) {
                arrayList.addAll(a(strArr2, "*" + a(name, a) + "*", strArr));
            }
        }
        if (!(c == null || this.a.H() == null)) {
            arrayList.addAll(a(b(this.a.H()), c, strArr));
        }
        if (c == null && name == null) {
            arrayList.addAll(a(strArr2, "*", strArr));
        }
        return arrayList;
    }

    private List a(X509CertPairStoreSelector x509CertPairStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) {
        String str;
        List arrayList = new ArrayList();
        String str2 = null;
        if (x509CertPairStoreSelector.b() != null) {
            str2 = c(x509CertPairStoreSelector.b());
        }
        if (x509CertPairStoreSelector.a() == null || x509CertPairStoreSelector.a().a() == null) {
            str = str2;
        } else {
            str = x509CertPairStoreSelector.a().a().getSubjectX500Principal().getName("RFC1779");
        }
        if (str != null) {
            for (String a : strArr3) {
                arrayList.addAll(a(strArr2, "*" + a(str, a) + "*", strArr));
            }
        }
        if (str == null) {
            arrayList.addAll(a(strArr2, "*", strArr));
        }
        return arrayList;
    }

    private List a(X509AttributeCertStoreSelector x509AttributeCertStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) {
        Principal[] d;
        String str = null;
        List arrayList = new ArrayList();
        Collection<String> hashSet = new HashSet();
        if (x509AttributeCertStoreSelector.c() != null) {
            if (x509AttributeCertStoreSelector.c().f() != null) {
                hashSet.add(x509AttributeCertStoreSelector.c().f().toString());
            }
            if (x509AttributeCertStoreSelector.c().d() != null) {
                d = x509AttributeCertStoreSelector.c().d();
                if (x509AttributeCertStoreSelector.a() != null) {
                    if (x509AttributeCertStoreSelector.a().c().d() != null) {
                        d = x509AttributeCertStoreSelector.a().c().d();
                    }
                    hashSet.add(x509AttributeCertStoreSelector.a().a().toString());
                }
                if (d != null) {
                    if (d[0] instanceof X500Principal) {
                        str = d[0].getName();
                    } else {
                        str = ((X500Principal) d[0]).getName("RFC1779");
                    }
                }
                if (x509AttributeCertStoreSelector.d() != null) {
                    hashSet.add(x509AttributeCertStoreSelector.d().toString());
                }
                if (str != null) {
                    for (String a : strArr3) {
                        arrayList.addAll(a(strArr2, "*" + a(str, a) + "*", strArr));
                    }
                }
                if (hashSet.size() > 0 && this.a.H() != null) {
                    for (String a2 : hashSet) {
                        arrayList.addAll(a(b(this.a.H()), a2, strArr));
                    }
                }
                if (hashSet.size() == 0 && str == null) {
                    arrayList.addAll(a(strArr2, "*", strArr));
                }
                return arrayList;
            }
        }
        d = null;
        if (x509AttributeCertStoreSelector.a() != null) {
            if (x509AttributeCertStoreSelector.a().c().d() != null) {
                d = x509AttributeCertStoreSelector.a().c().d();
            }
            hashSet.add(x509AttributeCertStoreSelector.a().a().toString());
        }
        if (d != null) {
            if (d[0] instanceof X500Principal) {
                str = d[0].getName();
            } else {
                str = ((X500Principal) d[0]).getName("RFC1779");
            }
        }
        if (x509AttributeCertStoreSelector.d() != null) {
            hashSet.add(x509AttributeCertStoreSelector.d().toString());
        }
        if (str != null) {
            while (r0 < strArr3.length) {
                arrayList.addAll(a(strArr2, "*" + a(str, a) + "*", strArr));
            }
        }
        while (r2.hasNext()) {
            arrayList.addAll(a(b(this.a.H()), a2, strArr));
        }
        arrayList.addAll(a(strArr2, "*", strArr));
        return arrayList;
    }

    private List a(X509CRLStoreSelector x509CRLStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) {
        int i;
        List arrayList = new ArrayList();
        Collection<X500Principal> hashSet = new HashSet();
        if (x509CRLStoreSelector.getIssuers() != null) {
            hashSet.addAll(x509CRLStoreSelector.getIssuers());
        }
        if (x509CRLStoreSelector.getCertificateChecking() != null) {
            hashSet.add(a(x509CRLStoreSelector.getCertificateChecking()));
        }
        if (x509CRLStoreSelector.a() != null) {
            Principal[] a = x509CRLStoreSelector.a().d().a();
            for (i = 0; i < a.length; i++) {
                if (a[i] instanceof X500Principal) {
                    hashSet.add(a[i]);
                }
            }
        }
        String str = null;
        for (X500Principal name : hashSet) {
            String name2 = name.getName("RFC1779");
            for (String a2 : strArr3) {
                arrayList.addAll(a(strArr2, "*" + a(name2, a2) + "*", strArr));
            }
            str = name2;
        }
        if (str == null) {
            arrayList.addAll(a(strArr2, "*", strArr));
        }
        return arrayList;
    }

    private List a(String[] strArr, String str, String[] strArr2) {
        String str2;
        String str3;
        DirContext dirContext = null;
        int i = 0;
        if (strArr == null) {
            str2 = null;
        } else {
            str2 = "";
            if (str.equals("**")) {
                str = "*";
            }
            str3 = str2;
            for (String str4 : strArr) {
                str3 = str3 + "(" + str4 + "=" + str + ")";
            }
            str2 = "(|" + str3 + ")";
        }
        str3 = "";
        while (i < strArr2.length) {
            str3 = str3 + "(" + strArr2[i] + "=*)";
            i++;
        }
        String str5 = "(|" + str3 + ")";
        str3 = "(&" + str2 + "" + str5 + ")";
        if (str2 != null) {
            str5 = str3;
        }
        List a = a(str5);
        if (a != null) {
            return a;
        }
        List arrayList = new ArrayList();
        try {
            dirContext = a();
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(2);
            searchControls.setCountLimit(0);
            searchControls.setReturningAttributes(strArr2);
            NamingEnumeration search = dirContext.search(this.a.m(), str5, searchControls);
            while (search.hasMoreElements()) {
                NamingEnumeration all = ((Attribute) ((SearchResult) search.next()).getAttributes().getAll().next()).getAll();
                while (all.hasMore()) {
                    arrayList.add(all.next());
                }
            }
            a(str5, arrayList);
            if (dirContext != null) {
                try {
                    dirContext.close();
                } catch (Exception e) {
                }
            }
        } catch (NamingException e2) {
            if (dirContext != null) {
                try {
                    dirContext.close();
                } catch (Exception e3) {
                }
            }
        } catch (Throwable th) {
            if (dirContext != null) {
                try {
                    dirContext.close();
                } catch (Exception e4) {
                }
            }
        }
        return arrayList;
    }

    private Set a(List list, X509CRLStoreSelector x509CRLStoreSelector) {
        Set hashSet = new HashSet();
        X509CRLParser x509CRLParser = new X509CRLParser();
        for (byte[] byteArrayInputStream : list) {
            try {
                x509CRLParser.a(new ByteArrayInputStream(byteArrayInputStream));
                Object obj = (X509CRL) x509CRLParser.a();
                if (x509CRLStoreSelector.a(obj)) {
                    hashSet.add(obj);
                }
            } catch (StreamParsingException e) {
            }
        }
        return hashSet;
    }

    private Set a(List list, X509CertPairStoreSelector x509CertPairStoreSelector) {
        Set hashSet = new HashSet();
        int i;
        for (int i2 = 0; i2 < list.size(); i2 = i + 1) {
            try {
                X509CertPairParser x509CertPairParser = new X509CertPairParser();
                x509CertPairParser.a(new ByteArrayInputStream((byte[]) list.get(i2)));
                Object obj = (X509CertificatePair) x509CertPairParser.a();
                i = i2;
            } catch (StreamParsingException e) {
                try {
                    i = i2 + 1;
                    X509CertificatePair obj2 = new X509CertificatePair(new CertificatePair(Certificate.a(new ASN1InputStream((byte[]) list.get(i2)).d()), Certificate.a(new ASN1InputStream((byte[]) list.get(i2 + 1)).d())));
                } catch (CertificateParsingException e2) {
                    i = i2;
                } catch (IOException e3) {
                    i = i2;
                }
            }
            try {
                if (x509CertPairStoreSelector.a(obj2)) {
                    hashSet.add(obj2);
                }
            } catch (CertificateParsingException e4) {
            } catch (IOException e5) {
            }
        }
        return hashSet;
    }

    private Set a(List list, X509AttributeCertStoreSelector x509AttributeCertStoreSelector) {
        Set hashSet = new HashSet();
        X509AttrCertParser x509AttrCertParser = new X509AttrCertParser();
        for (byte[] byteArrayInputStream : list) {
            try {
                x509AttrCertParser.a(new ByteArrayInputStream(byteArrayInputStream));
                X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate) x509AttrCertParser.a();
                if (x509AttributeCertStoreSelector.a(x509AttributeCertificate)) {
                    hashSet.add(x509AttributeCertificate);
                }
            } catch (StreamParsingException e) {
            }
        }
        return hashSet;
    }

    public Collection a(X509CRLStoreSelector x509CRLStoreSelector) {
        String[] b = b(this.a.k());
        String[] b2 = b(this.a.A());
        String[] b3 = b(this.a.l());
        Collection a = a(a(x509CRLStoreSelector, b, b2, b3), x509CRLStoreSelector);
        if (a.size() == 0) {
            a.addAll(a(a(new X509CRLStoreSelector(), b, b2, b3), x509CRLStoreSelector));
        }
        return a;
    }

    public Collection b(X509CRLStoreSelector x509CRLStoreSelector) {
        String[] b = b(this.a.g());
        String[] b2 = b(this.a.y());
        String[] b3 = b(this.a.h());
        Collection a = a(a(x509CRLStoreSelector, b, b2, b3), x509CRLStoreSelector);
        if (a.size() == 0) {
            a.addAll(a(a(new X509CRLStoreSelector(), b, b2, b3), x509CRLStoreSelector));
        }
        return a;
    }

    public Collection c(X509CRLStoreSelector x509CRLStoreSelector) {
        String[] b = b(this.a.c());
        String[] b2 = b(this.a.w());
        String[] b3 = b(this.a.d());
        Collection a = a(a(x509CRLStoreSelector, b, b2, b3), x509CRLStoreSelector);
        if (a.size() == 0) {
            a.addAll(a(a(new X509CRLStoreSelector(), b, b2, b3), x509CRLStoreSelector));
        }
        return a;
    }

    public Collection a(X509CertPairStoreSelector x509CertPairStoreSelector) {
        String[] b = b(this.a.r());
        String[] b2 = b(this.a.D());
        String[] b3 = b(this.a.s());
        Collection a = a(a(x509CertPairStoreSelector, b, b2, b3), x509CertPairStoreSelector);
        if (a.size() == 0) {
            X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
            X509CertPairStoreSelector x509CertPairStoreSelector2 = new X509CertPairStoreSelector();
            x509CertPairStoreSelector2.a(x509CertStoreSelector);
            x509CertPairStoreSelector2.b(x509CertStoreSelector);
            a.addAll(a(a(x509CertPairStoreSelector2, b, b2, b3), x509CertPairStoreSelector));
        }
        return a;
    }

    public Collection a(X509CertStoreSelector x509CertStoreSelector) {
        String[] b = b(this.a.I());
        String[] b2 = b(this.a.G());
        String[] b3 = b(this.a.J());
        Collection a = a(a(x509CertStoreSelector, b, b2, b3), x509CertStoreSelector);
        if (a.size() == 0) {
            a.addAll(a(a(new X509CertStoreSelector(), b, b2, b3), x509CertStoreSelector));
        }
        return a;
    }

    public Collection a(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) {
        String[] b = b(this.a.a());
        String[] b2 = b(this.a.v());
        String[] b3 = b(this.a.b());
        Collection a = a(a(x509AttributeCertStoreSelector, b, b2, b3), x509AttributeCertStoreSelector);
        if (a.size() == 0) {
            a.addAll(a(a(new X509AttributeCertStoreSelector(), b, b2, b3), x509AttributeCertStoreSelector));
        }
        return a;
    }

    public Collection b(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) {
        String[] b = b(this.a.i());
        String[] b2 = b(this.a.z());
        String[] b3 = b(this.a.j());
        Collection a = a(a(x509AttributeCertStoreSelector, b, b2, b3), x509AttributeCertStoreSelector);
        if (a.size() == 0) {
            a.addAll(a(a(new X509AttributeCertStoreSelector(), b, b2, b3), x509AttributeCertStoreSelector));
        }
        return a;
    }

    public Collection b(X509CertStoreSelector x509CertStoreSelector) {
        String[] b = b(this.a.n());
        String[] b2 = b(this.a.B());
        String[] b3 = b(this.a.o());
        Collection a = a(a(x509CertStoreSelector, b, b2, b3), x509CertStoreSelector);
        if (a.size() == 0) {
            a.addAll(a(a(new X509CertStoreSelector(), b, b2, b3), x509CertStoreSelector));
        }
        return a;
    }

    public Collection d(X509CRLStoreSelector x509CRLStoreSelector) {
        String[] b = b(this.a.t());
        String[] b2 = b(this.a.E());
        String[] b3 = b(this.a.u());
        Collection a = a(a(x509CRLStoreSelector, b, b2, b3), x509CRLStoreSelector);
        if (a.size() == 0) {
            a.addAll(a(a(new X509CRLStoreSelector(), b, b2, b3), x509CRLStoreSelector));
        }
        return a;
    }

    public Collection c(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) {
        String[] b = b(this.a.e());
        String[] b2 = b(this.a.x());
        String[] b3 = b(this.a.f());
        Collection a = a(a(x509AttributeCertStoreSelector, b, b2, b3), x509AttributeCertStoreSelector);
        if (a.size() == 0) {
            a.addAll(a(a(new X509AttributeCertStoreSelector(), b, b2, b3), x509AttributeCertStoreSelector));
        }
        return a;
    }

    public Collection e(X509CRLStoreSelector x509CRLStoreSelector) {
        String[] b = b(this.a.p());
        String[] b2 = b(this.a.C());
        String[] b3 = b(this.a.q());
        Collection a = a(a(x509CRLStoreSelector, b, b2, b3), x509CRLStoreSelector);
        if (a.size() == 0) {
            a.addAll(a(a(new X509CRLStoreSelector(), b, b2, b3), x509CRLStoreSelector));
        }
        return a;
    }

    private synchronized void a(String str, List list) {
        Date date = new Date(System.currentTimeMillis());
        List arrayList = new ArrayList();
        arrayList.add(date);
        arrayList.add(list);
        if (this.d.containsKey(str)) {
            this.d.put(str, arrayList);
        } else {
            if (this.d.size() >= e) {
                long time = date.getTime();
                Object obj = null;
                for (Entry entry : this.d.entrySet()) {
                    Object key;
                    long j;
                    long time2 = ((Date) ((List) entry.getValue()).get(0)).getTime();
                    if (time2 < time) {
                        key = entry.getKey();
                        j = time2;
                    } else {
                        key = obj;
                        j = time;
                    }
                    time = j;
                    obj = key;
                }
                this.d.remove(obj);
            }
            this.d.put(str, arrayList);
        }
    }

    private List a(String str) {
        List list = (List) this.d.get(str);
        long currentTimeMillis = System.currentTimeMillis();
        if (list == null) {
            return null;
        }
        if (((Date) list.get(0)).getTime() < currentTimeMillis - f) {
            return null;
        }
        return (List) list.get(1);
    }

    private String[] b(String str) {
        return str.split("\\s+");
    }

    private String c(X509CertStoreSelector x509CertStoreSelector) {
        try {
            byte[] subjectAsBytes = x509CertStoreSelector.getSubjectAsBytes();
            if (subjectAsBytes != null) {
                return new X500Principal(subjectAsBytes).getName("RFC1779");
            }
            return null;
        } catch (Throwable e) {
            throw new StoreException("exception processing name: " + e.getMessage(), e);
        }
    }

    private X500Principal a(X509Certificate x509Certificate) {
        return x509Certificate.getIssuerX500Principal();
    }
}
