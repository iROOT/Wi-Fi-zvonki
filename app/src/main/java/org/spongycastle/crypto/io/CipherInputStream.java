package org.spongycastle.crypto.io;

import java.io.FilterInputStream;
import java.io.IOException;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.modes.AEADBlockCipher;

public class CipherInputStream extends FilterInputStream {
    private BufferedBlockCipher a;
    private StreamCipher b;
    private AEADBlockCipher c;
    private final byte[] d;
    private final byte[] e;
    private int f;
    private int g;
    private boolean h;

    private int a() {
        if (this.h) {
            return -1;
        }
        this.f = 0;
        this.g = 0;
        while (this.g == 0) {
            int read = this.in.read(this.e);
            if (read == -1) {
                b();
                if (this.g == 0) {
                    return -1;
                }
                return this.g;
            } else if (this.a != null) {
                this.g = this.a.a(this.e, 0, read, this.d, 0);
            } else {
                try {
                    if (this.c != null) {
                        this.g = this.c.a(this.e, 0, read, this.d, 0);
                    } else {
                        this.b.a(this.e, 0, read, this.d, 0);
                        this.g = read;
                    }
                } catch (Exception e) {
                    throw new IOException("Error processing stream " + e);
                }
            }
        }
        return this.g;
    }

    private void b() {
        try {
            this.h = true;
            if (this.a != null) {
                this.g = this.a.a(this.d, 0);
            } else if (this.c != null) {
                this.g = this.c.a(this.d, 0);
            } else {
                this.g = 0;
            }
        } catch (Throwable e) {
            throw new InvalidCipherTextIOException("Error finalising cipher", e);
        } catch (Exception e2) {
            throw new IOException("Error finalising cipher " + e2);
        }
    }

    public int read() {
        if (this.f >= this.g && a() < 0) {
            return -1;
        }
        byte[] bArr = this.d;
        int i = this.f;
        this.f = i + 1;
        return bArr[i] & 255;
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.f >= this.g && a() < 0) {
            return -1;
        }
        int min = Math.min(i2, available());
        System.arraycopy(this.d, this.f, bArr, i, min);
        this.f += min;
        return min;
    }

    public long skip(long j) {
        if (j <= 0) {
            return 0;
        }
        int min = (int) Math.min(j, (long) available());
        this.f += min;
        return (long) min;
    }

    public int available() {
        return this.g - this.f;
    }

    public void close() {
        try {
            this.in.close();
            this.f = 0;
            this.g = 0;
        } finally {
            if (!this.h) {
                b();
            }
        }
    }

    public void mark(int i) {
    }

    public void reset() {
    }

    public boolean markSupported() {
        return false;
    }
}
