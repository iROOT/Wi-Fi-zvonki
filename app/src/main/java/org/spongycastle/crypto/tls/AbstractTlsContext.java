package org.spongycastle.crypto.tls;

import java.security.SecureRandom;

abstract class AbstractTlsContext implements TlsContext {
    private SecureRandom a;
    private SecurityParameters b;
    private ProtocolVersion c;
    private ProtocolVersion d;

    public SecureRandom a() {
        return this.a;
    }

    public SecurityParameters b() {
        return this.b;
    }

    public ProtocolVersion c() {
        return this.c;
    }

    void a(ProtocolVersion protocolVersion) {
        this.c = protocolVersion;
    }

    public ProtocolVersion d() {
        return this.d;
    }

    void b(ProtocolVersion protocolVersion) {
        this.d = protocolVersion;
    }
}
