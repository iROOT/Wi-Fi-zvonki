package org.spongycastle.crypto.tls;

import android.support.v4.app.NotificationCompat;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.io.Streams;

public class TlsRSAKeyExchange extends AbstractTlsKeyExchange {
    protected AsymmetricKeyParameter d = null;
    protected RSAKeyParameters e = null;
    protected TlsEncryptionCredentials f = null;
    protected byte[] g;

    public TlsRSAKeyExchange(Vector vector) {
        super(1, vector);
    }

    public void e() {
        throw new TlsFatalAlert((short) 10);
    }

    public void a(TlsCredentials tlsCredentials) {
        if (tlsCredentials instanceof TlsEncryptionCredentials) {
            a(tlsCredentials.a());
            this.f = (TlsEncryptionCredentials) tlsCredentials;
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public void a(Certificate certificate) {
        if (certificate.c()) {
            throw new TlsFatalAlert((short) 42);
        }
        Certificate a = certificate.a(0);
        try {
            this.d = PublicKeyFactory.a(a.k());
            if (this.d.a()) {
                throw new TlsFatalAlert((short) 80);
            }
            this.e = a((RSAKeyParameters) this.d);
            TlsUtils.a(a, 32);
            super.a(certificate);
        } catch (RuntimeException e) {
            throw new TlsFatalAlert((short) 43);
        }
    }

    public void a(CertificateRequest certificateRequest) {
        short[] a = certificateRequest.a();
        int i = 0;
        while (i < a.length) {
            switch (a[i]) {
                case (short) 1:
                case (short) 2:
                case NotificationCompat.FLAG_FOREGROUND_SERVICE /*64*/:
                    i++;
                default:
                    throw new TlsFatalAlert((short) 47);
            }
        }
    }

    public void b(TlsCredentials tlsCredentials) {
        if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public void a(OutputStream outputStream) {
        this.g = TlsRSAUtils.a(this.c, this.e, outputStream);
    }

    public void b(InputStream inputStream) {
        byte[] a;
        if (TlsUtils.a(this.c)) {
            a = Streams.a(inputStream);
        } else {
            a = TlsUtils.f(inputStream);
        }
        this.g = TlsRSAUtils.a(this.c, this.f, a);
    }

    public byte[] f() {
        if (this.g == null) {
            throw new TlsFatalAlert((short) 80);
        }
        byte[] bArr = this.g;
        this.g = null;
        return bArr;
    }

    protected RSAKeyParameters a(RSAKeyParameters rSAKeyParameters) {
        if (rSAKeyParameters.c().isProbablePrime(2)) {
            return rSAKeyParameters;
        }
        throw new TlsFatalAlert((short) 47);
    }
}
