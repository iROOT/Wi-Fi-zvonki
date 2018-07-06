package org.spongycastle.crypto.tls;

import java.util.Hashtable;
import org.spongycastle.crypto.tls.SessionParameters.Builder;

public class DTLSClientProtocol extends DTLSProtocol {

    protected static class ClientHandshakeState {
        TlsClient a = null;
        TlsClientContextImpl b = null;
        TlsSession c = null;
        SessionParameters d = null;
        Builder e = null;
        int[] f = null;
        short[] g = null;
        Hashtable h = null;
        byte[] i = null;
        int j = -1;
        short k = (short) -1;
        boolean l = false;
        short m = (short) -1;
        boolean n = false;
        boolean o = false;
        TlsKeyExchange p = null;
        TlsAuthentication q = null;
        CertificateStatus r = null;
        CertificateRequest s = null;
        TlsCredentials t = null;

        protected ClientHandshakeState() {
        }
    }
}
