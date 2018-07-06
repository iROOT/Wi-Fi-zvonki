package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.x509.ExtendedPKIXParameters;

public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) {
        if (certPathParameters instanceof PKIXParameters) {
            ExtendedPKIXParameters extendedPKIXParameters;
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
            } else {
                extendedPKIXParameters = ExtendedPKIXParameters.c((PKIXParameters) certPathParameters);
            }
            if (extendedPKIXParameters.getTrustAnchors() == null) {
                throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
            }
            List certificates = certPath.getCertificates();
            int size = certificates.size();
            if (certificates.isEmpty()) {
                throw new CertPathValidatorException("Certification path is empty.", null, certPath, 0);
            }
            Set initialPolicies = extendedPKIXParameters.getInitialPolicies();
            try {
                TrustAnchor a = CertPathValidatorUtilities.a((X509Certificate) certificates.get(certificates.size() - 1), extendedPKIXParameters.getTrustAnchors(), extendedPKIXParameters.getSigProvider());
                if (a == null) {
                    throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
                }
                int i;
                int i2;
                int i3;
                int i4;
                PublicKey publicKey;
                X500Principal a2;
                List[] listArr = new ArrayList[(size + 1)];
                for (i = 0; i < listArr.length; i++) {
                    listArr[i] = new ArrayList();
                }
                Set hashSet = new HashSet();
                hashSet.add("2.5.29.32.0");
                PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet, null, new HashSet(), "2.5.29.32.0", false);
                listArr[0].add(pKIXPolicyNode);
                PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
                Set hashSet2 = new HashSet();
                if (extendedPKIXParameters.isExplicitPolicyRequired()) {
                    i2 = 0;
                } else {
                    i2 = size + 1;
                }
                if (extendedPKIXParameters.isAnyPolicyInhibited()) {
                    i3 = 0;
                } else {
                    i3 = size + 1;
                }
                if (extendedPKIXParameters.isPolicyMappingInhibited()) {
                    i4 = 0;
                } else {
                    i4 = size + 1;
                }
                X509Certificate trustedCert = a.getTrustedCert();
                if (trustedCert != null) {
                    try {
                        publicKey = trustedCert.getPublicKey();
                        a2 = CertPathValidatorUtilities.a(trustedCert);
                    } catch (Throwable e) {
                        throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e, certPath, -1);
                    }
                }
                publicKey = a.getCAPublicKey();
                a2 = new X500Principal(a.getCAName());
                try {
                    AlgorithmIdentifier a3 = CertPathValidatorUtilities.a(publicKey);
                    a3.d();
                    a3.f();
                    if (extendedPKIXParameters.h() == null || extendedPKIXParameters.h().a((X509Certificate) certificates.get(0))) {
                        int i5;
                        Collection criticalExtensionOIDs;
                        Set hashSet3;
                        List<PKIXCertPathChecker> certPathCheckers = extendedPKIXParameters.getCertPathCheckers();
                        for (PKIXCertPathChecker init : certPathCheckers) {
                            init.init(false);
                        }
                        X509Certificate x509Certificate = null;
                        int i6 = size;
                        int i7 = i4;
                        int i8 = i3;
                        int i9 = i2;
                        PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
                        PublicKey publicKey2 = publicKey;
                        i2 = certificates.size() - 1;
                        X509Certificate x509Certificate2 = trustedCert;
                        while (i2 >= 0) {
                            PKIXPolicyNode pKIXPolicyNode3;
                            int i10 = size - i2;
                            x509Certificate = (X509Certificate) certificates.get(i2);
                            RFC3280CertPathUtilities.a(certPath, extendedPKIXParameters, i2, publicKey2, i2 == certificates.size() + -1, a2, x509Certificate2);
                            RFC3280CertPathUtilities.a(certPath, i2, pKIXNameConstraintValidator);
                            pKIXPolicyNode = RFC3280CertPathUtilities.a(certPath, i2, RFC3280CertPathUtilities.a(certPath, i2, hashSet2, pKIXPolicyNode2, listArr, i8));
                            RFC3280CertPathUtilities.a(certPath, i2, pKIXPolicyNode, i9);
                            if (i10 == size) {
                                i5 = i7;
                                i4 = i9;
                                pKIXPolicyNode3 = pKIXPolicyNode;
                                i = i6;
                            } else if (x509Certificate == null || x509Certificate.getVersion() != 1) {
                                RFC3280CertPathUtilities.a(certPath, i2);
                                pKIXPolicyNode3 = RFC3280CertPathUtilities.a(certPath, i2, listArr, pKIXPolicyNode, i7);
                                RFC3280CertPathUtilities.b(certPath, i2, pKIXNameConstraintValidator);
                                i = RFC3280CertPathUtilities.f(certPath, i2, i9);
                                i5 = RFC3280CertPathUtilities.g(certPath, i2, i7);
                                i3 = RFC3280CertPathUtilities.h(certPath, i2, i8);
                                int a4 = RFC3280CertPathUtilities.a(certPath, i2, i);
                                i4 = RFC3280CertPathUtilities.b(certPath, i2, i5);
                                i8 = RFC3280CertPathUtilities.c(certPath, i2, i3);
                                RFC3280CertPathUtilities.b(certPath, i2);
                                i5 = RFC3280CertPathUtilities.e(certPath, i2, RFC3280CertPathUtilities.d(certPath, i2, i6));
                                RFC3280CertPathUtilities.c(certPath, i2);
                                criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
                                if (criticalExtensionOIDs != null) {
                                    hashSet3 = new HashSet(criticalExtensionOIDs);
                                    hashSet3.remove(RFC3280CertPathUtilities.m);
                                    hashSet3.remove(RFC3280CertPathUtilities.a);
                                    hashSet3.remove(RFC3280CertPathUtilities.b);
                                    hashSet3.remove(RFC3280CertPathUtilities.c);
                                    hashSet3.remove(RFC3280CertPathUtilities.d);
                                    hashSet3.remove(RFC3280CertPathUtilities.f);
                                    hashSet3.remove(RFC3280CertPathUtilities.g);
                                    hashSet3.remove(RFC3280CertPathUtilities.h);
                                    hashSet3.remove(RFC3280CertPathUtilities.j);
                                    hashSet3.remove(RFC3280CertPathUtilities.k);
                                } else {
                                    hashSet3 = new HashSet();
                                }
                                RFC3280CertPathUtilities.a(certPath, i2, hashSet3, (List) certPathCheckers);
                                a2 = CertPathValidatorUtilities.a(x509Certificate);
                                try {
                                    publicKey2 = CertPathValidatorUtilities.a(certPath.getCertificates(), i2);
                                    AlgorithmIdentifier a5 = CertPathValidatorUtilities.a(publicKey2);
                                    a5.d();
                                    a5.f();
                                    i = i5;
                                    i5 = i4;
                                    i4 = a4;
                                    x509Certificate2 = x509Certificate;
                                } catch (Throwable e2) {
                                    throw new CertPathValidatorException("Next working key could not be retrieved.", e2, certPath, i2);
                                }
                            } else {
                                throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i2);
                            }
                            i2--;
                            i6 = i;
                            i7 = i5;
                            i9 = i4;
                            pKIXPolicyNode2 = pKIXPolicyNode3;
                        }
                        i5 = RFC3280CertPathUtilities.i(certPath, i2 + 1, RFC3280CertPathUtilities.a(i9, x509Certificate));
                        criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs != null) {
                            hashSet3 = new HashSet(criticalExtensionOIDs);
                            hashSet3.remove(RFC3280CertPathUtilities.m);
                            hashSet3.remove(RFC3280CertPathUtilities.a);
                            hashSet3.remove(RFC3280CertPathUtilities.b);
                            hashSet3.remove(RFC3280CertPathUtilities.c);
                            hashSet3.remove(RFC3280CertPathUtilities.d);
                            hashSet3.remove(RFC3280CertPathUtilities.f);
                            hashSet3.remove(RFC3280CertPathUtilities.g);
                            hashSet3.remove(RFC3280CertPathUtilities.h);
                            hashSet3.remove(RFC3280CertPathUtilities.j);
                            hashSet3.remove(RFC3280CertPathUtilities.k);
                            hashSet3.remove(RFC3280CertPathUtilities.i);
                        } else {
                            hashSet3 = new HashSet();
                        }
                        RFC3280CertPathUtilities.a(certPath, i2 + 1, (List) certPathCheckers, hashSet3);
                        PolicyNode a6 = RFC3280CertPathUtilities.a(certPath, extendedPKIXParameters, initialPolicies, i2 + 1, listArr, pKIXPolicyNode2, hashSet2);
                        if (i5 > 0 || a6 != null) {
                            return new PKIXCertPathValidatorResult(a, a6, x509Certificate.getPublicKey());
                        }
                        throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i2);
                    }
                    throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                } catch (Throwable e22) {
                    throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e22, certPath, -1);
                }
            } catch (Throwable e222) {
                throw new CertPathValidatorException(e222.getMessage(), e222, certPath, certificates.size() - 1);
            }
        }
        throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
    }
}
