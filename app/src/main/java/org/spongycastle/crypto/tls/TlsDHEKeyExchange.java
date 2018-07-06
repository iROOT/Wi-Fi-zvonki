package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.util.io.TeeInputStream;

public class TlsDHEKeyExchange extends TlsDHKeyExchange {
    protected TlsSignerCredentials d = null;

    public TlsDHEKeyExchange(int i, Vector vector, DHParameters dHParameters) {
        super(i, vector, dHParameters);
    }

    public void a(TlsCredentials tlsCredentials) {
        if (tlsCredentials instanceof TlsSignerCredentials) {
            a(tlsCredentials.a());
            this.d = (TlsSignerCredentials) tlsCredentials;
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public byte[] b() {
        if (this.h == null) {
            throw new TlsFatalAlert((short) 80);
        }
        SignatureAndHashAlgorithm b_;
        Digest b;
        OutputStream digestInputBuffer = new DigestInputBuffer();
        this.m = TlsDHUtils.b(this.c.a(), this.h, digestInputBuffer);
        if (TlsUtils.c(this.c)) {
            b_ = this.d.b_();
            if (b_ == null) {
                throw new TlsFatalAlert((short) 80);
            }
            b = TlsUtils.b(b_.a());
        } else {
            b_ = null;
            b = new CombinedHash();
        }
        SecurityParameters b2 = this.c.b();
        b.a(b2.g, 0, b2.g.length);
        b.a(b2.h, 0, b2.h.length);
        digestInputBuffer.a(b);
        byte[] bArr = new byte[b.b()];
        b.a(bArr, 0);
        new DigitallySigned(b_, this.d.a(bArr)).a(digestInputBuffer);
        return digestInputBuffer.toByteArray();
    }

    public void a(InputStream inputStream) {
        SecurityParameters b = this.c.b();
        OutputStream signerInputBuffer = new SignerInputBuffer();
        ServerDHParams a = ServerDHParams.a(new TeeInputStream(inputStream, signerInputBuffer));
        DigitallySigned a2 = DigitallySigned.a(this.c, inputStream);
        Signer a3 = a(this.g, a2.a(), b);
        signerInputBuffer.a(a3);
        if (a3.a(a2.b())) {
            this.j = TlsDHUtils.a(a.a());
            return;
        }
        throw new TlsFatalAlert((short) 51);
    }

    protected Signer a(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer a = tlsSigner.a(signatureAndHashAlgorithm, this.i);
        a.a(securityParameters.g, 0, securityParameters.g.length);
        a.a(securityParameters.h, 0, securityParameters.h.length);
        return a;
    }
}
