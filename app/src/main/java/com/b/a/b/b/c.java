package com.b.a.b.b;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import com.b.a.b.a.d;
import com.b.a.b.a.e;
import com.b.a.b.a.h;
import com.b.a.b.d.b;

public class c {
    private final String a;
    private final String b;
    private final String c;
    private final e d;
    private final d e;
    private final h f;
    private final b g;
    private final Object h;
    private final boolean i;
    private final Options j = new Options();

    public c(String str, String str2, String str3, e eVar, h hVar, b bVar, com.b.a.b.c cVar) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = eVar;
        this.e = cVar.j();
        this.f = hVar;
        this.g = bVar;
        this.h = cVar.n();
        this.i = cVar.m();
        a(cVar.k(), this.j);
    }

    private void a(Options options, Options options2) {
        options2.inDensity = options.inDensity;
        options2.inDither = options.inDither;
        options2.inInputShareable = options.inInputShareable;
        options2.inJustDecodeBounds = options.inJustDecodeBounds;
        options2.inPreferredConfig = options.inPreferredConfig;
        options2.inPurgeable = options.inPurgeable;
        options2.inSampleSize = options.inSampleSize;
        options2.inScaled = options.inScaled;
        options2.inScreenDensity = options.inScreenDensity;
        options2.inTargetDensity = options.inTargetDensity;
        options2.inTempStorage = options.inTempStorage;
        if (VERSION.SDK_INT >= 10) {
            b(options, options2);
        }
        if (VERSION.SDK_INT >= 11) {
            c(options, options2);
        }
    }

    @TargetApi(10)
    private void b(Options options, Options options2) {
        options2.inPreferQualityOverSpeed = options.inPreferQualityOverSpeed;
    }

    @TargetApi(11)
    private void c(Options options, Options options2) {
        options2.inBitmap = options.inBitmap;
        options2.inMutable = options.inMutable;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public e c() {
        return this.d;
    }

    public d d() {
        return this.e;
    }

    public h e() {
        return this.f;
    }

    public b f() {
        return this.g;
    }

    public Object g() {
        return this.h;
    }

    public boolean h() {
        return this.i;
    }

    public Options i() {
        return this.j;
    }
}
