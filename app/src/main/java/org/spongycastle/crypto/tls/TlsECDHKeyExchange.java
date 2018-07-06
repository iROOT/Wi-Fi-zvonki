package org.spongycastle.crypto.tls;

import android.support.v4.app.NotificationCompat;
import com.fgmicrotec.mobile.android.fgmag.VoIP;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;

public class TlsECDHKeyExchange extends AbstractTlsKeyExchange {
    protected TlsSigner e;
    protected int[] f;
    protected short[] g;
    protected short[] h;
    protected AsymmetricKeyParameter i;
    protected TlsAgreementCredentials j;
    protected ECPrivateKeyParameters k;
    protected ECPublicKeyParameters l;

    public TlsECDHKeyExchange(int i, Vector vector, int[] iArr, short[] sArr, short[] sArr2) {
        super(i, vector);
        switch (i) {
            case 16:
            case 18:
                this.e = null;
                break;
            case 17:
                this.e = new TlsECDSASigner();
                break;
            case 19:
                this.e = new TlsRSASigner();
                break;
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
        this.a = i;
        this.f = iArr;
        this.g = sArr;
        this.h = sArr2;
    }

    public void a(TlsContext tlsContext) {
        super.a(tlsContext);
        if (this.e != null) {
            this.e.a(tlsContext);
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
            if (this.e == null) {
                try {
                    this.l = TlsECCUtils.a((ECPublicKeyParameters) this.i);
                    TlsUtils.a(a, 8);
                } catch (ClassCastException e) {
                    throw new TlsFatalAlert((short) 46);
                }
            } else if (this.e.a(this.i)) {
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
            case 17:
            case 19:
            case VoIP.ERR_SIP_TIMED_OUT /*20*/:
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
                case NotificationCompat.FLAG_FOREGROUND_SERVICE /*64*/:
                case (short) 65:
                case (short) 66:
                    i++;
                default:
                    throw new TlsFatalAlert((short) 47);
            }
        }
    }

    public void b(TlsCredentials tlsCredentials) {
        if (tlsCredentials instanceof TlsAgreementCredentials) {
            this.j = (TlsAgreementCredentials) tlsCredentials;
        } else if (!(tlsCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public void a(OutputStream outputStream) {
        if (this.j == null) {
            this.k = TlsECCUtils.a(this.c.a(), this.h, this.l.b(), outputStream);
        }
    }

    public void b(Certificate certificate) {
    }

    public void b(InputStream inputStream) {
        if (this.l == null) {
            byte[] e = TlsUtils.e(inputStream);
            this.l = TlsECCUtils.a(TlsECCUtils.a(this.h, this.k.b(), e));
        }
    }

    public byte[] f() {
        if (this.j != null) {
            return this.j.a(this.l);
        }
        if (this.k != null) {
            return TlsECCUtils.a(this.l, this.k);
        }
        throw new TlsFatalAlert((short) 80);
    }
}
