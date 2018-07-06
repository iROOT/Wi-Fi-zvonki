package org.spongycastle.crypto.prng.drbg;

import android.support.v4.app.NotificationCompat;
import java.util.Hashtable;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.util.Integers;

class Utils {
    static final Hashtable a = new Hashtable();

    Utils() {
    }

    static {
        a.put("SHA-1", Integers.a(NotificationCompat.FLAG_HIGH_PRIORITY));
        a.put("SHA-224", Integers.a(192));
        a.put("SHA-256", Integers.a(256));
        a.put("SHA-384", Integers.a(256));
        a.put("SHA-512", Integers.a(256));
        a.put("SHA-512/224", Integers.a(192));
        a.put("SHA-512/256", Integers.a(256));
    }

    static int a(Digest digest) {
        return ((Integer) a.get(digest.a())).intValue();
    }

    static int a(Mac mac) {
        String a = mac.a();
        return ((Integer) a.get(a.substring(0, a.indexOf("/")))).intValue();
    }

    static byte[] a(Digest digest, byte[] bArr, int i) {
        int i2;
        int i3 = 0;
        Object obj = new byte[((i + 7) / 8)];
        int length = obj.length / digest.b();
        Object obj2 = new byte[digest.b()];
        int i4 = 1;
        for (i2 = 0; i2 <= length; i2++) {
            digest.a((byte) i4);
            digest.a((byte) (i >> 24));
            digest.a((byte) (i >> 16));
            digest.a((byte) (i >> 8));
            digest.a((byte) i);
            digest.a(bArr, 0, bArr.length);
            digest.a(obj2, 0);
            System.arraycopy(obj2, 0, obj, obj2.length * i2, obj.length - (obj2.length * i2) > obj2.length ? obj2.length : obj.length - (obj2.length * i2));
            i4++;
        }
        if (i % 8 != 0) {
            i4 = 8 - (i % 8);
            int i5 = 0;
            while (i5 != obj.length) {
                i2 = obj[i5] & 255;
                obj[i5] = (byte) ((i3 << (8 - i4)) | (i2 >>> i4));
                i5++;
                i3 = i2;
            }
        }
        return obj;
    }

    static boolean a(byte[] bArr, int i) {
        return bArr != null && bArr.length > i;
    }
}
