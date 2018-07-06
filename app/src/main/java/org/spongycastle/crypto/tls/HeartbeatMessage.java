package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;

public class HeartbeatMessage {

    static class PayloadBuffer extends ByteArrayOutputStream {
        PayloadBuffer() {
        }
    }
}
