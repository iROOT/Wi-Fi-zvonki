package org.spongycastle.pqc.jcajce.provider.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

public abstract class AsymmetricBlockCipher extends CipherSpiExt {
    protected ByteArrayOutputStream a = new ByteArrayOutputStream();
    protected int c;
    protected int x_;

    protected abstract void a(Key key, AlgorithmParameterSpec algorithmParameterSpec);

    protected abstract void a(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom);

    protected abstract byte[] a(byte[] bArr);

    protected abstract byte[] b(byte[] bArr);

    public final int a() {
        return this.w_ == 1 ? this.x_ : this.c;
    }

    public final byte[] b() {
        return null;
    }

    public final int a_(int i) {
        int size = i + this.a.size();
        int a = a();
        if (size > a) {
            return 0;
        }
        return a;
    }

    public final void b(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        this.w_ = 1;
        a(key, algorithmParameterSpec, secureRandom);
    }

    public final void b(Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        this.w_ = 2;
        a(key, algorithmParameterSpec);
    }

    public final byte[] a(byte[] bArr, int i, int i2) {
        if (i2 != 0) {
            this.a.write(bArr, i, i2);
        }
        return new byte[0];
    }

    public final int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        a(bArr, i, i2);
        return 0;
    }

    public final byte[] b(byte[] bArr, int i, int i2) {
        b(i2);
        a(bArr, i, i2);
        byte[] toByteArray = this.a.toByteArray();
        this.a.reset();
        switch (this.w_) {
            case 1:
                return a(toByteArray);
            case 2:
                return b(toByteArray);
            default:
                return null;
        }
    }

    public final int b(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (bArr2.length < a_(i2)) {
            throw new ShortBufferException("Output buffer too short.");
        }
        Object b = b(bArr, i, i2);
        System.arraycopy(b, 0, bArr2, i3, b.length);
        return b.length;
    }

    protected final void a(String str) {
    }

    protected final void b(String str) {
    }

    protected void b(int i) {
        int size = this.a.size() + i;
        if (this.w_ == 1) {
            if (size > this.x_) {
                throw new IllegalBlockSizeException("The length of the plaintext (" + size + " bytes) is not supported by " + "the cipher (max. " + this.x_ + " bytes).");
            }
        } else if (this.w_ == 2 && size != this.c) {
            throw new IllegalBlockSizeException("Illegal ciphertext length (expected " + this.c + " bytes, was " + size + " bytes).");
        }
    }
}
