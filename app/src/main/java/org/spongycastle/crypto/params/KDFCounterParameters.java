package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.util.Arrays;

public final class KDFCounterParameters implements DerivationParameters {
    private final byte[] a;
    private final byte[] b;
    private final int c;

    public byte[] a() {
        return this.a;
    }

    public byte[] b() {
        return Arrays.b(this.b);
    }

    public int c() {
        return this.c;
    }
}
