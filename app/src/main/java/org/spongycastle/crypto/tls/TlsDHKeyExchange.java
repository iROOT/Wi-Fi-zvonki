package org.spongycastle.crypto.tls;

import android.support.v4.app.NotificationCompat;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;

public class TlsDHKeyExchange extends AbstractTlsKeyExchange {
    protected static final BigInteger e = BigInteger.valueOf(1);
    protected static final BigInteger f = BigInteger.valueOf(2);
    protected TlsSigner g;
    protected DHParameters h;
    protected AsymmetricKeyParameter i;
    protected DHPublicKeyParameters j;
    protected TlsAgreementCredentials k;
    protected DHPrivateKeyParameters l;
    protected DHPrivateKeyParameters m;
    protected DHPublicKeyParameters n;

    public TlsDHKeyExchange(int i, Vector vector, DHParameters dHParameters) {
        super(i, vector);
        switch (i) {
            case 3:
                this.g = new TlsDSSSigner();
                break;
            case 5:
                this.g = new TlsRSASigner();
                break;
            case 7:
            case 9:
                this.g = null;
                break;
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
        this.h = dHParameters;
    }

    public void a(TlsContext tlsContext) {
        super.a(tlsContext);
        if (this.g != null) {
            this.g.a(tlsContext);
        }
    }

    public void e() {
        throw new TlsFatalAlert((short) 10);
    }

    public void a(Certificate certificate) {
        if (certificate.c()) {
            throw new TlsFatalAlert((short) 42);
        }
        Certificate a = certificate.a(0);
        try {
            this.i = PublicKeyFactory.a(a.k());
            if (this.g == null) {
                try {
                    this.j = TlsDHUtils.a((DHPublicKeyParameters) this.i);
                    TlsUtils.a(a, 8);
                } catch (ClassCastException e) {
                    throw new TlsFatalAlert((short) 46);
                }
            } else if (this.g.a(this.i)) {
                TlsUtils.a(a, (int) NotificationCompat.FLAG_HIGH_PRIORITY);
            } else {
                throw new TlsFatalAlert((short) 46);
            }
            super.a(certificate);
        } catch (RuntimeException e2) {
            throw new TlsFatalAlert((short) 43);
        }
    }

    public boolean a() {
        switch (this.a) {
            case 3:
            case 5:
            case 11:
                return true;
            default:
                return false;
        }
    }

    public void a(CertificateRequest certificateRequest) {
        short[] a = certificateRequest.a();
        int i = 0;
        while (i < a.length) {
            switch (a[i]) {
                case (short) 1:
                case (short) 2:
                case (short) 3:
                case (short) 4:
                case NotificationCompat.FLAG_FOREGROUND_SERVICE /*64*/:
                    i++;
                default:
                    throw new TlsFatalAlert((short) 47);
            }
        }
    }

    public void b(TlsCredentials tlsCredentials) {
        if (tlsCredentials instanceof TlsAgreementCredentials) {
            this.k = (TlsAgreementCredentials) tlsCredentials;
        } else if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public void a(OutputStream outputStream) {
        if (this.k == null) {
            this.l = TlsDHUtils.a(this.c.a(), this.j.b(), outputStream);
        }
    }

    public byte[] f() {
        if (this.k != null) {
            return this.k.a(this.j);
        }
        if (this.m != null) {
            return TlsDHUtils.a(this.n, this.m);
        }
        if (this.l != null) {
            return TlsDHUtils.a(this.j, this.l);
        }
        throw new TlsFatalAlert((short) 80);
    }
}
