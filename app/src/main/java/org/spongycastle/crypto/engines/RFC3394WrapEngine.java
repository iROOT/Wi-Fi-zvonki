package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

public class RFC3394WrapEngine implements Wrapper {
    private BlockCipher a;
    private KeyParameter b;
    private boolean c;
    private byte[] d = new byte[]{(byte) -90, (byte) -90, (byte) -90, (byte) -90, (byte) -90, (byte) -90, (byte) -90, (byte) -90};

    public RFC3394WrapEngine(BlockCipher blockCipher) {
        this.a = blockCipher;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        CipherParameters b;
        this.c = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            b = ((ParametersWithRandom) cipherParameters).b();
        } else {
            b = cipherParameters;
        }
        if (b instanceof KeyParameter) {
            this.b = (KeyParameter) b;
        } else if (b instanceof ParametersWithIV) {
            this.d = ((ParametersWithIV) b).a();
            this.b = (KeyParameter) ((ParametersWithIV) b).b();
            if (this.d.length != 8) {
                throw new IllegalArgumentException("IV not equal to 8");
            }
        }
    }

    public String a() {
        return this.a.a();
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        if (this.c) {
            int i3 = i2 / 8;
            if (i3 * 8 != i2) {
                throw new DataLengthException("wrap data must be a multiple of 8 bytes");
            }
            Object obj = new byte[(this.d.length + i2)];
            Object obj2 = new byte[(this.d.length + 8)];
            System.arraycopy(this.d, 0, obj, 0, this.d.length);
            System.arraycopy(bArr, i, obj, this.d.length, i2);
            this.a.a(true, this.b);
            for (int i4 = 0; i4 != 6; i4++) {
                for (int i5 = 1; i5 <= i3; i5++) {
                    System.arraycopy(obj, 0, obj2, 0, this.d.length);
                    System.arraycopy(obj, i5 * 8, obj2, this.d.length, 8);
                    this.a.a(obj2, 0, obj2, 0);
                    int i6 = (i3 * i4) + i5;
                    int i7 = 1;
                    while (i6 != 0) {
                        int length = this.d.length - i7;
                        obj2[length] = (byte) (((byte) i6) ^ obj2[length]);
                        i6 >>>= 8;
                        i7++;
                    }
                    System.arraycopy(obj2, 0, obj, 0, 8);
                    System.arraycopy(obj2, 8, obj, i5 * 8, 8);
                }
            }
            return obj;
        }
        throw new IllegalStateException("not set for wrapping");
    }

    public byte[] b(byte[] bArr, int i, int i2) {
        if (this.c) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int i3 = i2 / 8;
        if (i3 * 8 != i2) {
            throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
        }
        Object obj = new byte[(i2 - this.d.length)];
        byte[] bArr2 = new byte[this.d.length];
        Object obj2 = new byte[(this.d.length + 8)];
        System.arraycopy(bArr, i, bArr2, 0, this.d.length);
        System.arraycopy(bArr, this.d.length + i, obj, 0, i2 - this.d.length);
        this.a.a(false, this.b);
        int i4 = i3 - 1;
        for (int i5 = 5; i5 >= 0; i5--) {
            for (int i6 = i4; i6 >= 1; i6--) {
                System.arraycopy(bArr2, 0, obj2, 0, this.d.length);
                System.arraycopy(obj, (i6 - 1) * 8, obj2, this.d.length, 8);
                int i7 = (i4 * i5) + i6;
                i3 = 1;
                while (i7 != 0) {
                    int length = this.d.length - i3;
                    obj2[length] = (byte) (((byte) i7) ^ obj2[length]);
                    i7 >>>= 8;
                    i3++;
                }
                this.a.a(obj2, 0, obj2, 0);
                System.arraycopy(obj2, 0, bArr2, 0, 8);
                System.arraycopy(obj2, 8, obj, (i6 - 1) * 8, 8);
            }
        }
        if (Arrays.b(bArr2, this.d)) {
            return obj;
        }
        throw new InvalidCipherTextException("checksum failed");
    }
}
