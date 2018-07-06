package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;

public class ISO18033KDFParameters implements DerivationParameters {
    byte[] a;

    public byte[] a() {
        return this.a;
    }
}
