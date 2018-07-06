package org.spongycastle.x509;

import java.security.cert.CertPath;
import java.util.Collection;

public abstract class PKIXAttrCertChecker implements Cloneable {
    public abstract void a(X509AttributeCertificate x509AttributeCertificate, CertPath certPath, CertPath certPath2, Collection collection);

    public abstract Object clone();
}
