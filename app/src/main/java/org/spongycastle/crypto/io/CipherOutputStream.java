package org.spongycastle.crypto.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.modes.AEADBlockCipher;

public class CipherOutputStream extends FilterOutputStream {
    private BufferedBlockCipher a;
    private StreamCipher b;
    private AEADBlockCipher c;
    private final byte[] d;
    private byte[] e;

    public void write(int i) {
        this.d[0] = (byte) i;
        if (this.b != null) {
            this.out.write(this.b.a((byte) i));
        } else {
            write(this.d, 0, 1);
        }
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        a(i2);
        int a;
        if (this.a != null) {
            a = this.a.a(bArr, i, i2, this.e, 0);
            if (a != 0) {
                this.out.write(this.e, 0, a);
            }
        } else if (this.c != null) {
            a = this.c.a(bArr, i, i2, this.e, 0);
            if (a != 0) {
                this.out.write(this.e, 0, a);
            }
        } else {
            this.b.a(bArr, i, i2, this.e, 0);
            this.out.write(this.e, 0, i2);
        }
    }

    private void a(int i) {
        if (this.a != null) {
            i = this.a.b(i);
        } else if (this.c != null) {
            i = this.c.b(i);
        }
        if (this.e == null || this.e.length < i) {
            this.e = new byte[i];
        }
    }

    public void flush() {
        this.out.flush();
    }

    public void close() {
        IOException e;
        a(0);
        IOException iOException = null;
        try {
            int a;
            if (this.a != null) {
                a = this.a.a(this.e, 0);
                if (a != 0) {
                    this.out.write(this.e, 0, a);
                }
            } else if (this.c != null) {
                a = this.c.a(this.e, 0);
                if (a != 0) {
                    this.out.write(this.e, 0, a);
                }
            }
        } catch (Throwable e2) {
            iOException = new InvalidCipherTextIOException("Error finalising cipher data", e2);
        } catch (Exception e3) {
            iOException = new IOException("Error closing stream: " + e3);
        }
        try {
            flush();
            this.out.close();
            e = iOException;
        } catch (IOException e4) {
            e = e4;
            if (iOException != null) {
                e = iOException;
            }
        }
        if (e != null) {
            throw e;
        }
    }
}
