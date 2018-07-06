package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;

public class ServerDHParams {
    protected DHPublicKeyParameters a;

    public ServerDHParams(DHPublicKeyParameters dHPublicKeyParameters) {
        if (dHPublicKeyParameters == null) {
            throw new IllegalArgumentException("'publicKey' cannot be null");
        }
        this.a = dHPublicKeyParameters;
    }

    public DHPublicKeyParameters a() {
        return this.a;
    }

    public void a(OutputStream outputStream) {
        DHParameters b = this.a.b();
        BigInteger c = this.a.c();
        TlsDHUtils.a(b.a(), outputStream);
        TlsDHUtils.a(b.b(), outputStream);
        TlsDHUtils.a(c, outputStream);
    }

    public static ServerDHParams a(InputStream inputStream) {
        return new ServerDHParams(new DHPublicKeyParameters(TlsDHUtils.a(inputStream), new DHParameters(TlsDHUtils.a(inputStream), TlsDHUtils.a(inputStream))));
    }
}
