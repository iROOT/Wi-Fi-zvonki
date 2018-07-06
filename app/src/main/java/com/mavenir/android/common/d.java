package com.mavenir.android.common;

import android.content.Context;
import android.support.v4.content.a;

public abstract class d<D> extends a<D> {
    private D f;

    public abstract D h();

    public d(Context context) {
        super(context);
    }

    public D d() {
        return h();
    }

    public void b(D d) {
        if (p()) {
            d(d);
            return;
        }
        D d2 = this.f;
        this.f = d;
        if (n()) {
            super.b((Object) d);
        }
        if (d2 != null && d2 != d) {
            d(d2);
        }
    }

    protected void i() {
        if (this.f != null) {
            b(this.f);
        }
        if (x() || this.f == null) {
            s();
        }
    }

    protected void j() {
        r();
    }

    protected void k() {
        j();
        if (this.f != null) {
            d(this.f);
            this.f = null;
        }
    }

    public void a(D d) {
        super.a(d);
        d(d);
    }

    protected void d(D d) {
    }
}
