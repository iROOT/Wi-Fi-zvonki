package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class DESedeEngine extends DESEngine {
    private int[] a = null;
    private int[] b = null;
    private int[] c = null;
    private boolean d;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            Object a = ((KeyParameter) cipherParameters).a();
            if (a.length == 24 || a.length == 16) {
                this.d = z;
                Object obj = new byte[8];
                System.arraycopy(a, 0, obj, 0, obj.length);
                this.a = a(z, (byte[]) obj);
                Object obj2 = new byte[8];
                System.arraycopy(a, 8, obj2, 0, obj2.length);
                this.b = a(!z, (byte[]) obj2);
                if (a.length == 24) {
                    obj = new byte[8];
                    System.arraycopy(a, 16, obj, 0, obj.length);
                    this.c = a(z, (byte[]) obj);
                    return;
                }
                this.c = this.a;
                return;
            }
            throw new IllegalArgumentException("key size must be 16 or 24 bytes.");
        }
        throw new IllegalArgumentException("invalid parameter passed to DESede init - " + cipherParameters.getClass().getName());
    }

    public String a() {
        return "DESede";
    }

    public int b() {
        return 8;
    }

    public int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.a == null) {
            throw new IllegalStateException("DESede engine not initialised");
        } else if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 8 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            byte[] bArr3 = new byte[8];
            if (this.d) {
                a(this.a, bArr, i, bArr3, 0);
                a(this.b, bArr3, 0, bArr3, 0);
                a(this.c, bArr3, 0, bArr2, i2);
            } else {
                a(this.c, bArr, i, bArr3, 0);
                a(this.b, bArr3, 0, bArr3, 0);
                a(this.a, bArr3, 0, bArr2, i2);
            }
            return 8;
        }
    }

    public void c() {
    }
}
