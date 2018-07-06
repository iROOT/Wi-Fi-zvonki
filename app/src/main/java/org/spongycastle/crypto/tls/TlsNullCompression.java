package org.spongycastle.crypto.tls;

import java.io.OutputStream;

public class TlsNullCompression implements TlsCompression {
    public OutputStream a(OutputStream outputStream) {
        return outputStream;
    }

    public OutputStream b(OutputStream outputStream) {
        return outputStream;
    }
}
