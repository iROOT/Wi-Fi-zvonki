package org.spongycastle.crypto.paddings;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class PaddedBufferedBlockCipher extends BufferedBlockCipher {
    BlockCipherPadding g;

    public PaddedBufferedBlockCipher(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this.d = blockCipher;
        this.g = blockCipherPadding;
        this.a = new byte[blockCipher.b()];
        this.b = 0;
    }

    public PaddedBufferedBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, new PKCS7Padding());
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        this.c = z;
        c();
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.g.a(parametersWithRandom.a());
            this.d.a(z, parametersWithRandom.b());
            return;
        }
        this.g.a(null);
        this.d.a(z, cipherParameters);
    }

    public int b(int i) {
        int i2 = this.b + i;
        int length = i2 % this.a.length;
        if (length != 0) {
            return (i2 - length) + this.a.length;
        }
        if (this.c) {
            return i2 + this.a.length;
        }
        return i2;
    }

    public int a(int i) {
        int i2 = this.b + i;
        int length = i2 % this.a.length;
        if (length == 0) {
            return i2 - this.a.length;
        }
        return i2 - length;
    }

    public int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int b = b();
        int a = a(i2);
        if (a <= 0 || a + i3 <= bArr2.length) {
            int i4;
            int length = this.a.length - this.b;
            if (i2 > length) {
                System.arraycopy(bArr, i, this.a, this.b, length);
                a = this.d.a(this.a, 0, bArr2, i3) + 0;
                this.b = 0;
                i4 = i2 - length;
                length += i;
                while (i4 > this.a.length) {
                    a += this.d.a(bArr, length, bArr2, i3 + a);
                    i4 -= b;
                    length += b;
                }
            } else {
                a = 0;
                length = i;
                i4 = i2;
            }
            System.arraycopy(bArr, length, this.a, this.b, i4);
            this.b = i4 + this.b;
            return a;
        }
        throw new OutputLengthException("output buffer too short");
    }

    public int a(byte[] bArr, int i) {
        int b = this.d.b();
        if (this.c) {
            if (this.b != b) {
                b = 0;
            } else if ((b * 2) + i > bArr.length) {
                c();
                throw new OutputLengthException("output buffer too short");
            } else {
                b = this.d.a(this.a, 0, bArr, i);
                this.b = 0;
            }
            this.g.a(this.a, this.b);
            b += this.d.a(this.a, 0, bArr, i + b);
            c();
            return b;
        } else if (this.b == b) {
            b = this.d.a(this.a, 0, this.a, 0);
            this.b = 0;
            try {
                b -= this.g.a(this.a);
                System.arraycopy(this.a, 0, bArr, i, b);
                return b;
            } finally {
                c();
            }
        } else {
            c();
            throw new DataLengthException("last block incomplete in decryption");
        }
    }
}
