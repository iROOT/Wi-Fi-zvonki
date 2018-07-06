package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.digests.SkeinEngine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.SkeinParameters;
import org.spongycastle.crypto.params.SkeinParameters.Builder;

public class SkeinMac implements Mac {
    private SkeinEngine a;

    public SkeinMac(int i, int i2) {
        this.a = new SkeinEngine(i, i2);
    }

    public String a() {
        return "Skein-MAC-" + (this.a.b() * 8) + "-" + (this.a.a() * 8);
    }

    public void a(CipherParameters cipherParameters) {
        SkeinParameters skeinParameters;
        if (cipherParameters instanceof SkeinParameters) {
            skeinParameters = (SkeinParameters) cipherParameters;
        } else if (cipherParameters instanceof KeyParameter) {
            skeinParameters = new Builder().a(((KeyParameter) cipherParameters).a()).a();
        } else {
            throw new IllegalArgumentException("Invalid parameter passed to Skein MAC init - " + cipherParameters.getClass().getName());
        }
        if (skeinParameters.b() == null) {
            throw new IllegalArgumentException("Skein MAC requires a key parameter.");
        }
        this.a.a(skeinParameters);
    }

    public int b() {
        return this.a.a();
    }

    public void c() {
        this.a.c();
    }

    public void a(byte b) {
        this.a.a(b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }

    public int a(byte[] bArr, int i) {
        return this.a.a(bArr, i);
    }
}
