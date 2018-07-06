package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.crypto.CipherKeyGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;

public class BaseKeyGenerator extends KeyGeneratorSpi {
    protected String a;
    protected int b;
    protected int c;
    protected CipherKeyGenerator d;
    protected boolean e = true;

    protected BaseKeyGenerator(String str, int i, CipherKeyGenerator cipherKeyGenerator) {
        this.a = str;
        this.c = i;
        this.b = i;
        this.d = cipherKeyGenerator;
    }

    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        throw new InvalidAlgorithmParameterException("Not Implemented");
    }

    protected void engineInit(SecureRandom secureRandom) {
        if (secureRandom != null) {
            this.d.a(new KeyGenerationParameters(secureRandom, this.c));
            this.e = false;
        }
    }

    protected void engineInit(int i, SecureRandom secureRandom) {
        if (secureRandom == null) {
            try {
                secureRandom = new SecureRandom();
            } catch (IllegalArgumentException e) {
                throw new InvalidParameterException(e.getMessage());
            }
        }
        this.d.a(new KeyGenerationParameters(secureRandom, i));
        this.e = false;
    }

    protected SecretKey engineGenerateKey() {
        if (this.e) {
            this.d.a(new KeyGenerationParameters(new SecureRandom(), this.c));
            this.e = false;
        }
        return new SecretKeySpec(this.d.a(), this.a);
    }
}
