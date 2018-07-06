package org.spongycastle.x509;

import java.security.cert.CRLException;
import org.spongycastle.asn1.x509.V2TBSCertListGenerator;
import org.spongycastle.asn1.x509.X509ExtensionsGenerator;

public class X509V2CRLGenerator {
    private V2TBSCertListGenerator a = new V2TBSCertListGenerator();
    private X509ExtensionsGenerator b = new X509ExtensionsGenerator();

    private static class ExtCRLException extends CRLException {
        Throwable a;

        public Throwable getCause() {
            return this.a;
        }
    }
}
