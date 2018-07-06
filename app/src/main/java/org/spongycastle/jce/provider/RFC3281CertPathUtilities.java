package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.DistributionPoint;
import org.spongycastle.asn1.x509.DistributionPointName;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.TargetInformation;
import org.spongycastle.asn1.x509.X509Extensions;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.util.Selector;
import org.spongycastle.x509.ExtendedPKIXBuilderParameters;
import org.spongycastle.x509.ExtendedPKIXParameters;
import org.spongycastle.x509.PKIXAttrCertChecker;
import org.spongycastle.x509.X509AttributeCertificate;
import org.spongycastle.x509.X509CertStoreSelector;

class RFC3281CertPathUtilities {
    private static final String a = X509Extensions.E.d();
    private static final String b = X509Extensions.D.d();
    private static final String c = X509Extensions.p.d();
    private static final String d = X509Extensions.x.d();

    RFC3281CertPathUtilities() {
    }

    protected static void a(X509AttributeCertificate x509AttributeCertificate, CertPath certPath, CertPath certPath2, ExtendedPKIXParameters extendedPKIXParameters) {
        Object criticalExtensionOIDs = x509AttributeCertificate.getCriticalExtensionOIDs();
        if (criticalExtensionOIDs.contains(a)) {
            try {
                TargetInformation.a(CertPathValidatorUtilities.a((X509Extension) x509AttributeCertificate, a));
            } catch (Throwable e) {
                throw new ExtCertPathValidatorException("Target information extension could not be read.", e);
            } catch (Throwable e2) {
                throw new ExtCertPathValidatorException("Target information extension could not be read.", e2);
            }
        }
        criticalExtensionOIDs.remove(a);
        for (PKIXAttrCertChecker a : extendedPKIXParameters.l()) {
            a.a(x509AttributeCertificate, certPath, certPath2, criticalExtensionOIDs);
        }
        if (!criticalExtensionOIDs.isEmpty()) {
            throw new CertPathValidatorException("Attribute certificate contains unsupported critical extensions: " + criticalExtensionOIDs);
        }
    }

    protected static void a(X509AttributeCertificate x509AttributeCertificate, ExtendedPKIXParameters extendedPKIXParameters, X509Certificate x509Certificate, Date date, List list) {
        if (!extendedPKIXParameters.isRevocationEnabled()) {
            return;
        }
        if (x509AttributeCertificate.getExtensionValue(b) == null) {
            try {
                CRLDistPoint a = CRLDistPoint.a(CertPathValidatorUtilities.a((X509Extension) x509AttributeCertificate, c));
                try {
                    Object obj;
                    CertPathValidatorUtilities.a(a, extendedPKIXParameters);
                    CertStatus certStatus = new CertStatus();
                    ReasonsMask reasonsMask = new ReasonsMask();
                    Throwable th = null;
                    if (a != null) {
                        try {
                            DistributionPoint[] d = a.d();
                            int i = 0;
                            obj = null;
                            while (i < d.length && certStatus.b() == 11 && !reasonsMask.a()) {
                                try {
                                    a(d[i], x509AttributeCertificate, (ExtendedPKIXParameters) extendedPKIXParameters.clone(), date, x509Certificate, certStatus, reasonsMask, list);
                                    obj = 1;
                                    i++;
                                } catch (Throwable e) {
                                    th = new AnnotatedException("No valid CRL for distribution point found.", e);
                                }
                            }
                        } catch (Throwable e2) {
                            throw new ExtCertPathValidatorException("Distribution points could not be read.", e2);
                        }
                    }
                    obj = null;
                    if (certStatus.b() == 11 && !reasonsMask.a()) {
                        try {
                            a(new DistributionPoint(new DistributionPointName(0, new GeneralNames(new GeneralName(4, new ASN1InputStream(((X500Principal) x509AttributeCertificate.d().a()[0]).getEncoded()).d()))), null, null), x509AttributeCertificate, (ExtendedPKIXParameters) extendedPKIXParameters.clone(), date, x509Certificate, certStatus, reasonsMask, list);
                            obj = 1;
                        } catch (Throwable e22) {
                            throw new AnnotatedException("Issuer from certificate for CRL could not be reencoded.", e22);
                        } catch (Throwable e222) {
                            th = new AnnotatedException("No valid CRL for distribution point found.", e222);
                        }
                    }
                    if (obj == null) {
                        throw new ExtCertPathValidatorException("No valid CRL found.", th);
                    } else if (certStatus.b() != 11) {
                        throw new CertPathValidatorException(("Attribute certificate revocation after " + certStatus.a()) + ", reason: " + RFC3280CertPathUtilities.o[certStatus.b()]);
                    } else {
                        if (!reasonsMask.a() && certStatus.b() == 11) {
                            certStatus.a(12);
                        }
                        if (certStatus.b() == 12) {
                            throw new CertPathValidatorException("Attribute certificate status could not be determined.");
                        }
                    }
                } catch (Throwable e2222) {
                    throw new CertPathValidatorException("No additional CRL locations could be decoded from CRL distribution point extension.", e2222);
                }
            } catch (Throwable e22222) {
                throw new CertPathValidatorException("CRL distribution point extension could not be read.", e22222);
            }
        } else if (x509AttributeCertificate.getExtensionValue(c) != null || x509AttributeCertificate.getExtensionValue(d) != null) {
            throw new CertPathValidatorException("No rev avail extension is set, but also an AC revocation pointer.");
        }
    }

    protected static void a(X509AttributeCertificate x509AttributeCertificate, ExtendedPKIXParameters extendedPKIXParameters) {
        for (String str : extendedPKIXParameters.k()) {
            if (x509AttributeCertificate.a(str) != null) {
                throw new CertPathValidatorException("Attribute certificate contains prohibited attribute: " + str + ".");
            }
        }
        for (String str2 : extendedPKIXParameters.j()) {
            if (x509AttributeCertificate.a(str2) == null) {
                throw new CertPathValidatorException("Attribute certificate does not contain necessary attribute: " + str2 + ".");
            }
        }
    }

    protected static void b(X509AttributeCertificate x509AttributeCertificate, ExtendedPKIXParameters extendedPKIXParameters) {
        try {
            x509AttributeCertificate.a(CertPathValidatorUtilities.a((PKIXParameters) extendedPKIXParameters));
        } catch (Throwable e) {
            throw new ExtCertPathValidatorException("Attribute certificate is not valid.", e);
        } catch (Throwable e2) {
            throw new ExtCertPathValidatorException("Attribute certificate is not valid.", e2);
        }
    }

    protected static void a(X509Certificate x509Certificate, ExtendedPKIXParameters extendedPKIXParameters) {
        Object obj = null;
        for (TrustAnchor trustAnchor : extendedPKIXParameters.i()) {
            Object obj2;
            if (x509Certificate.getSubjectX500Principal().getName("RFC2253").equals(trustAnchor.getCAName()) || x509Certificate.equals(trustAnchor.getTrustedCert())) {
                obj2 = 1;
            } else {
                obj2 = obj;
            }
            obj = obj2;
        }
        if (obj == null) {
            throw new CertPathValidatorException("Attribute certificate issuer is not directly trusted.");
        }
    }

    protected static void b(X509Certificate x509Certificate, ExtendedPKIXParameters extendedPKIXParameters) {
        if (x509Certificate.getKeyUsage() != null && !x509Certificate.getKeyUsage()[0] && !x509Certificate.getKeyUsage()[1]) {
            throw new CertPathValidatorException("Attribute certificate issuer public key cannot be used to validate digital signatures.");
        } else if (x509Certificate.getBasicConstraints() != -1) {
            throw new CertPathValidatorException("Attribute certificate issuer is also a public key certificate issuer.");
        }
    }

    protected static CertPathValidatorResult a(CertPath certPath, ExtendedPKIXParameters extendedPKIXParameters) {
        try {
            try {
                return CertPathValidator.getInstance("PKIX", "SC").validate(certPath, extendedPKIXParameters);
            } catch (Throwable e) {
                throw new ExtCertPathValidatorException("Certification path for issuer certificate of attribute certificate could not be validated.", e);
            } catch (InvalidAlgorithmParameterException e2) {
                throw new RuntimeException(e2.getMessage());
            }
        } catch (Throwable e3) {
            throw new ExtCertPathValidatorException("Support class could not be created.", e3);
        } catch (Throwable e32) {
            throw new ExtCertPathValidatorException("Support class could not be created.", e32);
        }
    }

    protected static CertPath c(X509AttributeCertificate x509AttributeCertificate, ExtendedPKIXParameters extendedPKIXParameters) {
        CertPathBuilderResult certPathBuilderResult = null;
        int i = 0;
        Set<X509Certificate> hashSet = new HashSet();
        if (x509AttributeCertificate.c().e() != null) {
            X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
            x509CertStoreSelector.setSerialNumber(x509AttributeCertificate.c().f());
            Principal[] e = x509AttributeCertificate.c().e();
            int i2 = 0;
            while (i2 < e.length) {
                try {
                    if (e[i2] instanceof X500Principal) {
                        x509CertStoreSelector.setIssuer(((X500Principal) e[i2]).getEncoded());
                    }
                    hashSet.addAll(CertPathValidatorUtilities.a(x509CertStoreSelector, extendedPKIXParameters.f()));
                    i2++;
                } catch (Throwable e2) {
                    throw new ExtCertPathValidatorException("Public key certificate for attribute certificate cannot be searched.", e2);
                } catch (Throwable e22) {
                    throw new ExtCertPathValidatorException("Unable to encode X500 principal.", e22);
                }
            }
            if (hashSet.isEmpty()) {
                throw new CertPathValidatorException("Public key certificate specified in base certificate ID for attribute certificate cannot be found.");
            }
        }
        if (x509AttributeCertificate.c().d() != null) {
            X509CertStoreSelector x509CertStoreSelector2 = new X509CertStoreSelector();
            Principal[] d = x509AttributeCertificate.c().d();
            while (i < d.length) {
                try {
                    if (d[i] instanceof X500Principal) {
                        x509CertStoreSelector2.setIssuer(((X500Principal) d[i]).getEncoded());
                    }
                    hashSet.addAll(CertPathValidatorUtilities.a(x509CertStoreSelector2, extendedPKIXParameters.f()));
                    i++;
                } catch (Throwable e222) {
                    throw new ExtCertPathValidatorException("Public key certificate for attribute certificate cannot be searched.", e222);
                } catch (Throwable e2222) {
                    throw new ExtCertPathValidatorException("Unable to encode X500 principal.", e2222);
                }
            }
            if (hashSet.isEmpty()) {
                throw new CertPathValidatorException("Public key certificate specified in entity name for attribute certificate cannot be found.");
            }
        }
        ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters = (ExtendedPKIXBuilderParameters) ExtendedPKIXBuilderParameters.b(extendedPKIXParameters);
        CertPathBuilderResult certPathBuilderResult2 = null;
        for (X509Certificate certificate : hashSet) {
            Selector x509CertStoreSelector3 = new X509CertStoreSelector();
            x509CertStoreSelector3.setCertificate(certificate);
            extendedPKIXBuilderParameters.a(x509CertStoreSelector3);
            try {
                ExtCertPathValidatorException extCertPathValidatorException;
                try {
                    CertPathBuilderResult certPathBuilderResult3 = certPathBuilderResult2;
                    certPathBuilderResult2 = CertPathBuilder.getInstance("PKIX", "SC").build(ExtendedPKIXBuilderParameters.b(extendedPKIXBuilderParameters));
                    extCertPathValidatorException = certPathBuilderResult3;
                } catch (Throwable e3) {
                    extCertPathValidatorException = new ExtCertPathValidatorException("Certification path for public key certificate of attribute certificate could not be build.", e3);
                    certPathBuilderResult2 = certPathBuilderResult;
                } catch (InvalidAlgorithmParameterException e4) {
                    throw new RuntimeException(e4.getMessage());
                }
                certPathBuilderResult = certPathBuilderResult2;
                Object certPathBuilderResult22 = extCertPathValidatorException;
            } catch (Throwable e22222) {
                throw new ExtCertPathValidatorException("Support class could not be created.", e22222);
            } catch (Throwable e222222) {
                throw new ExtCertPathValidatorException("Support class could not be created.", e222222);
            }
        }
        if (certPathBuilderResult22 == null) {
            return certPathBuilderResult.getCertPath();
        }
        throw certPathBuilderResult22;
    }

    private static void a(DistributionPoint distributionPoint, X509AttributeCertificate x509AttributeCertificate, ExtendedPKIXParameters extendedPKIXParameters, Date date, X509Certificate x509Certificate, CertStatus certStatus, ReasonsMask reasonsMask, List list) {
        if (x509AttributeCertificate.getExtensionValue(X509Extensions.D.d()) == null) {
            Date date2 = new Date(System.currentTimeMillis());
            if (date.getTime() > date2.getTime()) {
                throw new AnnotatedException("Validation time is in future.");
            }
            Iterator it = CertPathValidatorUtilities.a(distributionPoint, (Object) x509AttributeCertificate, date2, extendedPKIXParameters).iterator();
            AnnotatedException annotatedException = null;
            Object obj = null;
            while (it.hasNext() && certStatus.b() == 11 && !reasonsMask.a()) {
                try {
                    X509CRL x509crl = (X509CRL) it.next();
                    ReasonsMask a = RFC3280CertPathUtilities.a(x509crl, distributionPoint);
                    if (a.c(reasonsMask)) {
                        PublicKey a2 = RFC3280CertPathUtilities.a(x509crl, RFC3280CertPathUtilities.a(x509crl, (Object) x509AttributeCertificate, null, null, extendedPKIXParameters, list));
                        X509CRL x509crl2 = null;
                        if (extendedPKIXParameters.c()) {
                            x509crl2 = RFC3280CertPathUtilities.a(CertPathValidatorUtilities.a(date2, extendedPKIXParameters, x509crl), a2);
                        }
                        if (extendedPKIXParameters.d() == 1 || x509AttributeCertificate.b().getTime() >= x509crl.getThisUpdate().getTime()) {
                            RFC3280CertPathUtilities.b(distributionPoint, (Object) x509AttributeCertificate, x509crl);
                            RFC3280CertPathUtilities.a(distributionPoint, (Object) x509AttributeCertificate, x509crl);
                            RFC3280CertPathUtilities.a(x509crl2, x509crl, extendedPKIXParameters);
                            RFC3280CertPathUtilities.a(date, x509crl2, (Object) x509AttributeCertificate, certStatus, extendedPKIXParameters);
                            RFC3280CertPathUtilities.a(date, x509crl, (Object) x509AttributeCertificate, certStatus);
                            if (certStatus.b() == 8) {
                                certStatus.a(11);
                            }
                            reasonsMask.a(a);
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
    }
}
