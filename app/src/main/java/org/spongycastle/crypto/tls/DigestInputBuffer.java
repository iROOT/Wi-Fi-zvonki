package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.Digest;

class DigestInputBuffer extends ByteArrayOutputStream {
    DigestInputBuffer() {
    }

    void a(Digest digest) {
        digest.a(this.buf, 0, this.count);
    }
}
