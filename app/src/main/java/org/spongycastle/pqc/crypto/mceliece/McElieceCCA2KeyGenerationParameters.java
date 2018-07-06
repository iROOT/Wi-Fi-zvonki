package org.spongycastle.pqc.crypto.mceliece;

import android.support.v4.app.NotificationCompat;
import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

public class McElieceCCA2KeyGenerationParameters extends KeyGenerationParameters {
    private McElieceCCA2Parameters a;

    public McElieceCCA2KeyGenerationParameters(SecureRandom secureRandom, McElieceCCA2Parameters mcElieceCCA2Parameters) {
        super(secureRandom, NotificationCompat.FLAG_HIGH_PRIORITY);
        this.a = mcElieceCCA2Parameters;
    }

    public McElieceCCA2Parameters c() {
        return this.a;
    }
}
