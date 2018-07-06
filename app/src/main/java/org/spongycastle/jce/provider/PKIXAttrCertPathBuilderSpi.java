package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.Principal;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.jce.exception.ExtCertPathBuilderException;
import org.spongycastle.util.Selector;
import org.spongycastle.x509.ExtendedPKIXBuilderParameters;
import org.spongycastle.x509.ExtendedPKIXParameters;
import org.spongycastle.x509.X509AttributeCertStoreSelector;
import org.spongycastle.x509.X509AttributeCertificate;
import org.spongycastle.x509.X509CertStoreSelector;

public class PKIXAttrCertPathBuilderSpi extends CertPathBuilderSpi {
    private Exception a;

    public CertPathBuilderResult engineBuild(CertPathParameters certPathParameters) {
        if ((certPathParameters instanceof PKIXBuilderParameters) || (certPathParameters instanceof ExtendedPKIXBuilderParameters)) {
            ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters;
            if (certPathParameters instanceof ExtendedPKIXBuilderParameters) {
                extendedPKIXBuilderParameters = (ExtendedPKIXBuilderParameters) certPathParameters;
            } else {
                extendedPKIXBuilderParameters = (ExtendedPKIXBuilderParameters) ExtendedPKIXBuilderParameters.b((PKIXBuilderParameters) certPathParameters);
            }
            List arrayList = new ArrayList();
            Selector h = extendedPKIXBuilderParameters.h();
            if (h instanceof X509AttributeCertStoreSelector) {
                try {
                    Collection a = CertPathValidatorUtilities.a((X509AttributeCertStoreSelector) h, extendedPKIXBuilderParameters.f());
                    if (a.isEmpty()) {
                        throw new CertPathBuilderException("No attribute certificate found matching targetContraints.");
                    }
                    CertPathBuilderResult certPathBuilderResult = null;
                    Iterator it = a.iterator();
                    while (it.hasNext() && certPathBuilderResult == null) {
                        X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate) it.next();
                        X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
                        Principal[] a2 = x509AttributeCertificate.d().a();
                        Set hashSet = new HashSet();
                        int i = 0;
                        while (i < a2.length) {
                            try {
                                if (a2[i] instanceof X500Principal) {
                                    x509CertStoreSelector.setSubject(((X500Principal) a2[i]).getEncoded());
                                }
                                hashSet.addAll(CertPathValidatorUtilities.a(x509CertStoreSelector, extendedPKIXBuilderParameters.f()));
                                hashSet.addAll(CertPathValidatorUtilities.a(x509CertStoreSelector, extendedPKIXBuilderParameters.getCertStores()));
                                i++;
                            } catch (Throwable e) {
                                throw new ExtCertPathBuilderException("Public key certificate for attribute certificate cannot be searched.", e);
                            } catch (Throwable e2) {
                                throw new ExtCertPathBuilderException("cannot encode X500Principal.", e2);
                            }
                        }
                        if (hashSet.isEmpty()) {
                            throw new CertPathBuilderException("Public key certificate for attribute certificate cannot be found.");
                        }
                        Iterator it2 = hashSet.iterator();
                        CertPathBuilderResult certPathBuilderResult2 = certPathBuilderResult;
                        while (it2.hasNext() && certPathBuilderResult2 == null) {
                            certPathBuilderResult2 = a(x509AttributeCertificate, (X509Certificate) it2.next(), extendedPKIXBuilderParameters, arrayList);
                        }
                        certPathBuilderResult = certPathBuilderResult2;
                    }
                    if (certPathBuilderResult == null && this.a != null) {
                        throw new ExtCertPathBuilderException("Possible certificate chain could not be validated.", this.a);
                    } else if (certPathBuilderResult != null || this.a != null) {
                        return certPathBuilderResult;
                    } else {
                        throw new CertPathBuilderException("Unable to find certificate chain.");
                    }
                } catch (Throwable e22) {
                    throw new ExtCertPathBuilderException("Error finding target attribute certificate.", e22);
                }
            }
            throw new CertPathBuilderException("TargetConstraints must be an instance of " + X509AttributeCertStoreSelector.class.getName() + " for " + getClass().getName() + " class.");
        }
        throw new InvalidAlgorithmParameterException("Parameters must be an instance of " + PKIXBuilderParameters.class.getName() + " or " + ExtendedPKIXBuilderParameters.class.getName() + ".");
    }

    private CertPathBuilderResult a(X509AttributeCertificate x509AttributeCertificate, X509Certificate x509Certificate, ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters, List list) {
        CertPathBuilderResult certPathBuilderResult = null;
        if (list.contains(x509Certificate)) {
            return null;
        }
        if (extendedPKIXBuilderParameters.a().contains(x509Certificate)) {
            return null;
        }
        if (extendedPKIXBuilderParameters.b() != -1 && list.size() - 1 > extendedPKIXBuilderParameters.b()) {
            return null;
        }
        list.add(x509Certificate);
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509", "SC");
            CertPathValidator instance2 = CertPathValidator.getInstance("RFC3281", "SC");
            CertPathBuilderResult certPathBuilderResult2;
            try {
                if (CertPathValidatorUtilities.a(x509Certificate, extendedPKIXBuilderParameters.getTrustAnchors(), extendedPKIXBuilderParameters.getSigProvider()) != null) {
                    CertPath generateCertPath = instance.generateCertPath(list);
                    PKIXCertPathValidatorResult pKIXCertPathValidatorResult = (PKIXCertPathValidatorResult) instance2.validate(generateCertPath, extendedPKIXBuilderParameters);
                    return new PKIXCertPathBuilderResult(generateCertPath, pKIXCertPathValidatorResult.getTrustAnchor(), pKIXCertPathValidatorResult.getPolicyTree(), pKIXCertPathValidatorResult.getPublicKey());
                }
                CertPathValidatorUtilities.a(x509Certificate, (ExtendedPKIXParameters) extendedPKIXBuilderParameters);
                Collection hashSet = new HashSet();
                hashSet.addAll(CertPathValidatorUtilities.a(x509Certificate, extendedPKIXBuilderParameters));
                if (hashSet.isEmpty()) {
                    throw new AnnotatedException("No issuer certificate for certificate in certification path found.");
                }
                Iterator it = hashSet.iterator();
                while (it.hasNext() && certPathBuilderResult == null) {
                    X509Certificate x509Certificate2 = (X509Certificate) it.next();
                    if (!x509Certificate2.getIssuerX500Principal().equals(x509Certificate2.getSubjectX500Principal())) {
                        certPathBuilderResult = a(x509AttributeCertificate, x509Certificate2, extendedPKIXBuilderParameters, list);
                    }
                }
                certPathBuilderResult2 = certPathBuilderResult;
                if (certPathBuilderResult2 != null) {
                    return certPathBuilderResult2;
                }
                list.remove(x509Certificate);
                return certPathBuilderResult2;
            } catch (Throwable e) {
                throw new AnnotatedException("Cannot find issuer certificate for certificate in certification path.", e);
            } catch (Throwable e2) {
                throw new AnnotatedException("No additional X.509 stores can be added from certificate locations.", e2);
            } catch (Throwable e22) {
                throw new AnnotatedException("Certification path could not be validated.", e22);
            } catch (Throwable e222) {
                throw new AnnotatedException("Certification path could not be constructed from certificate list.", e222);
            } catch (Throwable e2222) {
                this.a = new AnnotatedException("No valid certification path could be build.", e2222);
                certPathBuilderResult2 = null;
            }
        } catch (Exception e3) {
            throw new RuntimeException("Exception creating support classes.");
        }
    }
}
