package com.b.a.c;

import com.b.a.b.a.e;
import java.util.Comparator;

public final class d {
    public static String a(String str, e eVar) {
        return "_" + eVar.a() + "x" + eVar.b();
    }

    public static Comparator<String> a() {
        return new Comparator<String>() {
            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((String) obj, (String) obj2);
            }

            public int a(String str, String str2) {
                return str.substring(0, str.lastIndexOf("_")).compareTo(str2.substring(0, str2.lastIndexOf("_")));
            }
        };
    }
}
