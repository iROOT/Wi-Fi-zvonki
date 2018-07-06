package org.spongycastle.crypto.generators;

import android.support.v4.app.FragmentTransaction;
import java.math.BigInteger;
import java.security.SecureRandom;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.DSAParameterGenerationParameters;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAValidationParameters;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.encoders.Hex;

public class DSAParametersGenerator {
    private static final BigInteger f = BigInteger.valueOf(0);
    private static final BigInteger g = BigInteger.valueOf(1);
    private static final BigInteger h = BigInteger.valueOf(2);
    private Digest a;
    private int b;
    private int c;
    private int d;
    private SecureRandom e;
    private boolean i;
    private int j;

    public DSAParametersGenerator() {
        this(new SHA1Digest());
    }

    public DSAParametersGenerator(Digest digest) {
        this.a = digest;
    }

    public void a(int i, int i2, SecureRandom secureRandom) {
        this.i = false;
        this.b = i;
        this.c = a(i);
        this.d = i2;
        this.e = secureRandom;
    }

    public void a(DSAParameterGenerationParameters dSAParameterGenerationParameters) {
        this.i = true;
        this.b = dSAParameterGenerationParameters.a();
        this.c = dSAParameterGenerationParameters.b();
        this.d = dSAParameterGenerationParameters.c();
        this.e = dSAParameterGenerationParameters.d();
        this.j = dSAParameterGenerationParameters.e();
        if (this.b < k.FEEDBACK_FAILED_TITLE_ID || this.b > 3072 || this.b % k.FEEDBACK_FAILED_TITLE_ID != 0) {
            throw new IllegalArgumentException("L values must be between 1024 and 3072 and a multiple of 1024");
        } else if (this.b == k.FEEDBACK_FAILED_TITLE_ID && this.c != 160) {
            throw new IllegalArgumentException("N must be 160 for L = 1024");
        } else if (this.b == k.DIALOG_POSITIVE_BUTTON_ID && this.c != 224 && this.c != 256) {
            throw new IllegalArgumentException("N must be 224 or 256 for L = 2048");
        } else if (this.b == 3072 && this.c != 256) {
            throw new IllegalArgumentException("N must be 256 for L = 3072");
        } else if (this.a.b() * 8 < this.c) {
            throw new IllegalStateException("Digest output size too small for value of N");
        }
    }

    public DSAParameters a() {
        return this.i ? c() : b();
    }

    private DSAParameters b() {
        byte[] bArr = new byte[20];
        byte[] bArr2 = new byte[20];
        byte[] bArr3 = new byte[20];
        byte[] bArr4 = new byte[20];
        int i = (this.b - 1) / 160;
        Object obj = new byte[(this.b / 8)];
        if (this.a instanceof SHA1Digest) {
            while (true) {
                int i2;
                this.e.nextBytes(bArr);
                a(this.a, bArr, bArr2);
                System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
                a(bArr3);
                a(this.a, bArr3, bArr3);
                for (i2 = 0; i2 != bArr4.length; i2++) {
                    bArr4[i2] = (byte) (bArr2[i2] ^ bArr3[i2]);
                }
                bArr4[0] = (byte) (bArr4[0] | -128);
                bArr4[19] = (byte) (bArr4[19] | 1);
                BigInteger bigInteger = new BigInteger(1, bArr4);
                if (bigInteger.isProbablePrime(this.d)) {
                    byte[] b = Arrays.b(bArr);
                    a(b);
                    for (int i3 = 0; i3 < FragmentTransaction.TRANSIT_ENTER_MASK; i3++) {
                        for (i2 = 0; i2 < i; i2++) {
                            a(b);
                            a(this.a, b, bArr2);
                            System.arraycopy(bArr2, 0, obj, obj.length - ((i2 + 1) * bArr2.length), bArr2.length);
                        }
                        a(b);
                        a(this.a, b, bArr2);
                        System.arraycopy(bArr2, bArr2.length - (obj.length - (bArr2.length * i)), obj, 0, obj.length - (bArr2.length * i));
                        obj[0] = (byte) (obj[0] | -128);
                        BigInteger bigInteger2 = new BigInteger(1, obj);
                        bigInteger2 = bigInteger2.subtract(bigInteger2.mod(bigInteger.shiftLeft(1)).subtract(g));
                        if (bigInteger2.bitLength() == this.b && bigInteger2.isProbablePrime(this.d)) {
                            return new DSAParameters(bigInteger2, bigInteger, a(bigInteger2, bigInteger, this.e), new DSAValidationParameters(bArr, i3));
                        }
                    }
                    continue;
                }
            }
        } else {
            throw new IllegalStateException("can only use SHA-1 for generating FIPS 186-2 parameters");
        }
    }

    private static BigInteger a(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        BigInteger modPow;
        BigInteger divide = bigInteger.subtract(g).divide(bigInteger2);
        BigInteger subtract = bigInteger.subtract(h);
        do {
            modPow = BigIntegers.a(h, subtract, secureRandom).modPow(divide, bigInteger);
        } while (modPow.bitLength() <= 1);
        return modPow;
    }

    private DSAParameters c() {
        BigInteger subtract;
        int i;
        BigInteger subtract2;
        Digest digest = this.a;
        int b = digest.b() * 8;
        byte[] bArr = new byte[(this.c / 8)];
        int i2 = (this.b - 1) / b;
        int i3 = (this.b - 1) % b;
        byte[] bArr2 = new byte[digest.b()];
        loop0:
        while (true) {
            this.e.nextBytes(bArr);
            a(digest, bArr, bArr2);
            BigInteger mod = new BigInteger(1, bArr2).mod(g.shiftLeft(this.c - 1));
            subtract = g.shiftLeft(this.c - 1).add(mod).add(g).subtract(mod.mod(h));
            if (subtract.isProbablePrime(this.d)) {
                byte[] b2 = Arrays.b(bArr);
                int i4 = this.b * 4;
                i = 0;
                while (i < i4) {
                    BigInteger bigInteger = f;
                    int i5 = 0;
                    int i6 = 0;
                    while (i5 <= i2) {
                        a(b2);
                        a(digest, b2, bArr2);
                        mod = new BigInteger(1, bArr2);
                        if (i5 == i2) {
                            mod = mod.mod(g.shiftLeft(i3));
                        }
                        bigInteger = bigInteger.add(mod.shiftLeft(i6));
                        i5++;
                        i6 += b;
                    }
                    mod = bigInteger.add(g.shiftLeft(this.b - 1));
                    subtract2 = mod.subtract(mod.mod(subtract.shiftLeft(1)).subtract(g));
                    if (subtract2.bitLength() == this.b && subtract2.isProbablePrime(this.d)) {
                        break loop0;
                    }
                    i++;
                }
                continue;
            }
        }
        if (this.j >= 0) {
            BigInteger a = a(digest, subtract2, subtract, bArr, this.j);
            if (a != null) {
                return new DSAParameters(subtract2, subtract, a, new DSAValidationParameters(bArr, i, this.j));
            }
        }
        return new DSAParameters(subtract2, subtract, b(subtract2, subtract, this.e), new DSAValidationParameters(bArr, i));
    }

    private static BigInteger b(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        return a(bigInteger, bigInteger2, secureRandom);
    }

    private static BigInteger a(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr, int i) {
        BigInteger divide = bigInteger.subtract(g).divide(bigInteger2);
        Object a = Hex.a("6767656E");
        byte[] bArr2 = new byte[(((bArr.length + a.length) + 1) + 2)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(a, 0, bArr2, bArr.length, a.length);
        bArr2[bArr2.length - 3] = (byte) i;
        byte[] bArr3 = new byte[digest.b()];
        for (int i2 = 1; i2 < 65536; i2++) {
            a(bArr2);
            a(digest, bArr2, bArr3);
            BigInteger modPow = new BigInteger(1, bArr3).modPow(divide, bigInteger);
            if (modPow.compareTo(h) >= 0) {
                return modPow;
            }
        }
        return null;
    }

    private static void a(Digest digest, byte[] bArr, byte[] bArr2) {
        digest.a(bArr, 0, bArr.length);
        digest.a(bArr2, 0);
    }

    private static int a(int i) {
        return i > k.FEEDBACK_FAILED_TITLE_ID ? 256 : 160;
    }

    private static void a(byte[] bArr) {
        int length = bArr.length - 1;
        while (length >= 0) {
            byte b = (byte) ((bArr[length] + 1) & 255);
            bArr[length] = b;
            if (b == (byte) 0) {
                length--;
            } else {
                return;
            }
        }
    }
}
