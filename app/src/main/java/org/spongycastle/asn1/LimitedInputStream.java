package org.spongycastle.asn1;

import java.io.InputStream;

abstract class LimitedInputStream extends InputStream {
    protected final InputStream a;
    private int b;

    LimitedInputStream(InputStream inputStream, int i) {
        this.a = inputStream;
        this.b = i;
    }

    int a() {
        return this.b;
    }

    protected void b(boolean z) {
        if (this.a instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) this.a).a(z);
        }
    }
}
