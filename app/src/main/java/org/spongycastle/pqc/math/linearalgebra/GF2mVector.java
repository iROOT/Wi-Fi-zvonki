package org.spongycastle.pqc.math.linearalgebra;

public class GF2mVector extends Vector {
    private GF2mField b;
    private int[] c;

    public GF2mVector(GF2mField gF2mField, int[] iArr) {
        this.b = gF2mField;
        this.a = iArr.length;
        int length = iArr.length - 1;
        while (length >= 0) {
            if (gF2mField.c(iArr[length])) {
                length--;
            } else {
                throw new ArithmeticException("Element array is not specified over the given finite field.");
            }
        }
        this.c = IntUtils.a(iArr);
    }

    public GF2mField a() {
        return this.b;
    }

    public int[] b() {
        return IntUtils.a(this.c);
    }

    public Vector a(Vector vector) {
        throw new RuntimeException("not implemented");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GF2mVector)) {
            return false;
        }
        GF2mVector gF2mVector = (GF2mVector) obj;
        if (this.b.equals(gF2mVector.b)) {
            return IntUtils.a(this.c, gF2mVector.c);
        }
        return false;
    }

    public int hashCode() {
        return (this.b.hashCode() * 31) + this.c.hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i : this.c) {
            for (int i2 = 0; i2 < this.b.a(); i2++) {
                if (((1 << (i2 & 31)) & i) != 0) {
                    stringBuffer.append('1');
                } else {
                    stringBuffer.append('0');
                }
            }
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }
}
