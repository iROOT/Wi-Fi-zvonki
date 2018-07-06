package org.spongycastle.asn1;

import java.io.EOFException;
import java.io.InputStream;
import org.spongycastle.util.io.Streams;

class DefiniteLengthInputStream extends LimitedInputStream {
    private static final byte[] b = new byte[0];
    private final int c;
    private int d;

    DefiniteLengthInputStream(InputStream inputStream, int i) {
        super(inputStream, i);
        if (i < 0) {
            throw new IllegalArgumentException("negative lengths not allowed");
        }
        this.c = i;
        this.d = i;
        if (i == 0) {
            b(true);
        }
    }

    int a() {
        return this.d;
    }

    public int read() {
        if (this.d == 0) {
            return -1;
        }
        int read = this.a.read();
        if (read < 0) {
            throw new EOFException("DEF length " + this.c + " object truncated by " + this.d);
        }
        int i = this.d - 1;
        this.d = i;
        if (i != 0) {
            return read;
        }
        b(true);
        return read;
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.d == 0) {
            return -1;
        }
        int read = this.a.read(bArr, i, Math.min(i2, this.d));
        if (read < 0) {
            throw new EOFException("DEF length " + this.c + " object truncated by " + this.d);
        }
        int i3 = this.d - read;
        this.d = i3;
        if (i3 != 0) {
            return read;
        }
        b(true);
        return read;
    }

    byte[] b() {
        if (this.d == 0) {
            return b;
        }
        byte[] bArr = new byte[this.d];
        int a = this.d - Streams.a(this.a, bArr);
        this.d = a;
        if (a != 0) {
            throw new EOFException("DEF length " + this.c + " object truncated by " + this.d);
        }
        b(true);
        return bArr;
    }
}
