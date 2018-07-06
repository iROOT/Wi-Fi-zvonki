package org.spongycastle.pqc.crypto.ntru;

import java.security.SecureRandom;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;
import org.spongycastle.util.Arrays;

public class NTRUEngine implements AsymmetricBlockCipher {
    private boolean a;
    private NTRUEncryptionParameters b;
    private NTRUEncryptionPublicKeyParameters c;
    private NTRUEncryptionPrivateKeyParameters d;
    private SecureRandom e;

    public void a(boolean z, CipherParameters cipherParameters) {
        this.a = z;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.e = parametersWithRandom.a();
                this.c = (NTRUEncryptionPublicKeyParameters) parametersWithRandom.b();
            } else {
                this.e = new SecureRandom();
                this.c = (NTRUEncryptionPublicKeyParameters) cipherParameters;
            }
            this.b = this.c.b();
            return;
        }
        this.d = (NTRUEncryptionPrivateKeyParameters) cipherParameters;
        this.b = this.d.b();
    }

    public int a() {
        return this.b.m;
    }

    public int b() {
        return ((this.b.a * a(this.b.b)) + 7) / 8;
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        if (this.a) {
            return a(bArr2, this.c);
        }
        return a(bArr2, this.d);
    }

    private byte[] a(byte[] bArr, NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters) {
        IntegerPolynomial integerPolynomial = nTRUEncryptionPublicKeyParameters.c;
        int i = this.b.a;
        int i2 = this.b.b;
        int i3 = this.b.m;
        int i4 = this.b.n;
        int i5 = this.b.o;
        int i6 = this.b.q;
        int i7 = this.b.r;
        int i8 = this.b.u;
        boolean z = this.b.v;
        byte[] bArr2 = this.b.w;
        int length = bArr.length;
        if (i3 > 255) {
            throw new IllegalArgumentException("llen values bigger than 1 are not supported");
        } else if (length > i3) {
            throw new DataLengthException("Message too long: " + length + ">" + i3);
        } else {
            while (true) {
                Object obj = new byte[(i4 / 8)];
                this.e.nextBytes(obj);
                Object obj2 = new byte[((i3 + 1) - length)];
                Object obj3 = new byte[(i5 / 8)];
                System.arraycopy(obj, 0, obj3, 0, obj.length);
                obj3[obj.length] = (byte) length;
                System.arraycopy(bArr, 0, obj3, obj.length + 1, bArr.length);
                System.arraycopy(obj2, 0, obj3, (obj.length + 1) + bArr.length, obj2.length);
                IntegerPolynomial a = IntegerPolynomial.a((byte[]) obj3, i);
                IntegerPolynomial a2 = a(a(bArr2, bArr, length, obj, a(integerPolynomial.a(i2), i7 / 8)), (byte[]) obj3).a(integerPolynomial, i2);
                IntegerPolynomial integerPolynomial2 = (IntegerPolynomial) a2.clone();
                integerPolynomial2.g(4);
                a.b(a(integerPolynomial2.a(4), i, i8, z));
                a.h();
                if (a.n(-1) >= i6 && a.n(0) >= i6 && a.n(1) >= i6) {
                    a2.b(a, i2);
                    a2.j(i2);
                    return a2.a(i2);
                }
            }
        }
    }

    private byte[] a(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        Object obj = new byte[(((bArr.length + i) + bArr3.length) + bArr4.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, obj, bArr.length + bArr2.length, bArr3.length);
        System.arraycopy(bArr4, 0, obj, (bArr.length + bArr2.length) + bArr3.length, bArr4.length);
        return obj;
    }

    private Polynomial a(byte[] bArr, byte[] bArr2) {
        IndexGenerator indexGenerator = new IndexGenerator(bArr, this.b);
        if (this.b.z == 1) {
            return new ProductFormPolynomial(new SparseTernaryPolynomial(a(indexGenerator, this.b.h)), new SparseTernaryPolynomial(a(indexGenerator, this.b.i)), new SparseTernaryPolynomial(a(indexGenerator, this.b.j)));
        }
        int i = this.b.g;
        boolean z = this.b.x;
        int[] a = a(indexGenerator, i);
        if (z) {
            return new SparseTernaryPolynomial(a);
        }
        return new DenseTernaryPolynomial(a);
    }

    private int[] a(IndexGenerator indexGenerator, int i) {
        int[] iArr = new int[this.b.a];
        for (int i2 = -1; i2 <= 1; i2 += 2) {
            int i3 = 0;
            while (i3 < i) {
                int a = indexGenerator.a();
                if (iArr[a] == 0) {
                    iArr[a] = i2;
                    i3++;
                }
            }
        }
        return iArr;
    }

    private IntegerPolynomial a(byte[] bArr, int i, int i2, boolean z) {
        Digest digest = this.b.A;
        int b = digest.b();
        Object obj = new byte[(i2 * b)];
        if (z) {
            bArr = a(digest, bArr);
        }
        int i3 = 0;
        while (i3 < i2) {
            digest.a(bArr, 0, bArr.length);
            a(digest, i3);
            System.arraycopy(a(digest), 0, obj, i3 * b, b);
            i3++;
        }
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(i);
        while (true) {
            int i4 = 0;
            for (b = 0; b != obj.length; b++) {
                int i5 = obj[b] & 255;
                if (i5 < 243) {
                    int i6 = i5;
                    i5 = i4;
                    for (i4 = 0; i4 < 4; i4++) {
                        int i7 = i6 % 3;
                        integerPolynomial.a[i5] = i7 - 1;
                        i5++;
                        if (i5 == i) {
                            return integerPolynomial;
                        }
                        i6 = (i6 - i7) / 3;
                    }
                    integerPolynomial.a[i5] = i6 - 1;
                    i4 = i5 + 1;
                    if (i4 == i) {
                        return integerPolynomial;
                    }
                }
            }
            if (i4 >= i) {
                return integerPolynomial;
            }
            digest.a(bArr, 0, bArr.length);
            a(digest, i3);
            obj = a(digest);
            i3++;
        }
    }

    private void a(Digest digest, int i) {
        digest.a((byte) (i >> 24));
        digest.a((byte) (i >> 16));
        digest.a((byte) (i >> 8));
        digest.a((byte) i);
    }

    private byte[] a(Digest digest) {
        byte[] bArr = new byte[digest.b()];
        digest.a(bArr, 0);
        return bArr;
    }

    private byte[] a(Digest digest, byte[] bArr) {
        byte[] bArr2 = new byte[digest.b()];
        digest.a(bArr, 0, bArr.length);
        digest.a(bArr2, 0);
        return bArr2;
    }

    private byte[] a(byte[] bArr, NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters) {
        Polynomial polynomial = nTRUEncryptionPrivateKeyParameters.c;
        IntegerPolynomial integerPolynomial = nTRUEncryptionPrivateKeyParameters.d;
        IntegerPolynomial integerPolynomial2 = nTRUEncryptionPrivateKeyParameters.e;
        int i = this.b.a;
        int i2 = this.b.b;
        int i3 = this.b.n;
        int i4 = this.b.m;
        int i5 = this.b.q;
        int i6 = this.b.r;
        int i7 = this.b.u;
        boolean z = this.b.v;
        byte[] bArr2 = this.b.w;
        if (i4 > 255) {
            throw new DataLengthException("maxMsgLenBytes values bigger than 255 are not supported");
        }
        int i8 = i3 / 8;
        IntegerPolynomial a = IntegerPolynomial.a(bArr, i, i2);
        integerPolynomial = a(a, polynomial, integerPolynomial);
        if (integerPolynomial.n(-1) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal -1");
        } else if (integerPolynomial.n(0) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal 0");
        } else if (integerPolynomial.n(1) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal 1");
        } else {
            IntegerPolynomial integerPolynomial3 = (IntegerPolynomial) a.clone();
            integerPolynomial3.c(integerPolynomial);
            integerPolynomial3.g(i2);
            IntegerPolynomial integerPolynomial4 = (IntegerPolynomial) integerPolynomial3.clone();
            integerPolynomial4.g(4);
            integerPolynomial.c(a(integerPolynomial4.a(4), i, i7, z));
            integerPolynomial.h();
            Object d = integerPolynomial.d();
            Object obj = new byte[i8];
            System.arraycopy(d, 0, obj, 0, i8);
            i = d[i8] & 255;
            if (i > i4) {
                throw new InvalidCipherTextException("Message too long: " + i + ">" + i4);
            }
            byte[] bArr3 = new byte[i];
            System.arraycopy(d, i8 + 1, bArr3, 0, i);
            byte[] bArr4 = new byte[(d.length - ((i8 + 1) + i))];
            System.arraycopy(d, (i8 + 1) + i, bArr4, 0, bArr4.length);
            if (Arrays.a(bArr4, new byte[bArr4.length])) {
                integerPolynomial4 = a(a(bArr2, bArr3, i, obj, a(integerPolynomial2.a(i2), i6 / 8)), bArr3).a(integerPolynomial2);
                integerPolynomial4.g(i2);
                if (integerPolynomial4.equals(integerPolynomial3)) {
                    return bArr3;
                }
                throw new InvalidCipherTextException("Invalid message encoding");
            }
            throw new InvalidCipherTextException("The message is not followed by zeroes");
        }
    }

    protected IntegerPolynomial a(IntegerPolynomial integerPolynomial, Polynomial polynomial, IntegerPolynomial integerPolynomial2) {
        IntegerPolynomial a;
        if (this.b.y) {
            a = polynomial.a(integerPolynomial, this.b.b);
            a.e(3);
            a.b(integerPolynomial);
        } else {
            a = polynomial.a(integerPolynomial, this.b.b);
        }
        a.m(this.b.b);
        a.h();
        if (!this.b.y) {
            a = new DenseTernaryPolynomial(a).a(integerPolynomial2, 3);
        }
        a.m(3);
        return a;
    }

    private byte[] a(byte[] bArr, int i) {
        Object obj = new byte[i];
        if (i >= bArr.length) {
            i = bArr.length;
        }
        System.arraycopy(bArr, 0, obj, 0, i);
        return obj;
    }

    private int a(int i) {
        if (i == k.DIALOG_POSITIVE_BUTTON_ID) {
            return 11;
        }
        throw new IllegalStateException("log2 not fully implemented");
    }
}
