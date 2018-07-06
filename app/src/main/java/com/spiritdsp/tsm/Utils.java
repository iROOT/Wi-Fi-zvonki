package com.spiritdsp.tsm;

class Utils {
    Utils() {
    }

    public static int clp2(int word) {
        int w = word - 1;
        w |= w >> 1;
        w |= w >> 2;
        w |= w >> 4;
        w |= w >> 8;
        return (w | (w >> 16)) + 1;
    }
}
