package org.spongycastle.crypto.modes;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.macs.CBCBlockCipherMac;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

public class CCMBlockCipher implements AEADBlockCipher {
    private BlockCipher a;
    private int b;
    private boolean c;
    private byte[] d;
    private byte[] e;
    private int f;
    private CipherParameters g;
    private byte[] h;
    private ExposedByteArrayOutputStream i = new ExposedByteArrayOutputStream(this);
    private ExposedByteArrayOutputStream j = new ExposedByteArrayOutputStream(this);

    private class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        final /* synthetic */ CCMBlockCipher a;

        public ExposedByteArrayOutputStream(CCMBlockCipher cCMBlockCipher) {
            this.a = cCMBlockCipher;
        }

        public byte[] a() {
            return this.buf;
        }
    }

    public CCMBlockCipher(BlockCipher blockCipher) {
        this.a = blockCipher;
        this.b = blockCipher.b();
        this.h = new byte[this.b];
        if (this.b != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
    }

    public BlockCipher a() {
        return this.a;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        CipherParameters a;
        this.c = z;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            this.d = aEADParameters.d();
            this.e = aEADParameters.c();
            this.f = aEADParameters.b() / 8;
            a = aEADParameters.a();
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.d = parametersWithIV.a();
            this.e = null;
            this.f = this.h.length / 2;
            a = parametersWithIV.b();
        } else {
            throw new IllegalArgumentException("invalid parameters passed to CCM");
        }
        if (a != null) {
            this.g = a;
        }
        if (this.d == null || this.d.length < 7 || this.d.length > 13) {
            throw new IllegalArgumentException("nonce must have length from 7 to 13 octets");
        }
        b();
    }

    public void a(byte[] bArr, int i, int i2) {
        this.i.write(bArr, i, i2);
    }

    public int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.j.write(bArr, i, i2);
        return 0;
    }

    public int a(byte[] bArr, int i) {
        int b = b(this.j.a(), 0, this.j.size(), bArr, i);
        b();
        return b;
    }

    public void b() {
        this.a.c();
        this.i.reset();
        this.j.reset();
    }

    public int a(int i) {
        return 0;
    }

    public int b(int i) {
        int size = this.j.size() + i;
        if (this.c) {
            return size + this.f;
        }
        return size < this.f ? 0 : size - this.f;
    }

    public int b(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (this.g == null) {
            throw new IllegalStateException("CCM cipher unitialized.");
        }
        int length = 15 - this.d.length;
        if (length >= 4 || i2 < (1 << (length * 8))) {
            Object obj = new byte[this.b];
            obj[0] = (byte) ((length - 1) & 7);
            System.arraycopy(this.d, 0, obj, 1, this.d.length);
            BlockCipher sICBlockCipher = new SICBlockCipher(this.a);
            sICBlockCipher.a(this.c, new ParametersWithIV(this.g, obj));
            int i4;
            int i5;
            Object obj2;
            if (this.c) {
                i4 = i2 + this.f;
                if (bArr2.length < i4 + i3) {
                    throw new DataLengthException("Output buffer too short.");
                }
                a(bArr, i, i2, this.h);
                sICBlockCipher.a(this.h, 0, this.h, 0);
                length = i3;
                i5 = i;
                while (i5 < (i + i2) - this.b) {
                    sICBlockCipher.a(bArr, i5, bArr2, length);
                    length += this.b;
                    i5 += this.b;
                }
                obj2 = new byte[this.b];
                System.arraycopy(bArr, i5, obj2, 0, (i2 + i) - i5);
                sICBlockCipher.a(obj2, 0, obj2, 0);
                System.arraycopy(obj2, 0, bArr2, length, (i2 + i) - i5);
                System.arraycopy(this.h, 0, bArr2, i3 + i2, this.f);
                return i4;
            } else if (i2 < this.f) {
                throw new InvalidCipherTextException("data too short");
            } else {
                i4 = i2 - this.f;
                if (bArr2.length < i4 + i3) {
                    throw new DataLengthException("Output buffer too short.");
                }
                System.arraycopy(bArr, i + i4, this.h, 0, this.f);
                sICBlockCipher.a(this.h, 0, this.h, 0);
                for (length = this.f; length != this.h.length; length++) {
                    this.h[length] = (byte) 0;
                }
                length = i3;
                i5 = i;
                while (i5 < (i + i4) - this.b) {
                    sICBlockCipher.a(bArr, i5, bArr2, length);
                    length += this.b;
                    i5 += this.b;
                }
                obj2 = new byte[this.b];
                System.arraycopy(bArr, i5, obj2, 0, i4 - (i5 - i));
                sICBlockCipher.a(obj2, 0, obj2, 0);
                System.arraycopy(obj2, 0, bArr2, length, i4 - (i5 - i));
                byte[] bArr3 = new byte[this.b];
                a(bArr2, i3, i4, bArr3);
                if (Arrays.b(this.h, bArr3)) {
                    return i4;
                }
                throw new InvalidCipherTextException("mac check in CCM failed");
            }
        }
        throw new IllegalStateException("CCM packet too large for choice of q.");
    }

    private int a(byte[] bArr, int i, int i2, byte[] bArr2) {
        int i3 = 1;
        Mac cBCBlockCipherMac = new CBCBlockCipherMac(this.a, this.f * 8);
        cBCBlockCipherMac.a(this.g);
        Object obj = new byte[16];
        if (d()) {
            obj[0] = (byte) (obj[0] | 64);
        }
        obj[0] = (byte) (obj[0] | ((((cBCBlockCipherMac.b() - 2) / 2) & 7) << 3));
        obj[0] = (byte) (obj[0] | (((15 - this.d.length) - 1) & 7));
        System.arraycopy(this.d, 0, obj, 1, this.d.length);
        int i4 = i2;
        while (i4 > 0) {
            obj[obj.length - i3] = (byte) (i4 & 255);
            i4 >>>= 8;
            i3++;
        }
        cBCBlockCipherMac.a(obj, 0, obj.length);
        if (d()) {
            i4 = c();
            if (i4 < 65280) {
                cBCBlockCipherMac.a((byte) (i4 >> 8));
                cBCBlockCipherMac.a((byte) i4);
                i3 = 2;
            } else {
                cBCBlockCipherMac.a((byte) -1);
                cBCBlockCipherMac.a((byte) -2);
                cBCBlockCipherMac.a((byte) (i4 >> 24));
                cBCBlockCipherMac.a((byte) (i4 >> 16));
                cBCBlockCipherMac.a((byte) (i4 >> 8));
                cBCBlockCipherMac.a((byte) i4);
                i3 = 6;
            }
            if (this.e != null) {
                cBCBlockCipherMac.a(this.e, 0, this.e.length);
            }
            if (this.i.size() > 0) {
                cBCBlockCipherMac.a(this.i.a(), 0, this.i.size());
            }
            i3 = (i3 + i4) % 16;
            if (i3 != 0) {
                while (i3 != 16) {
                    cBCBlockCipherMac.a((byte) 0);
                    i3++;
                }
            }
        }
        cBCBlockCipherMac.a(bArr, i, i2);
        return cBCBlockCipherMac.a(bArr2, 0);
    }

    private int c() {
        return (this.e == null ? 0 : this.e.length) + this.i.size();
    }

    private boolean d() {
        return c() > 0;
    }
}
