package org.spongycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.Digest;

public class NullDigest implements Digest {
    private ByteArrayOutputStream a = new ByteArrayOutputStream();

    public String a() {
        return "NULL";
    }

    public int b() {
        return this.a.size();
    }

    public void a(byte b) {
        this.a.write(b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.a.write(bArr, i, i2);
    }

    public int a(byte[] bArr, int i) {
        Object toByteArray = this.a.toByteArray();
        System.arraycopy(toByteArray, 0, bArr, i, toByteArray.length);
        c();
        return toByteArray.length;
    }

    public void c() {
        this.a.reset();
    }
}
