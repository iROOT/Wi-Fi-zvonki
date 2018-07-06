package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.ParametersWithSBox;

public class GCFBBlockCipher implements BlockCipher {
    private static final byte[] a = new byte[]{(byte) 105, (byte) 0, (byte) 114, (byte) 34, (byte) 100, (byte) -55, (byte) 4, (byte) 35, (byte) -115, (byte) 58, (byte) -37, (byte) -106, (byte) 70, (byte) -23, (byte) 42, (byte) -60, (byte) 24, (byte) -2, (byte) -84, (byte) -108, (byte) 0, (byte) -19, (byte) 7, (byte) 18, (byte) -64, (byte) -122, (byte) -36, (byte) -62, (byte) -17, (byte) 76, (byte) -87, (byte) 43};
    private final CFBBlockCipher b;
    private KeyParameter c;
    private long d = 0;
    private boolean e;

    public GCFBBlockCipher(BlockCipher blockCipher) {
        this.b = new CFBBlockCipher(blockCipher, blockCipher.b() * 8);
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        CipherParameters b;
        this.d = 0;
        this.b.a(z, cipherParameters);
        this.e = z;
        if (cipherParameters instanceof ParametersWithIV) {
            b = ((ParametersWithIV) cipherParameters).b();
        } else {
            b = cipherParameters;
        }
        if (b instanceof ParametersWithRandom) {
            b = ((ParametersWithRandom) b).b();
        }
        if (b instanceof ParametersWithSBox) {
            b = ((ParametersWithSBox) b).b();
        }
        this.c = (KeyParameter) b;
    }

    public String a() {
        return "G" + this.b.a();
    }

    public int b() {
        return this.b.b();
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.d > 0 && this.d % 1024 == 0) {
            BlockCipher d = this.b.d();
            d.a(false, this.c);
            byte[] bArr3 = new byte[32];
            d.a(a, 0, bArr3, 0);
            d.a(a, 8, bArr3, 8);
            d.a(a, 16, bArr3, 16);
            d.a(a, 24, bArr3, 24);
            this.c = new KeyParameter(bArr3);
            bArr3 = new byte[8];
            d.a(true, this.c);
            d.a(this.b.e(), 0, bArr3, 0);
            this.b.a(this.e, new ParametersWithIV(this.c, bArr3));
        }
        this.d += (long) this.b.b();
        return this.b.a(bArr, i, bArr2, i2);
    }

    public void c() {
        this.d = 0;
        this.b.c();
    }
}
