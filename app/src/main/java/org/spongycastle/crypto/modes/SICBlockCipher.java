package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithIV;

public class SICBlockCipher implements BlockCipher {
    private final BlockCipher a;
    private final int b = this.a.b();
    private byte[] c = new byte[this.b];
    private byte[] d = new byte[this.b];
    private byte[] e = new byte[this.b];

    public SICBlockCipher(BlockCipher blockCipher) {
        this.a = blockCipher;
    }

    public BlockCipher d() {
        return this.a;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            System.arraycopy(parametersWithIV.a(), 0, this.c, 0, this.c.length);
            c();
            if (parametersWithIV.b() != null) {
                this.a.a(true, parametersWithIV.b());
                return;
            }
            return;
        }
        throw new IllegalArgumentException("SIC mode requires ParametersWithIV");
    }

    public String a() {
        return this.a.a() + "/SIC";
    }

    public int b() {
        return this.a.b();
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        this.a.a(this.d, 0, this.e, 0);
        while (i3 < this.e.length) {
            bArr2[i2 + i3] = (byte) (this.e[i3] ^ bArr[i + i3]);
            i3++;
        }
        for (i3 = this.d.length - 1; i3 >= 0; i3--) {
            byte[] bArr3 = this.d;
            byte b = (byte) (bArr3[i3] + 1);
            bArr3[i3] = b;
            if (b != (byte) 0) {
                break;
            }
        }
        return this.d.length;
    }

    public void c() {
        System.arraycopy(this.c, 0, this.d, 0, this.d.length);
        this.a.c();
    }
}
