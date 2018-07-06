package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.util.Arrays;

public final class KDFDoublePipelineIterationParameters implements DerivationParameters {
    private final byte[] a;
    private final boolean b;
    private final int c;
    private final byte[] d;

    public byte[] a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public byte[] d() {
        return Arrays.b(this.d);
    }
}
