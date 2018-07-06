package com.a.a;

import android.view.animation.Interpolator;

public abstract class g implements Cloneable {
    float a;
    Class b;
    boolean c = false;
    private Interpolator d = null;

    static class a extends g {
        float d;

        public /* synthetic */ Object clone() {
            return g();
        }

        public /* synthetic */ g e() {
            return g();
        }

        a(float f, float f2) {
            this.a = f;
            this.d = f2;
            this.b = Float.TYPE;
            this.c = true;
        }

        a(float f) {
            this.a = f;
            this.b = Float.TYPE;
        }

        public float f() {
            return this.d;
        }

        public Object b() {
            return Float.valueOf(this.d);
        }

        public void a(Object obj) {
            if (obj != null && obj.getClass() == Float.class) {
                this.d = ((Float) obj).floatValue();
                this.c = true;
            }
        }

        public a g() {
            a aVar = new a(c(), this.d);
            aVar.a(d());
            return aVar;
        }
    }

    static class b extends g {
        int d;

        public /* synthetic */ Object clone() {
            return g();
        }

        public /* synthetic */ g e() {
            return g();
        }

        b(float f, int i) {
            this.a = f;
            this.d = i;
            this.b = Integer.TYPE;
            this.c = true;
        }

        b(float f) {
            this.a = f;
            this.b = Integer.TYPE;
        }

        public int f() {
            return this.d;
        }

        public Object b() {
            return Integer.valueOf(this.d);
        }

        public void a(Object obj) {
            if (obj != null && obj.getClass() == Integer.class) {
                this.d = ((Integer) obj).intValue();
                this.c = true;
            }
        }

        public b g() {
            b bVar = new b(c(), this.d);
            bVar.a(d());
            return bVar;
        }
    }

    public abstract void a(Object obj);

    public abstract Object b();

    public abstract g e();

    public /* synthetic */ Object clone() {
        return e();
    }

    public static g a(float f, int i) {
        return new b(f, i);
    }

    public static g a(float f) {
        return new b(f);
    }

    public static g a(float f, float f2) {
        return new a(f, f2);
    }

    public static g b(float f) {
        return new a(f);
    }

    public boolean a() {
        return this.c;
    }

    public float c() {
        return this.a;
    }

    public Interpolator d() {
        return this.d;
    }

    public void a(Interpolator interpolator) {
        this.d = interpolator;
    }
}
