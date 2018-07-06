package org.spongycastle.pqc.crypto.ntru;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.ntru.NTRUSigningPrivateKeyParameters.Basis;
import org.spongycastle.pqc.math.ntru.euclid.BigIntEuclidean;
import org.spongycastle.pqc.math.ntru.polynomial.BigDecimalPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Resultant;

public class NTRUSigningKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUSigningKeyGenerationParameters a;

    private class BasisGenerationTask implements Callable<Basis> {
        final /* synthetic */ NTRUSigningKeyPairGenerator a;

        private BasisGenerationTask(NTRUSigningKeyPairGenerator nTRUSigningKeyPairGenerator) {
            this.a = nTRUSigningKeyPairGenerator;
        }

        public /* synthetic */ Object call() {
            return a();
        }

        public Basis a() {
            return this.a.b();
        }
    }

    public class FGBasis extends Basis {
        public IntegerPolynomial a;
        public IntegerPolynomial b;
        final /* synthetic */ NTRUSigningKeyPairGenerator c;

        FGBasis(NTRUSigningKeyPairGenerator nTRUSigningKeyPairGenerator, Polynomial polynomial, Polynomial polynomial2, IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
            this.c = nTRUSigningKeyPairGenerator;
            super(polynomial, polynomial2, integerPolynomial, nTRUSigningKeyGenerationParameters);
            this.a = integerPolynomial2;
            this.b = integerPolynomial3;
        }

        boolean a() {
            double d = this.g.t;
            int i = this.g.h;
            return ((double) this.a.k(i)) < d && ((double) this.b.k(i)) < d;
        }
    }

    public AsymmetricCipherKeyPair a() {
        AsymmetricKeyParameter asymmetricKeyParameter = null;
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        List arrayList = new ArrayList();
        for (int i = this.a.m; i >= 0; i--) {
            arrayList.add(newCachedThreadPool.submit(new BasisGenerationTask()));
        }
        newCachedThreadPool.shutdown();
        List arrayList2 = new ArrayList();
        int i2 = this.a.m;
        while (i2 >= 0) {
            Future future = (Future) arrayList.get(i2);
            try {
                AsymmetricKeyParameter nTRUSigningPublicKeyParameters;
                arrayList2.add(future.get());
                if (i2 == this.a.m) {
                    nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(((Basis) future.get()).f, this.a.c());
                } else {
                    nTRUSigningPublicKeyParameters = asymmetricKeyParameter;
                }
                i2--;
                asymmetricKeyParameter = nTRUSigningPublicKeyParameters;
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
        return new AsymmetricCipherKeyPair(asymmetricKeyParameter, new NTRUSigningPrivateKeyParameters(arrayList2, asymmetricKeyParameter));
    }

    private void a(IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, IntegerPolynomial integerPolynomial4, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += (i * 2) * ((integerPolynomial.a[i3] * integerPolynomial.a[i3]) + (integerPolynomial2.a[i3] * integerPolynomial2.a[i3]));
        }
        int i4 = i2 - 4;
        IntegerPolynomial integerPolynomial5 = (IntegerPolynomial) integerPolynomial.clone();
        IntegerPolynomial integerPolynomial6 = (IntegerPolynomial) integerPolynomial2.clone();
        int i5 = 0;
        int i6 = 0;
        while (i6 < i && r3 < i) {
            int i7;
            int i8 = 0;
            for (i7 = 0; i7 < i; i7++) {
                i8 += ((integerPolynomial3.a[i7] * integerPolynomial.a[i7]) + (integerPolynomial4.a[i7] * integerPolynomial2.a[i7])) * (i * 4);
            }
            i7 = i8 - ((integerPolynomial3.i() + integerPolynomial4.i()) * 4);
            if (i7 > i4) {
                integerPolynomial3.c(integerPolynomial5);
                integerPolynomial4.c(integerPolynomial6);
                i6++;
                i5 = 0;
            } else if (i7 < (-i4)) {
                integerPolynomial3.b(integerPolynomial5);
                integerPolynomial4.b(integerPolynomial6);
                i6++;
                i5 = 0;
            }
            i5++;
            integerPolynomial5.k();
            integerPolynomial6.k();
        }
    }

    private FGBasis c() {
        Polynomial a;
        IntegerPolynomial m;
        Polynomial a2;
        IntegerPolynomial m2;
        Resultant f;
        BigIntEuclidean a3;
        IntegerPolynomial integerPolynomial;
        IntegerPolynomial integerPolynomial2;
        BigIntPolynomial a4;
        IntegerPolynomial a5;
        Polynomial polynomial;
        int i = this.a.g;
        int i2 = this.a.h;
        int i3 = this.a.i;
        int i4 = this.a.j;
        int i5 = this.a.k;
        int i6 = this.a.l;
        int i7 = this.a.v;
        int i8 = (i * 2) + 1;
        boolean z = this.a.u;
        while (true) {
            IntegerPolynomial b;
            if (this.a.A == 0) {
                a = DenseTernaryPolynomial.a(i, i3 + 1, i3, new SecureRandom());
            } else {
                a = ProductFormPolynomial.a(i, i4, i5, i6 + 1, i6, new SecureRandom());
            }
            m = a.m();
            if (!z || !m.c(i8).c.equals(BigInteger.ZERO)) {
                b = m.b(i2);
                if (b != null) {
                    break;
                }
            }
        }
        Resultant f2 = m.f();
        while (true) {
            if (this.a.A == 0) {
                a2 = DenseTernaryPolynomial.a(i, i3 + 1, i3, new SecureRandom());
            } else {
                Object a22 = ProductFormPolynomial.a(i, i4, i5, i6 + 1, i6, new SecureRandom());
            }
            m2 = a22.m();
            if (!((z && m2.c(i8).c.equals(BigInteger.ZERO)) || m2.b(i2) == null)) {
                f = m2.f();
                a3 = BigIntEuclidean.a(f2.c, f.c);
                if (a3.c.equals(BigInteger.ONE)) {
                    break;
                }
            }
        }
        BigIntPolynomial bigIntPolynomial = (BigIntPolynomial) f2.b.clone();
        bigIntPolynomial.a(a3.a.multiply(BigInteger.valueOf((long) i2)));
        BigIntPolynomial bigIntPolynomial2 = (BigIntPolynomial) f.b.clone();
        bigIntPolynomial2.a(a3.b.multiply(BigInteger.valueOf((long) (-i2))));
        if (this.a.y == 0) {
            int[] iArr = new int[i];
            int[] iArr2 = new int[i];
            iArr[0] = m.a[0];
            iArr2[0] = m2.a[0];
            for (i6 = 1; i6 < i; i6++) {
                iArr[i6] = m.a[i - i6];
                iArr2[i6] = m2.a[i - i6];
            }
            integerPolynomial = new IntegerPolynomial(iArr);
            integerPolynomial2 = new IntegerPolynomial(iArr2);
            IntegerPolynomial a6 = a.a(integerPolynomial);
            a6.b(a22.a(integerPolynomial2));
            Resultant f3 = a6.f();
            a4 = integerPolynomial.a(bigIntPolynomial2);
            a4.b(integerPolynomial2.a(bigIntPolynomial));
            a4 = a4.a(f3.b);
            a4.b(f3.c);
        } else {
            int i9 = 0;
            for (i6 = 1; i6 < i; i6 *= 10) {
                i9++;
            }
            BigDecimalPolynomial a7 = f2.b.a(new BigDecimal(f2.c), (bigIntPolynomial2.a() + 1) + i9);
            BigDecimalPolynomial a8 = f.b.a(new BigDecimal(f.c), i9 + (bigIntPolynomial.a() + 1));
            a7 = a7.a(bigIntPolynomial2);
            a7.b(a8.a(bigIntPolynomial));
            a7.a();
            a4 = a7.b();
        }
        bigIntPolynomial2 = (BigIntPolynomial) bigIntPolynomial2.clone();
        bigIntPolynomial2.c(a.a(a4));
        bigIntPolynomial = (BigIntPolynomial) bigIntPolynomial.clone();
        bigIntPolynomial.c(a22.a(a4));
        integerPolynomial = new IntegerPolynomial(bigIntPolynomial2);
        integerPolynomial2 = new IntegerPolynomial(bigIntPolynomial);
        a(m, m2, integerPolynomial, integerPolynomial2, i);
        if (i7 == 0) {
            a5 = a22.a(b, i2);
            polynomial = integerPolynomial;
        } else {
            a5 = integerPolynomial.a(b, i2);
            polynomial = a22;
        }
        a5.g(i2);
        return new FGBasis(this, a, polynomial, a5, integerPolynomial, integerPolynomial2, this.a);
    }

    public Basis b() {
        Basis c;
        do {
            c = c();
        } while (!c.a());
        return c;
    }
}
