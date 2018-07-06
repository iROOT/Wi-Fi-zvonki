package org.spongycastle.crypto.tls;

import android.support.v4.app.NotificationCompat;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;

public abstract class TlsProtocol {
    protected static final Integer h = Integers.a(65281);
    protected static final Integer i = Integers.a(35);
    private ByteQueue a;
    private ByteQueue b;
    private ByteQueue c;
    private volatile boolean d;
    private volatile boolean e;
    private volatile boolean f;
    private volatile boolean g;
    protected RecordStream j;
    protected TlsSession k;
    protected SessionParameters l;
    protected SecurityParameters m;
    protected Certificate n;
    protected int[] o;
    protected short[] p;
    protected Hashtable q;
    protected Hashtable r;
    protected short s;
    protected boolean t;
    protected boolean u;
    protected boolean v;
    protected boolean w;
    protected boolean x;
    private byte[] y;

    class HandshakeMessage extends ByteArrayOutputStream {
        final /* synthetic */ TlsProtocol a;

        HandshakeMessage(TlsProtocol tlsProtocol, short s) {
            this(tlsProtocol, s, 60);
        }

        HandshakeMessage(TlsProtocol tlsProtocol, short s, int i) {
            this.a = tlsProtocol;
            super(i + 4);
            TlsUtils.a(s, (OutputStream) this);
            this.count += 3;
        }

        void a() {
            int i = this.count - 4;
            TlsUtils.c(i);
            TlsUtils.b(i, this.buf, 1);
            this.a.c(this.buf, 0, this.count);
            this.buf = null;
        }
    }

    protected abstract AbstractTlsContext a();

    protected abstract void a(short s, byte[] bArr);

    protected abstract TlsPeer b();

    protected void d() {
    }

    protected void a(short s) {
    }

    protected void a(short s, byte[] bArr, int i, int i2) {
        switch (s) {
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                d(bArr, i, i2);
                return;
            case VoIP.ERR_SESSION_NOT_FOUND /*21*/:
                this.b.a(bArr, i, i2);
                l();
                return;
            case VoIP.ERR_TRANSPORT_ERROR /*22*/:
                this.c.a(bArr, i, i2);
                c();
                return;
            case VoIP.ERR_SOCKET_CONNECTION_BROKE_NET_DOWN /*23*/:
                if (this.f) {
                    this.a.a(bArr, i, i2);
                    k();
                    return;
                }
                throw new TlsFatalAlert((short) 10);
            default:
                return;
        }
    }

    private void c() {
        int i;
        do {
            if (this.c.a() >= 4) {
                byte[] bArr = new byte[4];
                this.c.a(bArr, 0, 4, 0);
                InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
                short a = TlsUtils.a(byteArrayInputStream);
                int c = TlsUtils.c(byteArrayInputStream);
                if (this.c.a() >= c + 4) {
                    byte[] a2 = this.c.a(c, 4);
                    switch (a) {
                        case (short) 0:
                            break;
                        case VoIP.ERR_SIP_TIMED_OUT /*20*/:
                            if (this.y == null) {
                                this.y = a(!a().e());
                                break;
                            }
                            break;
                    }
                    this.j.a(bArr, 0, 4);
                    this.j.a(a2, 0, c);
                    a(a, a2);
                    i = 1;
                    continue;
                }
            }
            i = 0;
            continue;
        } while (i != 0);
    }

    private void k() {
    }

    private void l() {
        while (this.b.a() >= 2) {
            byte[] a = this.b.a(2, 0);
            short s = (short) a[0];
            short s2 = (short) a[1];
            b().a(s, s2);
            if (s == (short) 2) {
                f();
                this.e = true;
                this.d = true;
                this.j.i();
                throw new IOException("Internal TLS error, this could be an attack");
            }
            if (s2 == (short) 0) {
                b(false);
            }
            a(s2);
        }
    }

    private void d(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            if (TlsUtils.a(bArr, i + i3) != (short) 1) {
                throw new TlsFatalAlert((short) 50);
            } else if (this.u) {
                throw new TlsFatalAlert((short) 10);
            } else {
                this.u = true;
                this.j.d();
                d();
                i3++;
            }
        }
    }

    protected int a(byte[] bArr, int i, int i2) {
        if (i2 < 1) {
            return 0;
        }
        while (this.a.a() == 0) {
            if (!this.d) {
                e();
            } else if (!this.e) {
                return -1;
            } else {
                throw new IOException("Internal TLS error, this could be an attack");
            }
        }
        int min = Math.min(i2, this.a.a());
        this.a.b(bArr, i, min, 0);
        return min;
    }

    protected void e() {
        try {
            if (!this.j.e()) {
                throw new EOFException();
            }
        } catch (Exception e) {
            if (!this.d) {
                a((short) 2, e.a(), "Failed to read record", e);
            }
            throw e;
        } catch (Exception e2) {
            if (!this.d) {
                a((short) 2, (short) 80, "Failed to read record", e2);
            }
            throw e2;
        } catch (Exception e22) {
            if (!this.d) {
                a((short) 2, (short) 80, "Failed to read record", e22);
            }
            throw e22;
        }
    }

    protected void b(short s, byte[] bArr, int i, int i2) {
        try {
            this.j.a(s, bArr, i, i2);
        } catch (Exception e) {
            if (!this.d) {
                a((short) 2, e.a(), "Failed to write record", e);
            }
            throw e;
        } catch (Exception e2) {
            if (!this.d) {
                a((short) 2, (short) 80, "Failed to write record", e2);
            }
            throw e2;
        } catch (Exception e22) {
            if (!this.d) {
                a((short) 2, (short) 80, "Failed to write record", e22);
            }
            throw e22;
        }
    }

    protected void b(byte[] bArr, int i, int i2) {
        if (!this.d) {
            int i3 = i2;
            int i4 = i;
            while (i3 > 0) {
                if (this.g) {
                    b((short) 23, bArr, i4, 1);
                    i4++;
                    i3--;
                }
                if (i3 > 0) {
                    int min = Math.min(i3, this.j.a());
                    b((short) 23, bArr, i4, min);
                    i4 += min;
                    i3 -= min;
                }
            }
        } else if (this.e) {
            throw new IOException("Internal TLS error, this could be an attack");
        } else {
            throw new IOException("Sorry, connection has been closed, you cannot write more data");
        }
    }

    protected void c(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            int min = Math.min(i2, this.j.a());
            b((short) 22, bArr, i, min);
            i += min;
            i2 -= min;
        }
    }

    protected void a(short s, short s2, String str, Exception exception) {
        if (!this.d) {
            this.d = true;
            if (s == (short) 2) {
                f();
                this.e = true;
            }
            b(s, s2, str, exception);
            this.j.i();
            if (s != (short) 2) {
                return;
            }
        }
        throw new IOException("Internal TLS error, this could be an attack");
    }

    protected void f() {
        if (this.l != null) {
            this.l.a();
            this.l = null;
        }
        if (this.k != null) {
            this.k.b();
            this.k = null;
        }
    }

    protected void c(ByteArrayInputStream byteArrayInputStream) {
        byte[] b = TlsUtils.b(this.y.length, (InputStream) byteArrayInputStream);
        d(byteArrayInputStream);
        if (!Arrays.b(this.y, b)) {
            throw new TlsFatalAlert((short) 51);
        }
    }

    protected void b(short s, short s2, String str, Exception exception) {
        b().a(s, s2, str, exception);
        b((short) 21, new byte[]{(byte) s, (byte) s2}, 0, 2);
    }

    protected void a(short s, String str) {
        b((short) 1, s, str, null);
    }

    protected void a(Certificate certificate) {
        if (certificate == null) {
            certificate = Certificate.a;
        }
        if (certificate.b() == 0 && !a().e()) {
            ProtocolVersion d = a().d();
            if (d.d()) {
                a((short) 41, d.toString() + " client didn't provide credentials");
                return;
            }
        }
        OutputStream handshakeMessage = new HandshakeMessage(this, (short) 11);
        certificate.a(handshakeMessage);
        handshakeMessage.a();
    }

    protected void g() {
        byte[] bArr = new byte[]{(byte) 1};
        b((short) 20, bArr, 0, bArr.length);
        this.j.c();
    }

    protected void h() {
        byte[] a = a(a().e());
        HandshakeMessage handshakeMessage = new HandshakeMessage(this, (short) 20, a.length);
        handshakeMessage.write(a);
        handshakeMessage.a();
    }

    protected void b(Vector vector) {
        OutputStream handshakeMessage = new HandshakeMessage(this, (short) 23);
        a(handshakeMessage, vector);
        handshakeMessage.a();
    }

    protected byte[] a(boolean z) {
        TlsContext a = a();
        if (z) {
            return TlsUtils.a(a, "server finished", a(a(), this.j.g(), TlsUtils.d));
        }
        return TlsUtils.a(a, "client finished", a(a(), this.j.g(), TlsUtils.c));
    }

    public void i() {
        b(true);
    }

    protected void b(boolean z) {
        if (!this.d) {
            if (z && !this.f) {
                a((short) 90, "User canceled handshake");
            }
            a((short) 1, (short) 0, "Connection closed", null);
        }
    }

    protected void j() {
        this.j.j();
    }

    protected short a(Hashtable hashtable, Hashtable hashtable2, short s) {
        short b = TlsExtensionsUtils.b(hashtable2);
        if (b < (short) 0 || this.t || b == TlsExtensionsUtils.b(hashtable)) {
            return b;
        }
        throw new TlsFatalAlert(s);
    }

    protected static void d(ByteArrayInputStream byteArrayInputStream) {
        if (byteArrayInputStream.available() > 0) {
            throw new TlsFatalAlert((short) 50);
        }
    }

    protected static byte[] a(byte[] bArr) {
        return TlsUtils.a(bArr);
    }

    protected static void a(TlsContext tlsContext, TlsKeyExchange tlsKeyExchange) {
        byte[] f = tlsKeyExchange.f();
        try {
            tlsContext.b().f = TlsUtils.a(tlsContext, f);
        } finally {
            if (f != null) {
                Arrays.a(f, (byte) 0);
            }
        }
    }

    protected static byte[] a(TlsContext tlsContext, TlsHandshakeHash tlsHandshakeHash, byte[] bArr) {
        Digest g = tlsHandshakeHash.g();
        if (bArr != null && TlsUtils.a(tlsContext)) {
            g.a(bArr, 0, bArr.length);
        }
        byte[] bArr2 = new byte[g.b()];
        g.a(bArr2, 0);
        return bArr2;
    }

    protected static Hashtable e(ByteArrayInputStream byteArrayInputStream) {
        if (byteArrayInputStream.available() < 1) {
            return null;
        }
        byte[] f = TlsUtils.f((InputStream) byteArrayInputStream);
        d(byteArrayInputStream);
        InputStream byteArrayInputStream2 = new ByteArrayInputStream(f);
        Hashtable hashtable = new Hashtable();
        while (byteArrayInputStream2.available() > 0) {
            if (hashtable.put(Integers.a(TlsUtils.b(byteArrayInputStream2)), TlsUtils.f(byteArrayInputStream2)) != null) {
                throw new TlsFatalAlert((short) 47);
            }
        }
        return hashtable;
    }

    protected static Vector f(ByteArrayInputStream byteArrayInputStream) {
        byte[] g = TlsUtils.g((InputStream) byteArrayInputStream);
        d(byteArrayInputStream);
        InputStream byteArrayInputStream2 = new ByteArrayInputStream(g);
        Vector vector = new Vector();
        while (byteArrayInputStream2.available() > 0) {
            vector.addElement(new SupplementalDataEntry(TlsUtils.b(byteArrayInputStream2), TlsUtils.f(byteArrayInputStream2)));
        }
        return vector;
    }

    protected static void a(OutputStream outputStream, Hashtable hashtable) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            int intValue = num.intValue();
            byte[] bArr = (byte[]) hashtable.get(num);
            TlsUtils.b(intValue);
            TlsUtils.b(intValue, byteArrayOutputStream);
            TlsUtils.b(bArr, byteArrayOutputStream);
        }
        TlsUtils.b(byteArrayOutputStream.toByteArray(), outputStream);
    }

    protected static void a(OutputStream outputStream, Vector vector) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < vector.size(); i++) {
            SupplementalDataEntry supplementalDataEntry = (SupplementalDataEntry) vector.elementAt(i);
            int a = supplementalDataEntry.a();
            TlsUtils.b(a);
            TlsUtils.b(a, byteArrayOutputStream);
            TlsUtils.b(supplementalDataEntry.b(), byteArrayOutputStream);
        }
        TlsUtils.c(byteArrayOutputStream.toByteArray(), outputStream);
    }

    protected static int a(TlsContext tlsContext, int i) {
        boolean c = TlsUtils.c(tlsContext);
        switch (i) {
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case NotificationCompat.FLAG_FOREGROUND_SERVICE /*64*/:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 156:
            case 158:
            case 160:
            case 162:
            case 164:
            case 168:
            case 170:
            case 172:
            case 49187:
            case 49189:
            case 49191:
            case 49193:
            case 49195:
            case 49197:
            case 49199:
            case 49201:
            case 49308:
            case 49309:
            case 49310:
            case 49311:
            case 49312:
            case 49313:
            case 49314:
            case 49315:
            case 49316:
            case 49317:
            case 49318:
            case 49319:
            case 49320:
            case 49321:
            case 49322:
            case 49323:
                if (c) {
                    return 1;
                }
                throw new TlsFatalAlert((short) 47);
            case 157:
            case 159:
            case 161:
            case 163:
            case 165:
            case 169:
            case 171:
            case 173:
            case 49188:
            case 49190:
            case 49192:
            case 49194:
            case 49196:
            case 49198:
            case 49200:
            case 49202:
                if (c) {
                    return 2;
                }
                throw new TlsFatalAlert((short) 47);
            case 175:
            case 177:
            case 179:
            case 181:
            case 183:
            case 185:
            case 49208:
            case 49211:
                if (c) {
                    return 2;
                }
                return 0;
            default:
                if (c) {
                    return 1;
                }
                return 0;
        }
    }
}
