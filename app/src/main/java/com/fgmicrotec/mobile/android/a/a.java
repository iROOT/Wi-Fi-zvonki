package com.fgmicrotec.mobile.android.a;

import com.mavenir.android.vtow.activation.ActivationAdapter;

public class a {
    private static int a;
    private static char b;
    private static long c;
    private static long d;
    private static long e;
    private static long f;
    private static long g;
    private static long h;
    private static long i;
    private static long j;
    private static final short[] k = new short[]{(short) 27979, (short) 26956, (short) 25701, (short) 24218};
    private static final short[] l = new short[]{(short) 17055, (short) 18630, (short) 20326, (short) 22072};
    private static final short[] m = new short[]{(short) 19072, (short) 16324, (short) 13084, (short) 9314};
    private static final short[] n = new short[]{(short) 26645, (short) 28412, (short) 30042, (short) 31416};

    public static short a(long j) {
        return (short) ((int) ((16384 + j) >> 15));
    }

    public static void a(char c, int i, boolean z) {
        int i2 = 0;
        int i3 = 3;
        int i4 = 2;
        switch (c) {
            case '#':
                i2 = 2;
                break;
            case '*':
                break;
            case '0':
                i2 = 1;
                break;
            case '1':
                i3 = 0;
                break;
            case '2':
            case 'A':
            case 'B':
            case 'C':
                c = '2';
                i3 = 0;
                i2 = 1;
                break;
            case '3':
            case 'D':
            case 'E':
            case 'F':
                c = '3';
                i3 = 0;
                i2 = 2;
                break;
            case '4':
            case 'G':
            case 'H':
            case 'I':
                c = '4';
                i3 = 1;
                break;
            case '5':
            case 'J':
            case 'K':
            case 'L':
                c = '5';
                i2 = 1;
                i3 = 1;
                break;
            case '6':
            case 'M':
            case 'N':
            case 'O':
                c = '6';
                i2 = 2;
                i3 = 1;
                break;
            case '7':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
                c = '7';
                i3 = 2;
                break;
            case '8':
            case 'T':
            case 'U':
            case 'V':
                c = '8';
                i2 = 1;
                i3 = 2;
                break;
            case '9':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
                c = '9';
                i2 = 2;
                i3 = 2;
                break;
            case 'a':
                i3 = 0;
                i2 = 3;
                break;
            case 'b':
                i2 = 3;
                i3 = 1;
                break;
            case 'c':
                i2 = 3;
                i3 = 2;
                break;
            case ActivationAdapter.OP_ACTIVATION /*100*/:
                i2 = 3;
                break;
            default:
                c = ' ';
                i3 = 0;
                i = 0;
                break;
        }
        if (!z) {
            i4 = 1;
        }
        if (b != c || a != i) {
            a = i;
            b = c;
            d = 16384;
            f = 16384;
            c = 0;
            e = 0;
            h = (long) (k[i3] / i4);
            g = (long) (l[i3] / i4);
            j = (long) (m[i2] / i4);
            i = (long) (n[i2] / i4);
        }
    }

    public static void a(short[] sArr, int i) {
        if (i % 2 == 0) {
            for (int i2 = 0; i2 < i; i2++) {
                sArr[i2] = a(((long) a) * (c + e));
                long j = c;
                c = ((c * h) + (d * g)) >> 15;
                d = ((d * h) - (j * g)) >> 15;
                j = e;
                e = ((e * j) + (f * i)) >> 15;
                f = ((f * j) - (j * i)) >> 15;
            }
        }
    }
}
