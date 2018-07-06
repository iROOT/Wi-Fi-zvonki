package org.spongycastle.crypto.encodings;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class PKCS1Encoding implements AsymmetricBlockCipher {
    private SecureRandom a;
    private AsymmetricBlockCipher b;
    private boolean c;
    private boolean d;
    private boolean e = c();

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.b = asymmetricBlockCipher;
    }

    private boolean c() {
        String str = (String) AccessController.doPrivileged(new PrivilegedAction(this) {
            final /* synthetic */ PKCS1Encoding a;

            {
                this.a = r1;
            }

            public Object run() {
                return System.getProperty("org.spongycastle.pkcs1.strict");
            }
        });
        return str == null || str.equals("true");
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.a = parametersWithRandom.a();
            asymmetricKeyParameter = (AsymmetricKeyParameter) parametersWithRandom.b();
        } else {
            this.a = new SecureRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
        }
        this.b.a(z, cipherParameters);
        this.d = asymmetricKeyParameter.a();
        this.c = z;
    }

    public int a() {
        int a = this.b.a();
        if (this.c) {
            return a - 10;
        }
        return a;
    }

    public int b() {
        int b = this.b.b();
        return this.c ? b : b - 10;
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        if (this.c) {
            return b(bArr, i, i2);
        }
        return c(bArr, i, i2);
    }

    private byte[] b(byte[] bArr, int i, int i2) {
        int i3 = 1;
        if (i2 > a()) {
            throw new IllegalArgumentException("input data too large");
        }
        Object obj = new byte[this.b.a()];
        if (this.d) {
            obj[0] = 1;
            while (i3 != (obj.length - i2) - 1) {
                obj[i3] = (byte) -1;
                i3++;
            }
        } else {
            this.a.nextBytes(obj);
            obj[0] = (byte) 2;
            while (i3 != (obj.length - i2) - 1) {
                while (obj[i3] == (byte) 0) {
                    obj[i3] = (byte) this.a.nextInt();
                }
                i3++;
            }
        }
        obj[(obj.length - i2) - 1] = null;
        System.arraycopy(bArr, i, obj, obj.length - i2, i2);
        return this.b.a(obj, 0, obj.length);
    }

    private byte[] c(byte[] bArr, int i, int i2) {
        Object a = this.b.a(bArr, i, i2);
        if (a.length < b()) {
            throw new InvalidCipherTextException("block truncated");
        }
        byte b = a[0];
        if (this.d) {
            if (b != (byte) 2) {
                throw new InvalidCipherTextException("unknown block type");
            }
        } else if (b != (byte) 1) {
            throw new InvalidCipherTextException("unknown block type");
        }
        if (!this.e || a.length == this.b.b()) {
            int i3 = 1;
            while (i3 != a.length) {
                byte b2 = a[i3];
                if (b2 == (byte) 0) {
                    break;
                } else if (b != (byte) 1 || b2 == (byte) -1) {
                    i3++;
                } else {
                    throw new InvalidCipherTextException("block padding incorrect");
                }
            }
            i3++;
            if (i3 > a.length || i3 < 10) {
                throw new InvalidCipherTextException("no data in block");
            }
            Object obj = new byte[(a.length - i3)];
            System.arraycopy(a, i3, obj, 0, obj.length);
            return obj;
        }
        throw new InvalidCipherTextException("block incorrect size");
    }
}
