package com.a.a;

import java.util.ArrayList;

public abstract class a implements Cloneable {
    ArrayList<a> a = null;

    public interface a {
        void onAnimationCancel(a aVar);

        void onAnimationEnd(a aVar);

        void onAnimationRepeat(a aVar);

        void onAnimationStart(a aVar);
    }

    public /* synthetic */ Object clone() {
        return e();
    }

    public void a() {
    }

    public void b() {
    }

    public void c() {
    }

    public void a(a aVar) {
        if (this.a == null) {
            this.a = new ArrayList();
        }
        this.a.add(aVar);
    }

    public void d() {
        if (this.a != null) {
            this.a.clear();
            this.a = null;
        }
    }

    public a e() {
        try {
            a aVar = (a) super.clone();
            if (this.a != null) {
                ArrayList arrayList = this.a;
                aVar.a = new ArrayList();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    aVar.a.add(arrayList.get(i));
                }
            }
            return aVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
