package com.mavenir.android.common;

import java.util.ArrayList;
import java.util.Iterator;

public final class i {

    public interface a<T> {
        boolean a(T t);

        boolean b(T t);
    }

    public static <T extends a<T>> void a(ArrayList<T> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            a aVar = (a) arrayList.get(i);
            if (aVar != null) {
                for (int i2 = i + 1; i2 < size; i2++) {
                    a aVar2 = (a) arrayList.get(i2);
                    if (aVar2 != null && aVar.b(aVar2)) {
                        aVar.a(aVar2);
                        arrayList.set(i2, null);
                    }
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                it.remove();
            }
        }
    }
}
