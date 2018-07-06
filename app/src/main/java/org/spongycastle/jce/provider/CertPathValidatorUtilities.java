package org.spongycastle.jce.provider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyQualifierInfo;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1OutputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEREnumerated;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.isismtt.ISISMTTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.DistributionPoint;
import org.spongycastle.asn1.x509.DistributionPointName;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jce.X509LDAPCertStoreParameters.Builder;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.util.Integers;
import org.spongycastle.x509.ExtendedPKIXBuilderParameters;
import org.spongycastle.x509.ExtendedPKIXParameters;
import org.spongycastle.x509.X509AttributeCertStoreSelector;
import org.spongycastle.x509.X509AttributeCertificate;
import org.spongycastle.x509.X509CRLStoreSelector;
import org.spongycastle.x509.X509CertStoreSelector;
import org.spongycastle.x509.X509Store;
import org.spongycastle.x509.X509StoreParameters;

public class CertPathValidatorUtilities {
    protected static final PKIXCRLUtil a = new PKIXCRLUtil();
    protected static final String b = Extension.q.d();
    protected static final String c = Extension.g.d();
    protected static final String d = Extension.r.d();
    protected static final String e = Extension.e.d();
    protected static final String f = Extension.o.d();
    protected static final String g = Extension.c.d();
    protected static final String h = Extension.w.d();
    protected static final String i = Extension.m.d();
    protected static final String j = Extension.l.d();
    protected static final String k = Extension.t.d();
    protected static final String l = Extension.v.d();
    protected static final String m = Extension.p.d();
    protected static final String n = Extension.s.d();
    protected static final String o = Extension.h.d();
    protected static final String[] p = new String[]{"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};

    protected static TrustAnchor a(X509Certificate x509Certificate, Set set, String str) {
        X509CertSelector x509CertSelector = new X509CertSelector();
        X500Principal a = a((Object) x509Certificate);
        try {
            x509CertSelector.setSubject(a.getEncoded());
            Iterator it = set.iterator();
            Throwable th = null;
            PublicKey publicKey = null;
            TrustAnchor trustAnchor = null;
            while (it.hasNext() && trustAnchor == null) {
                TrustAnchor trustAnchor2;
                PublicKey publicKey2;
                PublicKey publicKey3;
                trustAnchor = (TrustAnchor) it.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        trustAnchor2 = trustAnchor;
                        publicKey2 = trustAnchor.getTrustedCert().getPublicKey();
                    } else {
                        publicKey2 = publicKey;
                        trustAnchor2 = null;
                    }
                } else if (trustAnchor.getCAName() == null || trustAnchor.getCAPublicKey() == null) {
                    publicKey2 = publicKey;
                    trustAnchor2 = null;
                } else {
                    try {
                        if (a.equals(new X500Principal(trustAnchor.getCAName()))) {
                            publicKey = trustAnchor.getCAPublicKey();
                        } else {
                            trustAnchor = null;
                        }
                        publicKey3 = publicKey;
                        trustAnchor2 = trustAnchor;
                        publicKey2 = publicKey3;
                    } catch (IllegalArgumentException e) {
                        publicKey2 = publicKey;
                        trustAnchor2 = null;
                    }
                }
                if (publicKey2 != null) {
                    try {
                        a(x509Certificate, publicKey2, str);
                        publicKey3 = publicKey2;
                        trustAnchor = trustAnchor2;
                        publicKey = publicKey3;
                    } catch (Throwable e2) {
                        th = e2;
                        publicKey = null;
                        trustAnchor = null;
                    }
                } else {
                    publicKey3 = publicKey2;
                    trustAnchor = trustAnchor2;
                    publicKey = publicKey3;
                }
            }
            if (trustAnchor != null || th == null) {
                return trustAnchor;
            }
            throw new AnnotatedException("TrustAnchor found but certificate validation failed.", th);
        } catch (Throwable e22) {
            throw new AnnotatedException("Cannot set subject search criteria for trust anchor.", e22);
        }
    }

    protected static void a(X509Certificate x509Certificate, ExtendedPKIXParameters extendedPKIXParameters) {
        if (x509Certificate.getIssuerAlternativeNames() != null) {
            for (List list : x509Certificate.getIssuerAlternativeNames()) {
                if (list.get(0).equals(Integers.a(6))) {
                    a((String) list.get(1), extendedPKIXParameters);
                }
            }
        }
    }

    protected static X500Principal a(Object obj) {
        if (obj instanceof X509Certificate) {
            return ((X509Certificate) obj).getIssuerX500Principal();
        }
        return (X500Principal) ((X509AttributeCertificate) obj).d().a()[0];
    }

    protected static Date a(PKIXParameters pKIXParameters) {
        Date date = pKIXParameters.getDate();
        if (date == null) {
            return new Date();
        }
        return date;
    }

    protected static X500Principal a(X509Certificate x509Certificate) {
        return x509Certificate.getSubjectX500Principal();
    }

    protected static boolean b(X509Certificate x509Certificate) {
        return x509Certificate.getSubjectDN().equals(x509Certificate.getIssuerDN());
    }

    protected static ASN1Primitive a(X509Extension x509Extension, String str) {
        byte[] extensionValue = x509Extension.getExtensionValue(str);
        if (extensionValue == null) {
            return null;
        }
        return a(str, extensionValue);
    }

    private static ASN1Primitive a(String str, byte[] bArr) {
        try {
            return new ASN1InputStream(((ASN1OctetString) new ASN1InputStream(bArr).d()).e()).d();
        } catch (Throwable e) {
            throw new AnnotatedException("exception processing extension " + str, e);
        }
    }

    protected static X500Principal a(X509CRL x509crl) {
        return x509crl.getIssuerX500Principal();
    }

    protected static AlgorithmIdentifier a(PublicKey publicKey) {
        try {
            return SubjectPublicKeyInfo.a(new ASN1InputStream(publicKey.getEncoded()).d()).e();
        } catch (Throwable e) {
            throw new ExtCertPathValidatorException("Subject public key cannot be decoded.", e);
        }
    }

    protected static final Set a(ASN1Sequence aSN1Sequence) {
        Set hashSet = new HashSet();
        if (aSN1Sequence == null) {
            return hashSet;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ASN1OutputStream aSN1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
        Enumeration e = aSN1Sequence.e();
        while (e.hasMoreElements()) {
            try {
                aSN1OutputStream.a((ASN1Encodable) e.nextElement());
                hashSet.add(new PolicyQualifierInfo(byteArrayOutputStream.toByteArray()));
                byteArrayOutputStream.reset();
            } catch (Throwable e2) {
                throw new ExtCertPathValidatorException("Policy qualifier info cannot be decoded.", e2);
            }
        }
        return hashSet;
    }

    protected static PKIXPolicyNode a(PKIXPolicyNode pKIXPolicyNode, List[] listArr, PKIXPolicyNode pKIXPolicyNode2) {
        PKIXPolicyNode pKIXPolicyNode3 = (PKIXPolicyNode) pKIXPolicyNode2.getParent();
        if (pKIXPolicyNode == null) {
            return null;
        }
        if (pKIXPolicyNode3 == null) {
            for (int i = 0; i < listArr.length; i++) {
                listArr[i] = new ArrayList();
            }
            return null;
        }
        pKIXPolicyNode3.b(pKIXPolicyNode2);
        a(listArr, pKIXPolicyNode2);
        return pKIXPolicyNode;
    }

    private static void a(List[] listArr, PKIXPolicyNode pKIXPolicyNode) {
        listArr[pKIXPolicyNode.getDepth()].remove(pKIXPolicyNode);
        if (pKIXPolicyNode.a()) {
            Iterator children = pKIXPolicyNode.getChildren();
            while (children.hasNext()) {
                a(listArr, (PKIXPolicyNode) children.next());
            }
        }
    }

    protected static boolean a(int i, List[] listArr, DERObjectIdentifier dERObjectIdentifier, Set set) {
        List list = listArr[i - 1];
        for (int i2 = 0; i2 < list.size(); i2++) {
            PKIXPolicyNode pKIXPolicyNode = (PKIXPolicyNode) list.get(i2);
            if (pKIXPolicyNode.getExpectedPolicies().contains(dERObjectIdentifier.d())) {
                Set hashSet = new HashSet();
                hashSet.add(dERObjectIdentifier.d());
                PKIXPolicyNode pKIXPolicyNode2 = new PKIXPolicyNode(new ArrayList(), i, hashSet, pKIXPolicyNode, set, dERObjectIdentifier.d(), false);
                pKIXPolicyNode.a(pKIXPolicyNode2);
                listArr[i].add(pKIXPolicyNode2);
                return true;
            }
        }
        return false;
    }

    protected static void b(int i, List[] listArr, DERObjectIdentifier dERObjectIdentifier, Set set) {
        List list = listArr[i - 1];
        for (int i2 = 0; i2 < list.size(); i2++) {
            PKIXPolicyNode pKIXPolicyNode = (PKIXPolicyNode) list.get(i2);
            if ("2.5.29.32.0".equals(pKIXPolicyNode.getValidPolicy())) {
                Set hashSet = new HashSet();
                hashSet.add(dERObjectIdentifier.d());
                PKIXPolicyNode pKIXPolicyNode2 = new PKIXPolicyNode(new ArrayList(), i, hashSet, pKIXPolicyNode, set, dERObjectIdentifier.d(), false);
                pKIXPolicyNode.a(pKIXPolicyNode2);
                listArr[i].add(pKIXPolicyNode2);
                return;
            }
        }
    }

    protected static boolean a(Set set) {
        return set == null || set.contains("2.5.29.32.0") || set.isEmpty();
    }

    protected static void a(String str, ExtendedPKIXParameters extendedPKIXParameters) {
        if (extendedPKIXParameters.g()) {
            try {
                if (str.startsWith("ldap://")) {
                    String substring = str.substring(7);
                    String str2 = null;
                    if (substring.indexOf("/") != -1) {
                        str2 = substring.substring(substring.indexOf("/"));
                        substring = "ldap://" + substring.substring(0, substring.indexOf("/"));
                    } else {
                        substring = "ldap://" + substring;
                    }
                    X509StoreParameters a = new Builder(substring, str2).a();
                    extendedPKIXParameters.a(X509Store.a("CERTIFICATE/LDAP", a, "SC"));
                    extendedPKIXParameters.a(X509Store.a("CRL/LDAP", a, "SC"));
                    extendedPKIXParameters.a(X509Store.a("ATTRIBUTECERTIFICATE/LDAP", a, "SC"));
                    extendedPKIXParameters.a(X509Store.a("CERTIFICATEPAIR/LDAP", a, "SC"));
                }
            } catch (Exception e) {
                throw new RuntimeException("Exception adding X.509 stores.");
            }
        }
    }

    protected static Collection a(X509CertStoreSelector x509CertStoreSelector, List list) {
        Collection hashSet = new HashSet();
        for (Object next : list) {
            if (next instanceof X509Store) {
                try {
                    hashSet.addAll(((X509Store) next).a(x509CertStoreSelector));
                } catch (Throwable e) {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", e);
                }
            }
            try {
                hashSet.addAll(((CertStore) next).getCertificates(x509CertStoreSelector));
            } catch (Throwable e2) {
                throw new AnnotatedException("Problem while picking certificates from certificate store.", e2);
            }
        }
        return hashSet;
    }

    protected static Collection a(X509AttributeCertStoreSelector x509AttributeCertStoreSelector, List list) {
        Collection hashSet = new HashSet();
        for (Object next : list) {
            if (next instanceof X509Store) {
                try {
                    hashSet.addAll(((X509Store) next).a(x509AttributeCertStoreSelector));
                } catch (Throwable e) {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", e);
                }
            }
        }
        return hashSet;
    }

    protected static void a(CRLDistPoint cRLDistPoint, ExtendedPKIXParameters extendedPKIXParameters) {
        if (cRLDistPoint != null) {
            try {
                DistributionPoint[] d = cRLDistPoint.d();
                for (DistributionPoint d2 : d) {
                    DistributionPointName d3 = d2.d();
                    if (d3 != null && d3.d() == 0) {
                        GeneralName[] d4 = GeneralNames.a(d3.e()).d();
                        for (int i = 0; i < d4.length; i++) {
                            if (d4[i].d() == 6) {
                                a(DERIA5String.a(d4[i].e()).a_(), extendedPKIXParameters);
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                throw new AnnotatedException("Distribution points could not be read.", e);
            }
        }
    }

    protected static void a(DistributionPoint distributionPoint, Collection collection, X509CRLSelector x509CRLSelector, ExtendedPKIXParameters extendedPKIXParameters) {
        List<X500Principal> arrayList = new ArrayList();
        if (distributionPoint.f() != null) {
            GeneralName[] d = distributionPoint.f().d();
            for (int i = 0; i < d.length; i++) {
                if (d[i].d() == 4) {
                    try {
                        arrayList.add(new X500Principal(d[i].e().a().b()));
                    } catch (Throwable e) {
                        throw new AnnotatedException("CRL issuer information from distribution point cannot be decoded.", e);
                    }
                }
            }
        } else if (distributionPoint.d() == null) {
            throw new AnnotatedException("CRL issuer is omitted from distribution point but no distributionPoint field present.");
        } else {
            for (X500Principal add : collection) {
                arrayList.add(add);
            }
        }
        for (X500Principal add2 : arrayList) {
            try {
                x509CRLSelector.addIssuerName(add2.getEncoded());
            } catch (Throwable e2) {
                throw new AnnotatedException("Cannot decode CRL issuer information.", e2);
            }
        }
    }

    private static BigInteger b(Object obj) {
        if (obj instanceof X509Certificate) {
            return ((X509Certificate) obj).getSerialNumber();
        }
        return ((X509AttributeCertificate) obj).a();
    }

    protected static void a(Date date, X509CRL x509crl, Object obj, CertStatus certStatus) {
        try {
            X509Extension x509Extension;
            if (X509CRLObject.a(x509crl)) {
                X509CRLEntry revokedCertificate = x509crl.getRevokedCertificate(b(obj));
                if (revokedCertificate != null) {
                    Object certificateIssuer = revokedCertificate.getCertificateIssuer();
                    if (certificateIssuer == null) {
                        certificateIssuer = a(x509crl);
                    }
                    if (a(obj).equals(certificateIssuer)) {
                        x509Extension = revokedCertificate;
                    } else {
                        return;
                    }
                }
                return;
            } else if (a(obj).equals(a(x509crl))) {
                x509Extension = x509crl.getRevokedCertificate(b(obj));
                if (x509Extension == null) {
                    return;
                }
            } else {
                return;
            }
            DEREnumerated dEREnumerated = null;
            if (x509Extension.hasExtensions()) {
                try {
                    dEREnumerated = DEREnumerated.a(a(x509Extension, org.spongycastle.asn1.x509.X509Extension.i.d()));
                } catch (Throwable e) {
                    throw new AnnotatedException("Reason code CRL entry extension could not be decoded.", e);
                }
            }
            if (date.getTime() >= x509Extension.getRevocationDate().getTime() || dEREnumerated == null || dEREnumerated.d().intValue() == 0 || dEREnumerated.d().intValue() == 1 || dEREnumerated.d().intValue() == 2 || dEREnumerated.d().intValue() == 8) {
                if (dEREnumerated != null) {
                    certStatus.a(dEREnumerated.d().intValue());
                } else {
                    certStatus.a(0);
                }
                certStatus.a(x509Extension.getRevocationDate());
            }
        } catch (Throwable e2) {
            throw new AnnotatedException("Failed check for indirect CRL.", e2);
        }
    }

    protected static Set a(Date date, ExtendedPKIXParameters extendedPKIXParameters, X509CRL x509crl) {
        BigInteger bigInteger = null;
        X509CRLStoreSelector x509CRLStoreSelector = new X509CRLStoreSelector();
        try {
            x509CRLStoreSelector.addIssuerName(a(x509crl).getEncoded());
            try {
                BigInteger e;
                ASN1Primitive a = a((X509Extension) x509crl, o);
                if (a != null) {
                    e = DERInteger.a((Object) a).e();
                } else {
                    e = null;
                }
                try {
                    byte[] extensionValue = x509crl.getExtensionValue(i);
                    if (e != null) {
                        bigInteger = e.add(BigInteger.valueOf(1));
                    }
                    x509CRLStoreSelector.setMinCRLNumber(bigInteger);
                    x509CRLStoreSelector.a(extensionValue);
                    x509CRLStoreSelector.a(true);
                    x509CRLStoreSelector.a(e);
                    Set<X509CRL> a2 = a.a(x509CRLStoreSelector, extendedPKIXParameters, date);
                    Set hashSet = new HashSet();
                    for (X509CRL x509crl2 : a2) {
                        if (b(x509crl2)) {
                            hashSet.add(x509crl2);
                        }
                    }
                    return hashSet;
                } catch (Throwable e2) {
                    throw new AnnotatedException("Issuing distribution point extension value could not be read.", e2);
                }
            } catch (Throwable e22) {
                throw new AnnotatedException("CRL number extension could not be extracted from CRL.", e22);
            }
        } catch (Throwable e222) {
            throw new AnnotatedException("Cannot extract issuer from CRL.", e222);
        }
    }

    private static boolean b(X509CRL x509crl) {
        Set criticalExtensionOIDs = x509crl.getCriticalExtensionOIDs();
        if (criticalExtensionOIDs == null) {
            return false;
        }
        return criticalExtensionOIDs.contains(RFC3280CertPathUtilities.f);
    }

    protected static Set a(DistributionPoint distributionPoint, Object obj, Date date, ExtendedPKIXParameters extendedPKIXParameters) {
        X509CRLSelector x509CRLStoreSelector = new X509CRLStoreSelector();
        try {
            Collection hashSet = new HashSet();
            if (obj instanceof X509AttributeCertificate) {
                hashSet.add(((X509AttributeCertificate) obj).d().a()[0]);
            } else {
                hashSet.add(a(obj));
            }
            a(distributionPoint, hashSet, x509CRLStoreSelector, extendedPKIXParameters);
            if (obj instanceof X509Certificate) {
                x509CRLStoreSelector.setCertificateChecking((X509Certificate) obj);
            } else if (obj instanceof X509AttributeCertificate) {
                x509CRLStoreSelector.a((X509AttributeCertificate) obj);
            }
            x509CRLStoreSelector.b(true);
            Set a = a.a(x509CRLStoreSelector, extendedPKIXParameters, date);
            if (!a.isEmpty()) {
                return a;
            }
            if (obj instanceof X509AttributeCertificate) {
                throw new AnnotatedException("No CRLs found for issuer \"" + ((X509AttributeCertificate) obj).d().a()[0] + "\"");
            }
            throw new AnnotatedException("No CRLs found for issuer \"" + ((X509Certificate) obj).getIssuerX500Principal() + "\"");
        } catch (Throwable e) {
            throw new AnnotatedException("Could not get issuer information from distribution point.", e);
        }
    }

    protected static Date a(ExtendedPKIXParameters extendedPKIXParameters, CertPath certPath, int i) {
        if (extendedPKIXParameters.d() != 1) {
            return a((PKIXParameters) extendedPKIXParameters);
        }
        if (i <= 0) {
            return a((PKIXParameters) extendedPKIXParameters);
        }
        if (i - 1 != 0) {
            return ((X509Certificate) certPath.getCertificates().get(i - 1)).getNotBefore();
        }
        try {
            DERGeneralizedTime a;
            byte[] extensionValue = ((X509Certificate) certPath.getCertificates().get(i - 1)).getExtensionValue(ISISMTTObjectIdentifiers.e.d());
            if (extensionValue != null) {
                a = DERGeneralizedTime.a(ASN1Primitive.a(extensionValue));
            } else {
                a = null;
            }
            if (a == null) {
                return ((X509Certificate) certPath.getCertificates().get(i - 1)).getNotBefore();
            }
            try {
                return a.e();
            } catch (Throwable e) {
                throw new AnnotatedException("Date from date of cert gen extension could not be parsed.", e);
            }
        } catch (IOException e2) {
            throw new AnnotatedException("Date of cert gen extension could not be read.");
        } catch (IllegalArgumentException e3) {
            throw new AnnotatedException("Date of cert gen extension could not be read.");
        }
    }

    protected static PublicKey a(List list, int i) {
        PublicKey publicKey = ((Certificate) list.get(i)).getPublicKey();
        if (publicKey instanceof DSAPublicKey) {
            DSAPublicKey publicKey2 = (DSAPublicKey) publicKey2;
            if (publicKey2.getParams() == null) {
                int i2 = i + 1;
                while (i2 < list.size()) {
                    PublicKey publicKey3 = ((X509Certificate) list.get(i2)).getPublicKey();
                    if (publicKey3 instanceof DSAPublicKey) {
                        DSAPublicKey dSAPublicKey = (DSAPublicKey) publicKey3;
                        if (dSAPublicKey.getParams() == null) {
                            i2++;
                        } else {
                            DSAParams params = dSAPublicKey.getParams();
                            try {
                                publicKey2 = KeyFactory.getInstance("DSA", "SC").generatePublic(new DSAPublicKeySpec(publicKey2.getY(), params.getP(), params.getQ(), params.getG()));
                            } catch (Exception e) {
                                throw new RuntimeException(e.getMessage());
                            }
                        }
                    }
                    throw new CertPathValidatorException("DSA parameters cannot be inherited from previous certificate.");
                }
                throw new CertPathValidatorException("DSA parameters cannot be inherited from previous certificate.");
            }
        }
        return publicKey2;
    }

    protected static Collection a(X509Certificate x509Certificate, ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters) {
        X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
        Collection hashSet = new HashSet();
        try {
            x509CertStoreSelector.setSubject(x509Certificate.getIssuerX500Principal().getEncoded());
            try {
                List<X509Certificate> arrayList = new ArrayList();
                arrayList.addAll(a(x509CertStoreSelector, extendedPKIXBuilderParameters.getCertStores()));
                arrayList.addAll(a(x509CertStoreSelector, extendedPKIXBuilderParameters.f()));
                arrayList.addAll(a(x509CertStoreSelector, extendedPKIXBuilderParameters.e()));
                for (X509Certificate add : arrayList) {
                    hashSet.add(add);
                }
                return hashSet;
            } catch (Throwable e) {
                throw new AnnotatedException("Issuer certificate cannot be searched.", e);
            }
        } catch (Throwable e2) {
            throw new AnnotatedException("Subject criteria for certificate selector to find issuer certificate could not be set.", e2);
        }
    }

    protected static void a(X509Certificate x509Certificate, PublicKey publicKey, String str) {
        if (str == null) {
            x509Certificate.verify(publicKey);
        } else {
            x509Certificate.verify(publicKey, str);
        }
    }
}
