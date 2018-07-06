package org.spongycastle.crypto.tls;

import java.io.OutputStream;

public interface TlsCompression {
    OutputStream a(OutputStream outputStream);

    OutputStream b(OutputStream outputStream);
}
