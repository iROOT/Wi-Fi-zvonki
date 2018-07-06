package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;

public class McElieceCCA2Parameters extends McElieceParameters {
    public Digest a = new SHA256Digest();

    public McElieceCCA2Parameters(int i, int i2) {
        super(i, i2);
    }

    public Digest a() {
        return this.a;
    }
}
