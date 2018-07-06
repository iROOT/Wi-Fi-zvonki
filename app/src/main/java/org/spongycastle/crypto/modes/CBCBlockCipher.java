package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

public class CBCBlockCipher implements BlockCipher {
    private byte[] a;
    private byte[] b;
    private byte[] c;
    private int d;
    private BlockCipher e = null;
    private boolean f;

    public CBCBlockCipher(BlockCipher blockCipher) {
        this.e = blockCipher;
        this.d = blockCipher.b();
        this.a = new byte[this.d];
        this.b = new byte[this.d];
        this.c = new byte[this.d];
    }

    public BlockCipher d() {
        return this.e;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        boolean z2 = this.f;
        this.f = z;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            Object a = parametersWithIV.a();
            if (a.length != this.d) {
                throw new IllegalArgumentException("initialisation vector must be the same length as block size");
            }
            System.arraycopy(a, 0, this.a, 0, a.length);
            c();
            if (parametersWithIV.b() != null) {
                this.e.a(z, parametersWithIV.b());
                return;
            } else if (z2 != z) {
                throw new IllegalArgumentException("cannot change encrypting state without providing key.");
            } else {
                return;
            }
        }
        c();
        if (cipherParameters != null) {
            this.e.a(z, cipherParameters);
        } else if (z2 != z) {
            throw new IllegalArgumentException("cannot change encrypting state without providing key.");
        }
    }

    public String a() {
        return this.e.a() + "/CBC";
    }

    public int b() {
        return this.e.b();
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.f ? b(bArr, i, bArr2, i2) : c(bArr, i, bArr2, i2);
    }

    public void c() {
        System.arraycopy(this.a, 0, this.b, 0, this.a.length);
        Arrays.a(this.c, (byte) 0);
        this.e.c();
    }

    private int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.d + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        int i3;
        for (i3 = 0; i3 < this.d; i3++) {
            byte[] bArr3 = this.b;
            bArr3[i3] = (byte) (bArr3[i3] ^ bArr[i + i3]);
        }
        i3 = this.e.a(this.b, 0, bArr2, i2);
        System.arraycopy(bArr2, i2, this.b, 0, this.b.length);
        return i3;
    }

    private int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        if (this.d + i > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        System.arraycopy(bArr, i, this.c, 0, this.d);
        int a = this.e.a(bArr, i, bArr2, i2);
        while (i3 < this.d) {
            int i4 = i2 + i3;
            bArr2[i4] = (byte) (bArr2[i4] ^ this.b[i3]);
            i3++;
        }
        byte[] bArr3 = this.b;
        this.b = this.c;
        this.c = bArr3;
        return a;
    }
}
