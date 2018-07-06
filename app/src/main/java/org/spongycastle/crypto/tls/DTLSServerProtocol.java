package org.spongycastle.crypto.tls;

import java.util.Hashtable;

public class DTLSServerProtocol extends DTLSProtocol {

    protected static class ServerHandshakeState {
        TlsServer a = null;
        TlsServerContextImpl b = null;
        int c = -1;
        short d = (short) -1;
        boolean e = false;
        short f = (short) -1;
        boolean g = false;
        boolean h = false;
        Hashtable i = null;
        TlsKeyExchange j = null;
        TlsCredentials k = null;
        CertificateRequest l = null;
        short m = (short) -1;
        Certificate n = null;

        protected ServerHandshakeState() {
        }
    }
}
