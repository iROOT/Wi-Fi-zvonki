package org.spongycastle.crypto;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;

public interface KeyEncoder {
    byte[] a(AsymmetricKeyParameter asymmetricKeyParameter);
}
