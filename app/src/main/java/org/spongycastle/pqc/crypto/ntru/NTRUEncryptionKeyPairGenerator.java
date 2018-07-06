package org.spongycastle.pqc.crypto.ntru;

import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.util.Util;

public class NTRUEncryptionKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUEncryptionKeyGenerationParameters a;

    public AsymmetricCipherKeyPair a() {
        IntegerPolynomial m;
        Polynomial polynomial;
        IntegerPolynomial integerPolynomial;
        DenseTernaryPolynomial a;
        int i = this.a.h;
        int i2 = this.a.i;
        int i3 = this.a.j;
        int i4 = this.a.k;
        int i5 = this.a.l;
        int i6 = this.a.m;
        int i7 = this.a.r;
        boolean z = this.a.F;
        boolean z2 = this.a.E;
        IntegerPolynomial integerPolynomial2 = null;
        while (true) {
            Polynomial a2;
            IntegerPolynomial integerPolynomial3;
            if (z) {
                if (this.a.G == 0) {
                    a2 = Util.a(i, i3, i3, z2, this.a.a());
                } else {
                    a2 = ProductFormPolynomial.a(i, i4, i5, i6, i6, this.a.a());
                }
                m = a2.m();
                m.e(3);
                int[] iArr = m.a;
                iArr[0] = iArr[0] + 1;
                integerPolynomial3 = m;
                m = integerPolynomial2;
                polynomial = a2;
                integerPolynomial = integerPolynomial3;
            } else {
                if (this.a.G == 0) {
                    a2 = Util.a(i, i3, i3 - 1, z2, this.a.a());
                } else {
                    a2 = ProductFormPolynomial.a(i, i4, i5, i6, i6 - 1, this.a.a());
                }
                m = a2.m();
                integerPolynomial2 = m.e();
                if (integerPolynomial2 != null) {
                    integerPolynomial3 = m;
                    m = integerPolynomial2;
                    polynomial = a2;
                    integerPolynomial = integerPolynomial3;
                } else {
                    continue;
                }
            }
            integerPolynomial = integerPolynomial.b(i2);
            if (integerPolynomial != null) {
                break;
            }
            integerPolynomial2 = m;
        }
        if (z) {
            m = new IntegerPolynomial(i);
            m.a[0] = 1;
        }
        do {
            a = DenseTernaryPolynomial.a(i, i7, i7 - 1, this.a.a());
        } while (a.b(i2) == null);
        IntegerPolynomial a3 = a.a(integerPolynomial, i2);
        a3.f(i2);
        a3.j(i2);
        a.l();
        integerPolynomial.l();
        return new AsymmetricCipherKeyPair(new NTRUEncryptionPublicKeyParameters(a3, this.a.c()), new NTRUEncryptionPrivateKeyParameters(a3, polynomial, m, this.a.c()));
    }
}
