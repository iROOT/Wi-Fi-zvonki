package com.b.a.b;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.b.a.b.a.g;
import com.b.a.c.d;
import java.io.InputStream;
import java.util.concurrent.Executor;

public final class e {
    final Resources a;
    final int b;
    final int c;
    final int d;
    final int e;
    final com.b.a.b.g.a f;
    final Executor g;
    final Executor h;
    final boolean i;
    final boolean j;
    final int k;
    final int l;
    final g m;
    final com.b.a.a.b.a n;
    final com.b.a.a.a.a o;
    final com.b.a.b.d.b p;
    final com.b.a.b.b.b q;
    final c r;
    final com.b.a.b.d.b s;
    final com.b.a.b.d.b t;

    public static class a {
        public static final g a = g.FIFO;
        private Context b;
        private int c = 0;
        private int d = 0;
        private int e = 0;
        private int f = 0;
        private com.b.a.b.g.a g = null;
        private Executor h = null;
        private Executor i = null;
        private boolean j = false;
        private boolean k = false;
        private int l = 3;
        private int m = 3;
        private boolean n = false;
        private g o = a;
        private int p = 0;
        private long q = 0;
        private int r = 0;
        private com.b.a.a.b.a s = null;
        private com.b.a.a.a.a t = null;
        private com.b.a.a.a.b.a u = null;
        private com.b.a.b.d.b v = null;
        private com.b.a.b.b.b w;
        private c x = null;
        private boolean y = false;

        public a(Context context) {
            this.b = context.getApplicationContext();
        }

        public a a(com.b.a.b.d.b bVar) {
            this.v = bVar;
            return this;
        }

        public e a() {
            b();
            return new e();
        }

        private void b() {
            if (this.h == null) {
                this.h = a.a(this.l, this.m, this.o);
            } else {
                this.j = true;
            }
            if (this.i == null) {
                this.i = a.a(this.l, this.m, this.o);
            } else {
                this.k = true;
            }
            if (this.t == null) {
                if (this.u == null) {
                    this.u = a.b();
                }
                this.t = a.a(this.b, this.u, this.q, this.r);
            }
            if (this.s == null) {
                this.s = a.a(this.b, this.p);
            }
            if (this.n) {
                this.s = new com.b.a.a.b.a.a(this.s, d.a());
            }
            if (this.v == null) {
                this.v = a.a(this.b);
            }
            if (this.w == null) {
                this.w = a.a(this.y);
            }
            if (this.x == null) {
                this.x = c.t();
            }
        }
    }

    private static class b implements com.b.a.b.d.b {
        private final com.b.a.b.d.b a;

        public b(com.b.a.b.d.b bVar) {
            this.a = bVar;
        }

        public InputStream a(String str, Object obj) {
            switch (com.b.a.b.d.b.a.a(str)) {
                case HTTP:
                case HTTPS:
                    throw new IllegalStateException();
                default:
                    return this.a.a(str, obj);
            }
        }
    }

    private static class c implements com.b.a.b.d.b {
        private final com.b.a.b.d.b a;

        public c(com.b.a.b.d.b bVar) {
            this.a = bVar;
        }

        public InputStream a(String str, Object obj) {
            InputStream a = this.a.a(str, obj);
            switch (com.b.a.b.d.b.a.a(str)) {
                case HTTP:
                case HTTPS:
                    return new com.b.a.b.a.c(a);
                default:
                    return a;
            }
        }
    }

    private e(a aVar) {
        this.a = aVar.b.getResources();
        this.b = aVar.c;
        this.c = aVar.d;
        this.d = aVar.e;
        this.e = aVar.f;
        this.f = aVar.g;
        this.g = aVar.h;
        this.h = aVar.i;
        this.k = aVar.l;
        this.l = aVar.m;
        this.m = aVar.o;
        this.o = aVar.t;
        this.n = aVar.s;
        this.r = aVar.x;
        this.p = aVar.v;
        this.q = aVar.w;
        this.i = aVar.j;
        this.j = aVar.k;
        this.s = new b(this.p);
        this.t = new c(this.p);
        com.b.a.c.c.a(aVar.y);
    }

    com.b.a.b.a.e a() {
        DisplayMetrics displayMetrics = this.a.getDisplayMetrics();
        int i = this.b;
        if (i <= 0) {
            i = displayMetrics.widthPixels;
        }
        int i2 = this.c;
        if (i2 <= 0) {
            i2 = displayMetrics.heightPixels;
        }
        return new com.b.a.b.a.e(i, i2);
    }
}
