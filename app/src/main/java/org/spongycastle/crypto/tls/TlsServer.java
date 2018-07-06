package org.spongycastle.crypto.tls;

import java.util.Hashtable;
import java.util.Vector;

public interface TlsServer extends TlsPeer {
    ProtocolVersion a();

    void a(Hashtable hashtable);

    void a(Vector vector);

    void a(Certificate certificate);

    void a(ProtocolVersion protocolVersion);

    void a(int[] iArr);

    void a(short[] sArr);

    int b();

    short d();

    Hashtable f();

    Vector g();

    TlsCredentials h();

    CertificateStatus i();

    TlsKeyExchange j();

    CertificateRequest k();

    NewSessionTicket l();
}
