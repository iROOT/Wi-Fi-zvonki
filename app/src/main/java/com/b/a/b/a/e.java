package com.b.a.b.a;

public class e {
    private final int a;
    private final int b;

    public e(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public e(int i, int i2, int i3) {
        if (i3 % 180 == 0) {
            this.a = i;
            this.b = i2;
            return;
        }
        this.a = i2;
        this.b = i;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public e a(int i) {
        return new e(this.a / i, this.b / i);
    }

    public e a(float f) {
        return new e((int) (((float) this.a) * f), (int) (((float) this.b) * f));
    }

    public String toString() {
        return this.a + "x" + this.b;
    }
}
