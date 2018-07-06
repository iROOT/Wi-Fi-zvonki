package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.util.Arrays;

public final class KDFFeedbackParameters implements DerivationParameters {
    private final byte[] a;
    private final byte[] b;
    private final boolean c;
    private final int d;
    private final byte[] e;

    public byte[] a() {
        return this.a;
    }

    public byte[] b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public byte[] e() {
        return Arrays.b(this.e);
    }
}
