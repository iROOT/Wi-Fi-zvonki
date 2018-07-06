package org.spongycastle.crypto.encodings;

import android.support.v4.app.NotificationCompat;
import java.math.BigInteger;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class ISO9796d1Encoding implements AsymmetricBlockCipher {
    private static final BigInteger a = BigInteger.valueOf(16);
    private static final BigInteger b = BigInteger.valueOf(6);
    private static byte[] c = new byte[]{(byte) 14, (byte) 3, (byte) 5, (byte) 8, (byte) 9, (byte) 4, (byte) 2, (byte) 15, (byte) 0, (byte) 13, (byte) 11, (byte) 6, (byte) 7, (byte) 10, (byte) 12, (byte) 1};
    private static byte[] d = new byte[]{(byte) 8, (byte) 15, (byte) 6, (byte) 1, (byte) 5, (byte) 2, (byte) 11, (byte) 12, (byte) 3, (byte) 4, (byte) 13, (byte) 10, (byte) 14, (byte) 9, (byte) 0, (byte) 7};
    private AsymmetricBlockCipher e;
    private boolean f;
    private int g;
    private int h = 0;
    private BigInteger i;

    public ISO9796d1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.e = asymmetricBlockCipher;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters;
        if (cipherParameters instanceof ParametersWithRandom) {
            rSAKeyParameters = (RSAKeyParameters) ((ParametersWithRandom) cipherParameters).b();
        } else {
            rSAKeyParameters = (RSAKeyParameters) cipherParameters;
        }
        this.e.a(z, cipherParameters);
        this.i = rSAKeyParameters.b();
        this.g = this.i.bitLength();
        this.f = z;
    }

    public int a() {
        int a = this.e.a();
        if (this.f) {
            return (a + 1) / 2;
        }
        return a;
    }

    public int b() {
        int b = this.e.b();
        return this.f ? b : (b + 1) / 2;
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        if (this.f) {
            return b(bArr, i, i2);
        }
        return c(bArr, i, i2);
    }

    private byte[] b(byte[] bArr, int i, int i2) {
        Object obj = new byte[((this.g + 7) / 8)];
        int i3 = this.h + 1;
        int i4 = (this.g + 13) / 16;
        int i5 = 0;
        while (i5 < i4) {
            if (i5 > i4 - i2) {
                System.arraycopy(bArr, (i + i2) - (i4 - i5), obj, obj.length - i4, i4 - i5);
            } else {
                System.arraycopy(bArr, i, obj, obj.length - (i5 + i2), i2);
            }
            i5 += i2;
        }
        for (i5 = obj.length - (i4 * 2); i5 != obj.length; i5 += 2) {
            byte b = obj[(obj.length - i4) + (i5 / 2)];
            obj[i5] = (byte) ((c[(b & 255) >>> 4] << 4) | c[b & 15]);
            obj[i5 + 1] = b;
        }
        i5 = obj.length - (i2 * 2);
        obj[i5] = (byte) (i3 ^ obj[i5]);
        obj[obj.length - 1] = (byte) ((obj[obj.length - 1] << 4) | 6);
        i5 = 8 - ((this.g - 1) % 8);
        if (i5 != 8) {
            obj[0] = (byte) (obj[0] & (255 >>> i5));
            obj[0] = (byte) ((NotificationCompat.FLAG_HIGH_PRIORITY >>> i5) | obj[0]);
            i5 = 0;
        } else {
            obj[0] = null;
            obj[1] = (byte) (obj[1] | NotificationCompat.FLAG_HIGH_PRIORITY);
            i5 = 1;
        }
        return this.e.a(obj, i5, obj.length - i5);
    }

    private byte[] c(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4 = (this.g + 13) / 16;
        BigInteger bigInteger = new BigInteger(1, this.e.a(bArr, i, i2));
        if (!bigInteger.mod(a).equals(b)) {
            if (this.i.subtract(bigInteger).mod(a).equals(b)) {
                bigInteger = this.i.subtract(bigInteger);
            } else {
                throw new InvalidCipherTextException("resulting integer iS or (modulus - iS) is not congruent to 6 mod 16");
            }
        }
        byte[] a = a(bigInteger);
        if ((a[a.length - 1] & 15) != 6) {
            throw new InvalidCipherTextException("invalid forcing byte in block");
        }
        a[a.length - 1] = (byte) (((a[a.length - 1] & 255) >>> 4) | (d[(a[a.length - 2] & 255) >> 4] << 4));
        a[0] = (byte) ((c[(a[1] & 255) >>> 4] << 4) | c[a[1] & 15]);
        int i5 = 0;
        int i6 = 0;
        int i7 = 1;
        for (int length = a.length - 1; length >= a.length - (i4 * 2); length -= 2) {
            int i8 = (c[(a[length] & 255) >>> 4] << 4) | c[a[length] & 15];
            if (((a[length - 1] ^ i8) & 255) != 0) {
                if (i6 == 0) {
                    i7 = (a[length - 1] ^ i8) & 255;
                    i5 = length - 1;
                    i6 = 1;
                } else {
                    throw new InvalidCipherTextException("invalid tsums in block");
                }
            }
        }
        a[i5] = (byte) 0;
        byte[] bArr2 = new byte[((a.length - i5) / 2)];
        while (i3 < bArr2.length) {
            bArr2[i3] = a[((i3 * 2) + i5) + 1];
            i3++;
        }
        this.h = i7 - 1;
        return bArr2;
    }

    private static byte[] a(BigInteger bigInteger) {
        Object toByteArray = bigInteger.toByteArray();
        if (toByteArray[0] != (byte) 0) {
            return toByteArray;
        }
        Object obj = new byte[(toByteArray.length - 1)];
        System.arraycopy(toByteArray, 1, obj, 0, obj.length);
        return obj;
    }
}
