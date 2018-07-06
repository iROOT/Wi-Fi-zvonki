package org.spongycastle.crypto.engines;

import net.hockeyapp.android.k;

public class VMPCKSA3Engine extends VMPCEngine {
    public String a() {
        return "VMPC-KSA3";
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
        for (i = 0; i < k.EXPIRY_INFO_TITLE_ID; i++) {
            this.c = this.b[((this.c + this.b[i & 255]) + bArr[i % bArr.length]) & 255];
            b = this.b[i & 255];
            this.b[i & 255] = this.b[this.c & 255];
            this.b[this.c & 255] = b;
        }
        this.a = (byte) 0;
    }
}
