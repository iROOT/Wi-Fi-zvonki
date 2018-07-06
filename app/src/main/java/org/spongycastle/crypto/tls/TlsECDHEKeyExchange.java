package org.spongycastle.crypto.tls;

import android.support.v4.app.NotificationCompat;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.io.TeeInputStream;

public class TlsECDHEKeyExchange extends TlsECDHKeyExchange {
    protected TlsSignerCredentials d = null;

    public TlsECDHEKeyExchange(int i, Vector vector, int[] iArr, short[] sArr, short[] sArr2) {
        super(i, vector, iArr, sArr, sArr2);
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
        int i;
        ECDomainParameters b;
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = null;
        if (this.f == null) {
            i = 23;
        } else {
            for (int i2 : this.f) {
                if (TlsECCUtils.d(i2)) {
                    break;
                }
            }
            i2 = -1;
        }
        if (i2 >= 0) {
            b = TlsECCUtils.b(i2);
        } else if (Arrays.a(this.f, 65281)) {
            b = TlsECCUtils.b(23);
        } else if (Arrays.a(this.f, 65282)) {
            b = TlsECCUtils.b(10);
        } else {
            b = null;
        }
        if (b == null) {
            throw new TlsFatalAlert((short) 80);
        }
        Digest b2;
        AsymmetricCipherKeyPair a = TlsECCUtils.a(this.c.a(), b);
        this.k = (ECPrivateKeyParameters) a.b();
        OutputStream digestInputBuffer = new DigestInputBuffer();
        if (i2 < 0) {
            TlsECCUtils.a(this.g, b, digestInputBuffer);
        } else {
            TlsECCUtils.b(i2, digestInputBuffer);
        }
        TlsECCUtils.a(this.g, ((ECPublicKeyParameters) a.a()).c(), digestInputBuffer);
        if (TlsUtils.c(this.c)) {
            signatureAndHashAlgorithm = this.d.b_();
            if (signatureAndHashAlgorithm == null) {
                throw new TlsFatalAlert((short) 80);
            }
            b2 = TlsUtils.b(signatureAndHashAlgorithm.a());
        } else {
            b2 = new CombinedHash();
        }
        SecurityParameters b3 = this.c.b();
        b2.a(b3.g, 0, b3.g.length);
        b2.a(b3.h, 0, b3.h.length);
        digestInputBuffer.a(b2);
        byte[] bArr = new byte[b2.b()];
        b2.a(bArr, 0);
        new DigitallySigned(signatureAndHashAlgorithm, this.d.a(bArr)).a(digestInputBuffer);
        return digestInputBuffer.toByteArray();
    }

    public void a(InputStream inputStream) {
        SecurityParameters b = this.c.b();
        OutputStream signerInputBuffer = new SignerInputBuffer();
        InputStream teeInputStream = new TeeInputStream(inputStream, signerInputBuffer);
        ECDomainParameters a = TlsECCUtils.a(this.f, this.g, teeInputStream);
        byte[] e = TlsUtils.e(teeInputStream);
        DigitallySigned a2 = DigitallySigned.a(this.c, inputStream);
        Signer a3 = a(this.e, a2.a(), b);
        signerInputBuffer.a(a3);
        if (a3.a(a2.b())) {
            this.l = TlsECCUtils.a(TlsECCUtils.a(this.g, a, e));
            return;
        }
        throw new TlsFatalAlert((short) 51);
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

    protected Signer a(TlsSigner tlsSigner, SignatureAndHashAlgorithm signatureAndHashAlgorithm, SecurityParameters securityParameters) {
        Signer a = tlsSigner.a(signatureAndHashAlgorithm, this.i);
        a.a(securityParameters.g, 0, securityParameters.g.length);
        a.a(securityParameters.h, 0, securityParameters.h.length);
        return a;
    }
}
