package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;

public class RC4Engine implements StreamCipher {
    private byte[] a = null;
    private int b = 0;
    private int c = 0;
    private byte[] d = null;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.d = ((KeyParameter) cipherParameters).a();
            a(this.d);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to RC4 init - " + cipherParameters.getClass().getName());
    }

    public String a() {
        return "RC4";
    }

    public byte a(byte b) {
        this.b = (this.b + 1) & 255;
        this.c = (this.a[this.b] + this.c) & 255;
        byte b2 = this.a[this.b];
        this.a[this.b] = this.a[this.c];
        this.a[this.c] = b2;
        return (byte) (this.a[(this.a[this.b] + this.a[this.c]) & 255] ^ b);
    }

    public void a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                this.b = (this.b + 1) & 255;
                this.c = (this.a[this.b] + this.c) & 255;
                byte b = this.a[this.b];
                this.a[this.b] = this.a[this.c];
                this.a[this.c] = b;
                bArr2[i4 + i3] = (byte) (bArr[i4 + i] ^ this.a[(this.a[this.b] + this.a[this.c]) & 255]);
            }
        }
    }

    public void b() {
        a(this.d);
    }

    private void a(byte[] bArr) {
        int i;
        int i2 = 0;
        this.d = bArr;
        this.b = 0;
        this.c = 0;
        if (this.a == null) {
            this.a = new byte[256];
        }
        for (i = 0; i < 256; i++) {
            this.a[i] = (byte) i;
        }
        i = 0;
        int i3 = 0;
        while (i2 < 256) {
            i = (i + ((bArr[i3] & 255) + this.a[i2])) & 255;
            byte b = this.a[i2];
            this.a[i2] = this.a[i];
            this.a[i] = b;
            i3 = (i3 + 1) % bArr.length;
            i2++;
        }
    }
}
