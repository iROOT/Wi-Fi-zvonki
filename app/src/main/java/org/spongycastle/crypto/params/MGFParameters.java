package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;

public class MGFParameters implements DerivationParameters {
    byte[] a;

    public byte[] a() {
        return this.a;
    }
}
