package org.spongycastle.jce.interfaces;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface MQVPrivateKey extends PrivateKey {
    PrivateKey a();

    PrivateKey b();

    PublicKey c();
}
