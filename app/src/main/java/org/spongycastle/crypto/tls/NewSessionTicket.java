package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;

public class NewSessionTicket {
    protected long a;
    protected byte[] b;

    public NewSessionTicket(long j, byte[] bArr) {
        this.a = j;
        this.b = bArr;
    }

    public void a(OutputStream outputStream) {
        TlsUtils.a(this.a, outputStream);
        TlsUtils.b(this.b, outputStream);
    }

    public static NewSessionTicket a(InputStream inputStream) {
        return new NewSessionTicket(TlsUtils.d(inputStream), TlsUtils.f(inputStream));
    }
}
