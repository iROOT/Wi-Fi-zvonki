package org.spongycastle.jce.provider;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertSelector;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.BasicConstraints;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.DistributionPoint;
import org.spongycastle.asn1.x509.DistributionPointName;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.GeneralSubtree;
import org.spongycastle.asn1.x509.IssuingDistributionPoint;
import org.spongycastle.asn1.x509.NameConstraints;
import org.spongycastle.asn1.x509.PolicyInformation;
import org.spongycastle.asn1.x509.X509Extensions;
import org.spongycastle.asn1.x509.X509Name;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.util.Arrays;
import org.spongycastle.x509.ExtendedPKIXBuilderParameters;
import org.spongycastle.x509.ExtendedPKIXParameters;
import org.spongycastle.x509.X509CertStoreSelector;

public class RFC3280CertPathUtilities {
    public static final String a = X509Extensions.q.d();
    public static final String b = X509Extensions.r.d();
    public static final String c = X509Extensions.w.d();
    public static final String d = X509Extensions.m.d();
    public static final String e = X509Extensions.v.d();
    public static final String f = X509Extensions.l.d();
    public static final String g = X509Extensions.t.d();
    public static final String h = X509Extensions.g.d();
    public static final String i = X509Extensions.p.d();
    public static final String j = X509Extensions.e.d();
    public static final String k = X509Extensions.o.d();
    public static final String l = X509Extensions.s.d();
    public static final String m = X509Extensions.c.d();
    public static final String n = X509Extensions.h.d();
    protected static final String[] o = new String[]{"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};
    private static final PKIXCRLUtil p = new PKIXCRLUtil();

    protected static void a(DistributionPoint distributionPoint, Object obj, X509CRL x509crl) {
        int i = 0;
        try {
            Object a = IssuingDistributionPoint.a(CertPathValidatorUtilities.a((X509Extension) x509crl, d));
            if (a != null) {
                if (a.h() != null) {
                    DistributionPointName h = IssuingDistributionPoint.a(a).h();
                    List arrayList = new ArrayList();
                    if (h.d() == 0) {
                        GeneralName[] d = GeneralNames.a(h.e()).d();
                        for (Object add : d) {
                            arrayList.add(add);
                        }
                    }
                    if (h.d() == 1) {
                        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                        try {
                            Enumeration e = ASN1Sequence.a(ASN1Primitive.a(CertPathValidatorUtilities.a(x509crl).getEncoded())).e();
                            while (e.hasMoreElements()) {
                                aSN1EncodableVector.a((ASN1Encodable) e.nextElement());
                            }
                            aSN1EncodableVector.a(h.e());
                            arrayList.add(new GeneralName(X509Name.a(new DERSequence(aSN1EncodableVector))));
                        } catch (Throwable e2) {
                            throw new AnnotatedException("Could not read CRL issuer.", e2);
                        }
                    }
                    GeneralName[] d2;
                    if (distributionPoint.d() != null) {
                        DistributionPointName d3 = distributionPoint.d();
                        GeneralName[] generalNameArr = null;
                        if (d3.d() == 0) {
                            generalNameArr = GeneralNames.a(d3.e()).d();
                        }
                        if (d3.d() == 1) {
                            if (distributionPoint.f() != null) {
                                d2 = distributionPoint.f().d();
                            } else {
                                d2 = new GeneralName[1];
                                try {
                                    d2[0] = new GeneralName(new X509Name((ASN1Sequence) ASN1Primitive.a(CertPathValidatorUtilities.a(obj).getEncoded())));
                                } catch (Throwable e22) {
                                    throw new AnnotatedException("Could not read certificate issuer.", e22);
                                }
                            }
                            for (int i2 = 0; i2 < d2.length; i2++) {
                                Enumeration e3 = ASN1Sequence.a(d2[i2].e().a()).e();
                                ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                                while (e3.hasMoreElements()) {
                                    aSN1EncodableVector2.a((ASN1Encodable) e3.nextElement());
                                }
                                aSN1EncodableVector2.a(d3.e());
                                d2[i2] = new GeneralName(new X509Name(new DERSequence(aSN1EncodableVector2)));
                            }
                        } else {
                            d2 = generalNameArr;
                        }
                        if (d2 != null) {
                            for (Object contains : d2) {
                                if (arrayList.contains(contains)) {
                                    i = 1;
                                    break;
                                }
                            }
                        }
                        if (i == 0) {
                            throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
                        }
                    } else if (distributionPoint.f() == null) {
                        throw new AnnotatedException("Either the cRLIssuer or the distributionPoint field must be contained in DistributionPoint.");
                    } else {
                        d2 = distributionPoint.f().d();
                        for (Object contains2 : d2) {
                            if (arrayList.contains(contains2)) {
                                i = 1;
                                break;
                            }
                        }
                        if (i == 0) {
                            throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
                        }
                    }
                }
                try {
                    BasicConstraints a2 = BasicConstraints.a(CertPathValidatorUtilities.a((X509Extension) obj, h));
                    if (obj instanceof X509Certificate) {
                        if (a.d() && a2 != null && a2.d()) {
                            throw new AnnotatedException("CA Cert CRL only contains user certificates.");
                        } else if (a.e() && (a2 == null || !a2.d())) {
                            throw new AnnotatedException("End CRL only contains CA certificates.");
                        }
                    }
                    if (a.g()) {
                        throw new AnnotatedException("onlyContainsAttributeCerts boolean is asserted.");
                    }
                } catch (Throwable e222) {
                    throw new AnnotatedException("Basic constraints extension could not be decoded.", e222);
                }
            }
        } catch (Throwable e2222) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e2222);
        }
    }

    protected static void b(DistributionPoint distributionPoint, Object obj, X509CRL x509crl) {
        int i;
        int i2 = 0;
        Object a = CertPathValidatorUtilities.a((X509Extension) x509crl, d);
        int i3;
        if (a == null || !IssuingDistributionPoint.a(a).f()) {
            i3 = 0;
        } else {
            i3 = 1;
        }
        byte[] encoded = CertPathValidatorUtilities.a(x509crl).getEncoded();
        if (distributionPoint.f() != null) {
            GeneralName[] d = distributionPoint.f().d();
            i = 0;
            while (i2 < d.length) {
                if (d[i2].d() == 4) {
                    try {
                        if (Arrays.a(d[i2].e().a().b(), encoded)) {
                            i = 1;
                        }
                    } catch (Throwable e) {
                        throw new AnnotatedException("CRL issuer information from distribution point cannot be decoded.", e);
                    }
                }
                i2++;
            }
            if (i != 0 && i3 == 0) {
                throw new AnnotatedException("Distribution point contains cRLIssuer field but CRL is not indirect.");
            } else if (i == 0) {
                throw new AnnotatedException("CRL issuer of CRL does not match CRL issuer of distribution point.");
            }
        } else if (CertPathValidatorUtilities.a(x509crl).equals(CertPathValidatorUtilities.a(obj))) {
            i = 1;
        } else {
            i = 0;
        }
        if (i == 0) {
            throw new AnnotatedException("Cannot find matching CRL issuer for certificate.");
        }
    }

    protected static ReasonsMask a(X509CRL x509crl, DistributionPoint distributionPoint) {
        try {
            IssuingDistributionPoint a = IssuingDistributionPoint.a(CertPathValidatorUtilities.a((X509Extension) x509crl, d));
            if (a != null && a.i() != null && distributionPoint.e() != null) {
                return new ReasonsMask(distributionPoint.e()).b(new ReasonsMask(a.i()));
            }
            if ((a == null || a.i() == null) && distributionPoint.e() == null) {
                return ReasonsMask.a;
            }
            return (distributionPoint.e() == null ? ReasonsMask.a : new ReasonsMask(distributionPoint.e())).b(a == null ? ReasonsMask.a : new ReasonsMask(a.i()));
        } catch (Throwable e) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e);
        }
    }

    protected static Set a(X509CRL x509crl, Object obj, X509Certificate x509Certificate, PublicKey publicKey, ExtendedPKIXParameters extendedPKIXParameters, List list) {
        int i = 0;
        X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
        try {
            x509CertStoreSelector.setSubject(CertPathValidatorUtilities.a(x509crl).getEncoded());
            try {
                Collection<X509Certificate> a = CertPathValidatorUtilities.a(x509CertStoreSelector, extendedPKIXParameters.f());
                a.addAll(CertPathValidatorUtilities.a(x509CertStoreSelector, extendedPKIXParameters.e()));
                a.addAll(CertPathValidatorUtilities.a(x509CertStoreSelector, extendedPKIXParameters.getCertStores()));
                a.add(x509Certificate);
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                for (X509Certificate x509Certificate2 : a) {
                    if (x509Certificate2.equals(x509Certificate)) {
                        arrayList.add(x509Certificate2);
                        arrayList2.add(publicKey);
                    } else {
                        try {
                            CertPathBuilder instance = CertPathBuilder.getInstance("PKIX", "SC");
                            CertSelector x509CertStoreSelector2 = new X509CertStoreSelector();
                            x509CertStoreSelector2.setCertificate(x509Certificate2);
                            ExtendedPKIXParameters extendedPKIXParameters2 = (ExtendedPKIXParameters) extendedPKIXParameters.clone();
                            extendedPKIXParameters2.setTargetCertConstraints(x509CertStoreSelector2);
                            ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters = (ExtendedPKIXBuilderParameters) ExtendedPKIXBuilderParameters.b(extendedPKIXParameters2);
                            if (list.contains(x509Certificate2)) {
                                extendedPKIXBuilderParameters.setRevocationEnabled(false);
                            } else {
                                extendedPKIXBuilderParameters.setRevocationEnabled(true);
                            }
                            List certificates = instance.build(extendedPKIXBuilderParameters).getCertPath().getCertificates();
                            arrayList.add(x509Certificate2);
                            arrayList2.add(CertPathValidatorUtilities.a(certificates, 0));
                        } catch (Throwable e) {
                            throw new AnnotatedException("Internal error.", e);
                        } catch (Throwable e2) {
                            throw new AnnotatedException("Public key of issuer certificate of CRL could not be retrieved.", e2);
                        } catch (Exception e3) {
                            throw new RuntimeException(e3.getMessage());
                        }
                    }
                }
                Set hashSet = new HashSet();
                AnnotatedException annotatedException = null;
                while (i < arrayList.size()) {
                    AnnotatedException annotatedException2;
                    boolean[] keyUsage = ((X509Certificate) arrayList.get(i)).getKeyUsage();
                    if (keyUsage == null || (keyUsage.length >= 7 && keyUsage[6])) {
                        hashSet.add(arrayList2.get(i));
                        annotatedException2 = annotatedException;
                    } else {
                        annotatedException2 = new AnnotatedException("Issuer certificate key usage extension does not permit CRL signing.");
                    }
                    i++;
                    annotatedException = annotatedException2;
                }
                if (hashSet.isEmpty() && annotatedException == null) {
                    throw new AnnotatedException("Cannot find a valid issuer certificate.");
                } else if (!hashSet.isEmpty() || annotatedException == null) {
                    return hashSet;
                } else {
                    throw annotatedException;
                }
            } catch (Throwable e22) {
                throw new AnnotatedException("Issuer certificate for CRL cannot be searched.", e22);
            }
        } catch (Throwable e222) {
            throw new AnnotatedException("Subject criteria for certificate selector to find issuer certificate for CRL could not be set.", e222);
        }
    }

    protected static PublicKey a(X509CRL x509crl, Set set) {
        Throwable e = null;
        for (PublicKey publicKey : set) {
            try {
                x509crl.verify(publicKey);
                return publicKey;
            } catch (Exception e2) {
                e = e2;
            }
        }
        throw new AnnotatedException("Cannot verify CRL.", e);
    }

    protected static X509CRL a(Set set, PublicKey publicKey) {
        Throwable e = null;
        for (X509CRL x509crl : set) {
            try {
                x509crl.verify(publicKey);
                return x509crl;
            } catch (Exception e2) {
                e = e2;
            }
        }
        if (e == null) {
            return null;
        }
        throw new AnnotatedException("Cannot verify delta CRL.", e);
    }

    protected static void a(X509CRL x509crl, X509CRL x509crl2, ExtendedPKIXParameters extendedPKIXParameters) {
        Object obj = 1;
        if (x509crl != null) {
            try {
                IssuingDistributionPoint a = IssuingDistributionPoint.a(CertPathValidatorUtilities.a((X509Extension) x509crl2, d));
                if (!extendedPKIXParameters.c()) {
                    return;
                }
                if (x509crl.getIssuerX500Principal().equals(x509crl2.getIssuerX500Principal())) {
                    try {
                        IssuingDistributionPoint a2 = IssuingDistributionPoint.a(CertPathValidatorUtilities.a((X509Extension) x509crl, d));
                        if (a != null ? a.equals(a2) : a2 == null) {
                            obj = null;
                        }
                        if (obj == null) {
                            throw new AnnotatedException("Issuing distribution point extension from delta CRL and complete CRL does not match.");
                        }
                        try {
                            ASN1Primitive a3 = CertPathValidatorUtilities.a((X509Extension) x509crl2, l);
                            try {
                                ASN1Primitive a4 = CertPathValidatorUtilities.a((X509Extension) x509crl, l);
                                if (a3 == null) {
                                    throw new AnnotatedException("CRL authority key identifier is null.");
                                } else if (a4 == null) {
                                    throw new AnnotatedException("Delta CRL authority key identifier is null.");
                                } else if (!a3.equals(a4)) {
                                    throw new AnnotatedException("Delta CRL authority key identifier does not match complete CRL authority key identifier.");
                                } else {
                                    return;
                                }
                            } catch (Throwable e) {
                                throw new AnnotatedException("Authority key identifier extension could not be extracted from delta CRL.", e);
                            }
                        } catch (Throwable e2) {
                            throw new AnnotatedException("Authority key identifier extension could not be extracted from complete CRL.", e2);
                        }
                    } catch (Throwable e22) {
                        throw new AnnotatedException("Issuing distribution point extension from delta CRL could not be decoded.", e22);
                    }
                }
                throw new AnnotatedException("Complete CRL issuer does not match delta CRL issuer.");
            } catch (Throwable e222) {
                throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e222);
            }
        }
    }

    protected static void a(Date date, X509CRL x509crl, Object obj, CertStatus certStatus, ExtendedPKIXParameters extendedPKIXParameters) {
        if (extendedPKIXParameters.c() && x509crl != null) {
            CertPathValidatorUtilities.a(date, x509crl, obj, certStatus);
        }
    }

    protected static void a(Date date, X509CRL x509crl, Object obj, CertStatus certStatus) {
        if (certStatus.b() == 11) {
            CertPathValidatorUtilities.a(date, x509crl, obj, certStatus);
        }
    }

    protected static PKIXPolicyNode a(CertPath certPath, int i, List[] listArr, PKIXPolicyNode pKIXPolicyNode, int i2) {
        List certificates = certPath.getCertificates();
        X509Extension x509Extension = (X509Certificate) certificates.get(i);
        int size = certificates.size() - i;
        try {
            ASN1Sequence a = ASN1Sequence.a((Object) CertPathValidatorUtilities.a(x509Extension, b));
            if (a != null) {
                String d;
                Map hashMap = new HashMap();
                Set<String> hashSet = new HashSet();
                for (int i3 = 0; i3 < a.f(); i3++) {
                    ASN1Sequence aSN1Sequence = (ASN1Sequence) a.a(i3);
                    String d2 = ((DERObjectIdentifier) aSN1Sequence.a(0)).d();
                    d = ((DERObjectIdentifier) aSN1Sequence.a(1)).d();
                    if (hashMap.containsKey(d2)) {
                        ((Set) hashMap.get(d2)).add(d);
                    } else {
                        Set hashSet2 = new HashSet();
                        hashSet2.add(d);
                        hashMap.put(d2, hashSet2);
                        hashSet.add(d2);
                    }
                }
                for (String d3 : hashSet) {
                    PKIXPolicyNode pKIXPolicyNode2;
                    if (i2 > 0) {
                        Object obj;
                        for (PKIXPolicyNode pKIXPolicyNode22 : listArr[size]) {
                            if (pKIXPolicyNode22.getValidPolicy().equals(d3)) {
                                pKIXPolicyNode22.c = (Set) hashMap.get(d3);
                                obj = 1;
                                break;
                            }
                        }
                        obj = null;
                        if (obj == null) {
                            for (PKIXPolicyNode pKIXPolicyNode222 : listArr[size]) {
                                if ("2.5.29.32.0".equals(pKIXPolicyNode222.getValidPolicy())) {
                                    Set set = null;
                                    try {
                                        Enumeration e = ((ASN1Sequence) CertPathValidatorUtilities.a(x509Extension, a)).e();
                                        while (e.hasMoreElements()) {
                                            try {
                                                PolicyInformation a2 = PolicyInformation.a(e.nextElement());
                                                if ("2.5.29.32.0".equals(a2.d().d())) {
                                                    try {
                                                        set = CertPathValidatorUtilities.a(a2.e());
                                                        break;
                                                    } catch (Throwable e2) {
                                                        throw new ExtCertPathValidatorException("Policy qualifier info set could not be decoded.", e2, certPath, i);
                                                    }
                                                }
                                            } catch (Throwable e22) {
                                                throw new CertPathValidatorException("Policy information could not be decoded.", e22, certPath, i);
                                            }
                                        }
                                        boolean z = false;
                                        if (x509Extension.getCriticalExtensionOIDs() != null) {
                                            z = x509Extension.getCriticalExtensionOIDs().contains(a);
                                        }
                                        PKIXPolicyNode pKIXPolicyNode3 = (PKIXPolicyNode) pKIXPolicyNode222.getParent();
                                        if ("2.5.29.32.0".equals(pKIXPolicyNode3.getValidPolicy())) {
                                            pKIXPolicyNode222 = new PKIXPolicyNode(new ArrayList(), size, (Set) hashMap.get(d3), pKIXPolicyNode3, set, d3, z);
                                            pKIXPolicyNode3.a(pKIXPolicyNode222);
                                            listArr[size].add(pKIXPolicyNode222);
                                        }
                                    } catch (Throwable e222) {
                                        throw new ExtCertPathValidatorException("Certificate policies extension could not be decoded.", e222, certPath, i);
                                    }
                                }
                            }
                            continue;
                        } else {
                            continue;
                        }
                    } else if (i2 <= 0) {
                        Iterator it = listArr[size].iterator();
                        while (it.hasNext()) {
                            pKIXPolicyNode222 = (PKIXPolicyNode) it.next();
                            if (pKIXPolicyNode222.getValidPolicy().equals(d3)) {
                                ((PKIXPolicyNode) pKIXPolicyNode222.getParent()).b(pKIXPolicyNode222);
                                it.remove();
                                int i4 = size - 1;
                                PKIXPolicyNode pKIXPolicyNode4 = pKIXPolicyNode;
                                while (i4 >= 0) {
                                    List list = listArr[i4];
                                    PKIXPolicyNode pKIXPolicyNode5 = pKIXPolicyNode4;
                                    for (int i5 = 0; i5 < list.size(); i5++) {
                                        pKIXPolicyNode222 = (PKIXPolicyNode) list.get(i5);
                                        if (!pKIXPolicyNode222.a()) {
                                            pKIXPolicyNode5 = CertPathValidatorUtilities.a(pKIXPolicyNode5, listArr, pKIXPolicyNode222);
                                            if (pKIXPolicyNode5 == null) {
                                                break;
                                            }
                                        }
                                    }
                                    i4--;
                                    pKIXPolicyNode4 = pKIXPolicyNode5;
                                }
                                pKIXPolicyNode = pKIXPolicyNode4;
                            }
                        }
                    }
                }
            }
            return pKIXPolicyNode;
        } catch (Throwable e2222) {
            throw new ExtCertPathValidatorException("Policy mappings extension could not be decoded.", e2222, certPath, i);
        }
    }

    protected static void a(CertPath certPath, int i) {
        try {
            ASN1Sequence a = ASN1Sequence.a((Object) CertPathValidatorUtilities.a((X509Certificate) certPath.getCertificates().get(i), b));
            if (a != null) {
                int i2 = 0;
                while (i2 < a.f()) {
                    try {
                        ASN1Sequence a2 = ASN1Sequence.a((Object) a.a(i2));
                        DERObjectIdentifier a3 = DERObjectIdentifier.a(a2.a(0));
                        DERObjectIdentifier a4 = DERObjectIdentifier.a(a2.a(1));
                        if ("2.5.29.32.0".equals(a3.d())) {
                            throw new CertPathValidatorException("IssuerDomainPolicy is anyPolicy", null, certPath, i);
                        } else if ("2.5.29.32.0".equals(a4.d())) {
                            throw new CertPathValidatorException("SubjectDomainPolicy is anyPolicy,", null, certPath, i);
                        } else {
                            i2++;
                        }
                    } catch (Throwable e) {
                        throw new ExtCertPathValidatorException("Policy mappings extension contents could not be decoded.", e, certPath, i);
                    }
                }
            }
        } catch (Throwable e2) {
            throw new ExtCertPathValidatorException("Policy mappings extension could not be decoded.", e2, certPath, i);
        }
    }

    protected static void a(CertPath certPath, int i, PKIXPolicyNode pKIXPolicyNode, int i2) {
        if (i2 <= 0 && pKIXPolicyNode == null) {
            throw new ExtCertPathValidatorException("No valid policy tree found when one expected.", null, certPath, i);
        }
    }

    protected static PKIXPolicyNode a(CertPath certPath, int i, PKIXPolicyNode pKIXPolicyNode) {
        try {
            if (ASN1Sequence.a((Object) CertPathValidatorUtilities.a((X509Certificate) certPath.getCertificates().get(i), a)) == null) {
                return null;
            }
            return pKIXPolicyNode;
        } catch (Throwable e) {
            throw new ExtCertPathValidatorException("Could not read certificate policies extension from certificate.", e, certPath, i);
        }
    }

    protected static void a(CertPath certPath, int i, PKIXNameConstraintValidator pKIXNameConstraintValidator) {
        List certificates = certPath.getCertificates();
        X509Extension x509Extension = (X509Certificate) certificates.get(i);
        int size = certificates.size();
        int i2 = size - i;
        if (!CertPathValidatorUtilities.b((X509Certificate) x509Extension) || i2 >= size) {
            try {
                ASN1Sequence a = ASN1Sequence.a((Object) new ASN1InputStream(CertPathValidatorUtilities.a((X509Certificate) x509Extension).getEncoded()).d());
                try {
                    pKIXNameConstraintValidator.a(a);
                    pKIXNameConstraintValidator.b(a);
                    try {
                        GeneralNames a2 = GeneralNames.a(CertPathValidatorUtilities.a(x509Extension, j));
                        Enumeration elements = new X509Name(a).a(X509Name.D).elements();
                        while (elements.hasMoreElements()) {
                            GeneralName generalName = new GeneralName(1, (String) elements.nextElement());
                            try {
                                pKIXNameConstraintValidator.a(generalName);
                                pKIXNameConstraintValidator.b(generalName);
                            } catch (Throwable e) {
                                throw new CertPathValidatorException("Subtree check for certificate subject alternative email failed.", e, certPath, i);
                            }
                        }
                        if (a2 != null) {
                            try {
                                GeneralName[] d = a2.d();
                                int i3 = 0;
                                while (i3 < d.length) {
                                    try {
                                        pKIXNameConstraintValidator.a(d[i3]);
                                        pKIXNameConstraintValidator.b(d[i3]);
                                        i3++;
                                    } catch (Throwable e2) {
                                        throw new CertPathValidatorException("Subtree check for certificate subject alternative name failed.", e2, certPath, i);
                                    }
                                }
                            } catch (Throwable e22) {
                                throw new CertPathValidatorException("Subject alternative name contents could not be decoded.", e22, certPath, i);
                            }
                        }
                    } catch (Throwable e222) {
                        throw new CertPathValidatorException("Subject alternative name extension could not be decoded.", e222, certPath, i);
                    }
                } catch (Throwable e2222) {
                    throw new CertPathValidatorException("Subtree check for certificate subject failed.", e2222, certPath, i);
                }
            } catch (Throwable e22222) {
                throw new CertPathValidatorException("Exception extracting subject name when checking subtrees.", e22222, certPath, i);
            }
        }
    }

    protected static PKIXPolicyNode a(CertPath certPath, int i, Set set, PKIXPolicyNode pKIXPolicyNode, List[] listArr, int i2) {
        List certificates = certPath.getCertificates();
        X509Certificate x509Certificate = (X509Certificate) certificates.get(i);
        int size = certificates.size();
        int i3 = size - i;
        try {
            ASN1Sequence a = ASN1Sequence.a((Object) CertPathValidatorUtilities.a((X509Extension) x509Certificate, a));
            if (a == null || pKIXPolicyNode == null) {
                return null;
            }
            Set a2;
            Iterator children;
            PKIXPolicyNode pKIXPolicyNode2;
            Enumeration e = a.e();
            Collection hashSet = new HashSet();
            while (e.hasMoreElements()) {
                PolicyInformation a3 = PolicyInformation.a(e.nextElement());
                DERObjectIdentifier d = a3.d();
                hashSet.add(d.d());
                if (!"2.5.29.32.0".equals(d.d())) {
                    try {
                        a2 = CertPathValidatorUtilities.a(a3.e());
                        if (!CertPathValidatorUtilities.a(i3, listArr, d, a2)) {
                            CertPathValidatorUtilities.b(i3, listArr, d, a2);
                        }
                    } catch (Throwable e2) {
                        throw new ExtCertPathValidatorException("Policy qualifier info set could not be build.", e2, certPath, i);
                    }
                }
            }
            if (set.isEmpty() || set.contains("2.5.29.32.0")) {
                set.clear();
                set.addAll(hashSet);
            } else {
                Collection hashSet2 = new HashSet();
                for (Object next : set) {
                    if (hashSet.contains(next)) {
                        hashSet2.add(next);
                    }
                }
                set.clear();
                set.addAll(hashSet2);
            }
            if (i2 > 0 || (i3 < size && CertPathValidatorUtilities.b(x509Certificate))) {
                Enumeration e3 = a.e();
                while (e3.hasMoreElements()) {
                    PolicyInformation a4 = PolicyInformation.a(e3.nextElement());
                    if ("2.5.29.32.0".equals(a4.d().d())) {
                        a2 = CertPathValidatorUtilities.a(a4.e());
                        List list = listArr[i3 - 1];
                        for (int i4 = 0; i4 < list.size(); i4++) {
                            PKIXPolicyNode pKIXPolicyNode3 = (PKIXPolicyNode) list.get(i4);
                            for (Object next2 : pKIXPolicyNode3.getExpectedPolicies()) {
                                Object next22;
                                String str;
                                if (next22 instanceof String) {
                                    str = (String) next22;
                                } else if (next22 instanceof DERObjectIdentifier) {
                                    str = ((DERObjectIdentifier) next22).d();
                                }
                                children = pKIXPolicyNode3.getChildren();
                                Object obj = null;
                                while (children.hasNext()) {
                                    if (str.equals(((PKIXPolicyNode) children.next()).getValidPolicy())) {
                                        next22 = 1;
                                    } else {
                                        next22 = obj;
                                    }
                                    obj = next22;
                                }
                                if (obj == null) {
                                    Set hashSet3 = new HashSet();
                                    hashSet3.add(str);
                                    pKIXPolicyNode2 = new PKIXPolicyNode(new ArrayList(), i3, hashSet3, pKIXPolicyNode3, a2, str, false);
                                    pKIXPolicyNode3.a(pKIXPolicyNode2);
                                    listArr[i3].add(pKIXPolicyNode2);
                                }
                            }
                        }
                    }
                }
            }
            int i5 = i3 - 1;
            PKIXPolicyNode pKIXPolicyNode4 = pKIXPolicyNode;
            while (i5 >= 0) {
                List list2 = listArr[i5];
                PKIXPolicyNode pKIXPolicyNode5 = pKIXPolicyNode4;
                for (int i6 = 0; i6 < list2.size(); i6++) {
                    pKIXPolicyNode2 = (PKIXPolicyNode) list2.get(i6);
                    if (!pKIXPolicyNode2.a()) {
                        pKIXPolicyNode5 = CertPathValidatorUtilities.a(pKIXPolicyNode5, listArr, pKIXPolicyNode2);
                        if (pKIXPolicyNode5 == null) {
                            break;
                        }
                    }
                }
                i5--;
                pKIXPolicyNode4 = pKIXPolicyNode5;
            }
            Set criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs == null) {
                return pKIXPolicyNode4;
            }
            boolean contains = criticalExtensionOIDs.contains(a);
            List list3 = listArr[i3];
            for (i3 = 0; i3 < list3.size(); i3++) {
                ((PKIXPolicyNode) list3.get(i3)).a(contains);
            }
            return pKIXPolicyNode4;
        } catch (Throwable e22) {
            throw new ExtCertPathValidatorException("Could not read certificate policies extension from certificate.", e22, certPath, i);
        }
    }

    protected static void a(CertPath certPath, ExtendedPKIXParameters extendedPKIXParameters, int i, PublicKey publicKey, boolean z, X500Principal x500Principal, X509Certificate x509Certificate) {
        List certificates = certPath.getCertificates();
        Object obj = (X509Certificate) certificates.get(i);
        if (!z) {
            try {
                CertPathValidatorUtilities.a((X509Certificate) obj, publicKey, extendedPKIXParameters.getSigProvider());
            } catch (Throwable e) {
                Throwable e2;
                throw new ExtCertPathValidatorException("Could not validate certificate signature.", e2, certPath, i);
            }
        }
        try {
            obj.checkValidity(CertPathValidatorUtilities.a(extendedPKIXParameters, certPath, i));
            if (extendedPKIXParameters.isRevocationEnabled()) {
                try {
                    a(extendedPKIXParameters, (X509Certificate) obj, CertPathValidatorUtilities.a(extendedPKIXParameters, certPath, i), x509Certificate, publicKey, certificates);
                } catch (Throwable e3) {
                    if (e3.getCause() != null) {
                        e2 = e3.getCause();
                    } else {
                        e2 = e3;
                    }
                    throw new ExtCertPathValidatorException(e3.getMessage(), e2, certPath, i);
                }
            }
            if (!CertPathValidatorUtilities.a(obj).equals(x500Principal)) {
                throw new ExtCertPathValidatorException("IssuerName(" + CertPathValidatorUtilities.a(obj) + ") does not match SubjectName(" + x500Principal + ") of signing certificate.", null, certPath, i);
            }
        } catch (Throwable e22) {
            throw new ExtCertPathValidatorException("Could not validate certificate: " + e22.getMessage(), e22, certPath, i);
        } catch (Throwable e222) {
            throw new ExtCertPathValidatorException("Could not validate certificate: " + e222.getMessage(), e222, certPath, i);
        } catch (Throwable e2222) {
            throw new ExtCertPathValidatorException("Could not validate time of certificate.", e2222, certPath, i);
        }
    }

    protected static int a(CertPath certPath, int i, int i2) {
        try {
            ASN1Sequence a = ASN1Sequence.a((Object) CertPathValidatorUtilities.a((X509Certificate) certPath.getCertificates().get(i), g));
            if (a == null) {
                return i2;
            }
            Enumeration e = a.e();
            while (e.hasMoreElements()) {
                try {
                    ASN1TaggedObject a2 = ASN1TaggedObject.a(e.nextElement());
                    if (a2.d() == 0) {
                        int intValue = DERInteger.a(a2, false).d().intValue();
                        return intValue < i2 ? intValue : i2;
                    }
                } catch (Throwable e2) {
                    throw new ExtCertPathValidatorException("Policy constraints extension contents cannot be decoded.", e2, certPath, i);
                }
            }
            return i2;
        } catch (Throwable e22) {
            throw new ExtCertPathValidatorException("Policy constraints extension cannot be decoded.", e22, certPath, i);
        }
    }

    protected static int b(CertPath certPath, int i, int i2) {
        try {
            ASN1Sequence a = ASN1Sequence.a((Object) CertPathValidatorUtilities.a((X509Certificate) certPath.getCertificates().get(i), g));
            if (a == null) {
                return i2;
            }
            Enumeration e = a.e();
            while (e.hasMoreElements()) {
                try {
                    ASN1TaggedObject a2 = ASN1TaggedObject.a(e.nextElement());
                    if (a2.d() == 1) {
                        int intValue = DERInteger.a(a2, false).d().intValue();
                        return intValue < i2 ? intValue : i2;
                    }
                } catch (Throwable e2) {
                    throw new ExtCertPathValidatorException("Policy constraints extension contents cannot be decoded.", e2, certPath, i);
                }
            }
            return i2;
        } catch (Throwable e22) {
            throw new ExtCertPathValidatorException("Policy constraints extension cannot be decoded.", e22, certPath, i);
        }
    }

    protected static void b(CertPath certPath, int i, PKIXNameConstraintValidator pKIXNameConstraintValidator) {
        try {
            NameConstraints a;
            Object a2 = ASN1Sequence.a((Object) CertPathValidatorUtilities.a((X509Certificate) certPath.getCertificates().get(i), k));
            if (a2 != null) {
                a = NameConstraints.a(a2);
            } else {
                a = null;
            }
            if (a != null) {
                GeneralSubtree[] d = a.d();
                if (d != null) {
                    try {
                        pKIXNameConstraintValidator.a(d);
                    } catch (Throwable e) {
                        throw new ExtCertPathValidatorException("Permitted subtrees cannot be build from name constraints extension.", e, certPath, i);
                    }
                }
                d = a.e();
                if (d != null) {
                    int i2 = 0;
                    while (i2 != d.length) {
                        try {
                            pKIXNameConstraintValidator.a(d[i2]);
                            i2++;
                        } catch (Throwable e2) {
                            throw new ExtCertPathValidatorException("Excluded subtrees cannot be build from name constraints extension.", e2, certPath, i);
                        }
                    }
                }
            }
        } catch (Throwable e22) {
            throw new ExtCertPathValidatorException("Name constraints extension could not be decoded.", e22, certPath, i);
        }
    }

    private static void a(DistributionPoint distributionPoint, ExtendedPKIXParameters extendedPKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, CertStatus certStatus, ReasonsMask reasonsMask, List list) {
        Date date2 = new Date(System.currentTimeMillis());
        if (date.getTime() > date2.getTime()) {
            throw new AnnotatedException("Validation time is in future.");
        }
        Iterator it = CertPathValidatorUtilities.a(distributionPoint, (Object) x509Certificate, date2, extendedPKIXParameters).iterator();
        AnnotatedException annotatedException = null;
        Object obj = null;
        while (it.hasNext() && certStatus.b() == 11 && !reasonsMask.a()) {
            try {
                X509CRL x509crl = (X509CRL) it.next();
                ReasonsMask a = a(x509crl, distributionPoint);
                if (a.c(reasonsMask)) {
                    PublicKey a2 = a(x509crl, a(x509crl, (Object) x509Certificate, x509Certificate2, publicKey, extendedPKIXParameters, list));
                    X509CRL x509crl2 = null;
                    if (extendedPKIXParameters.c()) {
                        x509crl2 = a(CertPathValidatorUtilities.a(date2, extendedPKIXParameters, x509crl), a2);
                    }
                    if (extendedPKIXParameters.d() == 1 || x509Certificate.getNotAfter().getTime() >= x509crl.getThisUpdate().getTime()) {
                        b(distributionPoint, (Object) x509Certificate, x509crl);
                        a(distributionPoint, (Object) x509Certificate, x509crl);
                        a(x509crl2, x509crl, extendedPKIXParameters);
                        a(date, x509crl2, (Object) x509Certificate, certStatus, extendedPKIXParameters);
                        a(date, x509crl, (Object) x509Certificate, certStatus);
                        if (certStatus.b() == 8) {
                            certStatus.a(11);
                        }
                        reasonsMask.a(a);
                        Collection criticalExtensionOIDs = x509crl.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs != null) {
                            Set hashSet = new HashSet(criticalExtensionOIDs);
                            hashSet.remove(X509Extensions.m.d());
                            hashSet.remove(X509Extensions.l.d());
                            if (!hashSet.isEmpty()) {
                                throw new AnnotatedException("CRL contains unsupported critical extensions.");
                            }
                        }
                        if (x509crl2 != null) {
                            criticalExtensionOIDs = x509crl2.getCriticalExtensionOIDs();
                            if (criticalExtensionOIDs != null) {
                                Set hashSet2 = new HashSet(criticalExtensionOIDs);
                                hashSet2.remove(X509Extensions.m.d());
                                hashSet2.remove(X509Extensions.l.d());
                                if (!hashSet2.isEmpty()) {
                                    throw new AnnotatedException("Delta CRL contains unsupported critical extension.");
                                }
                            }
                        }
                        obj = 1;
                    } else {
                        throw new AnnotatedException("No valid CRL for current time found.");
                    }
                }
                continue;
            } catch (AnnotatedException e) {
                annotatedException = e;
            }
        }
        if (obj == null) {
            throw annotatedException;
        }
    }

    protected static void a(ExtendedPKIXParameters extendedPKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, List list) {
        Throwable th = null;
        try {
            CRLDistPoint a = CRLDistPoint.a(CertPathValidatorUtilities.a((X509Extension) x509Certificate, i));
            try {
                CertPathValidatorUtilities.a(a, extendedPKIXParameters);
                CertStatus certStatus = new CertStatus();
                ReasonsMask reasonsMask = new ReasonsMask();
                Object obj = null;
                if (a != null) {
                    try {
                        DistributionPoint[] d = a.d();
                        if (d != null) {
                            int i = 0;
                            while (i < d.length && certStatus.b() == 11 && !reasonsMask.a()) {
                                Object obj2;
                                Throwable th2;
                                try {
                                    a(d[i], (ExtendedPKIXParameters) extendedPKIXParameters.clone(), x509Certificate, date, x509Certificate2, publicKey, certStatus, reasonsMask, list);
                                    obj2 = 1;
                                    th2 = th;
                                } catch (Throwable e) {
                                    th2 = e;
                                    obj2 = obj;
                                }
                                i++;
                                obj = obj2;
                                th = th2;
                            }
                        }
                    } catch (Throwable e2) {
                        throw new AnnotatedException("Distribution points could not be read.", e2);
                    }
                }
                if (certStatus.b() == 11 && !reasonsMask.a()) {
                    try {
                        a(new DistributionPoint(new DistributionPointName(0, new GeneralNames(new GeneralName(4, new ASN1InputStream(CertPathValidatorUtilities.a((Object) x509Certificate).getEncoded()).d()))), null, null), (ExtendedPKIXParameters) extendedPKIXParameters.clone(), x509Certificate, date, x509Certificate2, publicKey, certStatus, reasonsMask, list);
                        obj = 1;
                    } catch (Throwable e22) {
                        throw new AnnotatedException("Issuer from certificate for CRL could not be reencoded.", e22);
                    } catch (Throwable e222) {
                        th = e222;
                    }
                }
                if (obj == null) {
                    if (th instanceof AnnotatedException) {
                        throw th;
                    }
                    throw new AnnotatedException("No valid CRL found.", th);
                } else if (certStatus.b() != 11) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    throw new AnnotatedException(("Certificate revocation after " + simpleDateFormat.format(certStatus.a())) + ", reason: " + o[certStatus.b()]);
                } else {
                    if (!reasonsMask.a() && certStatus.b() == 11) {
                        certStatus.a(12);
                    }
                    if (certStatus.b() == 12) {
                        throw new AnnotatedException("Certificate status could not be determined.");
                    }
                }
            } catch (Throwable e2222) {
                throw new AnnotatedException("No additional CRL locations could be decoded from CRL distribution point extension.", e2222);
            }
        } catch (Throwable e22222) {
            throw new AnnotatedException("CRL distribution point extension could not be read.", e22222);
        }
    }

    protected static int c(CertPath certPath, int i, int i2) {
        try {
            DERInteger a = DERInteger.a(CertPathValidatorUtilities.a((X509Certificate) certPath.getCertificates().get(i), c));
            if (a == null) {
                return i2;
            }
            int intValue = a.d().intValue();
            if (intValue < i2) {
                return intValue;
            }
            return i2;
        } catch (Throwable e) {
            throw new ExtCertPathValidatorException("Inhibit any-policy extension cannot be decoded.", e, certPath, i);
        }
    }

    protected static void b(CertPath certPath, int i) {
        try {
            BasicConstraints a = BasicConstraints.a(CertPathValidatorUtilities.a((X509Certificate) certPath.getCertificates().get(i), h));
            if (a == null) {
                throw new CertPathValidatorException("Intermediate certificate lacks BasicConstraints");
            } else if (!a.d()) {
                throw new CertPathValidatorException("Not a CA certificate");
            }
        } catch (Throwable e) {
            throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", e, certPath, i);
        }
    }

    protected static int d(CertPath certPath, int i, int i2) {
        if (CertPathValidatorUtilities.b((X509Certificate) certPath.getCertificates().get(i))) {
            return i2;
        }
        if (i2 > 0) {
            return i2 - 1;
        }
        throw new ExtCertPathValidatorException("Max path length not greater than zero", null, certPath, i);
    }

    protected static int e(CertPath certPath, int i, int i2) {
        try {
            BasicConstraints a = BasicConstraints.a(CertPathValidatorUtilities.a((X509Certificate) certPath.getCertificates().get(i), h));
            if (a == null) {
                return i2;
            }
            BigInteger e = a.e();
            if (e == null) {
                return i2;
            }
            int intValue = e.intValue();
            if (intValue < i2) {
                return intValue;
            }
            return i2;
        } catch (Throwable e2) {
            throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", e2, certPath, i);
        }
    }

    protected static void c(CertPath certPath, int i) {
        boolean[] keyUsage = ((X509Certificate) certPath.getCertificates().get(i)).getKeyUsage();
        if (keyUsage != null && !keyUsage[5]) {
            throw new ExtCertPathValidatorException("Issuer certificate keyusage extension is critical and does not permit key signing.", null, certPath, i);
        }
    }

    protected static void a(CertPath certPath, int i, Set set, List list) {
        X509Certificate x509Certificate = (X509Certificate) certPath.getCertificates().get(i);
        for (PKIXCertPathChecker check : list) {
            try {
                check.check(x509Certificate, set);
            } catch (CertPathValidatorException e) {
                throw new CertPathValidatorException(e.getMessage(), e.getCause(), certPath, i);
            }
        }
        if (!set.isEmpty()) {
            throw new ExtCertPathValidatorException("Certificate has unsupported critical extension: " + set, null, certPath, i);
        }
    }

    protected static int f(CertPath certPath, int i, int i2) {
        if (CertPathValidatorUtilities.b((X509Certificate) certPath.getCertificates().get(i)) || i2 == 0) {
            return i2;
        }
        return i2 - 1;
    }

    protected static int g(CertPath certPath, int i, int i2) {
        if (CertPathValidatorUtilities.b((X509Certificate) certPath.getCertificates().get(i)) || i2 == 0) {
            return i2;
        }
        return i2 - 1;
    }

    protected static int h(CertPath certPath, int i, int i2) {
        if (CertPathValidatorUtilities.b((X509Certificate) certPath.getCertificates().get(i)) || i2 == 0) {
            return i2;
        }
        return i2 - 1;
    }

    protected static int a(int i, X509Certificate x509Certificate) {
        if (CertPathValidatorUtilities.b(x509Certificate) || i == 0) {
            return i;
        }
        return i - 1;
    }

    protected static int i(CertPath certPath, int i, int i2) {
        try {
            ASN1Sequence a = ASN1Sequence.a((Object) CertPathValidatorUtilities.a((X509Certificate) certPath.getCertificates().get(i), g));
            if (a == null) {
                return i2;
            }
            Enumeration e = a.e();
            while (e.hasMoreElements()) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) e.nextElement();
                switch (aSN1TaggedObject.d()) {
                    case 0:
                        try {
                            if (DERInteger.a(aSN1TaggedObject, false).d().intValue() != 0) {
                                break;
                            }
                            return 0;
                        } catch (Throwable e2) {
                            throw new ExtCertPathValidatorException("Policy constraints requireExplicitPolicy field could not be decoded.", e2, certPath, i);
                        }
                    default:
                        break;
                }
            }
            return i2;
        } catch (Throwable e22) {
            throw new ExtCertPathValidatorException("Policy constraints could not be decoded.", e22, certPath, i);
        }
    }

    protected static void a(CertPath certPath, int i, List list, Set set) {
        X509Certificate x509Certificate = (X509Certificate) certPath.getCertificates().get(i);
        for (PKIXCertPathChecker check : list) {
            try {
                check.check(x509Certificate, set);
            } catch (Throwable e) {
                throw new ExtCertPathValidatorException("Additional certificate path checker failed.", e, certPath, i);
            }
        }
        if (!set.isEmpty()) {
            throw new ExtCertPathValidatorException("Certificate has unsupported critical extension: " + set, null, certPath, i);
        }
    }

    protected static PKIXPolicyNode a(CertPath certPath, ExtendedPKIXParameters extendedPKIXParameters, Set set, int i, List[] listArr, PKIXPolicyNode pKIXPolicyNode, Set set2) {
        int size = certPath.getCertificates().size();
        if (pKIXPolicyNode == null) {
            if (!extendedPKIXParameters.isExplicitPolicyRequired()) {
                return null;
            }
            throw new ExtCertPathValidatorException("Explicit policy requested but none available.", null, certPath, i);
        } else if (!CertPathValidatorUtilities.a(set)) {
            r4 = new HashSet();
            for (List list : listArr) {
                for (r2 = 0; r2 < list.size(); r2++) {
                    r0 = (PKIXPolicyNode) list.get(r2);
                    if ("2.5.29.32.0".equals(r0.getValidPolicy())) {
                        Iterator children = r0.getChildren();
                        while (children.hasNext()) {
                            r0 = (PKIXPolicyNode) children.next();
                            if (!"2.5.29.32.0".equals(r0.getValidPolicy())) {
                                r4.add(r0);
                            }
                        }
                    }
                }
            }
            for (PKIXPolicyNode pKIXPolicyNode2 : r4) {
                if (!set.contains(pKIXPolicyNode2.getValidPolicy())) {
                    pKIXPolicyNode = CertPathValidatorUtilities.a(pKIXPolicyNode, listArr, pKIXPolicyNode2);
                }
            }
            if (pKIXPolicyNode != null) {
                size--;
                r1 = pKIXPolicyNode;
                while (size >= 0) {
                    r4 = listArr[size];
                    r2 = r1;
                    for (r1 = 0; r1 < r4.size(); r1++) {
                        pKIXPolicyNode2 = (PKIXPolicyNode) r4.get(r1);
                        if (!pKIXPolicyNode2.a()) {
                            r2 = CertPathValidatorUtilities.a(r2, listArr, pKIXPolicyNode2);
                        }
                    }
                    size--;
                    r1 = r2;
                }
            } else {
                r1 = pKIXPolicyNode;
            }
            return r1;
        } else if (!extendedPKIXParameters.isExplicitPolicyRequired()) {
            return pKIXPolicyNode;
        } else {
            if (set2.isEmpty()) {
                throw new ExtCertPathValidatorException("Explicit policy requested but none available.", null, certPath, i);
            }
            r4 = new HashSet();
            for (List list2 : listArr) {
                for (r2 = 0; r2 < list2.size(); r2++) {
                    pKIXPolicyNode2 = (PKIXPolicyNode) list2.get(r2);
                    if ("2.5.29.32.0".equals(pKIXPolicyNode2.getValidPolicy())) {
                        Iterator children2 = pKIXPolicyNode2.getChildren();
                        while (children2.hasNext()) {
                            r4.add(children2.next());
                        }
                    }
                }
            }
            for (PKIXPolicyNode pKIXPolicyNode22 : r4) {
                if (set2.contains(pKIXPolicyNode22.getValidPolicy())) {
                }
            }
            if (pKIXPolicyNode == null) {
                return pKIXPolicyNode;
            }
            size--;
            r1 = pKIXPolicyNode;
            while (size >= 0) {
                r4 = listArr[size];
                r2 = r1;
                for (r1 = 0; r1 < r4.size(); r1++) {
                    pKIXPolicyNode22 = (PKIXPolicyNode) r4.get(r1);
                    if (!pKIXPolicyNode22.a()) {
                        r2 = CertPathValidatorUtilities.a(r2, listArr, pKIXPolicyNode22);
                    }
                }
                size--;
                r1 = r2;
            }
            return r1;
        }
    }
}
