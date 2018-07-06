package org.spongycastle.crypto.tls;

import com.fgmicrotec.mobile.android.fgmag.VoIP;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;

public class TlsServerProtocol extends TlsProtocol {
    protected TlsServer a;
    protected TlsServerContextImpl b;
    protected TlsKeyExchange c;
    protected TlsCredentials d;
    protected CertificateRequest e;
    protected short f;
    protected TlsHandshakeHash g;

    protected AbstractTlsContext a() {
        return this.b;
    }

    protected TlsPeer b() {
        return this.a;
    }

    protected void a(short s, byte[] bArr) {
        Certificate certificate = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        switch (s) {
            case (short) 1:
                switch (this.s) {
                    case (short) 0:
                        g(byteArrayInputStream);
                        this.s = (short) 1;
                        c();
                        this.s = (short) 2;
                        Vector g = this.a.g();
                        if (g != null) {
                            b(g);
                        }
                        this.s = (short) 3;
                        this.c = this.a.j();
                        this.c.a(a());
                        this.d = this.a.h();
                        if (this.d == null) {
                            this.c.e();
                        } else {
                            this.c.a(this.d);
                            certificate = this.d.a();
                            a(certificate);
                        }
                        this.s = (short) 4;
                        if (certificate == null || certificate.c()) {
                            this.w = false;
                        }
                        if (this.w) {
                            CertificateStatus i = this.a.i();
                            if (i != null) {
                                a(i);
                            }
                        }
                        this.s = (short) 5;
                        byte[] b = this.c.b();
                        if (b != null) {
                            b(b);
                        }
                        this.s = (short) 6;
                        if (this.d != null) {
                            this.e = this.a.k();
                            if (this.e != null) {
                                this.c.a(this.e);
                                a(this.e);
                                TlsUtils.a(this.j.g(), this.e.b());
                            }
                        }
                        this.s = (short) 7;
                        k();
                        this.s = (short) 8;
                        this.j.g().e();
                        return;
                    default:
                        throw new TlsFatalAlert((short) 10);
                }
            case (short) 11:
                switch (this.s) {
                    case (short) 8:
                        this.a.a(null);
                        break;
                    case (short) 9:
                        break;
                    default:
                        throw new TlsFatalAlert((short) 10);
                }
                if (this.e == null) {
                    throw new TlsFatalAlert((short) 10);
                }
                a(byteArrayInputStream);
                this.s = (short) 10;
                return;
            case (short) 15:
                switch (this.s) {
                    case (short) 11:
                        if (l()) {
                            b(byteArrayInputStream);
                            this.s = (short) 12;
                            return;
                        }
                        throw new TlsFatalAlert((short) 10);
                    default:
                        throw new TlsFatalAlert((short) 10);
                }
            case (short) 16:
                switch (this.s) {
                    case (short) 8:
                        this.a.a(null);
                        break;
                    case (short) 9:
                        break;
                    case (short) 10:
                        break;
                    default:
                        throw new TlsFatalAlert((short) 10);
                }
                if (this.e == null) {
                    this.c.d();
                } else if (TlsUtils.c(a())) {
                    throw new TlsFatalAlert((short) 10);
                } else if (!TlsUtils.a(a())) {
                    b(Certificate.a);
                } else if (this.n == null) {
                    throw new TlsFatalAlert((short) 10);
                }
                h(byteArrayInputStream);
                this.s = (short) 11;
                return;
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                switch (this.s) {
                    case (short) 11:
                        if (l()) {
                            throw new TlsFatalAlert((short) 10);
                        }
                        break;
                    case (short) 12:
                        break;
                    default:
                        throw new TlsFatalAlert((short) 10);
                }
                c(byteArrayInputStream);
                this.s = (short) 13;
                if (this.x) {
                    a(this.a.l());
                    g();
                }
                this.s = (short) 14;
                h();
                this.s = (short) 15;
                this.s = (short) 16;
                return;
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                switch (this.s) {
                    case (short) 8:
                        this.a.a(TlsProtocol.f(byteArrayInputStream));
                        this.s = (short) 9;
                        return;
                    default:
                        throw new TlsFatalAlert((short) 10);
                }
            default:
                throw new TlsFatalAlert((short) 10);
        }
    }

    protected void a(short s) {
        switch (s) {
            case (short) 41:
                if (TlsUtils.a(a()) && this.e != null) {
                    b(Certificate.a);
                    return;
                }
                return;
            default:
                super.a(s);
                return;
        }
    }

    protected void b(Certificate certificate) {
        if (this.e == null) {
            throw new IllegalStateException();
        } else if (this.n != null) {
            throw new TlsFatalAlert((short) 10);
        } else {
            this.n = certificate;
            if (certificate.c()) {
                this.c.d();
            } else {
                this.f = TlsUtils.a(certificate, this.d.a());
                this.c.b(certificate);
            }
            this.a.a(certificate);
        }
    }

    protected void a(ByteArrayInputStream byteArrayInputStream) {
        Certificate a = Certificate.a((InputStream) byteArrayInputStream);
        TlsProtocol.d(byteArrayInputStream);
        b(a);
    }

    protected void b(ByteArrayInputStream byteArrayInputStream) {
        DigitallySigned a = DigitallySigned.a(a(), byteArrayInputStream);
        TlsProtocol.d(byteArrayInputStream);
        try {
            byte[] a2 = TlsProtocol.a(a(), this.g, null);
            AsymmetricKeyParameter a3 = PublicKeyFactory.a(this.n.a(0).k());
            TlsSigner e = TlsUtils.e(this.f);
            e.a(a());
            e.a(a.a(), a.b(), a3, a2);
        } catch (Exception e2) {
            throw new TlsFatalAlert((short) 51);
        }
    }

    protected void g(ByteArrayInputStream byteArrayInputStream) {
        ProtocolVersion h = TlsUtils.h((InputStream) byteArrayInputStream);
        if (h.c()) {
            throw new TlsFatalAlert((short) 47);
        }
        byte[] b = TlsUtils.b(32, (InputStream) byteArrayInputStream);
        if (TlsUtils.e((InputStream) byteArrayInputStream).length > 32) {
            throw new TlsFatalAlert((short) 47);
        }
        int b2 = TlsUtils.b((InputStream) byteArrayInputStream);
        if (b2 < 2 || (b2 & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        this.o = TlsUtils.d(b2 / 2, (InputStream) byteArrayInputStream);
        b2 = TlsUtils.a((InputStream) byteArrayInputStream);
        if (b2 < (short) 1) {
            throw new TlsFatalAlert((short) 47);
        }
        this.p = TlsUtils.c(b2, (InputStream) byteArrayInputStream);
        this.q = TlsProtocol.e(byteArrayInputStream);
        a().a(h);
        this.a.a(h);
        this.m.g = b;
        this.a.a(this.o);
        this.a.a(this.p);
        if (Arrays.a(this.o, 255)) {
            this.v = true;
        }
        byte[] a = TlsUtils.a(this.q, h);
        if (a != null) {
            this.v = true;
            if (!Arrays.b(a, TlsProtocol.a(TlsUtils.a))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        this.a.a(this.v);
        if (this.q != null) {
            this.a.a(this.q);
        }
    }

    protected void h(ByteArrayInputStream byteArrayInputStream) {
        this.c.b((InputStream) byteArrayInputStream);
        TlsProtocol.d(byteArrayInputStream);
        TlsProtocol.a(a(), this.c);
        this.j.a(b().c(), b().e());
        this.g = this.j.h();
        if (!this.x) {
            g();
        }
    }

    protected void a(CertificateRequest certificateRequest) {
        OutputStream handshakeMessage = new HandshakeMessage(this, (short) 13);
        certificateRequest.a(handshakeMessage);
        handshakeMessage.a();
    }

    protected void a(CertificateStatus certificateStatus) {
        OutputStream handshakeMessage = new HandshakeMessage(this, (short) 22);
        certificateStatus.a(handshakeMessage);
        handshakeMessage.a();
    }

    protected void a(NewSessionTicket newSessionTicket) {
        if (newSessionTicket == null) {
            throw new TlsFatalAlert((short) 80);
        }
        OutputStream handshakeMessage = new HandshakeMessage(this, (short) 4);
        newSessionTicket.a(handshakeMessage);
        handshakeMessage.a();
    }

    protected void c() {
        boolean z = false;
        OutputStream handshakeMessage = new HandshakeMessage(this, (short) 2);
        ProtocolVersion a = this.a.a();
        if (a.a(a().c())) {
            this.j.a(a);
            this.j.b(a);
            this.j.a(true);
            a().b(a);
            TlsUtils.a(a, handshakeMessage);
            handshakeMessage.write(this.m.h);
            TlsUtils.a(TlsUtils.a, handshakeMessage);
            int b = this.a.b();
            if (!Arrays.a(this.o, b) || b == 0 || b == 255) {
                throw new TlsFatalAlert((short) 80);
            }
            this.m.b = b;
            short d = this.a.d();
            if (Arrays.a(this.p, d)) {
                this.m.c = d;
                TlsUtils.b(b, handshakeMessage);
                TlsUtils.a(d, handshakeMessage);
                this.r = this.a.f();
                if (this.v) {
                    if (TlsUtils.a(this.r, h) == null) {
                        this.r = TlsExtensionsUtils.a(this.r);
                        this.r.put(h, TlsProtocol.a(TlsUtils.a));
                    }
                }
                if (this.r != null) {
                    this.m.i = a(this.q, this.r, (short) 80);
                    this.m.j = TlsExtensionsUtils.c(this.r);
                    boolean z2 = !this.t && TlsUtils.a(this.r, TlsExtensionsUtils.d, (short) 80);
                    this.w = z2;
                    if (!this.t && TlsUtils.a(this.r, TlsProtocol.i, (short) 80)) {
                        z = true;
                    }
                    this.x = z;
                    TlsProtocol.a(handshakeMessage, this.r);
                }
                if (this.m.i >= (short) 0) {
                    this.j.a(1 << (this.m.i + 8));
                }
                this.m.d = TlsProtocol.a(a(), this.m.a());
                this.m.e = 12;
                handshakeMessage.a();
                this.j.f();
                return;
            }
            throw new TlsFatalAlert((short) 80);
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void k() {
        byte[] bArr = new byte[4];
        TlsUtils.a((short) 14, bArr, 0);
        TlsUtils.b(0, bArr, 1);
        c(bArr, 0, bArr.length);
    }

    protected void b(byte[] bArr) {
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, (short) 12, bArr.length);
        handshakeMessage.write(bArr);
        handshakeMessage.a();
    }

    protected boolean l() {
        return this.f >= (short) 0 && TlsUtils.d(this.f);
    }
}
