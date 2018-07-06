package org.spongycastle.jce.provider;

import java.security.Permission;
import javax.crypto.spec.DHParameterSpec;
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jcajce.provider.config.ProviderConfigurationPermission;
import org.spongycastle.jce.spec.ECParameterSpec;

class BouncyCastleProviderConfiguration implements ProviderConfiguration {
    private static Permission a = new ProviderConfigurationPermission("SC", "threadLocalEcImplicitlyCa");
    private static Permission b = new ProviderConfigurationPermission("SC", "ecImplicitlyCa");
    private static Permission c = new ProviderConfigurationPermission("SC", "threadLocalDhDefaultParams");
    private static Permission d = new ProviderConfigurationPermission("SC", "DhDefaultParams");
    private ThreadLocal e = new ThreadLocal();
    private ThreadLocal f = new ThreadLocal();
    private volatile ECParameterSpec g;
    private volatile Object h;

    BouncyCastleProviderConfiguration() {
    }

    public ECParameterSpec a() {
        ECParameterSpec eCParameterSpec = (ECParameterSpec) this.e.get();
        return eCParameterSpec != null ? eCParameterSpec : this.g;
    }

    public DHParameterSpec a(int i) {
        Object obj = this.f.get();
        if (obj == null) {
            obj = this.h;
        }
        if (obj instanceof DHParameterSpec) {
            DHParameterSpec dHParameterSpec = (DHParameterSpec) obj;
            if (dHParameterSpec.getP().bitLength() == i) {
                return dHParameterSpec;
            }
        } else if (obj instanceof DHParameterSpec[]) {
            DHParameterSpec[] dHParameterSpecArr = (DHParameterSpec[]) obj;
            for (int i2 = 0; i2 != dHParameterSpecArr.length; i2++) {
                if (dHParameterSpecArr[i2].getP().bitLength() == i) {
                    return dHParameterSpecArr[i2];
                }
            }
        }
        return null;
    }
}
