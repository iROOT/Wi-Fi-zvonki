package org.spongycastle.crypto.tls;

import com.fgmicrotec.mobile.android.fgmag.VoIP;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;

public class TlsPSKKeyExchange extends AbstractTlsKeyExchange {
    protected TlsPSKIdentity d;
    protected DHParameters e;
    protected byte[] f;
    protected DHPrivateKeyParameters g;
    protected DHPublicKeyParameters h;
    protected AsymmetricKeyParameter i;
    protected RSAKeyParameters j;
    protected TlsEncryptionCredentials k;
    protected byte[] l;

    public void e() {
        if (this.a == 15) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    public void a(TlsCredentials tlsCredentials) {
        if (tlsCredentials instanceof TlsEncryptionCredentials) {
            a(tlsCredentials.a());
            this.k = (TlsEncryptionCredentials) tlsCredentials;
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public byte[] b() {
        this.f = null;
        if (this.f == null && !a()) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (this.f == null) {
            TlsUtils.b(TlsUtils.a, byteArrayOutputStream);
        } else {
            TlsUtils.b(this.f, byteArrayOutputStream);
        }
        if (this.a == 14) {
            if (this.e == null) {
                throw new TlsFatalAlert((short) 80);
            }
            this.g = TlsDHUtils.b(this.c.a(), this.e, byteArrayOutputStream);
        } else if (this.a == 24) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    public void a(Certificate certificate) {
        if (this.a != 15) {
            throw new TlsFatalAlert((short) 10);
        } else if (certificate.c()) {
            throw new TlsFatalAlert((short) 42);
        } else {
            Certificate a = certificate.a(0);
            try {
                this.i = PublicKeyFactory.a(a.k());
                if (this.i.a()) {
                    throw new TlsFatalAlert((short) 80);
                }
                this.j = a((RSAKeyParameters) this.i);
                TlsUtils.a(a, 32);
                super.a(certificate);
            } catch (RuntimeException e) {
                throw new TlsFatalAlert((short) 43);
            }
        }
    }

    public boolean a() {
        switch (this.a) {
            case 14:
            case VoIP.ERR_SDP_NEGOTIATION_FAILED /*24*/:
                return true;
            default:
                return false;
        }
    }

    public void a(InputStream inputStream) {
        this.f = TlsUtils.f(inputStream);
        if (this.a == 14) {
            this.h = TlsDHUtils.a(ServerDHParams.a(inputStream).a());
        } else if (this.a != 24) {
        }
    }

    public void a(CertificateRequest certificateRequest) {
        throw new TlsFatalAlert((short) 10);
    }

    public void b(TlsCredentials tlsCredentials) {
        throw new TlsFatalAlert((short) 80);
    }

    public void a(OutputStream outputStream) {
        if (this.f == null) {
            this.d.a();
        } else {
            this.d.a(this.f);
        }
        TlsUtils.b(this.d.b(), outputStream);
        if (this.a == 14) {
            this.g = TlsDHUtils.a(this.c.a(), this.h.b(), outputStream);
        } else if (this.a == 24) {
            throw new TlsFatalAlert((short) 80);
        } else if (this.a == 15) {
            this.l = TlsRSAUtils.a(this.c, this.j, outputStream);
        }
    }

    public byte[] f() {
        byte[] c = this.d.c();
        byte[] a = a(c.length);
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream((a.length + 4) + c.length);
        TlsUtils.b(a, byteArrayOutputStream);
        TlsUtils.b(c, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    protected byte[] a(int i) {
        if (this.a == 14) {
            if (this.g != null) {
                return TlsDHUtils.a(this.h, this.g);
            }
            throw new TlsFatalAlert((short) 80);
        } else if (this.a == 24) {
            throw new TlsFatalAlert((short) 80);
        } else if (this.a == 15) {
            return this.l;
        } else {
            return new byte[i];
        }
    }

    protected RSAKeyParameters a(RSAKeyParameters rSAKeyParameters) {
        if (rSAKeyParameters.c().isProbablePrime(2)) {
            return rSAKeyParameters;
        }
        throw new TlsFatalAlert((short) 47);
    }
}
