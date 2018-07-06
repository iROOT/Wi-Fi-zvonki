package org.spongycastle.crypto.prng.drbg;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.prng.EntropySource;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.encoders.Hex;

public class CTRSP800DRBG implements SP80090DRBG {
    private static final byte[] i = Hex.a("000102030405060708090A0B0C0D0E0F101112131415161718191A1B1C1D1E1F");
    private EntropySource a;
    private BlockCipher b;
    private int c;
    private int d;
    private byte[] e;
    private byte[] f;
    private long g = 0;
    private boolean h = false;

    public CTRSP800DRBG(BlockCipher blockCipher, int i, int i2, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        this.a = entropySource;
        this.b = blockCipher;
        this.c = i;
        this.d = (blockCipher.b() * 8) + i;
        this.h = a(blockCipher);
        if (i2 > 256) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        } else if (a(blockCipher, i) < i2) {
            throw new IllegalArgumentException("Requested security strength is not supported by block cipher and key size");
        } else if (entropySource.b() < i2) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        } else {
            a(entropySource.a(), bArr2, bArr);
        }
    }

    private void a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] a = a(Arrays.a(bArr, bArr2, bArr3), this.d);
        int b = this.b.b();
        this.e = new byte[((this.c + 7) / 8)];
        this.f = new byte[b];
        b(a, this.e, this.f);
        this.g = 1;
    }

    private void b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[bArr.length];
        Object obj = new byte[this.b.b()];
        int b = this.b.b();
        this.b.a(true, new KeyParameter(b(bArr2)));
        for (int i = 0; i * b < bArr.length; i++) {
            c(bArr3);
            this.b.a(bArr3, 0, obj, 0);
            System.arraycopy(obj, 0, bArr4, i * b, bArr4.length - (i * b) > b ? b : bArr4.length - (i * b));
        }
        a(bArr4, bArr, bArr4, 0);
        System.arraycopy(bArr4, 0, bArr2, 0, bArr2.length);
        System.arraycopy(bArr4, bArr2.length, bArr3, 0, bArr3.length);
    }

    private void a(EntropySource entropySource, byte[] bArr) {
        b(a(Arrays.d(entropySource.a(), bArr), this.d), this.e, this.f);
        this.g = 1;
    }

    private void a(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) (bArr2[i2] ^ bArr3[i2 + i]);
        }
    }

    private void c(byte[] bArr) {
        int i = 1;
        for (int i2 = 1; i2 <= bArr.length; i2++) {
            int i3 = (bArr[bArr.length - i2] & 255) + i;
            i = i3 > 255 ? 1 : 0;
            bArr[bArr.length - i2] = (byte) i3;
        }
    }

    private byte[] a(byte[] bArr, int i) {
        int b = this.b.b();
        int length = bArr.length;
        int i2 = i / 8;
        byte[] bArr2 = new byte[((((((length + 8) + 1) + b) - 1) / b) * b)];
        a(bArr2, length, 0);
        a(bArr2, i2, 4);
        System.arraycopy(bArr, 0, bArr2, 8, length);
        bArr2[length + 8] = Byte.MIN_VALUE;
        Object obj = new byte[((this.c / 8) + b)];
        byte[] bArr3 = new byte[b];
        byte[] bArr4 = new byte[b];
        byte[] bArr5 = new byte[(this.c / 8)];
        System.arraycopy(i, 0, bArr5, 0, bArr5.length);
        for (length = 0; (length * b) * 8 < this.c + (b * 8); length++) {
            a(bArr4, length, 0);
            a(bArr3, bArr5, bArr4, bArr2);
            System.arraycopy(bArr3, 0, obj, length * b, obj.length - (length * b) > b ? b : obj.length - (length * b));
        }
        Object obj2 = new byte[b];
        System.arraycopy(obj, 0, bArr5, 0, bArr5.length);
        System.arraycopy(obj, bArr5.length, obj2, 0, obj2.length);
        obj = new byte[(i / 2)];
        this.b.a(true, new KeyParameter(b(bArr5)));
        for (length = 0; length * b < obj.length; length++) {
            this.b.a(obj2, 0, obj2, 0);
            System.arraycopy(obj2, 0, obj, length * b, obj.length - (length * b) > b ? b : obj.length - (length * b));
        }
        return obj;
    }

    private void a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int b = this.b.b();
        byte[] bArr5 = new byte[b];
        int length = bArr4.length / b;
        byte[] bArr6 = new byte[b];
        this.b.a(true, new KeyParameter(b(bArr2)));
        this.b.a(bArr3, 0, bArr5, 0);
        for (int i = 0; i < length; i++) {
            a(bArr6, bArr5, bArr4, i * b);
            this.b.a(bArr6, 0, bArr5, 0);
        }
        System.arraycopy(bArr5, 0, bArr, 0, bArr.length);
    }

    private void a(byte[] bArr, int i, int i2) {
        bArr[i2 + 0] = (byte) (i >> 24);
        bArr[i2 + 1] = (byte) (i >> 16);
        bArr[i2 + 2] = (byte) (i >> 8);
        bArr[i2 + 3] = (byte) i;
    }

    public int a(byte[] bArr, byte[] bArr2, boolean z) {
        byte[] a;
        if (this.h) {
            if (this.g > 2147483648L) {
                return -1;
            }
            if (Utils.a(bArr, 512)) {
                throw new IllegalArgumentException("Number of bits per request limited to 4096");
            }
        } else if (this.g > 140737488355328L) {
            return -1;
        } else {
            if (Utils.a(bArr, 32768)) {
                throw new IllegalArgumentException("Number of bits per request limited to 262144");
            }
        }
        if (z) {
            a(this.a, bArr2);
            bArr2 = null;
        }
        if (bArr2 != null) {
            a = a(bArr2, this.d);
            b(a, this.e, this.f);
        } else {
            a = new byte[this.d];
        }
        Object obj = new byte[this.f.length];
        this.b.a(true, new KeyParameter(b(this.e)));
        for (int i = 0; i < bArr.length / obj.length; i++) {
            c(this.f);
            this.b.a(this.f, 0, obj, 0);
            System.arraycopy(obj, 0, bArr, obj.length * i, bArr.length - (obj.length * i) > obj.length ? obj.length : bArr.length - (this.f.length * i));
        }
        b(a, this.e, this.f);
        this.g++;
        return bArr.length * 8;
    }

    public void a(byte[] bArr) {
        a(this.a, bArr);
    }

    private boolean a(BlockCipher blockCipher) {
        return blockCipher.a().equals("DESede") || blockCipher.a().equals("TDEA");
    }

    private int a(BlockCipher blockCipher, int i) {
        if (a(blockCipher) && i == 168) {
            return 112;
        }
        return !blockCipher.a().equals("AES") ? -1 : i;
    }

    byte[] b(byte[] bArr) {
        if (!this.h) {
            return bArr;
        }
        byte[] bArr2 = new byte[24];
        a(bArr, 0, bArr2, 0);
        a(bArr, 7, bArr2, 8);
        a(bArr, 14, bArr2, 16);
        return bArr2;
    }

    private void a(byte[] bArr, int i, byte[] bArr2, int i2) {
        bArr2[i2 + 0] = (byte) (bArr[i + 0] & 254);
        bArr2[i2 + 1] = (byte) ((bArr[i + 0] << 7) | ((bArr[i + 1] & 252) >>> 1));
        bArr2[i2 + 2] = (byte) ((bArr[i + 1] << 6) | ((bArr[i + 2] & 248) >>> 2));
        bArr2[i2 + 3] = (byte) ((bArr[i + 2] << 5) | ((bArr[i + 3] & 240) >>> 3));
        bArr2[i2 + 4] = (byte) ((bArr[i + 3] << 4) | ((bArr[i + 4] & 224) >>> 4));
        bArr2[i2 + 5] = (byte) ((bArr[i + 4] << 3) | ((bArr[i + 5] & 192) >>> 5));
        bArr2[i2 + 6] = (byte) ((bArr[i + 5] << 2) | ((bArr[i + 6] & NotificationCompat.FLAG_HIGH_PRIORITY) >>> 6));
        bArr2[i2 + 7] = (byte) (bArr[i + 6] << 1);
        for (int i3 = i2; i3 <= i2 + 7; i3++) {
            byte b = bArr2[i3];
            bArr2[i3] = (byte) (((((b >> 7) ^ ((((((b >> 1) ^ (b >> 2)) ^ (b >> 3)) ^ (b >> 4)) ^ (b >> 5)) ^ (b >> 6))) ^ 1) & 1) | (b & 254));
        }
    }
}
