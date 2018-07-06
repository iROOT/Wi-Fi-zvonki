package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.X509Certificate;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.util.Selector;
import org.spongycastle.x509.ExtendedPKIXParameters;
import org.spongycastle.x509.X509AttributeCertStoreSelector;
import org.spongycastle.x509.X509AttributeCertificate;

public class PKIXAttrCertPathValidatorSpi extends CertPathValidatorSpi {
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) {
        if (certPathParameters instanceof ExtendedPKIXParameters) {
            ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
            Selector h = extendedPKIXParameters.h();
            if (h instanceof X509AttributeCertStoreSelector) {
                X509AttributeCertificate a = ((X509AttributeCertStoreSelector) h).a();
                CertPath c = RFC3281CertPathUtilities.c(a, extendedPKIXParameters);
                CertPathValidatorResult a2 = RFC3281CertPathUtilities.a(certPath, extendedPKIXParameters);
                X509Certificate x509Certificate = (X509Certificate) certPath.getCertificates().get(0);
                RFC3281CertPathUtilities.b(x509Certificate, extendedPKIXParameters);
                RFC3281CertPathUtilities.a(x509Certificate, extendedPKIXParameters);
                RFC3281CertPathUtilities.b(a, extendedPKIXParameters);
                RFC3281CertPathUtilities.a(a, certPath, c, extendedPKIXParameters);
                RFC3281CertPathUtilities.a(a, extendedPKIXParameters);
                try {
                    RFC3281CertPathUtilities.a(a, extendedPKIXParameters, x509Certificate, CertPathValidatorUtilities.a(extendedPKIXParameters, null, -1), certPath.getCertificates());
                    return a2;
                } catch (Throwable e) {
                    throw new ExtCertPathValidatorException("Could not get validity date from attribute certificate.", e);
                }
            }
            throw new InvalidAlgorithmParameterException("TargetConstraints must be an instance of " + X509AttributeCertStoreSelector.class.getName() + " for " + getClass().getName() + " class.");
        }
        throw new InvalidAlgorithmParameterException("Parameters must be a " + ExtendedPKIXParameters.class.getName() + " instance.");
    }
}
