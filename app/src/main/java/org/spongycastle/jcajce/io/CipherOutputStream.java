package org.spongycastle.jcajce.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import javax.crypto.Cipher;
import org.spongycastle.crypto.io.InvalidCipherTextIOException;

public class CipherOutputStream extends FilterOutputStream {
    private final Cipher a;
    private final byte[] b;

    public void write(int i) {
        this.b[0] = (byte) i;
        write(this.b, 0, 1);
    }

    public void write(byte[] bArr, int i, int i2) {
        byte[] update = this.a.update(bArr, i, i2);
        if (update != null) {
            this.out.write(update);
        }
    }

    public void flush() {
        this.out.flush();
    }

    public void close() {
        IOException e;
        IOException iOException = null;
        try {
            byte[] doFinal = this.a.doFinal();
            if (doFinal != null) {
                this.out.write(doFinal);
            }
        } catch (Throwable e2) {
            iOException = new InvalidCipherTextIOException("Error during cipher finalisation", e2);
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
