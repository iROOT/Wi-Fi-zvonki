package org.spongycastle.util;

import android.support.v4.app.NotificationCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

public final class Strings {
    public static String a(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i3 < bArr.length) {
            i2++;
            if ((bArr[i3] & 240) == 240) {
                i2++;
                i3 += 4;
            } else if ((bArr[i3] & 224) == 224) {
                i3 += 3;
            } else if ((bArr[i3] & 192) == 192) {
                i3 += 2;
            } else {
                i3++;
            }
        }
        char[] cArr = new char[i2];
        i2 = 0;
        while (i2 < bArr.length) {
            char c;
            int i4;
            if ((bArr[i2] & 240) == 240) {
                i3 = (((((bArr[i2] & 3) << 18) | ((bArr[i2 + 1] & 63) << 12)) | ((bArr[i2 + 2] & 63) << 6)) | (bArr[i2 + 3] & 63)) - 65536;
                char c2 = (char) (55296 | (i3 >> 10));
                c = (char) ((i3 & 1023) | 56320);
                i4 = i + 1;
                cArr[i] = c2;
                i2 += 4;
            } else if ((bArr[i2] & 224) == 224) {
                c = (char) ((((bArr[i2] & 15) << 12) | ((bArr[i2 + 1] & 63) << 6)) | (bArr[i2 + 2] & 63));
                i2 += 3;
                i4 = i;
            } else if ((bArr[i2] & 208) == 208) {
                c = (char) (((bArr[i2] & 31) << 6) | (bArr[i2 + 1] & 63));
                i2 += 2;
                i4 = i;
            } else if ((bArr[i2] & 192) == 192) {
                c = (char) (((bArr[i2] & 31) << 6) | (bArr[i2 + 1] & 63));
                i2 += 2;
                i4 = i;
            } else {
                c = (char) (bArr[i2] & 255);
                i2++;
                i4 = i;
            }
            i = i4 + 1;
            cArr[i4] = c;
        }
        return new String(cArr);
    }

    public static byte[] a(String str) {
        return a(str.toCharArray());
    }

    public static byte[] a(char[] cArr) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a(cArr, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("cannot encode string to byte array!");
        }
    }

    public static void a(char[] cArr, OutputStream outputStream) {
        int i = 0;
        while (i < cArr.length) {
            char c = cArr[i];
            if (c < '') {
                outputStream.write(c);
            } else if (c < 'ࠀ') {
                outputStream.write((c >> 6) | 192);
                outputStream.write((c & 63) | NotificationCompat.FLAG_HIGH_PRIORITY);
            } else if (c < '?' || c > '?') {
                outputStream.write((c >> 12) | 224);
                outputStream.write(((c >> 6) & 63) | NotificationCompat.FLAG_HIGH_PRIORITY);
                outputStream.write((c & 63) | NotificationCompat.FLAG_HIGH_PRIORITY);
            } else if (i + 1 >= cArr.length) {
                throw new IllegalStateException("invalid UTF-16 codepoint");
            } else {
                i++;
                char c2 = cArr[i];
                if (c > '?') {
                    throw new IllegalStateException("invalid UTF-16 codepoint");
                }
                int i2 = (((c & 1023) << 10) | (c2 & 1023)) + 65536;
                outputStream.write((i2 >> 18) | 240);
                outputStream.write(((i2 >> 12) & 63) | NotificationCompat.FLAG_HIGH_PRIORITY);
                outputStream.write(((i2 >> 6) & 63) | NotificationCompat.FLAG_HIGH_PRIORITY);
                outputStream.write((i2 & 63) | NotificationCompat.FLAG_HIGH_PRIORITY);
            }
            i++;
        }
    }

    public static String b(String str) {
        int i = 0;
        char[] toCharArray = str.toCharArray();
        int i2 = 0;
        while (i != toCharArray.length) {
            char c = toCharArray[i];
            if ('a' <= c && 'z' >= c) {
                i2 = 1;
                toCharArray[i] = (char) ((c - 97) + 65);
            }
            i++;
        }
        if (i2 != 0) {
            return new String(toCharArray);
        }
        return str;
    }

    public static String c(String str) {
        int i = 0;
        char[] toCharArray = str.toCharArray();
        int i2 = 0;
        while (i != toCharArray.length) {
            char c = toCharArray[i];
            if ('A' <= c && 'Z' >= c) {
                i2 = 1;
                toCharArray[i] = (char) ((c - 65) + 97);
            }
            i++;
        }
        if (i2 != 0) {
            return new String(toCharArray);
        }
        return str;
    }

    public static byte[] d(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) str.charAt(i);
        }
        return bArr;
    }

    public static String b(byte[] bArr) {
        return new String(c(bArr));
    }

    public static char[] c(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return cArr;
    }

    public static String[] a(String str, char c) {
        int i = 0;
        Vector vector = new Vector();
        int i2 = 1;
        String str2 = str;
        while (i2 != 0) {
            int indexOf = str2.indexOf(c);
            if (indexOf > 0) {
                vector.addElement(str2.substring(0, indexOf));
                str2 = str2.substring(indexOf + 1);
            } else {
                vector.addElement(str2);
                i2 = 0;
            }
        }
        String[] strArr = new String[vector.size()];
        while (i != strArr.length) {
            strArr[i] = (String) vector.elementAt(i);
            i++;
        }
        return strArr;
    }
}
