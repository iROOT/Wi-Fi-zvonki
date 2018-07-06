package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.util.Arrays;

public class HKDFParameters implements DerivationParameters {
    private final byte[] a;
    private final boolean b;
    private final byte[] c;
    private final byte[] d;

    public byte[] a() {
        return Arrays.b(this.a);
    }

    public boolean b() {
        return this.b;
    }

    public byte[] c() {
        return Arrays.b(this.c);
    }

    public byte[] d() {
        return Arrays.b(this.d);
    }
}
