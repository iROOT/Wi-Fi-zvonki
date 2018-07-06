package com.b.a.b;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import com.b.a.b.a.e;
import com.b.a.b.a.f;
import com.b.a.b.f.a;
import com.b.a.b.f.b;
import com.b.a.c.c;

public class d {
    public static final String a = d.class.getSimpleName();
    private static volatile d e;
    private e b;
    private f c;
    private a d = new com.b.a.b.f.d();

    public static d a() {
        if (e == null) {
            synchronized (d.class) {
                if (e == null) {
                    e = new d();
                }
            }
        }
        return e;
    }

    protected d() {
    }

    public synchronized void a(e eVar) {
        if (eVar == null) {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        } else if (this.b == null) {
            c.a("Initialize ImageLoader with configuration", new Object[0]);
            this.c = new f(eVar);
            this.b = eVar;
        } else {
            c.c("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
        }
    }

    public void a(String str, com.b.a.b.e.a aVar, c cVar, a aVar2, b bVar) {
        a(str, aVar, cVar, null, aVar2, bVar);
    }

    public void a(String str, com.b.a.b.e.a aVar, c cVar, e eVar, a aVar2, b bVar) {
        d();
        if (aVar == null) {
            throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
        }
        a aVar3;
        c cVar2;
        if (aVar2 == null) {
            aVar3 = this.d;
        } else {
            aVar3 = aVar2;
        }
        if (cVar == null) {
            cVar2 = this.b.r;
        } else {
            cVar2 = cVar;
        }
        if (TextUtils.isEmpty(str)) {
            this.c.b(aVar);
            aVar3.a(str, aVar.d());
            if (cVar2.b()) {
                aVar.a(cVar2.b(this.b.a));
            } else {
                aVar.a(null);
            }
            aVar3.a(str, aVar.d(), null);
            return;
        }
        e a;
        if (eVar == null) {
            a = com.b.a.c.a.a(aVar, this.b.a());
        } else {
            a = eVar;
        }
        String a2 = com.b.a.c.d.a(str, a);
        this.c.a(aVar, a2);
        aVar3.a(str, aVar.d());
        Bitmap a3 = this.b.n.a(a2);
        if (a3 == null || a3.isRecycled()) {
            if (cVar2.a()) {
                aVar.a(cVar2.a(this.b.a));
            } else if (cVar2.g()) {
                aVar.a(null);
            }
            h hVar = new h(this.c, new g(str, aVar, a, a2, cVar2, aVar3, bVar, this.c.a(str)), a(cVar2));
            if (cVar2.s()) {
                hVar.run();
                return;
            } else {
                this.c.a(hVar);
                return;
            }
        }
        c.a("Load image from memory cache [%s]", a2);
        if (cVar2.e()) {
            i iVar = new i(this.c, a3, new g(str, aVar, a, a2, cVar2, aVar3, bVar, this.c.a(str)), a(cVar2));
            if (cVar2.s()) {
                iVar.run();
                return;
            } else {
                this.c.a(iVar);
                return;
            }
        }
        cVar2.q().a(a3, aVar, f.MEMORY_CACHE);
        aVar3.a(str, aVar.d(), a3);
    }

    public void a(String str, ImageView imageView, c cVar) {
        a(str, new com.b.a.b.e.b(imageView), cVar, null, null);
    }

    public void a(String str, ImageView imageView, c cVar, a aVar) {
        a(str, imageView, cVar, aVar, null);
    }

    public void a(String str, ImageView imageView, c cVar, a aVar, b bVar) {
        a(str, new com.b.a.b.e.b(imageView), cVar, aVar, bVar);
    }

    private void d() {
        if (this.b == null) {
            throw new IllegalStateException("ImageLoader must be init with configuration before using");
        }
    }

    public void a(ImageView imageView) {
        this.c.b(new com.b.a.b.e.b(imageView));
    }

    public void b() {
        this.c.a();
    }

    public void c() {
        this.c.b();
    }

    private static Handler a(c cVar) {
        Handler r = cVar.r();
        if (cVar.s()) {
            return null;
        }
        if (r == null && Looper.myLooper() == Looper.getMainLooper()) {
            return new Handler();
        }
        return r;
    }
}
