package org.spongycastle.jce.interfaces;

import java.security.PublicKey;

public interface MQVPublicKey extends PublicKey {
    PublicKey a();

    PublicKey b();
}
