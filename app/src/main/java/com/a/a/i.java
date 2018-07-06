package com.a.a;

import android.view.View;
import com.a.b.c;
import com.a.c.a.a;
import java.util.HashMap;
import java.util.Map;

public final class i extends m {
    private static final Map<String, c> h = new HashMap();
    private Object i;
    private String j;
    private c k;

    public /* synthetic */ m b(long j) {
        return a(j);
    }

    public /* synthetic */ Object clone() {
        return g();
    }

    public /* synthetic */ a e() {
        return g();
    }

    public /* synthetic */ m h() {
        return g();
    }

    static {
        h.put("alpha", j.a);
        h.put("pivotX", j.b);
        h.put("pivotY", j.c);
        h.put("translationX", j.d);
        h.put("translationY", j.e);
        h.put("rotation", j.f);
        h.put("rotationX", j.g);
        h.put("rotationY", j.h);
        h.put("scaleX", j.i);
        h.put("scaleY", j.j);
        h.put("scrollX", j.k);
        h.put("scrollY", j.l);
        h.put("x", j.m);
        h.put("y", j.n);
    }

    public void a(c cVar) {
        if (this.f != null) {
            k kVar = this.f[0];
            String c = kVar.c();
            kVar.a(cVar);
            this.g.remove(c);
            this.g.put(this.j, kVar);
        }
        if (this.k != null) {
            this.j = cVar.a();
        }
        this.k = cVar;
        this.e = false;
    }

    public static i a(Object obj, k... kVarArr) {
        i iVar = new i();
        iVar.i = obj;
        iVar.a(kVarArr);
        return iVar;
    }

    public void a() {
        super.a();
    }

    void f() {
        if (!this.e) {
            if (this.k == null && a.a && (this.i instanceof View) && h.containsKey(this.j)) {
                a((c) h.get(this.j));
            }
            for (k a : this.f) {
                a.a(this.i);
            }
            super.f();
        }
    }

    public i a(long j) {
        super.b(j);
        return this;
    }

    void a(float f) {
        super.a(f);
        for (k b : this.f) {
            b.b(this.i);
        }
    }

    public i g() {
        return (i) super.h();
    }

    public String toString() {
        String str = "ObjectAnimator@" + Integer.toHexString(hashCode()) + ", target " + this.i;
        if (this.f != null) {
            for (k kVar : this.f) {
                str = str + "\n    " + kVar.toString();
            }
        }
        return str;
    }
}
