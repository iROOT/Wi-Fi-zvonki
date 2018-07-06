package com.b.a.b;

import android.graphics.Bitmap;
import android.os.Handler;
import com.b.a.b.a.f;
import com.b.a.c.c;

final class i implements Runnable {
    private final f a;
    private final Bitmap b;
    private final g c;
    private final Handler d;

    public i(f fVar, Bitmap bitmap, g gVar, Handler handler) {
        this.a = fVar;
        this.b = bitmap;
        this.c = gVar;
        this.d = handler;
    }

    public void run() {
        c.a("PostProcess image before displaying [%s]", this.c.b);
        h.a(new b(this.c.e.p().a(this.b), this.c, this.a, f.MEMORY_CACHE), this.c.e.s(), this.d, this.a);
    }
}
