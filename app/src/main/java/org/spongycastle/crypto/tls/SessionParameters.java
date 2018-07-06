package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.util.Hashtable;
import org.spongycastle.util.Arrays;

public final class SessionParameters {
    private int a;
    private short b;
    private byte[] c;
    private byte[] d;

    public static final class Builder {
        private int a = -1;
        private short b = (short) -1;
        private byte[] c = null;
        private Certificate d = null;
        private byte[] e = null;
    }

    public void a() {
        if (this.c != null) {
            Arrays.a(this.c, (byte) 0);
        }
    }

    public int b() {
        return this.a;
    }

    public short c() {
        return this.b;
    }

    public byte[] d() {
        return this.c;
    }

    public Hashtable e() {
        if (this.d == null) {
            return null;
        }
        return TlsProtocol.e(new ByteArrayInputStream(this.d));
    }
}
