package org.spongycastle.crypto.tls;

import java.util.Hashtable;
import org.spongycastle.util.Integers;

public class TlsExtensionsUtils {
    public static final Integer a = Integers.a(15);
    public static final Integer b = Integers.a(1);
    public static final Integer c = Integers.a(0);
    public static final Integer d = Integers.a(5);
    public static final Integer e = Integers.a(4);

    public static Hashtable a(Hashtable hashtable) {
        return hashtable == null ? new Hashtable() : hashtable;
    }

    public static short b(Hashtable hashtable) {
        byte[] a = TlsUtils.a(hashtable, b);
        return a == null ? (short) -1 : a(a);
    }

    public static boolean c(Hashtable hashtable) {
        byte[] a = TlsUtils.a(hashtable, e);
        return a == null ? false : b(a);
    }

    public static short a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        } else if (bArr.length != 1) {
            throw new TlsFatalAlert((short) 50);
        } else {
            short s = (short) bArr[0];
            if (MaxFragmentLength.a(s)) {
                return s;
            }
            throw new TlsFatalAlert((short) 47);
        }
    }

    private static boolean b(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        } else if (bArr.length == 0) {
            return true;
        } else {
            throw new TlsFatalAlert((short) 47);
        }
    }
}
