package org.spongycastle.pqc.jcajce.provider.util;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.CipherSpi;

public abstract class CipherSpiExt extends CipherSpi {
    protected int w_;

    public abstract int a();

    public abstract int a(Key key);

    public abstract int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    protected abstract void a(String str);

    public abstract byte[] a(byte[] bArr, int i, int i2);

    public abstract int a_(int i);

    public abstract int b(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    protected abstract void b(String str);

    public abstract void b(Key key, AlgorithmParameterSpec algorithmParameterSpec);

    public abstract void b(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom);

    public abstract byte[] b();

    public abstract byte[] b(byte[] bArr, int i, int i2);

    protected final void engineInit(int i, Key key, SecureRandom secureRandom) {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    protected final void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        if (algorithmParameters == null) {
            engineInit(i, key, secureRandom);
        } else {
            engineInit(i, key, null, secureRandom);
        }
    }

    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec != null && !(algorithmParameterSpec instanceof AlgorithmParameterSpec)) {
            throw new InvalidAlgorithmParameterException();
        } else if (key == null || !(key instanceof Key)) {
            throw new InvalidKeyException();
        } else {
            this.w_ = i;
            if (i == 1) {
                b(key, algorithmParameterSpec, secureRandom);
            } else if (i == 2) {
                b(key, algorithmParameterSpec);
            }
        }
    }

    protected final byte[] engineDoFinal(byte[] bArr, int i, int i2) {
        return b(bArr, i, i2);
    }

    protected final int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        return b(bArr, i, i2, bArr2, i3);
    }

    protected final int engineGetBlockSize() {
        return a();
    }

    protected final int engineGetKeySize(Key key) {
        if (key instanceof Key) {
            return a(key);
        }
        throw new InvalidKeyException("Unsupported key.");
    }

    protected final byte[] engineGetIV() {
        return b();
    }

    protected final int engineGetOutputSize(int i) {
        return a_(i);
    }

    protected final AlgorithmParameters engineGetParameters() {
        return null;
    }

    protected final void engineSetMode(String str) {
        a(str);
    }

    protected final void engineSetPadding(String str) {
        b(str);
    }

    protected final byte[] engineUpdate(byte[] bArr, int i, int i2) {
        return a(bArr, i, i2);
    }

    protected final int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        return a(bArr, i, i2, bArr2, i3);
    }
}
