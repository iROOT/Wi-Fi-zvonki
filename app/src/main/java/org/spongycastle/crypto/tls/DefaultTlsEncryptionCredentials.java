package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.encodings.PKCS1Encoding;
import org.spongycastle.crypto.engines.RSABlindedEngine;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class DefaultTlsEncryptionCredentials extends AbstractTlsEncryptionCredentials {
    protected TlsContext a;
    protected Certificate b;
    protected AsymmetricKeyParameter c;

    public Certificate a() {
        return this.b;
    }

    public byte[] a(byte[] bArr) {
        PKCS1Encoding pKCS1Encoding = new PKCS1Encoding(new RSABlindedEngine());
        pKCS1Encoding.a(false, new ParametersWithRandom(this.c, this.a.a()));
        try {
            return pKCS1Encoding.a(bArr, 0, bArr.length);
        } catch (InvalidCipherTextException e) {
            throw new TlsFatalAlert((short) 47);
        }
    }
}
