package org.spongycastle.util;

import android.support.v4.app.NotificationCompat;

public class IPAddress {
    public static boolean a(String str) {
        if (str.length() == 0) {
            return false;
        }
        String str2 = str + ".";
        int i = 0;
        int i2 = 0;
        while (i < str2.length()) {
            int indexOf = str2.indexOf(46, i);
            if (indexOf <= i) {
                break;
            } else if (i2 == 4) {
                return false;
            } else {
                try {
                    i = Integer.parseInt(str2.substring(i, indexOf));
                    if (i < 0 || i > 255) {
                        return false;
                    }
                    i = indexOf + 1;
                    i2++;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        if (i2 == 4) {
            return true;
        }
        return false;
    }

    public static boolean b(String str) {
        int indexOf = str.indexOf("/");
        String substring = str.substring(indexOf + 1);
        if (indexOf <= 0 || !a(str.substring(0, indexOf))) {
            return false;
        }
        if (a(substring) || a(substring, 32)) {
            return true;
        }
        return false;
    }

    public static boolean c(String str) {
        int indexOf = str.indexOf("/");
        String substring = str.substring(indexOf + 1);
        if (indexOf <= 0 || !d(str.substring(0, indexOf))) {
            return false;
        }
        if (d(substring) || a(substring, NotificationCompat.FLAG_HIGH_PRIORITY)) {
            return true;
        }
        return false;
    }

    private static boolean a(String str, int i) {
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt < 0 || parseInt > i) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean d(String str) {
        if (str.length() == 0) {
            return false;
        }
        String str2 = str + ":";
        int i = 0;
        boolean z = false;
        int i2 = 0;
        while (i < str2.length()) {
            int indexOf = str2.indexOf(58, i);
            if (indexOf < i) {
                break;
            } else if (i2 == 8) {
                return false;
            } else {
                if (i != indexOf) {
                    String substring = str2.substring(i, indexOf);
                    if (indexOf != str2.length() - 1 || substring.indexOf(46) <= 0) {
                        try {
                            i = Integer.parseInt(str2.substring(i, indexOf), 16);
                            if (i < 0) {
                                return false;
                            }
                            if (i > 65535) {
                                return false;
                            }
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    } else if (!a(substring)) {
                        return false;
                    } else {
                        i2++;
                    }
                } else if (indexOf != 1 && indexOf != str2.length() - 1 && z) {
                    return false;
                } else {
                    z = true;
                }
                i = indexOf + 1;
                i2++;
            }
        }
        if (i2 == 8 || z) {
            return true;
        }
        return false;
    }
}
