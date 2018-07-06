package com.b.a.b;

import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.b.a.b.a.d;

public final class c {
    private final int a;
    private final int b;
    private final int c;
    private final Drawable d;
    private final Drawable e;
    private final Drawable f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final d j;
    private final Options k;
    private final int l;
    private final boolean m;
    private final Object n;
    private final com.b.a.b.g.a o;
    private final com.b.a.b.g.a p;
    private final com.b.a.b.c.a q;
    private final Handler r;
    private final boolean s;

    public static class a {
        private int a = 0;
        private int b = 0;
        private int c = 0;
        private Drawable d = null;
        private Drawable e = null;
        private Drawable f = null;
        private boolean g = false;
        private boolean h = false;
        private boolean i = false;
        private d j = d.IN_SAMPLE_POWER_OF_2;
        private Options k = new Options();
        private int l = 0;
        private boolean m = false;
        private Object n = null;
        private com.b.a.b.g.a o = null;
        private com.b.a.b.g.a p = null;
        private com.b.a.b.c.a q = a.c();
        private Handler r = null;
        private boolean s = false;

        public a a(boolean z) {
            this.g = z;
            return this;
        }

        public a b(boolean z) {
            this.h = z;
            return this;
        }

        public a c(boolean z) {
            this.i = z;
            return this;
        }

        public a a(d dVar) {
            this.j = dVar;
            return this;
        }

        public a a(Config config) {
            if (config == null) {
                throw new IllegalArgumentException("bitmapConfig can't be null");
            }
            this.k.inPreferredConfig = config;
            return this;
        }

        public a a(com.b.a.b.c.a aVar) {
            if (aVar == null) {
                throw new IllegalArgumentException("displayer can't be null");
            }
            this.q = aVar;
            return this;
        }

        public a a(c cVar) {
            this.a = cVar.a;
            this.b = cVar.b;
            this.c = cVar.c;
            this.d = cVar.d;
            this.e = cVar.e;
            this.f = cVar.f;
            this.g = cVar.g;
            this.h = cVar.h;
            this.i = cVar.i;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
            this.q = cVar.q;
            this.r = cVar.r;
            this.s = cVar.s;
            return this;
        }

        public c a() {
            return new c();
        }
    }

    private c(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
        this.m = aVar.m;
        this.n = aVar.n;
        this.o = aVar.o;
        this.p = aVar.p;
        this.q = aVar.q;
        this.r = aVar.r;
        this.s = aVar.s;
    }

    public boolean a() {
        return (this.d == null && this.a == 0) ? false : true;
    }

    public boolean b() {
        return (this.e == null && this.b == 0) ? false : true;
    }

    public boolean c() {
        return (this.f == null && this.c == 0) ? false : true;
    }

    public boolean d() {
        return this.o != null;
    }

    public boolean e() {
        return this.p != null;
    }

    public boolean f() {
        return this.l > 0;
    }

    public Drawable a(Resources resources) {
        return this.a != 0 ? resources.getDrawable(this.a) : this.d;
    }

    public Drawable b(Resources resources) {
        return this.b != 0 ? resources.getDrawable(this.b) : this.e;
    }

    public Drawable c(Resources resources) {
        return this.c != 0 ? resources.getDrawable(this.c) : this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public boolean i() {
        return this.i;
    }

    public d j() {
        return this.j;
    }

    public Options k() {
        return this.k;
    }

    public int l() {
        return this.l;
    }

    public boolean m() {
        return this.m;
    }

    public Object n() {
        return this.n;
    }

    public com.b.a.b.g.a o() {
        return this.o;
    }

    public com.b.a.b.g.a p() {
        return this.p;
    }

    public com.b.a.b.c.a q() {
        return this.q;
    }

    public Handler r() {
        return this.r;
    }

    boolean s() {
        return this.s;
    }

    public static c t() {
        return new a().a();
    }
}
