package org.spongycastle.pqc.math.ntru.polynomial;

import java.security.SecureRandom;
import net.hockeyapp.android.k;
import org.spongycastle.pqc.math.ntru.util.Util;
import org.spongycastle.util.Arrays;

public class DenseTernaryPolynomial extends IntegerPolynomial implements TernaryPolynomial {
    public DenseTernaryPolynomial(IntegerPolynomial integerPolynomial) {
        this(integerPolynomial.a);
    }

    public DenseTernaryPolynomial(int[] iArr) {
        super(iArr);
        n();
    }

    private void n() {
        for (int i = 0; i != this.a.length; i++) {
            int i2 = this.a[i];
            if (i2 < -1 || i2 > 1) {
                throw new IllegalStateException("Illegal value: " + i2 + ", must be one of {-1, 0, 1}");
            }
        }
    }

    public static DenseTernaryPolynomial a(int i, int i2, int i3, SecureRandom secureRandom) {
        return new DenseTernaryPolynomial(Util.a(i, i2, i3, secureRandom));
    }

    public IntegerPolynomial a(IntegerPolynomial integerPolynomial, int i) {
        if (i != k.DIALOG_POSITIVE_BUTTON_ID) {
            return super.a(integerPolynomial, i);
        }
        IntegerPolynomial integerPolynomial2 = (IntegerPolynomial) integerPolynomial.clone();
        integerPolynomial2.g(k.DIALOG_POSITIVE_BUTTON_ID);
        return new LongPolynomial5(integerPolynomial2).a(this).a();
    }

    public int[] a() {
        int length = this.a.length;
        int[] iArr = new int[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            if (this.a[i] == 1) {
                i3 = i2 + 1;
                iArr[i2] = i;
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return Arrays.b(iArr, i2);
    }

    public int[] b() {
        int length = this.a.length;
        int[] iArr = new int[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            if (this.a[i] == -1) {
                i3 = i2 + 1;
                iArr[i2] = i;
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return Arrays.b(iArr, i2);
    }

    public int c() {
        return this.a.length;
    }
}
