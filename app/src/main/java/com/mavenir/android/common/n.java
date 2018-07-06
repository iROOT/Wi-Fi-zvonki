package com.mavenir.android.common;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import com.fgmicrotec.mobile.android.fgvoip.f.b;

public class n {
    private static String[] a;
    private static String[] b;
    private static TypedArray c;

    private static void a(Context context) {
        Resources resources = context.getResources();
        if (a == null) {
            a = resources.getStringArray(b.emo_shortcuts);
        }
        if (b == null) {
            b = resources.getStringArray(b.emo_shortcuts_short);
        }
        if (c == null) {
            c = resources.obtainTypedArray(b.emo_image_ids);
        }
    }

    public static SpannableString a(String str, Context context) {
        if (!str.contains(":") && !str.contains(")") && !str.contains("!(") && !str.contains("<") && !str.contains(">") && !str.contains("|") && !str.contains(";") && !str.contains("=") && !str.contains("8-")) {
            return new SpannableString(str);
        }
        boolean z;
        int i;
        a(context);
        SpannableString spannableString = new SpannableString(str);
        int indexOf = str.indexOf("http://");
        int indexOf2 = str.indexOf("https://");
        if (indexOf == -1 && indexOf2 == -1) {
            z = false;
        } else {
            z = true;
        }
        String str2 = str;
        for (i = 0; i < a.length; i++) {
            str2 = a(a, str, context, str2, spannableString, z, i);
        }
        for (i = 0; i < b.length; i++) {
            str2 = a(b, str, context, str2, spannableString, z, i);
        }
        return spannableString;
    }

    private static String a(String[] strArr, String str, Context context, String str2, SpannableString spannableString, boolean z, int i) {
        if (!z || (z && !strArr[i].equals(":/"))) {
            int i2 = 0;
            while (str2.indexOf(strArr[i], i2) != -1) {
                str2 = str2.replace(strArr[i], "");
                int length = strArr[i].length();
                i2 = str.indexOf(strArr[i], i2);
                while (true) {
                    i2 = str.indexOf(strArr[i], i2);
                    if (i2 != -1) {
                        ImageSpan imageSpan = new ImageSpan(context, c.getResourceId(i, -1));
                        if (length == 2) {
                            spannableString.setSpan(imageSpan, i2, i2 + 2 > str.length() ? str.length() : i2 + 2, 0);
                        } else if (length == 3) {
                            spannableString.setSpan(imageSpan, i2, i2 + 3 > str.length() ? str.length() : i2 + 3, 0);
                        } else if (length == 4) {
                            spannableString.setSpan(imageSpan, i2, i2 + 4 > str.length() ? str.length() : i2 + 4, 0);
                        }
                        i2 += length;
                    }
                }
            }
        }
        return str2;
    }
}
