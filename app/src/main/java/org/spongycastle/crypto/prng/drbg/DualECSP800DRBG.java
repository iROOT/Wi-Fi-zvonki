package org.spongycastle.crypto.prng.drbg;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import java.math.BigInteger;
import org.spongycastle.asn1.nist.NISTNamedCurves;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.prng.EntropySource;
import org.spongycastle.math.ec.ECCurve.Fp;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

public class DualECSP800DRBG implements SP80090DRBG {
    private static final BigInteger a = new BigInteger("6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", 16);
    private static final BigInteger b = new BigInteger("4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5", 16);
    private static final BigInteger c = new BigInteger("c97445f45cdef9f0d3e05e1e585fc297235b82b5be8ff3efca67c59852018192", 16);
    private static final BigInteger d = new BigInteger("b28ef557ba31dfcbdd21ac46e2a91e3c304f44cb87058ada2cb815151e610046", 16);
    private static final BigInteger e = new BigInteger("aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", 16);
    private static final BigInteger f = new BigInteger("3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f", 16);
    private static final BigInteger g = new BigInteger("8e722de3125bddb05580164bfe20b8b432216a62926c57502ceede31c47816edd1e89769124179d0b695106428815065", 16);
    private static final BigInteger h = new BigInteger("023b1660dd701d0839fd45eec36f9ee7b32e13b315dc02610aa1b636e346df671f790f84c5e09b05674dbb7e45c803dd", 16);
    private static final BigInteger i = new BigInteger("c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", 16);
    private static final BigInteger j = new BigInteger("11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650", 16);
    private static final BigInteger k = new BigInteger("1b9fa3e518d683c6b65763694ac8efbaec6fab44f2276171a42726507dd08add4c3b3f4c1ebc5b1222ddba077f722943b24c3edfa0f85fe24d0c8c01591f0be6f63", 16);
    private static final BigInteger l = new BigInteger("1f3bdba585295d9a1110d1df1f9430ef8442c5018976ff3437ef91b81dc0b8132c8d5c39c32d0e004a3092b7d327c0e7a4d26d2c7b69b58f9066652911e457779de", 16);
    private static final DualECPoints[] m = new DualECPoints[3];
    private Digest n;
    private long o;
    private EntropySource p;
    private int q;
    private int r;
    private int s;
    private ECPoint t;
    private ECPoint u;
    private byte[] v;
    private int w;

    static {
        Fp fp = (Fp) NISTNamedCurves.a("P-256").d();
        m[0] = new DualECPoints(NotificationCompat.FLAG_HIGH_PRIORITY, fp.a(a, b), fp.a(c, d), 1);
        fp = (Fp) NISTNamedCurves.a("P-384").d();
        m[1] = new DualECPoints(192, fp.a(e, f), fp.a(g, h), 1);
        fp = (Fp) NISTNamedCurves.a("P-521").d();
        m[2] = new DualECPoints(256, fp.a(i, j), fp.a(k, l), 1);
    }

    public DualECSP800DRBG(Digest digest, int i, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        this(m, digest, i, entropySource, bArr, bArr2);
    }

    public DualECSP800DRBG(DualECPoints[] dualECPointsArr, Digest digest, int i, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        this.n = digest;
        this.p = entropySource;
        this.q = i;
        if (Utils.a(bArr, 512)) {
            throw new IllegalArgumentException("Personalization string too large");
        } else if (entropySource.b() < i || entropySource.b() > FragmentTransaction.TRANSIT_ENTER_MASK) {
            throw new IllegalArgumentException("EntropySource must provide between " + i + " and " + FragmentTransaction.TRANSIT_ENTER_MASK + " bits");
        } else {
            byte[] a = Arrays.a(entropySource.a(), bArr2, bArr);
            int i2 = 0;
            while (i2 != dualECPointsArr.length) {
                if (i > dualECPointsArr[i2].e()) {
                    i2++;
                } else if (Utils.a(digest) < dualECPointsArr[i2].e()) {
                    throw new IllegalArgumentException("Requested security strength is not supported by digest");
                } else {
                    this.r = dualECPointsArr[i2].a();
                    this.s = dualECPointsArr[i2].b() / 8;
                    this.t = dualECPointsArr[i2].c();
                    this.u = dualECPointsArr[i2].d();
                    if (this.t != null) {
                        throw new IllegalArgumentException("security strength cannot be greater than 256 bits");
                    }
                    this.v = Utils.a(this.n, a, this.r);
                    this.w = this.v.length;
                    this.o = 0;
                    return;
                }
            }
            if (this.t != null) {
                this.v = Utils.a(this.n, a, this.r);
                this.w = this.v.length;
                this.o = 0;
                return;
            }
            throw new IllegalArgumentException("security strength cannot be greater than 256 bits");
        }
    }

    public int a(byte[] bArr, byte[] bArr2, boolean z) {
        int length = bArr.length * 8;
        int length2 = bArr.length / this.s;
        if (Utils.a(bArr2, 512)) {
            throw new IllegalArgumentException("Additional input too large");
        } else if (this.o + ((long) length2) > 2147483648L) {
            return -1;
        } else {
            BigInteger bigInteger;
            if (z) {
                a(bArr2);
                bArr2 = null;
            }
            if (bArr2 != null) {
                bigInteger = new BigInteger(1, a(this.v, Utils.a(this.n, bArr2, this.r)));
            } else {
                bigInteger = new BigInteger(1, this.v);
            }
            Arrays.a(bArr, (byte) 0);
            int i = 0;
            int i2 = 0;
            while (i < length2) {
                BigInteger a = a(this.t, bigInteger);
                Object toByteArray = this.u.a(a).k().c().a().toByteArray();
                if (toByteArray.length > this.s) {
                    System.arraycopy(toByteArray, toByteArray.length - this.s, bArr, i2, this.s);
                } else {
                    System.arraycopy(toByteArray, 0, bArr, (this.s - toByteArray.length) + i2, toByteArray.length);
                }
                i2 += this.s;
                this.o++;
                i++;
                bigInteger = a;
            }
            if (i2 < bArr.length) {
                bigInteger = a(this.t, bigInteger);
                Object toByteArray2 = this.u.a(bigInteger).k().c().a().toByteArray();
                int length3 = bArr.length - i2;
                if (toByteArray2.length > this.s) {
                    System.arraycopy(toByteArray2, toByteArray2.length - this.s, bArr, i2, length3);
                } else {
                    System.arraycopy(toByteArray2, 0, bArr, i2 + (this.s - toByteArray2.length), length3);
                }
                this.o++;
            }
            this.v = BigIntegers.a(this.w, this.t.a(bigInteger).k().c().a());
            return length;
        }
    }

    public void a(byte[] bArr) {
        if (Utils.a(bArr, 512)) {
            throw new IllegalArgumentException("Additional input string too large");
        }
        this.v = Utils.a(this.n, Arrays.a(a(this.v, this.r), this.p.a(), bArr), this.r);
        this.o = 0;
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null) {
            return bArr;
        }
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i != bArr3.length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    private byte[] a(byte[] bArr, int i) {
        if (i % 8 != 0) {
            int i2 = 8 - (i % 8);
            int i3 = 0;
            int length = bArr.length - 1;
            while (length >= 0) {
                int i4 = bArr[length] & 255;
                bArr[length] = (byte) ((i3 >> (8 - i2)) | (i4 << i2));
                length--;
                i3 = i4;
            }
        }
        return bArr;
    }

    private BigInteger a(ECPoint eCPoint, BigInteger bigInteger) {
        return eCPoint.a(bigInteger).k().c().a();
    }
}
