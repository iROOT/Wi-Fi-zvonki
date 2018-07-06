package org.spongycastle.crypto.tls;

import java.util.Hashtable;
import java.util.Vector;

public abstract class AbstractTlsClient extends AbstractTlsPeer implements TlsClient {
    protected TlsCipherFactory a;
    protected TlsClientContext b;
    protected Vector c;
    protected int[] d;
    protected short[] e;
    protected short[] f;
    protected int g;
    protected short h;

    public AbstractTlsClient() {
        this(new DefaultTlsCipherFactory());
    }

    public AbstractTlsClient(TlsCipherFactory tlsCipherFactory) {
        this.a = tlsCipherFactory;
    }

    public ProtocolVersion a() {
        return ProtocolVersion.b;
    }

    public void a(ProtocolVersion protocolVersion) {
        if (!a().a(protocolVersion)) {
            throw new TlsFatalAlert((short) 70);
        }
    }

    public void a(byte[] bArr) {
    }

    public void a(int i) {
        this.g = i;
    }

    public void a(short s) {
        this.h = s;
    }

    public void a(Hashtable hashtable) {
        if (hashtable == null) {
            return;
        }
        if (hashtable.containsKey(TlsUtils.b)) {
            throw new TlsFatalAlert((short) 47);
        } else if (TlsECCUtils.a(hashtable) != null) {
            throw new TlsFatalAlert((short) 47);
        } else {
            this.f = TlsECCUtils.b(hashtable);
            if (this.f != null && !TlsECCUtils.c(this.g)) {
                throw new TlsFatalAlert((short) 47);
            }
        }
    }

    public void a(Vector vector) {
        if (vector != null) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    public Vector b() {
        return null;
    }

    public TlsCompression c() {
        switch (this.h) {
            case (short) 0:
                return new TlsNullCompression();
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    public void a(NewSessionTicket newSessionTicket) {
    }
}
