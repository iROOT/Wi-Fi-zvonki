package org.spongycastle.asn1;

import java.io.OutputStream;

public class BEROctetStringGenerator extends BERGenerator {

    private class BufferedBEROctetStream extends OutputStream {
        final /* synthetic */ BEROctetStringGenerator a;
        private byte[] b;
        private int c;
        private DEROutputStream d;

        public void write(int i) {
            byte[] bArr = this.b;
            int i2 = this.c;
            this.c = i2 + 1;
            bArr[i2] = (byte) i;
            if (this.c == this.b.length) {
                DEROctetString.a(this.d, this.b);
                this.c = 0;
            }
        }

        public void write(byte[] bArr, int i, int i2) {
            while (i2 > 0) {
                int min = Math.min(i2, this.b.length - this.c);
                System.arraycopy(bArr, i, this.b, this.c, min);
                this.c += min;
                if (this.c >= this.b.length) {
                    DEROctetString.a(this.d, this.b);
                    this.c = 0;
                    i += min;
                    i2 -= min;
                } else {
                    return;
                }
            }
        }

        public void close() {
            if (this.c != 0) {
                Object obj = new byte[this.c];
                System.arraycopy(this.b, 0, obj, 0, this.c);
                DEROctetString.a(this.d, obj);
            }
            this.a.a();
        }
    }
}
