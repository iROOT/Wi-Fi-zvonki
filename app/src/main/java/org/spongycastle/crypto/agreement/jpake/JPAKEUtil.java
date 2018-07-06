package org.spongycastle.crypto.agreement.jpake;

import java.math.BigInteger;

public class JPAKEUtil {
    static final BigInteger a = BigInteger.valueOf(0);
    static final BigInteger b = BigInteger.valueOf(1);

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str + " must not be null");
        }
    }
}
