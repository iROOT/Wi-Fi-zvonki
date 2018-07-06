package org.spongycastle.crypto.tls;

import java.security.SecureRandom;

public interface TlsContext {
    SecureRandom a();

    SecurityParameters b();

    ProtocolVersion c();

    ProtocolVersion d();

    boolean e();
}
