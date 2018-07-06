package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.util.Arrays;

public class TlsClientProtocol extends TlsProtocol {
    protected TlsClient a;
    protected TlsClientContextImpl b;
    protected byte[] c;
    protected TlsKeyExchange d;
    protected TlsAuthentication e;
    protected CertificateStatus f;
    protected CertificateRequest g;

    protected AbstractTlsContext a() {
        return this.b;
    }

    protected TlsPeer b() {
        return this.a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void a(short r9, byte[] r10) {
        /*
        r8 = this;
        r6 = 2;
        r5 = 40;
        r4 = 16;
        r1 = 0;
        r3 = 10;
        r0 = new java.io.ByteArrayInputStream;
        r0.<init>(r10);
        r2 = r8.t;
        if (r2 == 0) goto L_0x0030;
    L_0x0011:
        r1 = 20;
        if (r9 != r1) goto L_0x0019;
    L_0x0015:
        r1 = r8.s;
        if (r1 == r6) goto L_0x001f;
    L_0x0019:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x001f:
        r8.c(r0);
        r0 = 15;
        r8.s = r0;
        r8.h();
        r0 = 13;
        r8.s = r0;
        r8.s = r4;
    L_0x002f:
        return;
    L_0x0030:
        switch(r9) {
            case 0: goto L_0x02a8;
            case 1: goto L_0x0033;
            case 2: goto L_0x00b0;
            case 3: goto L_0x0033;
            case 4: goto L_0x0289;
            case 5: goto L_0x0033;
            case 6: goto L_0x0033;
            case 7: goto L_0x0033;
            case 8: goto L_0x0033;
            case 9: goto L_0x0033;
            case 10: goto L_0x0033;
            case 11: goto L_0x0039;
            case 12: goto L_0x0225;
            case 13: goto L_0x0247;
            case 14: goto L_0x0143;
            case 15: goto L_0x0033;
            case 16: goto L_0x0033;
            case 17: goto L_0x0033;
            case 18: goto L_0x0033;
            case 19: goto L_0x0033;
            case 20: goto L_0x009b;
            case 21: goto L_0x0033;
            case 22: goto L_0x0079;
            case 23: goto L_0x012f;
            default: goto L_0x0033;
        };
    L_0x0033:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x0039:
        r2 = r8.s;
        switch(r2) {
            case 2: goto L_0x0044;
            case 3: goto L_0x0047;
            default: goto L_0x003e;
        };
    L_0x003e:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x0044:
        r8.a(r1);
    L_0x0047:
        r1 = org.spongycastle.crypto.tls.Certificate.a(r0);
        r8.n = r1;
        org.spongycastle.crypto.tls.TlsProtocol.d(r0);
        r0 = r8.n;
        if (r0 == 0) goto L_0x005c;
    L_0x0054:
        r0 = r8.n;
        r0 = r0.c();
        if (r0 == 0) goto L_0x005f;
    L_0x005c:
        r0 = 0;
        r8.w = r0;
    L_0x005f:
        r0 = r8.d;
        r1 = r8.n;
        r0.a(r1);
        r0 = r8.a;
        r0 = r0.g();
        r8.e = r0;
        r0 = r8.e;
        r1 = r8.n;
        r0.a(r1);
        r0 = 4;
        r8.s = r0;
        goto L_0x002f;
    L_0x0079:
        r1 = r8.s;
        switch(r1) {
            case 4: goto L_0x0084;
            default: goto L_0x007e;
        };
    L_0x007e:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x0084:
        r1 = r8.w;
        if (r1 != 0) goto L_0x008e;
    L_0x0088:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x008e:
        r1 = org.spongycastle.crypto.tls.CertificateStatus.a(r0);
        r8.f = r1;
        org.spongycastle.crypto.tls.TlsProtocol.d(r0);
        r0 = 5;
        r8.s = r0;
        goto L_0x002f;
    L_0x009b:
        r1 = r8.s;
        switch(r1) {
            case 13: goto L_0x00a6;
            default: goto L_0x00a0;
        };
    L_0x00a0:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x00a6:
        r8.c(r0);
        r0 = 15;
        r8.s = r0;
        r8.s = r4;
        goto L_0x002f;
    L_0x00b0:
        r2 = r8.s;
        switch(r2) {
            case 1: goto L_0x00bb;
            default: goto L_0x00b5;
        };
    L_0x00b5:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x00bb:
        r8.b(r0);
        r8.s = r6;
        r0 = r8.m;
        r0 = r0.i;
        if (r0 < 0) goto L_0x00d3;
    L_0x00c6:
        r0 = 1;
        r2 = r8.m;
        r2 = r2.i;
        r2 = r2 + 8;
        r0 = r0 << r2;
        r2 = r8.j;
        r2.a(r0);
    L_0x00d3:
        r0 = r8.m;
        r2 = r8.a();
        r3 = r8.m;
        r3 = r3.a();
        r2 = org.spongycastle.crypto.tls.TlsProtocol.a(r2, r3);
        r0.d = r2;
        r0 = r8.m;
        r2 = 12;
        r0.e = r2;
        r0 = r8.j;
        r0.f();
        r0 = r8.t;
        if (r0 == 0) goto L_0x011c;
    L_0x00f4:
        r0 = r8.m;
        r1 = r8.l;
        r1 = r1.d();
        r1 = org.spongycastle.util.Arrays.b(r1);
        r0.f = r1;
        r0 = r8.j;
        r1 = r8.b();
        r1 = r1.c();
        r2 = r8.b();
        r2 = r2.e();
        r0.a(r1, r2);
        r8.g();
        goto L_0x002f;
    L_0x011c:
        r8.f();
        r0 = r8.c;
        r0 = r0.length;
        if (r0 <= 0) goto L_0x002f;
    L_0x0124:
        r0 = new org.spongycastle.crypto.tls.TlsSessionImpl;
        r2 = r8.c;
        r0.<init>(r2, r1);
        r8.k = r0;
        goto L_0x002f;
    L_0x012f:
        r1 = r8.s;
        switch(r1) {
            case 2: goto L_0x013a;
            default: goto L_0x0134;
        };
    L_0x0134:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x013a:
        r0 = org.spongycastle.crypto.tls.TlsProtocol.f(r0);
        r8.a(r0);
        goto L_0x002f;
    L_0x0143:
        r2 = r8.s;
        switch(r2) {
            case 2: goto L_0x014e;
            case 3: goto L_0x0151;
            case 4: goto L_0x0158;
            case 5: goto L_0x0158;
            case 6: goto L_0x015d;
            case 7: goto L_0x015d;
            default: goto L_0x0148;
        };
    L_0x0148:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r5);
        throw r0;
    L_0x014e:
        r8.a(r1);
    L_0x0151:
        r2 = r8.d;
        r2.e();
        r8.e = r1;
    L_0x0158:
        r2 = r8.d;
        r2.c();
    L_0x015d:
        org.spongycastle.crypto.tls.TlsProtocol.d(r0);
        r0 = 8;
        r8.s = r0;
        r0 = r8.j;
        r0 = r0.g();
        r0.e();
        r0 = r8.a;
        r0 = r0.b();
        if (r0 == 0) goto L_0x0178;
    L_0x0175:
        r8.b(r0);
    L_0x0178:
        r0 = 9;
        r8.s = r0;
        r0 = r8.g;
        if (r0 != 0) goto L_0x01d3;
    L_0x0180:
        r0 = r8.d;
        r0.d();
        r0 = r1;
    L_0x0186:
        r8.s = r3;
        r8.c();
        r2 = 11;
        r8.s = r2;
        r2 = r8.a();
        r3 = r8.d;
        org.spongycastle.crypto.tls.TlsProtocol.a(r2, r3);
        r2 = r8.j;
        r3 = r8.b();
        r3 = r3.c();
        r4 = r8.b();
        r4 = r4.e();
        r2.a(r3, r4);
        r2 = r8.j;
        r3 = r2.h();
        if (r0 == 0) goto L_0x020d;
    L_0x01b5:
        r2 = r0 instanceof org.spongycastle.crypto.tls.TlsSignerCredentials;
        if (r2 == 0) goto L_0x020d;
    L_0x01b9:
        r0 = (org.spongycastle.crypto.tls.TlsSignerCredentials) r0;
        r2 = r8.a();
        r2 = org.spongycastle.crypto.tls.TlsUtils.c(r2);
        if (r2 == 0) goto L_0x0219;
    L_0x01c5:
        r2 = r0.b_();
        if (r2 != 0) goto L_0x01f5;
    L_0x01cb:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r1 = 80;
        r0.<init>(r1);
        throw r0;
    L_0x01d3:
        r0 = r8.e;
        r2 = r8.g;
        r0 = r0.a(r2);
        if (r0 != 0) goto L_0x01e8;
    L_0x01dd:
        r2 = r8.d;
        r2.d();
        r2 = org.spongycastle.crypto.tls.Certificate.a;
        r8.a(r2);
        goto L_0x0186;
    L_0x01e8:
        r2 = r8.d;
        r2.b(r0);
        r2 = r0.a();
        r8.a(r2);
        goto L_0x0186;
    L_0x01f5:
        r1 = r2.a();
        r1 = r3.b(r1);
    L_0x01fd:
        r0 = r0.a(r1);
        r1 = new org.spongycastle.crypto.tls.DigitallySigned;
        r1.<init>(r2, r0);
        r8.a(r1);
        r0 = 12;
        r8.s = r0;
    L_0x020d:
        r8.g();
        r8.h();
        r0 = 13;
        r8.s = r0;
        goto L_0x002f;
    L_0x0219:
        r2 = r8.a();
        r2 = org.spongycastle.crypto.tls.TlsProtocol.a(r2, r3, r1);
        r7 = r2;
        r2 = r1;
        r1 = r7;
        goto L_0x01fd;
    L_0x0225:
        r2 = r8.s;
        switch(r2) {
            case 2: goto L_0x0230;
            case 3: goto L_0x0233;
            case 4: goto L_0x023a;
            case 5: goto L_0x023a;
            default: goto L_0x022a;
        };
    L_0x022a:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x0230:
        r8.a(r1);
    L_0x0233:
        r2 = r8.d;
        r2.e();
        r8.e = r1;
    L_0x023a:
        r1 = r8.d;
        r1.a(r0);
        org.spongycastle.crypto.tls.TlsProtocol.d(r0);
        r0 = 6;
        r8.s = r0;
        goto L_0x002f;
    L_0x0247:
        r1 = r8.s;
        switch(r1) {
            case 4: goto L_0x0252;
            case 5: goto L_0x0252;
            case 6: goto L_0x0257;
            default: goto L_0x024c;
        };
    L_0x024c:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x0252:
        r1 = r8.d;
        r1.c();
    L_0x0257:
        r1 = r8.e;
        if (r1 != 0) goto L_0x0261;
    L_0x025b:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r5);
        throw r0;
    L_0x0261:
        r1 = r8.a();
        r1 = org.spongycastle.crypto.tls.CertificateRequest.a(r1, r0);
        r8.g = r1;
        org.spongycastle.crypto.tls.TlsProtocol.d(r0);
        r0 = r8.d;
        r1 = r8.g;
        r0.a(r1);
        r0 = r8.j;
        r0 = r0.g();
        r1 = r8.g;
        r1 = r1.b();
        org.spongycastle.crypto.tls.TlsUtils.a(r0, r1);
        r0 = 7;
        r8.s = r0;
        goto L_0x002f;
    L_0x0289:
        r1 = r8.s;
        switch(r1) {
            case 13: goto L_0x0294;
            default: goto L_0x028e;
        };
    L_0x028e:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x0294:
        r1 = r8.x;
        if (r1 != 0) goto L_0x029e;
    L_0x0298:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r3);
        throw r0;
    L_0x029e:
        r8.f();
        r8.a(r0);
        r1 = 14;
        r8.s = r1;
    L_0x02a8:
        org.spongycastle.crypto.tls.TlsProtocol.d(r0);
        r0 = r8.s;
        if (r0 != r4) goto L_0x002f;
    L_0x02af:
        r0 = r8.a();
        r0 = org.spongycastle.crypto.tls.TlsUtils.a(r0);
        if (r0 == 0) goto L_0x02bf;
    L_0x02b9:
        r0 = new org.spongycastle.crypto.tls.TlsFatalAlert;
        r0.<init>(r5);
        throw r0;
    L_0x02bf:
        r0 = "Renegotiation not supported";
        r1 = 100;
        r8.a(r1, r0);
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsClientProtocol.a(short, byte[]):void");
    }

    protected void a(Vector vector) {
        this.a.a(vector);
        this.s = (short) 3;
        this.d = this.a.d();
        this.d.a(a());
    }

    protected void a(ByteArrayInputStream byteArrayInputStream) {
        NewSessionTicket a = NewSessionTicket.a((InputStream) byteArrayInputStream);
        TlsProtocol.d(byteArrayInputStream);
        this.a.a(a);
    }

    protected void b(ByteArrayInputStream byteArrayInputStream) {
        boolean z = true;
        ProtocolVersion h = TlsUtils.h((InputStream) byteArrayInputStream);
        if (h.c()) {
            throw new TlsFatalAlert((short) 47);
        } else if (!h.equals(this.j.b())) {
            throw new TlsFatalAlert((short) 47);
        } else if (h.a(a().c())) {
            this.j.b(h);
            a().b(h);
            this.a.a(h);
            this.m.h = TlsUtils.b(32, (InputStream) byteArrayInputStream);
            this.c = TlsUtils.e((InputStream) byteArrayInputStream);
            if (this.c.length > 32) {
                throw new TlsFatalAlert((short) 47);
            }
            this.a.a(this.c);
            boolean z2 = this.c.length > 0 && this.k != null && Arrays.a(this.c, this.k.a());
            this.t = z2;
            int b = TlsUtils.b((InputStream) byteArrayInputStream);
            if (!Arrays.a(this.o, b) || b == 0 || b == 255) {
                throw new TlsFatalAlert((short) 47);
            }
            this.a.a(b);
            short a = TlsUtils.a((InputStream) byteArrayInputStream);
            if (Arrays.a(this.p, a)) {
                this.a.a(a);
                this.r = TlsProtocol.e(byteArrayInputStream);
                if (this.r != null) {
                    Enumeration keys = this.r.keys();
                    while (keys.hasMoreElements()) {
                        Integer num = (Integer) keys.nextElement();
                        if (!num.equals(h)) {
                            if (this.t) {
                            }
                            if (TlsUtils.a(this.q, num) == null) {
                                throw new TlsFatalAlert((short) 110);
                            }
                        }
                    }
                }
                byte[] a2 = TlsUtils.a(this.r, h);
                if (a2 != null) {
                    this.v = true;
                    if (!Arrays.b(a2, TlsProtocol.a(TlsUtils.a))) {
                        throw new TlsFatalAlert((short) 40);
                    }
                }
                this.a.a(this.v);
                Hashtable hashtable = this.q;
                Hashtable hashtable2 = this.r;
                if (this.t) {
                    if (b == this.l.b() && a == this.l.c()) {
                        hashtable = null;
                        hashtable2 = this.l.e();
                    } else {
                        throw new TlsFatalAlert((short) 47);
                    }
                }
                this.m.b = b;
                this.m.c = a;
                if (hashtable2 != null) {
                    this.m.i = a(hashtable, hashtable2, (short) 47);
                    this.m.j = TlsExtensionsUtils.c(hashtable2);
                    boolean z3 = !this.t && TlsUtils.a(hashtable2, TlsExtensionsUtils.d, (short) 47);
                    this.w = z3;
                    if (this.t || !TlsUtils.a(hashtable2, TlsProtocol.i, (short) 47)) {
                        z = false;
                    }
                    this.x = z;
                }
                if (hashtable != null) {
                    this.a.a(hashtable2);
                    return;
                }
                return;
            }
            throw new TlsFatalAlert((short) 47);
        } else {
            throw new TlsFatalAlert((short) 47);
        }
    }

    protected void a(DigitallySigned digitallySigned) {
        OutputStream handshakeMessage = new HandshakeMessage(this, (short) 15);
        digitallySigned.a(handshakeMessage);
        handshakeMessage.a();
    }

    protected void c() {
        OutputStream handshakeMessage = new HandshakeMessage(this, (short) 16);
        this.d.a(handshakeMessage);
        handshakeMessage.a();
    }
}
