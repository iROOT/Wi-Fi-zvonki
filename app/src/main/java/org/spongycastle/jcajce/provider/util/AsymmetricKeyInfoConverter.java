package org.spongycastle.jcajce.provider.util;

import java.security.PrivateKey;
import java.security.PublicKey;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;

public interface AsymmetricKeyInfoConverter {
    PrivateKey a(PrivateKeyInfo privateKeyInfo);

    PublicKey a(SubjectPublicKeyInfo subjectPublicKeyInfo);
}
