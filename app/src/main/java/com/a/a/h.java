package com.a.a;

import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Arrays;

class h {
    int a;
    g b;
    g c;
    Interpolator d;
    ArrayList<g> e = new ArrayList();
    l f;

    public /* synthetic */ Object clone() {
        return b();
    }

    public h(g... gVarArr) {
        this.a = gVarArr.length;
        this.e.addAll(Arrays.asList(gVarArr));
        this.b = (g) this.e.get(0);
        this.c = (g) this.e.get(this.a - 1);
        this.d = this.c.d();
    }

    public static h a(int... iArr) {
        int i = 1;
        int length = iArr.length;
        b[] bVarArr = new b[Math.max(length, 2)];
        if (length == 1) {
            bVarArr[0] = (b) g.a(0.0f);
            bVarArr[1] = (b) g.a(1.0f, iArr[0]);
        } else {
            bVarArr[0] = (b) g.a(0.0f, iArr[0]);
            while (i < length) {
                bVarArr[i] = (b) g.a(((float) i) / ((float) (length - 1)), iArr[i]);
                i++;
            }
        }
        return new f(bVarArr);
    }

    public static h a(float... fArr) {
        int i = 1;
        int length = fArr.length;
        a[] aVarArr = new a[Math.max(length, 2)];
        if (length == 1) {
            aVarArr[0] = (a) g.b(0.0f);
            aVarArr[1] = (a) g.a(1.0f, fArr[0]);
        } else {
            aVarArr[0] = (a) g.a(0.0f, fArr[0]);
            while (i < length) {
                aVarArr[i] = (a) g.a(((float) i) / ((float) (length - 1)), fArr[i]);
                i++;
            }
        }
        return new d(aVarArr);
    }

    public void a(l lVar) {
        this.f = lVar;
    }

    public h b() {
        ArrayList arrayList = this.e;
        int size = this.e.size();
        g[] gVarArr = new g[size];
        for (int i = 0; i < size; i++) {
            gVarArr[i] = ((g) arrayList.get(i)).e();
        }
        return new h(gVarArr);
    }

    public Object a(float f) {
        if (this.a == 2) {
            if (this.d != null) {
                f = this.d.getInterpolation(f);
            }
            return this.f.a(f, this.b.b(), this.c.b());
        } else if (f <= 0.0f) {
            r0 = (g) this.e.get(1);
            r1 = r0.d();
            if (r1 != null) {
                f = r1.getInterpolation(f);
            }
            r1 = this.b.c();
            return this.f.a((f - r1) / (r0.c() - r1), this.b.b(), r0.b());
        } else if (f >= 1.0f) {
            r0 = (g) this.e.get(this.a - 2);
            r1 = this.c.d();
            if (r1 != null) {
                f = r1.getInterpolation(f);
            }
            r1 = r0.c();
            return this.f.a((f - r1) / (this.c.c() - r1), r0.b(), this.c.b());
        } else {
            g gVar = this.b;
            int i = 1;
            while (i < this.a) {
                r0 = (g) this.e.get(i);
                if (f < r0.c()) {
                    r1 = r0.d();
                    if (r1 != null) {
                        f = r1.getInterpolation(f);
                    }
                    r1 = gVar.c();
                    return this.f.a((f - r1) / (r0.c() - r1), gVar.b(), r0.b());
                }
                i++;
                gVar = r0;
            }
            return this.c.b();
        }
    }

    public String toString() {
        String str = " ";
        int i = 0;
        while (i < this.a) {
            String str2 = str + ((g) this.e.get(i)).b() + "  ";
            i++;
            str = str2;
        }
        return str;
    }
}
