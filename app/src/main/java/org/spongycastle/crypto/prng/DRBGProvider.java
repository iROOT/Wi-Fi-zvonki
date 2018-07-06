package org.spongycastle.crypto.prng;

import org.spongycastle.crypto.prng.drbg.SP80090DRBG;

interface DRBGProvider {
    SP80090DRBG a(EntropySource entropySource);
}
