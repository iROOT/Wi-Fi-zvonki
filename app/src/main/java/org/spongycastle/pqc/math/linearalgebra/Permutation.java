package org.spongycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;

public class Permutation {
    private int[] a;

    public Permutation(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("invalid length");
        }
        this.a = new int[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            this.a[i2] = i2;
        }
    }

    public Permutation(int i, SecureRandom secureRandom) {
        int i2 = 0;
        if (i <= 0) {
            throw new IllegalArgumentException("invalid length");
        }
        int i3;
        this.a = new int[i];
        int[] iArr = new int[i];
        for (i3 = 0; i3 < i; i3++) {
            iArr[i3] = i3;
        }
        i3 = i;
        while (i2 < i) {
            int a = RandUtils.a(secureRandom, i3);
            i3--;
            this.a[i2] = iArr[a];
            iArr[a] = iArr[i3];
            i2++;
        }
    }

    public byte[] a() {
        int i = 0;
        int length = this.a.length;
        int a = IntegerFunctions.a(length - 1);
        byte[] bArr = new byte[((length * a) + 4)];
        LittleEndianConversions.a(length, bArr, 0);
        while (i < length) {
            LittleEndianConversions.a(this.a[i], bArr, (i * a) + 4, a);
            i++;
        }
        return bArr;
    }

    public int[] b() {
        return IntUtils.a(this.a);
    }

    public Permutation c() {
        Permutation permutation = new Permutation(this.a.length);
        for (int length = this.a.length - 1; length >= 0; length--) {
            permutation.a[this.a[length]] = length;
        }
        return permutation;
    }

    public Permutation a(Permutation permutation) {
        if (permutation.a.length != this.a.length) {
            throw new IllegalArgumentException("length mismatch");
        }
        Permutation permutation2 = new Permutation(this.a.length);
        for (int length = this.a.length - 1; length >= 0; length--) {
            permutation2.a[length] = this.a[permutation.a[length]];
        }
        return permutation2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Permutation)) {
            return false;
        }
        return IntUtils.a(this.a, ((Permutation) obj).a);
    }

    public String toString() {
        String str = "[" + this.a[0];
        for (int i = 1; i < this.a.length; i++) {
            str = str + ", " + this.a[i];
        }
        return str + "]";
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
