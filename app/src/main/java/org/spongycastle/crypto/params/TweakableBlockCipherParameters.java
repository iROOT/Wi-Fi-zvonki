package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

public class TweakableBlockCipherParameters implements CipherParameters {
    private final byte[] a;
    private final KeyParameter b;

    public KeyParameter a() {
        return this.b;
    }

    public byte[] b() {
        return this.a;
    }
}
