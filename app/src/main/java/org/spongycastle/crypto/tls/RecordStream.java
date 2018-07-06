package org.spongycastle.crypto.tls;

import com.fgmicrotec.mobile.android.fgmag.VoIP;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.hockeyapp.android.k;

class RecordStream {
    private static int a = 16384;
    private TlsProtocol b;
    private InputStream c;
    private OutputStream d;
    private TlsCompression e;
    private TlsCompression f;
    private TlsCompression g;
    private TlsCipher h;
    private TlsCipher i;
    private TlsCipher j;
    private long k;
    private long l;
    private ByteArrayOutputStream m;
    private TlsHandshakeHash n;
    private ProtocolVersion o;
    private ProtocolVersion p;
    private boolean q;
    private int r;
    private int s;
    private int t;

    int a() {
        return this.r;
    }

    void a(int i) {
        this.r = i;
        this.s = this.r + k.FEEDBACK_FAILED_TITLE_ID;
        this.t = this.s + k.FEEDBACK_FAILED_TITLE_ID;
    }

    ProtocolVersion b() {
        return this.o;
    }

    void a(ProtocolVersion protocolVersion) {
        this.o = protocolVersion;
    }

    void b(ProtocolVersion protocolVersion) {
        this.p = protocolVersion;
    }

    void a(boolean z) {
        this.q = z;
    }

    void a(TlsCompression tlsCompression, TlsCipher tlsCipher) {
        this.e = tlsCompression;
        this.h = tlsCipher;
    }

    void c() {
        if (this.e == null || this.h == null) {
            throw new TlsFatalAlert((short) 40);
        }
        this.g = this.e;
        this.j = this.h;
        this.l = 0;
    }

    void d() {
        if (this.e == null || this.h == null) {
            throw new TlsFatalAlert((short) 40);
        }
        this.f = this.e;
        this.i = this.h;
        this.k = 0;
    }

    public boolean e() {
        byte[] a = TlsUtils.a(5, this.c);
        if (a == null) {
            return false;
        }
        short a2 = TlsUtils.a(a, 0);
        a(a2, (short) 10);
        if (this.q) {
            ProtocolVersion c = TlsUtils.c(a, 1);
            if (this.o == null) {
                this.o = c;
            } else if (!c.equals(this.o)) {
                throw new TlsFatalAlert((short) 47);
            }
        } else if ((TlsUtils.d(a, 1) & -256) != k.EXPIRY_INFO_TITLE_ID) {
            throw new TlsFatalAlert((short) 47);
        }
        a = a(a2, this.c, TlsUtils.b(a, 3));
        this.b.a(a2, a, 0, a.length);
        return true;
    }

    protected byte[] a(short s, InputStream inputStream, int i) {
        a(i, this.t, (short) 22);
        byte[] b = TlsUtils.b(i, inputStream);
        TlsCipher tlsCipher = this.i;
        long j = this.k;
        this.k = 1 + j;
        byte[] b2 = tlsCipher.b(j, s, b, 0, b.length);
        a(b2.length, this.s, (short) 22);
        OutputStream b3 = this.f.b(this.m);
        if (b3 != this.m) {
            b3.write(b2, 0, b2.length);
            b3.flush();
            b2 = k();
        }
        a(b2.length, this.r, (short) 30);
        if (b2.length >= 1 || s == (short) 23) {
            return b2;
        }
        throw new TlsFatalAlert((short) 47);
    }

    protected void a(short s, byte[] bArr, int i, int i2) {
        a(s, (short) 80);
        a(i2, this.r, (short) 80);
        if (i2 >= 1 || s == (short) 23) {
            Object a;
            if (s == (short) 22) {
                a(bArr, i, i2);
            }
            OutputStream a2 = this.g.a(this.m);
            TlsCipher tlsCipher;
            long j;
            if (a2 == this.m) {
                tlsCipher = this.j;
                j = this.l;
                this.l = j + 1;
                a = tlsCipher.a(j, s, bArr, i, i2);
            } else {
                a2.write(bArr, i, i2);
                a2.flush();
                byte[] k = k();
                a(k.length, i2 + k.FEEDBACK_FAILED_TITLE_ID, (short) 80);
                tlsCipher = this.j;
                j = this.l;
                this.l = 1 + j;
                a = tlsCipher.a(j, s, k, 0, k.length);
            }
            a(a.length, this.t, (short) 80);
            byte[] bArr2 = new byte[(a.length + 5)];
            TlsUtils.a(s, bArr2, 0);
            TlsUtils.a(this.p, bArr2, 1);
            TlsUtils.a(a.length, bArr2, 3);
            System.arraycopy(a, 0, bArr2, 5, a.length);
            this.d.write(bArr2);
            this.d.flush();
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    void f() {
        this.n = this.n.d();
    }

    TlsHandshakeHash g() {
        return this.n;
    }

    TlsHandshakeHash h() {
        TlsHandshakeHash tlsHandshakeHash = this.n;
        this.n = this.n.f();
        return tlsHandshakeHash;
    }

    void a(byte[] bArr, int i, int i2) {
        this.n.a(bArr, i, i2);
    }

    protected void i() {
        try {
            this.c.close();
        } catch (IOException e) {
        }
        try {
            this.d.close();
        } catch (IOException e2) {
        }
    }

    protected void j() {
        this.d.flush();
    }

    private byte[] k() {
        byte[] toByteArray = this.m.toByteArray();
        this.m.reset();
        return toByteArray;
    }

    private static void a(short s, short s2) {
        switch (s) {
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                return;
            default:
                throw new TlsFatalAlert(s2);
        }
    }

    private static void a(int i, int i2, short s) {
        if (i > i2) {
            throw new TlsFatalAlert(s);
        }
    }
}
