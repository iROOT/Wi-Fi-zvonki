package org.spongycastle.pqc.jcajce.provider.util;

import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.ShortBufferException;

public abstract class AsymmetricHybridCipher extends CipherSpiExt {
    protected abstract int a(int i);

    protected abstract void a(Key key, AlgorithmParameterSpec algorithmParameterSpec);

    protected abstract void a(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom);

    public abstract byte[] a(byte[] bArr, int i, int i2);

    protected abstract int b(int i);

    public abstract byte[] b(byte[] bArr, int i, int i2);

    protected final void a(String str) {
    }

    protected final void b(String str) {
    }

    public final byte[] b() {
        return null;
    }

    public final int a() {
        return 0;
    }

    public final int a_(int i) {
        return this.w_ == 1 ? a(i) : b(i);
    }

    public final void b(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        this.w_ = 1;
        a(key, algorithmParameterSpec, secureRandom);
    }

    public final void b(Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        this.w_ = 2;
        a(key, algorithmParameterSpec);
    }

    public final int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (bArr2.length < a_(i2)) {
            throw new ShortBufferException("output");
        }
        Object a = a(bArr, i, i2);
        System.arraycopy(a, 0, bArr2, i3, a.length);
        return a.length;
    }

    public final int b(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (bArr2.length < a_(i2)) {
            throw new ShortBufferException("Output buffer too short.");
        }
        Object b = b(bArr, i, i2);
        System.arraycopy(b, 0, bArr2, i3, b.length);
        return b.length;
    }
}
