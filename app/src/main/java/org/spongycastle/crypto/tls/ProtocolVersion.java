package org.spongycastle.crypto.tls;

import net.hockeyapp.android.k;

public final class ProtocolVersion {
    public static final ProtocolVersion a = new ProtocolVersion(k.EXPIRY_INFO_TITLE_ID, "SSL 3.0");
    public static final ProtocolVersion b = new ProtocolVersion(k.EXPIRY_INFO_TEXT_ID, "TLS 1.0");
    public static final ProtocolVersion c = new ProtocolVersion(770, "TLS 1.1");
    public static final ProtocolVersion d = new ProtocolVersion(771, "TLS 1.2");
    public static final ProtocolVersion e = new ProtocolVersion(65279, "DTLS 1.0");
    public static final ProtocolVersion f = new ProtocolVersion(65277, "DTLS 1.2");
    private int g;
    private String h;

    private ProtocolVersion(int i, String str) {
        this.g = 65535 & i;
        this.h = str;
    }

    public int a() {
        return this.g >> 8;
    }

    public int b() {
        return this.g & 255;
    }

    public boolean c() {
        return a() == 254;
    }

    public boolean d() {
        return this == a;
    }

    public ProtocolVersion e() {
        if (!c()) {
            return this;
        }
        if (this == e) {
            return c;
        }
        return d;
    }

    public boolean a(ProtocolVersion protocolVersion) {
        boolean z = true;
        if (a() != protocolVersion.a()) {
            return false;
        }
        int b = protocolVersion.b() - b();
        if (c()) {
            if (b > 0) {
                z = false;
            }
        } else if (b < 0) {
            z = false;
        }
        return z;
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public int hashCode() {
        return this.g;
    }

    public static ProtocolVersion a(int i, int i2) {
        switch (i) {
            case 3:
                switch (i2) {
                    case 0:
                        return a;
                    case 1:
                        return b;
                    case 2:
                        return c;
                    case 3:
                        return d;
                }
                break;
            case 254:
                break;
        }
        switch (i2) {
            case 253:
                return f;
            case 255:
                return e;
            default:
                break;
        }
        throw new TlsFatalAlert((short) 47);
    }

    public String toString() {
        return this.h;
    }
}
