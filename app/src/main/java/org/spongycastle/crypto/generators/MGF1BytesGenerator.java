package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.MGFParameters;

public class MGF1BytesGenerator implements DerivationFunction {
    private Digest a;
    private byte[] b;
    private int c;

    public void a(DerivationParameters derivationParameters) {
        if (derivationParameters instanceof MGFParameters) {
            this.b = ((MGFParameters) derivationParameters).a();
            return;
        }
        throw new IllegalArgumentException("MGF parameters required for MGF1Generator");
    }

    private void a(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
    }

    public int a(byte[] bArr, int i, int i2) {
        if (bArr.length - i2 < i) {
            throw new DataLengthException("output buffer too small");
        }
        int i3;
        Object obj = new byte[this.c];
        byte[] bArr2 = new byte[4];
        this.a.c();
        if (i2 > this.c) {
            i3 = 0;
            do {
                a(i3, bArr2);
                this.a.a(this.b, 0, this.b.length);
                this.a.a(bArr2, 0, bArr2.length);
                this.a.a(obj, 0);
                System.arraycopy(obj, 0, bArr, (this.c * i3) + i, this.c);
                i3++;
            } while (i3 < i2 / this.c);
        } else {
            i3 = 0;
        }
        if (this.c * i3 < i2) {
            a(i3, bArr2);
            this.a.a(this.b, 0, this.b.length);
            this.a.a(bArr2, 0, bArr2.length);
            this.a.a(obj, 0);
            System.arraycopy(obj, 0, bArr, (this.c * i3) + i, i2 - (i3 * this.c));
        }
        return i2;
    }
}
