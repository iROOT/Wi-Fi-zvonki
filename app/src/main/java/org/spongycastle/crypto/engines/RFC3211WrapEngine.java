package org.spongycastle.crypto.engines;

import java.security.SecureRandom;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class RFC3211WrapEngine implements Wrapper {
    private CBCBlockCipher a;
    private ParametersWithIV b;
    private boolean c;
    private SecureRandom d;

    public RFC3211WrapEngine(BlockCipher blockCipher) {
        this.a = new CBCBlockCipher(blockCipher);
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        this.c = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.d = parametersWithRandom.a();
            this.b = (ParametersWithIV) parametersWithRandom.b();
            return;
        }
        if (z) {
            this.d = new SecureRandom();
        }
        this.b = (ParametersWithIV) cipherParameters;
    }

    public String a() {
        return this.a.d().a() + "/RFC3211Wrap";
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.c) {
            byte[] bArr2;
            int i4;
            this.a.a(true, this.b);
            int b = this.a.b();
            if (i2 + 4 < b * 2) {
                bArr2 = new byte[(b * 2)];
            } else {
                bArr2 = new byte[((i2 + 4) % b == 0 ? i2 + 4 : (((i2 + 4) / b) + 1) * b)];
            }
            bArr2[0] = (byte) i2;
            bArr2[1] = (byte) (bArr[i] ^ -1);
            bArr2[2] = (byte) (bArr[i + 1] ^ -1);
            bArr2[3] = (byte) (bArr[i + 2] ^ -1);
            System.arraycopy(bArr, i, bArr2, 4, i2);
            for (i4 = i2 + 4; i4 < bArr2.length; i4++) {
                bArr2[i4] = (byte) this.d.nextInt();
            }
            for (i4 = 0; i4 < bArr2.length; i4 += b) {
                this.a.a(bArr2, i4, bArr2, i4);
            }
            while (i3 < bArr2.length) {
                this.a.a(bArr2, i3, bArr2, i3);
                i3 += b;
            }
            return bArr2;
        }
        throw new IllegalStateException("not set for wrapping");
    }

    public byte[] b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.c) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int b = this.a.b();
        if (i2 < b * 2) {
            throw new InvalidCipherTextException("input too short");
        }
        int i4;
        Object obj = new byte[i2];
        Object obj2 = new byte[b];
        System.arraycopy(bArr, i, obj, 0, i2);
        System.arraycopy(bArr, i, obj2, 0, obj2.length);
        this.a.a(false, new ParametersWithIV(this.b.b(), obj2));
        for (i4 = b; i4 < obj.length; i4 += b) {
            this.a.a(obj, i4, obj, i4);
        }
        System.arraycopy(obj, obj.length - obj2.length, obj2, 0, obj2.length);
        this.a.a(false, new ParametersWithIV(this.b.b(), obj2));
        this.a.a(obj, 0, obj, 0);
        this.a.a(false, this.b);
        for (i4 = 0; i4 < obj.length; i4 += b) {
            this.a.a(obj, i4, obj, i4);
        }
        if ((obj[0] & 255) > obj.length - 4) {
            throw new InvalidCipherTextException("wrapped key corrupted");
        }
        Object obj3 = new byte[(obj[0] & 255)];
        System.arraycopy(obj, 4, obj3, 0, obj[0]);
        i4 = 0;
        while (i3 != 3) {
            i4 |= ((byte) (obj[i3 + 1] ^ -1)) ^ obj3[i3];
            i3++;
        }
        if (i4 == 0) {
            return obj3;
        }
        throw new InvalidCipherTextException("wrapped key fails checksum");
    }
}
