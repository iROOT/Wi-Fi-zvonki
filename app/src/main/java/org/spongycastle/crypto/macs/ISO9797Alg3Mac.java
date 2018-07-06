package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.engines.DESEngine;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.paddings.BlockCipherPadding;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class ISO9797Alg3Mac implements Mac {
    private byte[] a;
    private byte[] b;
    private int c;
    private BlockCipher d;
    private BlockCipherPadding e;
    private int f;
    private KeyParameter g;
    private KeyParameter h;

    public ISO9797Alg3Mac(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.b() * 8, null);
    }

    public ISO9797Alg3Mac(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this(blockCipher, blockCipher.b() * 8, blockCipherPadding);
    }

    public ISO9797Alg3Mac(BlockCipher blockCipher, int i, BlockCipherPadding blockCipherPadding) {
        if (i % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        } else if (blockCipher instanceof DESEngine) {
            this.d = new CBCBlockCipher(blockCipher);
            this.e = blockCipherPadding;
            this.f = i / 8;
            this.a = new byte[blockCipher.b()];
            this.b = new byte[blockCipher.b()];
            this.c = 0;
        } else {
            throw new IllegalArgumentException("cipher must be instance of DESEngine");
        }
    }

    public String a() {
        return "ISO9797Alg3";
    }

    public void a(CipherParameters cipherParameters) {
        c();
        if ((cipherParameters instanceof KeyParameter) || (cipherParameters instanceof ParametersWithIV)) {
            KeyParameter keyParameter;
            CipherParameters keyParameter2;
            if (cipherParameters instanceof KeyParameter) {
                keyParameter = (KeyParameter) cipherParameters;
            } else {
                keyParameter = (KeyParameter) ((ParametersWithIV) cipherParameters).b();
            }
            byte[] a = keyParameter.a();
            if (a.length == 16) {
                keyParameter2 = new KeyParameter(a, 0, 8);
                this.g = new KeyParameter(a, 8, 8);
                this.h = keyParameter2;
            } else if (a.length == 24) {
                keyParameter2 = new KeyParameter(a, 0, 8);
                this.g = new KeyParameter(a, 8, 8);
                this.h = new KeyParameter(a, 16, 8);
            } else {
                throw new IllegalArgumentException("Key must be either 112 or 168 bit long");
            }
            if (cipherParameters instanceof ParametersWithIV) {
                this.d.a(true, new ParametersWithIV(keyParameter2, ((ParametersWithIV) cipherParameters).a()));
                return;
            } else {
                this.d.a(true, keyParameter2);
                return;
            }
        }
        throw new IllegalArgumentException("params must be an instance of KeyParameter or ParametersWithIV");
    }

    public int b() {
        return this.f;
    }

    public void a(byte b) {
        if (this.c == this.b.length) {
            this.d.a(this.b, 0, this.a, 0);
            this.c = 0;
        }
        byte[] bArr = this.b;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = b;
    }

    public void a(byte[] bArr, int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int b = this.d.b();
        int i3 = b - this.c;
        if (i2 > i3) {
            System.arraycopy(bArr, i, this.b, this.c, i3);
            int a = this.d.a(this.b, 0, this.a, 0) + 0;
            this.c = 0;
            i2 -= i3;
            i += i3;
            while (i2 > b) {
                a += this.d.a(bArr, i, this.a, 0);
                i2 -= b;
                i += b;
            }
        }
        System.arraycopy(bArr, i, this.b, this.c, i2);
        this.c += i2;
    }

    public int a(byte[] bArr, int i) {
        int b = this.d.b();
        if (this.e == null) {
            while (this.c < b) {
                this.b[this.c] = (byte) 0;
                this.c++;
            }
        } else {
            if (this.c == b) {
                this.d.a(this.b, 0, this.a, 0);
                this.c = 0;
            }
            this.e.a(this.b, this.c);
        }
        this.d.a(this.b, 0, this.a, 0);
        DESEngine dESEngine = new DESEngine();
        dESEngine.a(false, this.g);
        dESEngine.a(this.a, 0, this.a, 0);
        dESEngine.a(true, this.h);
        dESEngine.a(this.a, 0, this.a, 0);
        System.arraycopy(this.a, 0, bArr, i, this.f);
        c();
        return this.f;
    }

    public void c() {
        for (int i = 0; i < this.b.length; i++) {
            this.b[i] = (byte) 0;
        }
        this.c = 0;
        this.d.c();
    }
}
