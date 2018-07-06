package com.mavenir.android.calllog.provider;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public class d {
    private static final String[] a = new String[0];
    private final List<String> b = new ArrayList();

    public d(String str) {
        a(str);
    }

    public d a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b.add(str);
        }
        return this;
    }

    public String a() {
        if (this.b.size() == 0) {
            return null;
        }
        return c.a((String[]) this.b.toArray(a));
    }
}
