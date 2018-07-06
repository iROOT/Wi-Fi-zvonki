package org.spongycastle.crypto.tls;

import android.support.v4.app.NotificationCompat;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.agreement.srp.SRP6Client;
import org.spongycastle.crypto.agreement.srp.SRP6Util;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.io.TeeInputStream;

public class TlsSRPKeyExchange extends AbstractTlsKeyExchange {
    protected TlsSigner d;
    protected byte[] e;
    protected byte[] f;
    protected AsymmetricKeyParameter g;
    protected byte[] h;
    protected BigInteger i;
    protected SRP6Client j;

    public void a(TlsContext tlsContext) {
        super.a(tlsContext);
        if (this.d != null) {
            this.d.a(tlsContext);
        }
    }

    public void e() {
        if (this.d != null) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    public void a(Certificate certificate) {
        if (this.d == null) {
            throw new TlsFatalAlert((short) 10);
        } else if (certificate.c()) {
            throw new TlsFatalAlert((short) 42);
        } else {
            Certificate a = certificate.a(0);
            try {
                this.g = PublicKeyFactory.a(a.k());
                if (this.d.a(this.g)) {
                    TlsUtils.a(a, (int) NotificationCompat.FLAG_HIGH_PRIORITY);
                    super.a(certificate);
                    return;
                }
                throw new TlsFatalAlert((short) 46);
            } catch (RuntimeException e) {
                throw new TlsFatalAlert((short) 43);
            }
        }
    }

    public boolean a() {
        return true;
    }

    public void a(InputStream inputStream) {
        SignerInputBuffer signerInputBuffer;
        InputStream teeInputStream;
        SecurityParameters b = this.c.b();
        if (this.d != null) {
            signerInputBuffer = new SignerInputBuffer();
            teeInputStream = new TeeInputStream(inputStream, signerInputBuffer);
        } else {
            signerInputBuffer = null;
            teeInputStream = inputStream;
        }
        byte[] f = TlsUtils.f(teeInputStream);
        byte[] f2 = TlsUtils.f(teeInputStream);
        byte[] e = TlsUtils.e(teeInputStream);
        byte[] f3 = TlsUtils.f(teeInputStream);
        if (signerInputBuffer != null) {
            DigitallySigned a = DigitallySigned.a(this.c, inputStream);
            Signer a2 = a(this.d, a.a(), b);
            signerInputBuffer.a(a2);
            if (!a2.a(a.b())) {
                throw new TlsFatalAlert((short) 51);
            }
        }
        BigInteger bigInteger = new BigInteger(1, f);
        BigInteger bigInteger2 = new BigInteger(1, f2);
        this.h = e;
        try {
            this.i = SRP6Util.a(bigInteger, new BigInteger(1, f3));
            this.j.a(bigInteger, bigInteger2, new SHA1Digest(), this.c.a());
        } catch (CryptoException e2) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    public void a(CertificateRequest certificateRequest) {
        throw new TlsFatalAlert((short) 10);
    }

    public void b(TlsCredentials tlsCredentials) {
        throw new TlsFatalAlert((short) 80);
    }

    public void a(OutputStream outputStream) {
        TlsUtils.b(BigIntegers.a(this.j.a(this.h, this.e, this.f)), outputStream);
    }

    public byte[] f() {
        try {
            return BigIntegers.a(this.j.a(this.i));
        } catch (CryptoException e) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    protected Signer a(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer a = tlsSigner.a(signatureAndHashAlgorithm, this.g);
        a.a(securityParameters.g, 0, securityParameters.g.length);
        a.a(securityParameters.h, 0, securityParameters.h.length);
        return a;
    }
}
