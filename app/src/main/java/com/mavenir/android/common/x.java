package com.mavenir.android.common;

import java.util.Vector;

public class x {
    public static Vector<String> a(String str) {
        Vector<String> vector = new Vector();
        int i = 0;
        int i2 = -2;
        int i3 = 0;
        while (i3 < str.length()) {
            if (Character.isDigit(str.charAt(i3)) || str.charAt(i3) == '.' || str.charAt(i3) == '+' || str.charAt(i3) == '-' || str.charAt(i3) == '/' || str.charAt(i3) == ' ') {
                if (i3 - i2 == 1) {
                    i++;
                    if (i > 4 && i3 == str.length() - 1) {
                        vector.add(str.substring(i3 - i, i3 + 1));
                    }
                }
                i2 = i3;
            } else {
                if (i > 4) {
                    vector.add(str.substring((i3 - i) - 1, i3));
                }
                i = 0;
            }
            i3++;
        }
        return vector;
    }

    public static Vector<String> b(String str) {
        int i;
        Vector<String> vector = new Vector();
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        Vector vector4 = new Vector();
        int i2 = -1;
        do {
            i2 = str.indexOf("http://", i2 + 1);
            if (i2 > -1) {
                vector2.add(Integer.valueOf(i2));
                continue;
            }
        } while (i2 != -1);
        i2 = -1;
        do {
            i2 = str.indexOf("https://", i2 + 1);
            if (i2 > -1) {
                vector2.add(Integer.valueOf(i2));
                continue;
            }
        } while (i2 != -1);
        i2 = -1;
        do {
            i2 = str.indexOf("www.", i2 + 1);
            if (i2 > -1) {
                vector3.add(Integer.valueOf(i2));
                continue;
            }
        } while (i2 != -1);
        i2 = -1;
        do {
            int indexOf = str.indexOf(" ", i2 + 1);
            i2 = str.indexOf("\n", i2 + 1);
            if (indexOf != -1 && (indexOf < i2 || i2 == -1)) {
                vector4.add(Integer.valueOf(indexOf));
                i2 = indexOf;
                continue;
            } else if (i2 == -1 || (i2 >= indexOf && indexOf != -1)) {
                i2 = -1;
                continue;
            } else {
                vector4.add(Integer.valueOf(i2));
                continue;
            }
        } while (i2 != -1);
        for (i = 0; i < vector2.size(); i++) {
            int intValue = ((Integer) vector2.get(i)).intValue();
            int length = str.length();
            for (indexOf = 0; indexOf < vector4.size(); indexOf++) {
                if (((Integer) vector4.get(indexOf)).intValue() > intValue) {
                    indexOf = ((Integer) vector4.get(indexOf)).intValue();
                    break;
                }
            }
            indexOf = length;
            vector.add(str.substring(intValue, indexOf));
            length = 0;
            while (length < vector3.size()) {
                if (((Integer) vector3.get(length)).intValue() > intValue && ((Integer) vector3.get(length)).intValue() < indexOf) {
                    vector3.remove(length);
                    break;
                }
                length++;
            }
        }
        for (i = 0; i < vector3.size(); i++) {
            int intValue2 = ((Integer) vector3.get(i)).intValue();
            length = str.length();
            for (indexOf = 0; indexOf < vector4.size(); indexOf++) {
                if (((Integer) vector4.get(indexOf)).intValue() > intValue2) {
                    i2 = ((Integer) vector4.get(indexOf)).intValue();
                    break;
                }
            }
            i2 = length;
            vector.add(str.substring(intValue2, i2));
        }
        return vector;
    }
}
