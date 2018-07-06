package com.fgmicrotec.mobile.android.fgvoip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class b {
    public static List<a> a = new ArrayList();
    public static Map<Integer, a> b = new HashMap();

    public static class a {
        public int a;
        public String b;
        public String c;

        public a(int i, String str, String str2) {
            this.a = i;
            this.b = str;
            this.c = str2;
        }

        public String toString() {
            return this.b;
        }
    }

    public enum b {
        ABOUT,
        ACTIVATION,
        HELP
    }

    public static void a(a aVar) {
        a.add(aVar);
        b.put(Integer.valueOf(aVar.a), aVar);
    }

    public static void a() {
        a.clear();
        b.clear();
    }
}
