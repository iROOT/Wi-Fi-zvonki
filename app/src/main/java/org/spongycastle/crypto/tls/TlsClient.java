package org.spongycastle.crypto.tls;

import java.util.Hashtable;
import java.util.Vector;

public interface TlsClient extends TlsPeer {
    void a(int i);

    void a(Hashtable hashtable);

    void a(Vector vector);

    void a(NewSessionTicket newSessionTicket);

    void a(ProtocolVersion protocolVersion);

    void a(short s);

    void a(byte[] bArr);

    Vector b();

    TlsKeyExchange d();

    TlsAuthentication g();
}
