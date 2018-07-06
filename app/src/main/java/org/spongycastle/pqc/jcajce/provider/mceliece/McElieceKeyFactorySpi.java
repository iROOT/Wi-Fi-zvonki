package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;

public class McElieceKeyFactorySpi extends KeyFactorySpi {
    protected PublicKey engineGeneratePublic(KeySpec keySpec) {
        return null;
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec) {
        return null;
    }

    protected KeySpec engineGetKeySpec(Key key, Class cls) {
        return null;
    }

    protected Key engineTranslateKey(Key key) {
        return null;
    }
}
