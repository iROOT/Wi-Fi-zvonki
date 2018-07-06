package org.spongycastle.x509;

import java.math.BigInteger;
import java.security.cert.X509Extension;
import java.util.Date;

public interface X509AttributeCertificate extends X509Extension {
    BigInteger a();

    void a(Date date);

    X509Attribute[] a(String str);

    Date b();

    AttributeCertificateHolder c();

    AttributeCertificateIssuer d();

    byte[] e();
}
