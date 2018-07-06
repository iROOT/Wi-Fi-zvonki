package org.spongycastle.jcajce.provider.digest;

import java.security.MessageDigest;
import org.spongycastle.crypto.Digest;

public class BCMessageDigest extends MessageDigest {
    protected Digest a;

    protected BCMessageDigest(Digest digest) {
        super(digest.a());
        this.a = digest;
    }

    public void engineReset() {
        this.a.c();
    }

    public void engineUpdate(byte b) {
        this.a.a(b);
    }

    public void engineUpdate(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }

    public byte[] engineDigest() {
        byte[] bArr = new byte[this.a.b()];
        this.a.a(bArr, 0);
        return bArr;
    }
}
