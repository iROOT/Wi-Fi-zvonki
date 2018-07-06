package org.spongycastle.pqc.math.linearalgebra;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import net.hockeyapp.android.k;

public class GF2nPolynomialElement extends GF2nElement {
    private static final int[] c = new int[]{1, 2, 4, 8, 16, 32, 64, NotificationCompat.FLAG_HIGH_PRIORITY, 256, 512, k.FEEDBACK_FAILED_TITLE_ID, k.DIALOG_POSITIVE_BUTTON_ID, FragmentTransaction.TRANSIT_ENTER_MASK, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824, Integer.MIN_VALUE, 0};
    private GF2Polynomial d;

    public GF2nPolynomialElement(GF2nPolynomialElement gF2nPolynomialElement) {
        this.a = gF2nPolynomialElement.a;
        this.b = gF2nPolynomialElement.b;
        this.d = new GF2Polynomial(gF2nPolynomialElement.d);
    }

    public Object clone() {
        return new GF2nPolynomialElement(this);
    }

    public boolean a() {
        return this.d.f();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nPolynomialElement)) {
            return false;
        }
        GF2nPolynomialElement gF2nPolynomialElement = (GF2nPolynomialElement) obj;
        if (this.a == gF2nPolynomialElement.a || this.a.b().equals(gF2nPolynomialElement.a.b())) {
            return this.d.equals(gF2nPolynomialElement.d);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + this.d.hashCode();
    }

    public String toString() {
        return this.d.a(16);
    }
}
