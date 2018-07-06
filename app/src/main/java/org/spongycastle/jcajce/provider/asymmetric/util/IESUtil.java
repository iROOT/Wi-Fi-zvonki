package org.spongycastle.jcajce.provider.asymmetric.util;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.crypto.engines.IESEngine;
import org.spongycastle.jce.spec.IESParameterSpec;

public class IESUtil {
    public static IESParameterSpec a(IESEngine iESEngine) {
        if (iESEngine.a() == null) {
            return new IESParameterSpec(null, null, NotificationCompat.FLAG_HIGH_PRIORITY);
        }
        if (iESEngine.a().a().a().equals("DES") || iESEngine.a().a().a().equals("RC2") || iESEngine.a().a().a().equals("RC5-32") || iESEngine.a().a().a().equals("RC5-64")) {
            return new IESParameterSpec(null, null, 64, 64);
        }
        if (iESEngine.a().a().a().equals("SKIPJACK")) {
            return new IESParameterSpec(null, null, 80, 80);
        }
        if (iESEngine.a().a().a().equals("GOST28147")) {
            return new IESParameterSpec(null, null, 256, 256);
        }
        return new IESParameterSpec(null, null, NotificationCompat.FLAG_HIGH_PRIORITY, NotificationCompat.FLAG_HIGH_PRIORITY);
    }
}
