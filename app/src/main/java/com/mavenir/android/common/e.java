package com.mavenir.android.common;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.CallLog.Calls;
import android.text.TextUtils;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.k;
import com.mavenir.android.settings.c.c;

public class e {
    public static void a(String str, String str2, int i, long j, String str3) {
        if (str2 != null && str2.equals(FgVoIP.U().aA())) {
            return;
        }
        if (str2 == null || !str2.equalsIgnoreCase(c.h())) {
            String str4;
            String str5;
            h a = h.a(str2);
            ContentValues contentValues = new ContentValues();
            if (TextUtils.isEmpty(str2)) {
                str4 = "";
                str5 = "-1";
                if (VERSION.SDK_INT >= 19) {
                    str5 = "";
                    contentValues.put("presentation", Integer.valueOf(3));
                }
                if (Build.MANUFACTURER.equalsIgnoreCase("Samsung")) {
                    str5 = FgVoIP.U().getString(k.call_log_blocked_number);
                }
            } else {
                str5 = str2;
                str4 = str;
            }
            contentValues.put("number", str5);
            contentValues.put("type", Integer.valueOf(i));
            int currentTimeMillis = ((int) ((System.currentTimeMillis() - j) + 500)) / 1000;
            if (j == 0) {
                currentTimeMillis = 0;
            }
            contentValues.put("duration", Integer.valueOf(currentTimeMillis));
            contentValues.put("date", Long.valueOf(System.currentTimeMillis()));
            if (a != null) {
                contentValues.put("name", a.a());
                contentValues.put("numbertype", Integer.valueOf(a.c()));
                contentValues.put("numberlabel", a.d());
            }
            contentValues.put("new", Integer.valueOf(1));
            if (!TextUtils.isEmpty(str3)) {
                contentValues.put("voicemail_uri", str3);
            }
            if (Build.MANUFACTURER.equalsIgnoreCase("HTC")) {
                long b = k.b(str5);
                if (b != -1) {
                    contentValues.put("raw_contact_id", Long.valueOf(b));
                }
            }
            FgVoIP.U().getContentResolver().insert(Calls.CONTENT_URI, contentValues);
            switch (i) {
                case 1:
                    q.a("CallLogWriter", "writeSync(): added INCOMING_TYPE");
                    break;
                case 2:
                    q.a("CallLogWriter", "writeSync(): added OUTGOING_TYPE");
                    break;
                case 3:
                    q.a("CallLogWriter", "writeSync(): added MISSED_TYPE");
                    break;
                default:
                    q.a("CallLogWriter", "writeSync(): added " + i);
                    break;
            }
            if (i == 3) {
                com.fgmicrotec.mobile.android.fgvoip.e.a().a(str4, str2, null, System.currentTimeMillis());
            }
        }
    }

    public static void a(String str, String str2, int i, long j) {
        final String str3 = str;
        final String str4 = str2;
        final int i2 = i;
        final long j2 = j;
        new AsyncTask<Void, Void, Void>() {
            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((Void[]) objArr);
            }

            protected Void a(Void... voidArr) {
                e.a(str3, str4, i2, j2, null);
                return null;
            }
        }.execute(new Void[0]);
    }

    public static void b(String str, String str2, int i, long j, String str3) {
        final String str4 = str;
        final String str5 = str2;
        final int i2 = i;
        final long j2 = j;
        final String str6 = str3;
        new AsyncTask<Void, Void, Void>() {
            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((Void[]) objArr);
            }

            protected Void a(Void... voidArr) {
                e.a(str4, str5, i2, j2, str6);
                return null;
            }
        }.execute(new Void[0]);
    }
}
