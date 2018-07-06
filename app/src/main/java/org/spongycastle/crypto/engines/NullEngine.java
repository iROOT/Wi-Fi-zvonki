package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;

public class NullEngine implements BlockCipher {
    private boolean a;
    private final int b;

    public NullEngine() {
        this(1);
    }

    public NullEngine(int i) {
        this.b = i;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        this.a = true;
    }

    public String a() {
        return "Null";
    }

    public int b() {
        return this.b;
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this.a) {
            throw new IllegalStateException("Null engine not initialised");
        } else if (this.b + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (this.b + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            for (int i3 = 0; i3 < this.b; i3++) {
                bArr2[i2 + i3] = bArr[i + i3];
            }
            return this.b;
        }
    }

    public void c() {
    }
}
