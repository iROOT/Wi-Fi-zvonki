package org.spongycastle.ocsp;

import org.spongycastle.asn1.ocsp.RevokedInfo;

public class RevokedStatus implements CertificateStatus {
    RevokedInfo b;

    public RevokedStatus(RevokedInfo revokedInfo) {
        this.b = revokedInfo;
    }
}
