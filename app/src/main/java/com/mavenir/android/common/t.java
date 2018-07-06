package com.mavenir.android.common;

import android.preference.EditTextPreference;
import android.telephony.PhoneNumberUtils;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.fgmicrotec.mobile.android.fgvoip.FgVoIP;
import com.fgmicrotec.mobile.android.fgvoip.f.j;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class t {

    public static class a {
        public static void a(String str) {
            AccessibilityManager accessibilityManager = (AccessibilityManager) FgVoIP.U().getSystemService("accessibility");
            if (accessibilityManager != null && accessibilityManager.isEnabled() && !TextUtils.isEmpty(str)) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain();
                obtain.setEventType(16384);
                obtain.getText().add(str);
                accessibilityManager.sendAccessibilityEvent(obtain);
            }
        }

        public static String b(String str) {
            String str2 = "";
            if (!TextUtils.isEmpty(str)) {
                for (int i = 0; i < str.length(); i++) {
                    str2 = (str2 + str.charAt(i)) + ",";
                }
            }
            return str2;
        }

        public static String c(String str) {
            if (str.contains(":")) {
                String str2 = "";
                String[] split = str.split(":");
                try {
                    int parseInt = Integer.parseInt(split[0]);
                    if (parseInt != 0) {
                        str2 = str2 + FgVoIP.U().getResources().getQuantityString(j.cd_hours, parseInt, new Object[]{Integer.valueOf(parseInt)}) + " ";
                    }
                    parseInt = Integer.parseInt(split[1]);
                    if (parseInt != 0) {
                        str2 = str2 + FgVoIP.U().getResources().getQuantityString(j.cd_minutes, parseInt, new Object[]{Integer.valueOf(parseInt)});
                    }
                    if (split.length != 3) {
                        return str2;
                    }
                    int parseInt2 = Integer.parseInt(split[2]);
                    if (parseInt2 == 0) {
                        return str2;
                    }
                    return str2 + FgVoIP.U().getResources().getQuantityString(j.cd_seconds, parseInt2, new Object[]{Integer.valueOf(parseInt2)});
                } catch (Exception e) {
                    q.a("MingleUtils", "getAccessibilityTime(): invalid format, must be hh:mm or hh:mm:ss");
                    return str2;
                }
            }
            q.a("MingleUtils", "getAccessibilityTime(): invalid format, must be hh:mm or hh:mm:ss");
            return "";
        }
    }

    public static class b {
        public static int[] a(List<Integer> list) {
            if (list == null) {
                return new int[0];
            }
            int[] iArr = new int[list.size()];
            int i = 0;
            for (Integer intValue : list) {
                int i2 = i + 1;
                iArr[i] = intValue.intValue();
                i = i2;
            }
            return iArr;
        }

        public static String a(ArrayList<String> arrayList, String str) {
            if (arrayList == null) {
                return "";
            }
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                stringBuilder.append(str2);
                if (!((String) arrayList.get(arrayList.size() - 1)).equals(str2)) {
                    stringBuilder.append(str);
                }
            }
            return stringBuilder.toString();
        }

        public static String a(String[] strArr, String str) {
            if (strArr == null) {
                return "";
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < strArr.length; i++) {
                stringBuilder.append(strArr[i]);
                if (i != strArr.length - 1) {
                    stringBuilder.append(str);
                }
            }
            return stringBuilder.toString();
        }

        public static String[] b(List<String> list) {
            if (list == null) {
                return new String[0];
            }
            String[] strArr = new String[list.size()];
            int i = 0;
            for (String str : list) {
                strArr[i] = str;
                i++;
            }
            return strArr;
        }

        public static ArrayList<String> a(String[] strArr) {
            if (strArr == null) {
                return new ArrayList();
            }
            ArrayList<String> arrayList = new ArrayList(strArr.length);
            for (Object add : strArr) {
                arrayList.add(add);
            }
            return arrayList;
        }

        public static List<String> a(String str, String str2) {
            List<String> list = null;
            if (str != null) {
                String[] split = str.split(str2);
                if (split != null && split.length > 0) {
                    list = new ArrayList(split.length);
                    for (Object add : split) {
                        list.add(add);
                    }
                }
            }
            if (list == null) {
                return Collections.emptyList();
            }
            return list;
        }

        public static String[] b(String str, String str2) {
            return b(a(str, str2));
        }

        public static int[] c(String str, String str2) {
            int i = 1;
            int i2 = 0;
            List arrayList = new ArrayList();
            int i3 = !TextUtils.isEmpty(str) ? 1 : 0;
            if (TextUtils.isEmpty(str2)) {
                i = 0;
            }
            if ((i3 & i) != 0) {
                String[] split = str.split(str2);
                if (split != null) {
                    i = split.length;
                    while (i2 < i) {
                        try {
                            arrayList.add(Integer.valueOf(Integer.parseInt(split[i2])));
                        } catch (NumberFormatException e) {
                        }
                        i2++;
                    }
                }
            }
            return a(arrayList);
        }

        public static String b(String[] strArr, String str) {
            if (strArr == null || str == null) {
                return "";
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < strArr.length; i++) {
                stringBuilder.append(strArr[i]);
                if (i != strArr.length - 1) {
                    stringBuilder.append(str);
                }
            }
            return stringBuilder.toString();
        }

        public static String a(int[] iArr, String str) {
            StringBuilder stringBuilder = new StringBuilder();
            if (iArr == null || str == null) {
                return "";
            }
            for (int i : iArr) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(str);
                }
                stringBuilder.append(i);
            }
            return stringBuilder.toString();
        }
    }

    public static class c {
        private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        public static String a(byte[] bArr, char c) {
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            while (i < bArr.length) {
                if (c != '\u0000' && i > 0) {
                    stringBuilder.append(c);
                }
                stringBuilder.append(a[(bArr[i] >>> 4) & 15]);
                stringBuilder.append(a[bArr[i] & 15]);
                i++;
            }
            return stringBuilder.toString();
        }

        public static CharSequence a(CharSequence charSequence, int i) {
            if (charSequence == null || charSequence.length() == 0) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder(i);
            int length = charSequence.length();
            Object obj = 1;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = charSequence.charAt(i2);
                if (obj != null && (Character.isLetter(charAt) || Character.isDigit(charAt))) {
                    stringBuilder.append(Character.toUpperCase(charAt));
                    if (stringBuilder.length() == i) {
                        break;
                    }
                    obj = null;
                }
                if (Character.isWhitespace(charAt)) {
                    obj = 1;
                }
            }
            return stringBuilder;
        }

        public static CharSequence a(CharSequence charSequence) {
            return a(charSequence, 2);
        }
    }

    public static class d {
        private static EnumMap<a, String> a = new EnumMap(a.class);

        public enum a {
            kk_mm,
            yyyy,
            yyyy_MM_dash,
            yyyy_MM_slash,
            dd_MMM,
            dd_MMM_yyyy,
            dd_MMM_yyyy_kk_mm_ss,
            dd_MM_yyyy_dash,
            dd_MM_yyyy_slash,
            yyyy_MM_dd_dash,
            yyyy_MM_dd_slash,
            yyyy_MM_dd_kk_mm_dash,
            yyyy_MM_dd_kk_mm_slash,
            yyyy_MM_dd_kk_mm_ss_dash,
            yyyy_MM_dd_kk_mm_ss_slash
        }

        static {
            a.put(a.kk_mm, "kk:mm");
            a.put(a.yyyy, "yyyy");
            a.put(a.yyyy_MM_dash, "yyyy-MM");
            a.put(a.yyyy_MM_slash, "yyyy/MM");
            a.put(a.yyyy_MM_dd_dash, "yyyy-MM-dd");
            a.put(a.yyyy_MM_dd_slash, "yyyy/MM/dd");
            a.put(a.dd_MMM, "dd MMM");
            a.put(a.dd_MMM_yyyy, "dd MMM yyyy");
            a.put(a.dd_MMM_yyyy_kk_mm_ss, "dd MMM yyyy kk:mm:ss");
            a.put(a.yyyy_MM_dd_kk_mm_dash, "yyyy-MM-dd kk:mm");
            a.put(a.yyyy_MM_dd_kk_mm_slash, "yyyy/MM/dd kk:mm");
            a.put(a.yyyy_MM_dd_kk_mm_ss_dash, "yyyy-MM-dd kk:mm:ss");
            a.put(a.yyyy_MM_dd_kk_mm_ss_slash, "yyyy/MM/dd kk:mm:ss");
            a.put(a.dd_MM_yyyy_dash, "dd-MM-yyyy");
            a.put(a.dd_MM_yyyy_slash, "dd/MM/yyyy");
        }

        public static String a(long j, a aVar) {
            return DateFormat.format((CharSequence) a.get(aVar), j).toString();
        }

        public static String a(long j) {
            return DateFormat.format("yyyy/MM/dd kk:mm", j).toString();
        }

        public static String a(Date date) {
            return DateFormat.format("yyyy/MM/dd kk:mm", date).toString();
        }

        public static String a(long j, String str) {
            return DateFormat.format(str, j).toString();
        }

        public static String b(long j) {
            return DateFormat.format("yyyy/MM/dd", j).toString();
        }

        public static String c(long j) {
            return DateFormat.format("kk:mm", j).toString();
        }

        public static String d(long j) {
            int i = ((int) j) % 60;
            int i2 = (int) ((j / 60) % 60);
            int i3 = (int) ((j / 3600) % 24);
            return String.format(Locale.getDefault(), "%02d:%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i)});
        }

        public static boolean a(String str) {
            return b(System.currentTimeMillis()).equals(str);
        }

        public static boolean e(long j) {
            return b(System.currentTimeMillis()).equals(b(j));
        }

        public static boolean f(long j) {
            return b(System.currentTimeMillis() - 86400000).equals(b(j));
        }

        public static String g(long j) {
            if (a(b(j))) {
                return a(j, a.kk_mm);
            }
            return a(j, a.dd_MMM);
        }

        public static Date a(String str, String str2) {
            if (str == null) {
                return null;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = new Date();
            try {
                return simpleDateFormat.parse(str);
            } catch (ParseException e) {
                q.d("MingleUtils", "formatDateTime(): " + e);
                return date;
            }
        }
    }

    public static class e {
        public static int a(String str) {
            try {
                byte[] address = InetAddress.getByName(str).getAddress();
                return (address[0] & 255) | ((((address[3] & 255) << 24) | ((address[2] & 255) << 16)) | ((address[1] & 255) << 8));
            } catch (UnknownHostException e) {
                return -1;
            }
        }
    }

    public static class f {
        private static final char[] a = new char[]{'-', '(', ')', ' ', '/', '\\'};
        private static HashSet<Character> b = new HashSet(a.length);

        static {
            for (char valueOf : a) {
                b.add(Character.valueOf(valueOf));
            }
        }

        public static String a(String str) {
            if (str == null) {
                return null;
            }
            if (!str.contains("sip:") && !str.contains("tel:")) {
                return str.replaceAll("[^+0-9]", "");
            }
            if (str.contains("@")) {
                return str.substring(0, str.indexOf("@")).replaceAll("[^+0-9]", "");
            }
            return str.replaceAll("[^+0-9]", "");
        }

        public static String b(String str) {
            return str.replaceAll("[^+,0-9]", "");
        }

        public static String c(String str) {
            return str.replaceAll("[^+,0-9*#]", "");
        }

        public static String d(String str) {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            StringBuilder stringBuilder = new StringBuilder();
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                int digit = Character.digit(charAt, 10);
                if (digit != -1) {
                    stringBuilder.append(digit);
                } else if (i == 0 && charAt == '+') {
                    stringBuilder.append(charAt);
                } else if ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z')) {
                    q.b("MingleUtils", "normalizeNumber(): number before: " + str);
                    str = d(PhoneNumberUtils.convertKeypadLettersToDigits(str));
                    q.b("MingleUtils", "normalizeNumber(): number after: " + str);
                    return str;
                }
            }
            return stringBuilder.toString();
        }

        public static String e(String str) {
            String str2 = "";
            String str3 = "";
            String str4 = "";
            str2 = "";
            if (str.contains("sip:") || str.contains("tel:")) {
                str3 = str.substring(0, 4);
                if (str.contains("@")) {
                    str2 = str.substring(str.indexOf("@"));
                    str4 = str.substring(4, str.indexOf("@"));
                } else {
                    str4 = str.substring(4);
                }
            }
            return str3 + d(str4) + str2;
        }

        public static String f(String str) {
            if (str == null) {
                return str;
            }
            if (str.startsWith("sip:") || str.startsWith("tel:")) {
                str = str.replace("sip:", "").replace("tel:", "");
            }
            if (str.contains("@")) {
                str = str.substring(0, str.indexOf("@"));
            }
            if (str.contains(";")) {
                return str.substring(0, str.indexOf(";"));
            }
            return str;
        }

        public static String a(String str, String str2) {
            String str3 = "";
            if (str == null || str.length() == 0) {
                throw new Exception("Number is empty!");
            } else if (str2 == null || str2.length() == 0) {
                throw new Exception("Uri template is empty!");
            } else if (str2.contains(":")) {
                return e((str2.indexOf(":") + 1));
            } else {
                throw new Exception("Uri template is invalid!");
            }
        }

        public static String a(String str, String str2, int i) {
            StringBuffer stringBuffer = new StringBuffer();
            if (TextUtils.isEmpty(str)) {
                q.d("MingleUtils", "numberToUri(): empty number");
                return stringBuffer.toString();
            }
            if (i == 0) {
                if (!str.contains("tel:")) {
                    stringBuffer.append("tel:");
                }
                stringBuffer.append(str);
            } else {
                if (!str.contains("sip:")) {
                    stringBuffer.append("sip:");
                }
                stringBuffer.append(str);
                if (!str.contains("@") && str2.length() > 4) {
                    stringBuffer.append(str2.substring(4, str2.length()));
                }
            }
            if (i == -1) {
                stringBuffer = stringBuffer.delete(0, 4);
            }
            return stringBuffer.toString();
        }

        public static boolean g(String str) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                if (Character.isLetter(str.charAt(i))) {
                    return true;
                }
            }
            return false;
        }

        public static boolean h(String str) {
            if (str == null || str.length() <= 0) {
                return false;
            }
            String i = i(str);
            int i2 = 0;
            while (i2 < i.length()) {
                char charAt = i.charAt(i2);
                if ((charAt != '+' || i2 != 0) && !Character.isDigit(charAt)) {
                    return false;
                }
                i2++;
            }
            return true;
        }

        public static String i(String str) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (!(b.contains(Character.valueOf(charAt)) || Character.isWhitespace(charAt) || Character.isSpaceChar(charAt))) {
                    stringBuilder.append(charAt);
                }
            }
            return stringBuilder.toString();
        }

        public static boolean j(String str) {
            if (str == null || str.length() == 0 || str.substring(0, 1).equals("-") || str.toLowerCase().equals("unknown") || str.toLowerCase().equals("anonymous")) {
                return true;
            }
            return false;
        }
    }

    public static class g {
        public static final Pattern a = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+");
        public static final Pattern b = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?(\\([0-9]+\\)[\\- \\.]*)?([0-9][0-9\\- \\.][0-9\\- \\.]+[0-9])");
    }

    public static class h {
        public static byte[] a(String str, String str2) {
            try {
                return MessageDigest.getInstance(str).digest(str2.getBytes(net.hockeyapp.android.e.d.DEFAULT_CHARSET));
            } catch (Exception e) {
                q.d("MingleUtils", "generateHash(): " + e);
                return null;
            }
        }

        public static String b(String str, String str2) {
            if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
                return str2;
            }
            return c.a(a(str, str2), '\u0000');
        }
    }

    public static void a(EditTextPreference editTextPreference, int i) {
        editTextPreference.getEditText().setFilters(new InputFilter[]{new LengthFilter(i)});
    }
}
