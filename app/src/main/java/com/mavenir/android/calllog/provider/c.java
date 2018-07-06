package com.mavenir.android.calllog.provider;

import android.content.ContentValues;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class c {
    public static String a(String str, long j) {
        return a(str, "=", j);
    }

    public static String b(String str, long j) {
        return a(str, "!=", j);
    }

    private static String a(String str, String str2, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(str);
        stringBuilder.append(" ").append(str2).append(" ");
        stringBuilder.append(j);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public static String a(String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object obj : strArr) {
            if (!TextUtils.isEmpty(obj)) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(" AND ");
                }
                stringBuilder.append("(");
                stringBuilder.append(obj);
                stringBuilder.append(")");
            }
        }
        return stringBuilder.toString();
    }

    public static void a(HashMap<String, String> hashMap, ContentValues contentValues) {
        a(hashMap.keySet(), contentValues, "Is invalid.");
    }

    public static void a(Set<String> set, ContentValues contentValues, String str) {
        for (Entry entry : contentValues.valueSet()) {
            if (!set.contains(entry.getKey())) {
                throw new IllegalArgumentException("Column '" + ((String) entry.getKey()) + "'. " + str);
            }
        }
    }
}
