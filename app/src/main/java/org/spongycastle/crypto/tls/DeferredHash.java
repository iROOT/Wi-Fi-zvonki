package org.spongycastle.crypto.tls;

import java.util.Enumeration;
import java.util.Hashtable;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Shorts;

class DeferredHash implements TlsHandshakeHash {
    protected TlsContext a;
    private DigestInputBuffer b;
    private Hashtable c;
    private Short d;

    DeferredHash() {
        this.b = new DigestInputBuffer();
        this.c = new Hashtable();
        this.d = null;
    }

    private DeferredHash(Short sh, Digest digest) {
        this.b = null;
        this.c = new Hashtable();
        this.d = sh;
        this.c.put(sh, digest);
    }

    public void a(TlsContext tlsContext) {
        this.a = tlsContext;
    }

    public TlsHandshakeHash d() {
        int b = this.a.b().b();
        if (b == 0) {
            Object combinedHash = new CombinedHash();
            combinedHash.a(this.a);
            this.b.a(combinedHash);
            return combinedHash.d();
        }
        this.d = Shorts.a(TlsUtils.h(b));
        a(this.d);
        return this;
    }

    public void a(short s) {
        if (this.b == null) {
            throw new IllegalStateException("Too late to track more hash algorithms");
        }
        a(Shorts.a(s));
    }

    public void e() {
        h();
    }

    public TlsHandshakeHash f() {
        Digest a = TlsUtils.a(this.d.shortValue(), (Digest) this.c.get(this.d));
        if (this.b != null) {
            this.b.a(a);
        }
        TlsHandshakeHash deferredHash = new DeferredHash(this.d, a);
        deferredHash.a(this.a);
        return deferredHash;
    }

    public Digest g() {
        h();
        if (this.b == null) {
            return TlsUtils.a(this.d.shortValue(), (Digest) this.c.get(this.d));
        }
        Digest b = TlsUtils.b(this.d.shortValue());
        this.b.a(b);
        return b;
    }

    public byte[] b(short s) {
        Digest digest = (Digest) this.c.get(Shorts.a(s));
        if (digest == null) {
            throw new IllegalStateException("HashAlgorithm " + s + " is not being tracked");
        }
        digest = TlsUtils.a(s, digest);
        if (this.b != null) {
            this.b.a(digest);
        }
        byte[] bArr = new byte[digest.b()];
        digest.a(bArr, 0);
        return bArr;
    }

    public String a() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public int b() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public void a(byte b) {
        if (this.b != null) {
            this.b.write(b);
            return;
        }
        Enumeration elements = this.c.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).a(b);
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        if (this.b != null) {
            this.b.write(bArr, i, i2);
            return;
        }
        Enumeration elements = this.c.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).a(bArr, i, i2);
        }
    }

    public int a(byte[] bArr, int i) {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public void c() {
        if (this.b != null) {
            this.b.reset();
            return;
        }
        Enumeration elements = this.c.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).c();
        }
    }

    protected void h() {
        if (this.b != null && this.c.size() <= 4) {
            Enumeration elements = this.c.elements();
            while (elements.hasMoreElements()) {
                this.b.a((Digest) elements.nextElement());
            }
            this.b = null;
        }
    }

    protected void a(Short sh) {
        if (!this.c.containsKey(sh)) {
            this.c.put(sh, TlsUtils.b(sh.shortValue()));
        }
    }
}
