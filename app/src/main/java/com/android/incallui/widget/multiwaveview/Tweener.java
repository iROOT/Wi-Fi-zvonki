package com.android.incallui.widget.multiwaveview;

import android.view.animation.Interpolator;
import com.a.a.a.a;
import com.a.a.b;
import com.a.a.i;
import com.a.a.k;
import com.a.a.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

class Tweener {
    private static final boolean DEBUG = false;
    private static final String TAG = "Tweener";
    private static a mCleanupListener = new b() {
        public void onAnimationEnd(com.a.a.a aVar) {
            Tweener.remove(aVar);
        }

        public void onAnimationCancel(com.a.a.a aVar) {
            Tweener.remove(aVar);
        }
    };
    private static HashMap<Object, Tweener> sTweens = new HashMap();
    i animator;

    public Tweener(i iVar) {
        this.animator = iVar;
    }

    private static void remove(com.a.a.a aVar) {
        Iterator it = sTweens.entrySet().iterator();
        while (it.hasNext()) {
            if (((Tweener) ((Entry) it.next()).getValue()).animator == aVar) {
                it.remove();
                return;
            }
        }
    }

    public static Tweener to(Object obj, long j, Object... objArr) {
        i a;
        Tweener tweener;
        long j2 = 0;
        m.b bVar = null;
        a aVar = null;
        Interpolator interpolator = null;
        ArrayList arrayList = new ArrayList(objArr.length / 2);
        int i = 0;
        while (i < objArr.length) {
            if (objArr[i] instanceof String) {
                Interpolator interpolator2;
                a aVar2;
                m.b bVar2;
                long j3;
                String str = (String) objArr[i];
                Object obj2 = objArr[i + 1];
                if ("simultaneousTween".equals(str)) {
                    interpolator2 = interpolator;
                    aVar2 = aVar;
                    bVar2 = bVar;
                    j3 = j2;
                } else if ("ease".equals(str)) {
                    interpolator2 = (Interpolator) obj2;
                    aVar2 = aVar;
                    bVar2 = bVar;
                    j3 = j2;
                } else if ("onUpdate".equals(str) || "onUpdateListener".equals(str)) {
                    interpolator2 = interpolator;
                    bVar2 = (m.b) obj2;
                    aVar2 = aVar;
                    j3 = j2;
                } else if ("onComplete".equals(str) || "onCompleteListener".equals(str)) {
                    aVar2 = (a) obj2;
                    interpolator2 = interpolator;
                    bVar2 = bVar;
                    j3 = j2;
                } else if ("delay".equals(str)) {
                    bVar2 = bVar;
                    a aVar3 = aVar;
                    j3 = ((Number) obj2).longValue();
                    interpolator2 = interpolator;
                    aVar2 = aVar3;
                } else if ("syncWith".equals(str)) {
                    interpolator2 = interpolator;
                    aVar2 = aVar;
                    bVar2 = bVar;
                    j3 = j2;
                } else if (obj2 instanceof float[]) {
                    arrayList.add(k.a(str, ((float[]) obj2)[0], ((float[]) obj2)[1]));
                    interpolator2 = interpolator;
                    aVar2 = aVar;
                    bVar2 = bVar;
                    j3 = j2;
                } else if (obj2 instanceof int[]) {
                    arrayList.add(k.a(str, ((int[]) obj2)[0], ((int[]) obj2)[1]));
                    interpolator2 = interpolator;
                    aVar2 = aVar;
                    bVar2 = bVar;
                    j3 = j2;
                } else if (obj2 instanceof Number) {
                    arrayList.add(k.a(str, ((Number) obj2).floatValue()));
                    interpolator2 = interpolator;
                    aVar2 = aVar;
                    bVar2 = bVar;
                    j3 = j2;
                } else {
                    throw new IllegalArgumentException("Bad argument for key \"" + str + "\" with value " + obj2.getClass());
                }
                i += 2;
                j2 = j3;
                aVar = aVar2;
                bVar = bVar2;
                interpolator = interpolator2;
            } else {
                throw new IllegalArgumentException("Key must be a string: " + objArr[i]);
            }
        }
        Tweener tweener2 = (Tweener) sTweens.get(obj);
        if (tweener2 == null) {
            a = i.a(obj, (k[]) arrayList.toArray(new k[arrayList.size()]));
            tweener = new Tweener(a);
            sTweens.put(obj, tweener);
        } else {
            i iVar = ((Tweener) sTweens.get(obj)).animator;
            replace(arrayList, obj);
            i iVar2 = iVar;
            tweener = tweener2;
            a = iVar2;
        }
        if (interpolator != null) {
            a.a(interpolator);
        }
        a.d(j2);
        a.a(j);
        if (bVar != null) {
            a.j();
            a.a(bVar);
        }
        if (aVar != null) {
            a.d();
            a.a(aVar);
        }
        a.a(mCleanupListener);
        return tweener;
    }

    Tweener from(Object obj, long j, Object... objArr) {
        return to(obj, j, objArr);
    }

    public static void reset() {
        sTweens.clear();
    }

    private static void replace(ArrayList<k> arrayList, Object... objArr) {
        for (Object obj : objArr) {
            Tweener tweener = (Tweener) sTweens.get(obj);
            if (tweener != null) {
                tweener.animator.b();
                if (arrayList != null) {
                    tweener.animator.a((k[]) arrayList.toArray(new k[arrayList.size()]));
                } else {
                    sTweens.remove(tweener);
                }
            }
        }
    }
}
