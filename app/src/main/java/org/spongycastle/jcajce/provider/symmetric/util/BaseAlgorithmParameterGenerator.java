package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameterGeneratorSpi;
import java.security.SecureRandom;
import net.hockeyapp.android.k;

public abstract class BaseAlgorithmParameterGenerator extends AlgorithmParameterGeneratorSpi {
    protected SecureRandom b;
    protected int c = k.FEEDBACK_FAILED_TITLE_ID;

    protected void engineInit(int i, SecureRandom secureRandom) {
        this.c = i;
        this.b = secureRandom;
    }
}
