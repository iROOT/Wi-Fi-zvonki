package com.a.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class m extends a {
    private static ThreadLocal<a> h = new ThreadLocal();
    private static final ThreadLocal<ArrayList<m>> i = new ThreadLocal<ArrayList<m>>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<m> a() {
            return new ArrayList();
        }
    };
    private static final ThreadLocal<ArrayList<m>> j = new ThreadLocal<ArrayList<m>>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<m> a() {
            return new ArrayList();
        }
    };
    private static final ThreadLocal<ArrayList<m>> k = new ThreadLocal<ArrayList<m>>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<m> a() {
            return new ArrayList();
        }
    };
    private static final ThreadLocal<ArrayList<m>> l = new ThreadLocal<ArrayList<m>>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<m> a() {
            return new ArrayList();
        }
    };
    private static final ThreadLocal<ArrayList<m>> m = new ThreadLocal<ArrayList<m>>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<m> a() {
            return new ArrayList();
        }
    };
    private static final Interpolator n = new AccelerateDecelerateInterpolator();
    private static final l o = new e();
    private static final l p = new c();
    private static long z = 10;
    private int A = 0;
    private int B = 1;
    private Interpolator C = n;
    private ArrayList<b> D = null;
    long b;
    long c = -1;
    int d = 0;
    boolean e = false;
    k[] f;
    HashMap<String, k> g;
    private boolean q = false;
    private int r = 0;
    private float s = 0.0f;
    private boolean t = false;
    private long u;
    private boolean v = false;
    private boolean w = false;
    private long x = 300;
    private long y = 0;

    private static class a extends Handler {
        private a() {
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        public void handleMessage(Message message) {
            ArrayList arrayList;
            Object obj;
            ArrayList arrayList2;
            int size;
            int i;
            m mVar;
            ArrayList arrayList3 = (ArrayList) m.i.get();
            ArrayList arrayList4 = (ArrayList) m.k.get();
            switch (message.what) {
                case 0:
                    arrayList = (ArrayList) m.j.get();
                    if (arrayList3.size() > 0 || arrayList4.size() > 0) {
                        obj = null;
                    } else {
                        int obj2 = 1;
                    }
                    while (arrayList.size() > 0) {
                        arrayList2 = (ArrayList) arrayList.clone();
                        arrayList.clear();
                        size = arrayList2.size();
                        for (i = 0; i < size; i++) {
                            mVar = (m) arrayList2.get(i);
                            if (mVar.y == 0) {
                                mVar.r();
                            } else {
                                arrayList4.add(mVar);
                            }
                        }
                    }
                    break;
                case 1:
                    obj2 = 1;
                    break;
                default:
                    return;
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            arrayList = (ArrayList) m.m.get();
            arrayList2 = (ArrayList) m.l.get();
            size = arrayList4.size();
            for (i = 0; i < size; i++) {
                mVar = (m) arrayList4.get(i);
                if (mVar.a(currentAnimationTimeMillis)) {
                    arrayList.add(mVar);
                }
            }
            size = arrayList.size();
            if (size > 0) {
                for (i = 0; i < size; i++) {
                    mVar = (m) arrayList.get(i);
                    mVar.r();
                    mVar.v = true;
                    arrayList4.remove(mVar);
                }
                arrayList.clear();
            }
            i = arrayList3.size();
            int i2 = 0;
            while (i2 < i) {
                int i3;
                m mVar2 = (m) arrayList3.get(i2);
                if (mVar2.e(currentAnimationTimeMillis)) {
                    arrayList2.add(mVar2);
                }
                if (arrayList3.size() == i) {
                    i3 = i2 + 1;
                    i2 = i;
                } else {
                    i--;
                    arrayList2.remove(mVar2);
                    i3 = i2;
                    i2 = i;
                }
                i = i2;
                i2 = i3;
            }
            if (arrayList2.size() > 0) {
                for (i2 = 0; i2 < arrayList2.size(); i2++) {
                    ((m) arrayList2.get(i2)).g();
                }
                arrayList2.clear();
            }
            if (obj2 == null) {
                return;
            }
            if (!arrayList3.isEmpty() || !arrayList4.isEmpty()) {
                sendEmptyMessageDelayed(1, Math.max(0, m.z - (AnimationUtils.currentAnimationTimeMillis() - currentAnimationTimeMillis)));
            }
        }
    }

    public interface b {
        void onAnimationUpdate(m mVar);
    }

    public /* synthetic */ Object clone() {
        return h();
    }

    public /* synthetic */ a e() {
        return h();
    }

    public void a(k... kVarArr) {
        this.f = kVarArr;
        this.g = new HashMap(r2);
        for (k kVar : kVarArr) {
            this.g.put(kVar.c(), kVar);
        }
        this.e = false;
    }

    void f() {
        if (!this.e) {
            for (k b : this.f) {
                b.b();
            }
            this.e = true;
        }
    }

    public m b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.x = j;
        return this;
    }

    public void c(long j) {
        f();
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (this.d != 1) {
            this.c = j;
            this.d = 2;
        }
        this.b = currentAnimationTimeMillis - j;
        e(currentAnimationTimeMillis);
    }

    public long i() {
        if (!this.e || this.d == 0) {
            return 0;
        }
        return AnimationUtils.currentAnimationTimeMillis() - this.b;
    }

    public void d(long j) {
        this.y = j;
    }

    public void a(b bVar) {
        if (this.D == null) {
            this.D = new ArrayList();
        }
        this.D.add(bVar);
    }

    public void j() {
        if (this.D != null) {
            this.D.clear();
            this.D = null;
        }
    }

    public void a(Interpolator interpolator) {
        if (interpolator != null) {
            this.C = interpolator;
        } else {
            this.C = new LinearInterpolator();
        }
    }

    private void a(boolean z) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        this.q = z;
        this.r = 0;
        this.d = 0;
        this.w = true;
        this.t = false;
        ((ArrayList) j.get()).add(this);
        if (this.y == 0) {
            c(i());
            this.d = 0;
            this.v = true;
            if (this.a != null) {
                ArrayList arrayList = (ArrayList) this.a.clone();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((com.a.a.a.a) arrayList.get(i)).onAnimationStart(this);
                }
            }
        }
        a aVar = (a) h.get();
        if (aVar == null) {
            aVar = new a();
            h.set(aVar);
        }
        aVar.sendEmptyMessage(0);
    }

    public void a() {
        a(false);
    }

    public void b() {
        if (this.d != 0 || ((ArrayList) j.get()).contains(this) || ((ArrayList) k.get()).contains(this)) {
            if (this.v && this.a != null) {
                Iterator it = ((ArrayList) this.a.clone()).iterator();
                while (it.hasNext()) {
                    ((com.a.a.a.a) it.next()).onAnimationCancel(this);
                }
            }
            g();
        }
    }

    public void c() {
        if (!((ArrayList) i.get()).contains(this) && !((ArrayList) j.get()).contains(this)) {
            this.t = false;
            r();
        } else if (!this.e) {
            f();
        }
        if (this.A <= 0 || (this.A & 1) != 1) {
            a(1.0f);
        } else {
            a(0.0f);
        }
        g();
    }

    public boolean k() {
        return this.d == 1 || this.v;
    }

    private void g() {
        ((ArrayList) i.get()).remove(this);
        ((ArrayList) j.get()).remove(this);
        ((ArrayList) k.get()).remove(this);
        this.d = 0;
        if (this.v && this.a != null) {
            ArrayList arrayList = (ArrayList) this.a.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((com.a.a.a.a) arrayList.get(i)).onAnimationEnd(this);
            }
        }
        this.v = false;
        this.w = false;
    }

    private void r() {
        f();
        ((ArrayList) i.get()).add(this);
        if (this.y > 0 && this.a != null) {
            ArrayList arrayList = (ArrayList) this.a.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((com.a.a.a.a) arrayList.get(i)).onAnimationStart(this);
            }
        }
    }

    private boolean a(long j) {
        if (this.t) {
            long j2 = j - this.u;
            if (j2 > this.y) {
                this.b = j - (j2 - this.y);
                this.d = 1;
                return true;
            }
        }
        this.t = true;
        this.u = j;
        return false;
    }

    boolean e(long j) {
        boolean z = false;
        if (this.d == 0) {
            this.d = 1;
            if (this.c < 0) {
                this.b = j;
            } else {
                this.b = j - this.c;
                this.c = -1;
            }
        }
        switch (this.d) {
            case 1:
            case 2:
                float f;
                float f2 = this.x > 0 ? ((float) (j - this.b)) / ((float) this.x) : 1.0f;
                if (f2 < 1.0f) {
                    f = f2;
                } else if (this.r < this.A || this.A == -1) {
                    if (this.a != null) {
                        int size = this.a.size();
                        for (int i = 0; i < size; i++) {
                            ((com.a.a.a.a) this.a.get(i)).onAnimationRepeat(this);
                        }
                    }
                    if (this.B == 2) {
                        this.q = !this.q;
                    }
                    this.r += (int) f2;
                    f = f2 % 1.0f;
                    this.b += this.x;
                } else {
                    f = Math.min(f2, 1.0f);
                    z = true;
                }
                if (this.q) {
                    f = 1.0f - f;
                }
                a(f);
                break;
        }
        return z;
    }

    void a(float f) {
        int i;
        float interpolation = this.C.getInterpolation(f);
        this.s = interpolation;
        for (k a : this.f) {
            a.a(interpolation);
        }
        if (this.D != null) {
            int size = this.D.size();
            for (i = 0; i < size; i++) {
                ((b) this.D.get(i)).onAnimationUpdate(this);
            }
        }
    }

    public m h() {
        int i = 0;
        m mVar = (m) super.e();
        if (this.D != null) {
            ArrayList arrayList = this.D;
            mVar.D = new ArrayList();
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                mVar.D.add(arrayList.get(i2));
            }
        }
        mVar.c = -1;
        mVar.q = false;
        mVar.r = 0;
        mVar.e = false;
        mVar.d = 0;
        mVar.t = false;
        k[] kVarArr = this.f;
        if (kVarArr != null) {
            int length = kVarArr.length;
            mVar.f = new k[length];
            mVar.g = new HashMap(length);
            while (i < length) {
                k a = kVarArr[i].a();
                mVar.f[i] = a;
                mVar.g.put(a.c(), a);
                i++;
            }
        }
        return mVar;
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.f != null) {
            for (k kVar : this.f) {
                str = str + "\n    " + kVar.toString();
            }
        }
        return str;
    }
}
