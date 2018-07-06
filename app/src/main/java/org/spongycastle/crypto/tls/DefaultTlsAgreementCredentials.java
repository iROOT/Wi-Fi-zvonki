package org.spongycastle.crypto.tls;

import java.math.BigInteger;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.util.BigIntegers;

public class DefaultTlsAgreementCredentials extends AbstractTlsAgreementCredentials {
    protected Certificate a;
    protected AsymmetricKeyParameter b;
    protected BasicAgreement c;
    protected boolean d;

    public Certificate a() {
        return this.a;
    }

    public byte[] a(AsymmetricKeyParameter asymmetricKeyParameter) {
        this.c.a(this.b);
        BigInteger b = this.c.b(asymmetricKeyParameter);
        if (this.d) {
            return BigIntegers.a(b);
        }
        return BigIntegers.a(this.c.a(), b);
    }
}
