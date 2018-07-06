package org.spongycastle.jcajce.provider.util;

import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.ntt.NTTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.util.Integers;

public class SecretKeyUtil {
    private static Map a = new HashMap();

    static {
        a.put(PKCSObjectIdentifiers.B.d(), Integers.a(192));
        a.put(NISTObjectIdentifiers.k, Integers.a(NotificationCompat.FLAG_HIGH_PRIORITY));
        a.put(NISTObjectIdentifiers.r, Integers.a(192));
        a.put(NISTObjectIdentifiers.y, Integers.a(256));
        a.put(NTTObjectIdentifiers.a, Integers.a(NotificationCompat.FLAG_HIGH_PRIORITY));
        a.put(NTTObjectIdentifiers.b, Integers.a(192));
        a.put(NTTObjectIdentifiers.c, Integers.a(256));
    }
}
