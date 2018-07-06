package org.spongycastle.crypto.engines;

import net.hockeyapp.android.k;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class VMPCEngine implements StreamCipher {
    protected byte a = (byte) 0;
    protected byte[] b = null;
    protected byte c = (byte) 0;
    protected byte[] d;
    protected byte[] e;

    public String a() {
        return "VMPC";
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            if (parametersWithIV.b() instanceof KeyParameter) {
                KeyParameter keyParameter = (KeyParameter) parametersWithIV.b();
                this.d = parametersWithIV.a();
                if (this.d == null || this.d.length < 1 || this.d.length > k.EXPIRY_INFO_TITLE_ID) {
                    throw new IllegalArgumentException("VMPC requires 1 to 768 bytes of IV");
                }
                this.e = keyParameter.a();
                a(this.e, this.d);
                return;
            }
            throw new IllegalArgumentException("VMPC init parameters must include a key");
        }
        throw new IllegalArgumentException("VMPC init parameters must include an IV");
    }

    protected void a(byte[] bArr, byte[] bArr2) {
        int i;
        this.c = (byte) 0;
        this.b = new byte[256];
        for (i = 0; i < 256; i++) {
            this.b[i] = (byte) i;
        }
        for (i = 0; i < k.EXPIRY_INFO_TITLE_ID; i++) {
            this.c = this.b[((this.c + this.b[i & 255]) + bArr[i % bArr.length]) & 255];
            byte b = this.b[i & 255];
            this.b[i & 255] = this.b[this.c & 255];
            this.b[this.c & 255] = b;
        }
        for (i = 0; i < k.EXPIRY_INFO_TITLE_ID; i++) {
            this.c = this.b[((this.c + this.b[i & 255]) + bArr2[i % bArr2.length]) & 255];
            b = this.b[i & 255];
            this.b[i & 255] = this.b[this.c & 255];
            this.b[this.c & 255] = b;
        }
        this.a = (byte) 0;
    }

    public void a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                this.c = this.b[(this.c + this.b[this.a & 255]) & 255];
                byte b = this.b[(this.b[this.b[this.c & 255] & 255] + 1) & 255];
                byte b2 = this.b[this.a & 255];
                this.b[this.a & 255] = this.b[this.c & 255];
                this.b[this.c & 255] = b2;
                this.a = (byte) ((this.a + 1) & 255);
                bArr2[i4 + i3] = (byte) (b ^ bArr[i4 + i]);
            }
        }
    }

    public void b() {
        a(this.e, this.d);
    }

    public byte a(byte b) {
        this.c = this.b[(this.c + this.b[this.a & 255]) & 255];
        byte b2 = this.b[(this.b[this.b[this.c & 255] & 255] + 1) & 255];
        byte b3 = this.b[this.a & 255];
        this.b[this.a & 255] = this.b[this.c & 255];
        this.b[this.c & 255] = b3;
        this.a = (byte) ((this.a + 1) & 255);
        return (byte) (b2 ^ b);
    }
}
