package org.spongycastle.crypto.tls;

import org.spongycastle.asn1.x509.Certificate;

public class AlwaysValidVerifyer implements CertificateVerifyer {
    public boolean a(Certificate[] certificateArr) {
        return true;
    }
}
