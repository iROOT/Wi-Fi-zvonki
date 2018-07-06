package org.spongycastle.pqc.math.ntru.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.spongycastle.pqc.math.ntru.euclid.IntEuclidean;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.TernaryPolynomial;
import org.spongycastle.util.Integers;

public class Util {
    private static volatile boolean a;
    private static volatile boolean b;

    public static int a(int i, int i2) {
        int i3 = i % i2;
        if (i3 < 0) {
            i3 += i2;
        }
        return IntEuclidean.a(i3, i2).a;
    }

    public static int a(int i, int i2, int i3) {
        int i4 = 1;
        for (int i5 = 0; i5 < i2; i5++) {
            i4 = (i4 * i) % i3;
        }
        return i4;
    }

    public static TernaryPolynomial a(int i, int i2, int i3, boolean z, SecureRandom secureRandom) {
        if (z) {
            return SparseTernaryPolynomial.a(i, i2, i3, secureRandom);
        }
        return DenseTernaryPolynomial.a(i, i2, i3, secureRandom);
    }

    public static int[] a(int i, int i2, int i3, SecureRandom secureRandom) {
        int i4;
        Integer a = Integers.a(1);
        Integer a2 = Integers.a(-1);
        Integer a3 = Integers.a(0);
        List arrayList = new ArrayList();
        for (i4 = 0; i4 < i2; i4++) {
            arrayList.add(a);
        }
        for (i4 = 0; i4 < i3; i4++) {
            arrayList.add(a2);
        }
        while (arrayList.size() < i) {
            arrayList.add(a3);
        }
        Collections.shuffle(arrayList, secureRandom);
        int[] iArr = new int[i];
        for (i4 = 0; i4 < i; i4++) {
            iArr[i4] = ((Integer) arrayList.get(i4)).intValue();
        }
        return iArr;
    }

    public static boolean a() {
        if (!a) {
            String property = System.getProperty("os.arch");
            boolean z = "amd64".equals(property) || "x86_64".equals(property) || "ppc64".equals(property) || "64".equals(System.getProperty("sun.arch.data.model"));
            b = z;
            a = true;
        }
        return b;
    }
}
