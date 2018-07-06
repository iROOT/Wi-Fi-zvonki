package org.spongycastle.x509;

import org.spongycastle.asn1.x509.X509Extensions;
import org.spongycastle.jce.provider.CertPathValidatorUtilities;

public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String q = X509Extensions.B.d();
    private static final String r = X509Extensions.p.d();
    private static final String s = X509Extensions.x.d();
}
