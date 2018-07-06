package org.spongycastle.crypto.tls;

import java.io.IOException;

public class TlsFatalAlert extends IOException {
    private short a;

    public TlsFatalAlert(short s) {
        this.a = s;
    }

    public short a() {
        return this.a;
    }
}
