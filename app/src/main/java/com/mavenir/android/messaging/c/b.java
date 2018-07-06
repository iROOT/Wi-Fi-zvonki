package com.mavenir.android.messaging.c;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.mavenir.android.common.q;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class b extends ArrayList<a> {
    public static b a(Context context, long j, String str, boolean z) {
        b bVar = new b();
        for (String parseLong : str.split(" ")) {
            try {
                bVar.add(a.a(context, j, Long.parseLong(parseLong), z));
            } catch (Exception e) {
                q.c("ContactList", e.getLocalizedMessage(), e.getCause());
            }
        }
        return bVar;
    }

    public static b a(Context context, String[] strArr, boolean z) {
        b bVar = new b();
        if (strArr != null) {
            for (String a : strArr) {
                try {
                    bVar.add(a.a(context, a, z));
                } catch (Exception e) {
                    q.c("ContactList", e.getLocalizedMessage(), e.getCause());
                }
            }
        }
        return bVar;
    }

    public String[] a() {
        List arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.b() == null || aVar.b().length() <= 0) {
                arrayList.add(aVar.d());
            } else {
                arrayList.add(aVar.b());
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public String[] b() {
        List arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            CharSequence d = ((a) it.next()).d();
            if (!(TextUtils.isEmpty(d) || arrayList.contains(d))) {
                arrayList.add(d);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public String[] c() {
        List arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            CharSequence d = aVar.d();
            if (!(aVar.e() || TextUtils.isEmpty(d) || arrayList.contains(d))) {
                arrayList.add(d);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public boolean d() {
        Iterator it = iterator();
        while (it.hasNext()) {
            if (!((a) it.next()).e()) {
                return true;
            }
        }
        return false;
    }

    public String a(String str) {
        return TextUtils.join(str, a());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (size() != bVar.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            boolean z;
            for (int i2 = 0; i2 < bVar.size(); i2++) {
                if (PhoneNumberUtils.compare(((a) get(i)).d(), ((a) bVar.get(i2)).d())) {
                    z = true;
                    break;
                }
            }
            z = false;
            if (!z) {
                return false;
            }
        }
        return true;
    }
}
