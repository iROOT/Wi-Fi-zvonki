package org.spongycastle.crypto.tls;

import com.mavenir.android.vtow.activation.ActivationAdapter;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA384Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.engines.AESFastEngine;
import org.spongycastle.crypto.engines.CamelliaEngine;
import org.spongycastle.crypto.engines.DESedeEngine;
import org.spongycastle.crypto.engines.RC4Engine;
import org.spongycastle.crypto.engines.SEEDEngine;
import org.spongycastle.crypto.engines.Salsa20Engine;
import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.modes.CCMBlockCipher;
import org.spongycastle.crypto.modes.GCMBlockCipher;

public class DefaultTlsCipherFactory extends AbstractTlsCipherFactory {
    public TlsCipher a(TlsContext tlsContext, int i, int i2) {
        switch (i) {
            case 0:
                return b(tlsContext, i2);
            case 2:
                return f(tlsContext, 16, i2);
            case 7:
                return a(tlsContext, i2);
            case 8:
                return b(tlsContext, 16, i2);
            case 9:
                return b(tlsContext, 32, i2);
            case 10:
                return d(tlsContext, 16, 16);
            case 11:
                return d(tlsContext, 32, 16);
            case 12:
                return e(tlsContext, 16, i2);
            case 13:
                return e(tlsContext, 32, i2);
            case 14:
                return c(tlsContext, i2);
            case 15:
                return c(tlsContext, 16, 16);
            case 16:
                return c(tlsContext, 16, 8);
            case 17:
                return c(tlsContext, 32, 16);
            case 18:
                return c(tlsContext, 32, 8);
            case ActivationAdapter.OP_ACTIVATION /*100*/:
                return a(tlsContext, 12, 32, i2);
            case 101:
                return a(tlsContext, 20, 32, i2);
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }

    protected TlsBlockCipher b(TlsContext tlsContext, int i, int i2) {
        return new TlsBlockCipher(tlsContext, a(), a(), b(i2), b(i2), i);
    }

    protected TlsAEADCipher c(TlsContext tlsContext, int i, int i2) {
        return new TlsAEADCipher(tlsContext, b(), b(), i, i2);
    }

    protected TlsAEADCipher d(TlsContext tlsContext, int i, int i2) {
        return new TlsAEADCipher(tlsContext, c(), c(), i, i2);
    }

    protected TlsBlockCipher e(TlsContext tlsContext, int i, int i2) {
        return new TlsBlockCipher(tlsContext, d(), d(), b(i2), b(i2), i);
    }

    protected TlsBlockCipher a(TlsContext tlsContext, int i) {
        return new TlsBlockCipher(tlsContext, e(), e(), b(i), b(i), 24);
    }

    protected TlsNullCipher b(TlsContext tlsContext, int i) {
        return new TlsNullCipher(tlsContext, b(i), b(i));
    }

    protected TlsStreamCipher f(TlsContext tlsContext, int i, int i2) {
        return new TlsStreamCipher(tlsContext, f(), f(), b(i2), b(i2), i);
    }

    protected TlsStreamCipher a(TlsContext tlsContext, int i, int i2, int i3) {
        return new TlsStreamCipher(tlsContext, a(i), a(i), b(i3), b(i3), i2);
    }

    protected TlsBlockCipher c(TlsContext tlsContext, int i) {
        return new TlsBlockCipher(tlsContext, g(), g(), b(i), b(i), 16);
    }

    protected BlockCipher a() {
        return new CBCBlockCipher(new AESFastEngine());
    }

    protected AEADBlockCipher b() {
        return new CCMBlockCipher(new AESFastEngine());
    }

    protected AEADBlockCipher c() {
        return new GCMBlockCipher(new AESFastEngine());
    }

    protected BlockCipher d() {
        return new CBCBlockCipher(new CamelliaEngine());
    }

    protected BlockCipher e() {
        return new CBCBlockCipher(new DESedeEngine());
    }

    protected StreamCipher f() {
        return new RC4Engine();
    }

    protected StreamCipher a(int i) {
        return new Salsa20Engine(i);
    }

    protected BlockCipher g() {
        return new CBCBlockCipher(new SEEDEngine());
    }

    protected Digest b(int i) {
        switch (i) {
            case 0:
                return null;
            case 1:
                return new MD5Digest();
            case 2:
                return new SHA1Digest();
            case 3:
                return new SHA256Digest();
            case 4:
                return new SHA384Digest();
            case 5:
                return new SHA512Digest();
            default:
                throw new TlsFatalAlert((short) 80);
        }
    }
}
