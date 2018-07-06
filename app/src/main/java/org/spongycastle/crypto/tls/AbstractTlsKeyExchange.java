package org.spongycastle.crypto.tls;

import com.fgmicrotec.mobile.android.fgmag.VoIP;
import java.io.InputStream;
import java.util.Vector;

public abstract class AbstractTlsKeyExchange implements TlsKeyExchange {
    protected int a;
    protected Vector b;
    protected TlsContext c;

    protected AbstractTlsKeyExchange(int i, Vector vector) {
        this.a = i;
        this.b = vector;
    }

    public void a(TlsContext tlsContext) {
        this.c = tlsContext;
        ProtocolVersion c = tlsContext.c();
        if (TlsUtils.a(c)) {
            if (this.b == null) {
                switch (this.a) {
                    case 1:
                    case 5:
                    case 9:
                    case 15:
                    case 18:
                    case 19:
                    case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                        this.b = TlsUtils.c();
                        return;
                    case 3:
                    case 7:
                    case VoIP.ERR_TRANSPORT_ERROR /*22*/:
                        this.b = TlsUtils.a();
                        return;
                    case 13:
                    case 14:
                    case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                    case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                        return;
                    case 16:
                    case 17:
                        this.b = TlsUtils.b();
                        return;
                    default:
                        throw new IllegalStateException("unsupported key exchange algorithm");
                }
            }
        } else if (this.b != null) {
            throw new IllegalStateException("supported_signature_algorithms not allowed for " + c);
        }
    }

    public void a(Certificate certificate) {
        if (this.b != null) {
        }
    }

    public void a(TlsCredentials tlsCredentials) {
        a(tlsCredentials.a());
    }

    public boolean a() {
        return false;
    }

    public byte[] b() {
        if (!a()) {
            return null;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public void c() {
        if (a()) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    public void a(InputStream inputStream) {
        if (!a()) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    public void d() {
    }

    public void b(Certificate certificate) {
    }

    public void b(InputStream inputStream) {
        throw new TlsFatalAlert((short) 80);
    }
}
