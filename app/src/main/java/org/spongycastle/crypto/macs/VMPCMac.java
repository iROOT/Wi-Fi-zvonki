package org.spongycastle.crypto.macs;

import net.hockeyapp.android.k;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class VMPCMac implements Mac {
    private byte a;
    private byte b = (byte) 0;
    private byte[] c = null;
    private byte d = (byte) 0;
    private byte[] e;
    private byte[] f;
    private byte[] g;
    private byte h;
    private byte i;
    private byte j;
    private byte k;

    public int a(byte[] bArr, int i) {
        int i2;
        for (i2 = 1; i2 < 25; i2++) {
            this.d = this.c[(this.d + this.c[this.b & 255]) & 255];
            this.k = this.c[((this.k + this.j) + i2) & 255];
            this.j = this.c[((this.j + this.i) + i2) & 255];
            this.i = this.c[((this.i + this.h) + i2) & 255];
            this.h = this.c[((this.h + this.d) + i2) & 255];
            this.e[this.a & 31] = (byte) (this.e[this.a & 31] ^ this.h);
            this.e[(this.a + 1) & 31] = (byte) (this.e[(this.a + 1) & 31] ^ this.i);
            this.e[(this.a + 2) & 31] = (byte) (this.e[(this.a + 2) & 31] ^ this.j);
            this.e[(this.a + 3) & 31] = (byte) (this.e[(this.a + 3) & 31] ^ this.k);
            this.a = (byte) ((this.a + 4) & 31);
            byte b = this.c[this.b & 255];
            this.c[this.b & 255] = this.c[this.d & 255];
            this.c[this.d & 255] = b;
            this.b = (byte) ((this.b + 1) & 255);
        }
        for (i2 = 0; i2 < k.EXPIRY_INFO_TITLE_ID; i2++) {
            this.d = this.c[((this.d + this.c[i2 & 255]) + this.e[i2 & 31]) & 255];
            b = this.c[i2 & 255];
            this.c[i2 & 255] = this.c[this.d & 255];
            this.c[this.d & 255] = b;
        }
        Object obj = new byte[20];
        for (i2 = 0; i2 < 20; i2++) {
            this.d = this.c[(this.d + this.c[i2 & 255]) & 255];
            obj[i2] = this.c[(this.c[this.c[this.d & 255] & 255] + 1) & 255];
            byte b2 = this.c[i2 & 255];
            this.c[i2 & 255] = this.c[this.d & 255];
            this.c[this.d & 255] = b2;
        }
        System.arraycopy(obj, 0, bArr, i, obj.length);
        c();
        return obj.length;
    }

    public String a() {
        return "VMPC-MAC";
    }

    public int b() {
        return 20;
    }

    public void a(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            KeyParameter keyParameter = (KeyParameter) parametersWithIV.b();
            if (parametersWithIV.b() instanceof KeyParameter) {
                this.f = parametersWithIV.a();
                if (this.f == null || this.f.length < 1 || this.f.length > k.EXPIRY_INFO_TITLE_ID) {
                    throw new IllegalArgumentException("VMPC-MAC requires 1 to 768 bytes of IV");
                }
                this.g = keyParameter.a();
                c();
                return;
            }
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include a key");
        }
        throw new IllegalArgumentException("VMPC-MAC Init parameters must include an IV");
    }

    private void a(byte[] bArr, byte[] bArr2) {
        int i;
        this.d = (byte) 0;
        this.c = new byte[256];
        for (i = 0; i < 256; i++) {
            this.c[i] = (byte) i;
        }
        for (i = 0; i < k.EXPIRY_INFO_TITLE_ID; i++) {
            this.d = this.c[((this.d + this.c[i & 255]) + bArr[i % bArr.length]) & 255];
            byte b = this.c[i & 255];
            this.c[i & 255] = this.c[this.d & 255];
            this.c[this.d & 255] = b;
        }
        for (i = 0; i < k.EXPIRY_INFO_TITLE_ID; i++) {
            this.d = this.c[((this.d + this.c[i & 255]) + bArr2[i % bArr2.length]) & 255];
            b = this.c[i & 255];
            this.c[i & 255] = this.c[this.d & 255];
            this.c[this.d & 255] = b;
        }
        this.b = (byte) 0;
    }

    public void c() {
        a(this.g, this.f);
        this.b = (byte) 0;
        this.k = (byte) 0;
        this.j = (byte) 0;
        this.i = (byte) 0;
        this.h = (byte) 0;
        this.a = (byte) 0;
        this.e = new byte[32];
        for (int i = 0; i < 32; i++) {
            this.e[i] = (byte) 0;
        }
    }

    public void a(byte b) {
        this.d = this.c[(this.d + this.c[this.b & 255]) & 255];
        byte b2 = (byte) (this.c[(this.c[this.c[this.d & 255] & 255] + 1) & 255] ^ b);
        this.k = this.c[(this.k + this.j) & 255];
        this.j = this.c[(this.j + this.i) & 255];
        this.i = this.c[(this.i + this.h) & 255];
        this.h = this.c[(b2 + (this.h + this.d)) & 255];
        this.e[this.a & 31] = (byte) (this.e[this.a & 31] ^ this.h);
        this.e[(this.a + 1) & 31] = (byte) (this.e[(this.a + 1) & 31] ^ this.i);
        this.e[(this.a + 2) & 31] = (byte) (this.e[(this.a + 2) & 31] ^ this.j);
        this.e[(this.a + 3) & 31] = (byte) (this.e[(this.a + 3) & 31] ^ this.k);
        this.a = (byte) ((this.a + 4) & 31);
        b2 = this.c[this.b & 255];
        this.c[this.b & 255] = this.c[this.d & 255];
        this.c[this.d & 255] = b2;
        this.b = (byte) ((this.b + 1) & 255);
    }

    public void a(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            a(bArr[i3]);
        }
    }
}
