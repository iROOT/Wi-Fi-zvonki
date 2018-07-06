package org.spongycastle.jcajce.provider.config;

import javax.crypto.spec.DHParameterSpec;
import org.spongycastle.jce.spec.ECParameterSpec;

public interface ProviderConfiguration {
    DHParameterSpec a(int i);

    ECParameterSpec a();
}
