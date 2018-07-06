package org.spongycastle.util.io;

import java.io.OutputStream;
import org.spongycastle.util.Arrays;

public class BufferingOutputStream extends OutputStream {
    private final OutputStream a;
    private final byte[] b;
    private int c;

    public void write(byte[] bArr, int i, int i2) {
        if (i2 < this.b.length - this.c) {
            System.arraycopy(bArr, i, this.b, this.c, i2);
            this.c += i2;
            return;
        }
        int length = this.b.length - this.c;
        System.arraycopy(bArr, i, this.b, this.c, length);
        this.c += length;
        flush();
        int i3 = i + length;
        length = i2 - length;
        while (length >= this.b.length) {
            this.a.write(bArr, i3, this.b.length);
            i3 += this.b.length;
            length -= this.b.length;
        }
        if (length > 0) {
            System.arraycopy(bArr, i3, this.b, this.c, length);
            this.c = length + this.c;
        }
    }

    public void write(int i) {
        byte[] bArr = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr[i2] = (byte) i;
        if (this.c == this.b.length) {
            flush();
        }
    }

    public void flush() {
        this.a.write(this.b, 0, this.c);
        this.c = 0;
        Arrays.a(this.b, (byte) 0);
    }

    public void close() {
        flush();
        this.a.close();
    }
}
