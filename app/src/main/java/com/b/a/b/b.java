package com.b.a.b;

import android.graphics.Bitmap;
import com.b.a.b.a.f;
import com.b.a.b.e.a;
import com.b.a.c.c;

final class b implements Runnable {
    private final Bitmap a;
    private final String b;
    private final a c;
    private final String d;
    private final com.b.a.b.c.a e;
    private final com.b.a.b.f.a f;
    private final f g;
    private final f h;

    public b(Bitmap bitmap, g gVar, f fVar, f fVar2) {
        this.a = bitmap;
        this.b = gVar.a;
        this.c = gVar.c;
        this.d = gVar.b;
        this.e = gVar.e.q();
        this.f = gVar.f;
        this.g = fVar;
        this.h = fVar2;
    }

    public void run() {
        if (this.c.e()) {
            c.a("ImageAware was collected by GC. Task is cancelled. [%s]", this.d);
            this.f.b(this.b, this.c.d());
        } else if (a()) {
            c.a("ImageAware is reused for another image. Task is cancelled. [%s]", this.d);
            this.f.b(this.b, this.c.d());
        } else {
            c.a("Display image in ImageAware (loaded from %1$s) [%2$s]", this.h, this.d);
            this.e.a(this.a, this.c, this.h);
            this.g.b(this.c);
            this.f.a(this.b, this.c.d(), this.a);
        }
    }

    private boolean a() {
        return !this.d.equals(this.g.a(this.c));
    }
}
