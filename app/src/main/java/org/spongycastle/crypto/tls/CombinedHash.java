package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.Digest;

class CombinedHash implements TlsHandshakeHash {
    protected TlsContext a;
    protected Digest b;
    protected Digest c;

    CombinedHash() {
        this.b = TlsUtils.b((short) 1);
        this.c = TlsUtils.b((short) 2);
    }

    CombinedHash(CombinedHash combinedHash) {
        this.a = combinedHash.a;
        this.b = TlsUtils.a((short) 1, combinedHash.b);
        this.c = TlsUtils.a((short) 2, combinedHash.c);
    }

    public void a(TlsContext tlsContext) {
        this.a = tlsContext;
    }

    public TlsHandshakeHash d() {
        return this;
    }

    public void a(short s) {
        throw new IllegalStateException("CombinedHash only supports calculating the legacy PRF for handshake hash");
    }

    public void e() {
    }

    public TlsHandshakeHash f() {
        return new CombinedHash(this);
    }

    public Digest g() {
        return new CombinedHash(this);
    }

    public byte[] b(short s) {
        throw new IllegalStateException("CombinedHash doesn't support multiple hashes");
    }

    public String a() {
        return this.b.a() + " and " + this.c.a();
    }

    public int b() {
        return this.b.b() + this.c.b();
    }

    public void a(byte b) {
        this.b.a(b);
        this.c.a(b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.b.a(bArr, i, i2);
        this.c.a(bArr, i, i2);
    }

    public int a(byte[] bArr, int i) {
        if (this.a != null && TlsUtils.a(this.a)) {
            a(this.b, SSL3Mac.a, SSL3Mac.b, 48);
            a(this.c, SSL3Mac.a, SSL3Mac.b, 40);
        }
        int a = this.b.a(bArr, i);
        return a + this.c.a(bArr, i + a);
    }

    public void c() {
        this.b.c();
        this.c.c();
    }

    protected void a(Digest digest, byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = this.a.b().f;
        digest.a(bArr3, 0, bArr3.length);
        digest.a(bArr, 0, i);
        byte[] bArr4 = new byte[digest.b()];
        digest.a(bArr4, 0);
        digest.a(bArr3, 0, bArr3.length);
        digest.a(bArr2, 0, i);
        digest.a(bArr4, 0, bArr4.length);
    }
}
