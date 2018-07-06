package org.spongycastle.crypto.agreement;

import java.math.BigInteger;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class DHBasicAgreement implements BasicAgreement {
    private DHPrivateKeyParameters a;
    private DHParameters b;

    public void a(CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            asymmetricKeyParameter = (AsymmetricKeyParameter) ((ParametersWithRandom) cipherParameters).b();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
        }
        if (asymmetricKeyParameter instanceof DHPrivateKeyParameters) {
            this.a = (DHPrivateKeyParameters) asymmetricKeyParameter;
            this.b = this.a.b();
            return;
        }
        throw new IllegalArgumentException("DHEngine expects DHPrivateKeyParameters");
    }

    public int a() {
        return (this.a.b().a().bitLength() + 7) / 8;
    }

    public BigInteger b(CipherParameters cipherParameters) {
        DHPublicKeyParameters dHPublicKeyParameters = (DHPublicKeyParameters) cipherParameters;
        if (dHPublicKeyParameters.b().equals(this.b)) {
            return dHPublicKeyParameters.c().modPow(this.a.c(), this.b.a());
        }
        throw new IllegalArgumentException("Diffie-Hellman public key has wrong parameters.");
    }
}
