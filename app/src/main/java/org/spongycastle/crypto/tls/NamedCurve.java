package org.spongycastle.crypto.tls;

public class NamedCurve {
    public static boolean a(int i) {
        switch (i) {
            case 65281:
            case 65282:
                return false;
            default:
                return true;
        }
    }
}
